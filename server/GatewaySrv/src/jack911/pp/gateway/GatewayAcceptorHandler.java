package jack911.pp.gateway;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import jack911.pp.message.content.LoginMsg;
import jack911.pp.server.AcceptorHandler;
import jack911.pp.server.ServerId;

public class GatewayAcceptorHandler extends AcceptorHandler
{
	private static Logger logger = Logger.getLogger(GatewayAcceptorHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx)
	{
		ChannelId cid = ctx.channel().id();
		String shortId = cid.asShortText();
		String longId = cid.asLongText();
		logger.debug("channelActive  shortId=" + shortId + "  longId=" + longId);
		
		ChannelService.setContext(ctx);
		Long cccid = ChannelService.getCCCID(ctx);
		if(cccid != null)
		{
			logger.debug("开始会话的cccid=" + cccid);
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx)
	{
		ChannelId cid = ctx.channel().id();
		String shortId = cid.asShortText();
		String longId = cid.asLongText();
		logger.debug("channelInactive  shortId=" + shortId + "  longId=" + longId);
		
		Long cccid = ChannelService.getCCCID(ctx);
		if(cccid != null)
		{
			logger.debug("结束会话的cccid=" + cccid);
			LoginMsg.sendPlayerLogoutNoticeCmd(cccid, ServerId.CENTRE);
			LoginMsg.sendPlayerLogoutNoticeCmd(cccid, ServerId.GAME);
			LoginMsg.sendPlayerLogoutNoticeCmd(cccid, ServerId.WORLD);
			LoginMsg.sendPlayerLogoutNoticeCmd(cccid, ServerId.DB);//消息我发了，至于其他服务器处不处理，由各自服务器决断
		}
		ChannelService.removeContext(ctx);
	}
}
