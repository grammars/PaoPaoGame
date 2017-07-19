package jack911.pp.message;

import io.netty.channel.ChannelHandlerContext;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;
import jack911.pp.server.ServerManager;

public class MsgUtil
{	
	private static ChannelHandlerContext gatewayCtx;
	private static ChannelHandlerContext getGatewayCtx()
	{
		if(gatewayCtx==null) { gatewayCtx = ServerManager.getCtx(ServerId.GATEWAY); }
		return gatewayCtx;
	}
	
	/** 创建消息(Server to Server)服务器之间
	 * @param toServerId 发送到哪个服务器 */
	public static MsgUnit createMsgToServer(short majorId, short minorId, byte toServerId)
	{
		MsgUnit msg = new MsgUnit(ServerManager.getCtx(toServerId));
		MsgHead head = new MsgHead(ServerId.UNKNOWN, toServerId, majorId, minorId, null);
		head.writeTo(msg);
		return msg;
	}
	
	/** 创建消息(Server to Server)服务器之间
	 * @param wayCtx 发送通道 */
	public static MsgUnit createMsgToServer(short majorId, short minorId, ChannelHandlerContext wayCtx)
	{
		MsgUnit msg = new MsgUnit(wayCtx);
		Byte target = ServerManager.getSid(wayCtx);
		MsgHead head = new MsgHead(ServerId.UNKNOWN, target, majorId, minorId, null);
		head.writeTo(msg);
		return msg;
	}
	
	/** 创建消息(Server to Client)服务器到客户端 */
	public static MsgUnit createMsgToClient(short majorId, short minorId, Long cccid)
	{
		MsgUnit msg = new MsgUnit(getGatewayCtx());
		MsgHead head = new MsgHead(ServerId.UNKNOWN, ServerId.CLIENT, majorId, minorId, cccid);
		head.writeTo(msg);
		return msg;
	}
	
}
