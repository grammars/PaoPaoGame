package jack911.pp.message.content;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import jack911.netty.BBT;
import jack911.pp.message.MsgDef;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerManager;
import jack911.util.MyUtil;

public class ServerMsg
{
	private static Logger logger = Logger.getLogger(ServerMsg.class);
	
	private static final short MAJOR_MID = MsgDistributor.SERVER_MSG;
	
	/** echo命令 */
	private static final short ECHO_CMD = 1;
	/** 服务器身份识别请求 */
	private static final short ID_RECOG_REQ = 2;
	/** 服务器身份识别响应 */
	private static final short ID_RECOG_RESP = 3;
	
	private static int echoCount = 0;
	
	public static void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case ECHO_CMD:
			recvEchoCmd(msg);
			break;
		case ID_RECOG_REQ:
			recvIdRecogReq(msg);
			break;
		case ID_RECOG_RESP:
			recvIdRecogResp(msg);
			break;
		}
	}
	
	/** send(echo命令) */
	public static void sendEchoCmd(ChannelHandlerContext ctx, String words)
	{
		//
	}
	
	/** recv(echo命令) */
	private static void recvEchoCmd(MsgUnit msg)
	{
		try
		{
			String words = msg.readString();
			String say = "echo消息["+(++echoCount)+"]"+"("+MyUtil.now()+")"+words;
			logger.info(say);
		}
		catch(IndexOutOfBoundsException ex)
		{
			logger.error("recvEchoCmd"+MsgDef.MSG_LENGTH_WARN);
		}
	}
	
	/** send(服务器身份识别请求) */
	public static void sendIdRecogReq(byte sid, byte target, ChannelHandlerContext ctx)
	{
		MsgUnit msg = new MsgUnit(ctx);
		MsgHead head = new MsgHead(sid, target, MAJOR_MID, ID_RECOG_REQ, null);
		head.writeTo(msg);
		msg.writeByte(sid);
		msg.send();
	}
	
	/** recv(服务器身份识别请求) */
	private static void recvIdRecogReq(MsgUnit msg)
	{
		try
		{
			byte sid = BBT.readByte(msg.content);
			ServerManager.recogAndRegist(msg.ctx, sid, true);
		}
		catch(IndexOutOfBoundsException ex)
		{
			logger.error("recvIdRecogReq"+MsgDef.MSG_LENGTH_WARN);
		}
	}
	
	/** send(服务器身份识别响应) */
	public static void sendIdRecogResp(ChannelHandlerContext ctx)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, ID_RECOG_RESP, ctx);
		BBT.writeByte(msg.content, ServerManager.thisId);
		msg.send();
	}
	
	/** recv(服务器身份识别响应) */
	private static void recvIdRecogResp(MsgUnit msg)
	{
		try
		{
			byte toSid = BBT.readByte(msg.content);
			ServerManager.recogAndRegist(msg.ctx, toSid, false);
		}
		catch(IndexOutOfBoundsException ex)
		{
			logger.error("recvIdRecogResp"+MsgDef.MSG_LENGTH_WARN);
		}
	}
	
}
