package jack911.pp.gateway.msg;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import jack911.pp.gateway.ChannelService;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.ServerMsg;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;
import jack911.pp.server.ServerManager;

public class GatewayMsgDistributor implements MsgDistributor
{
	private static Logger logger = Logger.getLogger(GatewayMsgDistributor.class);
	
	@Override
	public void handle(MsgUnit msg)
	{
		MsgHead head = new MsgHead().readFrom(msg);
		if(head.target == ServerId.GATEWAY) //就是给网关服务器的
		{
			switch(head.majorId)
			{
			case SERVER_MSG:
				ServerMsg.handle(head, msg);
				break;
			}
		}
		else if(head.source == ServerId.CLIENT) //来自客户端的消息
		{
			switch(head.target)
			{
			case ServerId.CENTRE:
			//case ServerId.DB://client-->gateway不会需要与DB直接通讯
			case ServerId.GAME:
			case ServerId.WORLD:
				MsgHead.setCCCID(msg, ChannelService.getCCCID(msg.ctx));
				msg.content.readerIndex(0);
				msg.ctx = ServerManager.getCtx(head.target);
				msg.send();
				break;
			}
		}
		else if(head.target == ServerId.CLIENT) //需要转发给客户端的消息
		{
			logger.debug("发送消息到对应客户端");
			ChannelHandlerContext clientCtx = ChannelService.getContext(head.cccid);
			if(clientCtx != null)
			{
				msg.ctx = clientCtx;
				msg.content.readerIndex(0);
				msg.send();
			}
			else
			{
				logger.error("cccid="+head.cccid+"的ChannelHandlerContext为空!");
			}
		}
	}

}
