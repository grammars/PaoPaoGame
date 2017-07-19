package jack911.pp.server;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.content.ServerMsg;

public class ServerManager
{
	private static Logger logger = Logger.getLogger(ServerManager.class);
	
	/** 当前服务器id */
	public static byte thisId;
	/** 消息分发器 */
	public static MsgDistributor msgDistributor;
	/** 服务器状态监听 */
	public static ServerConnListener connListener;
	
	private static Map<Byte, ChannelHandlerContext> ctxMap = new HashMap<>();
	public static ChannelHandlerContext getCtx(Byte sid)
	{
		return ctxMap.get(sid);
	}
	private static Map<ChannelHandlerContext, Byte> sidMap = new HashMap<>();
	public static Byte getSid(ChannelHandlerContext ctx)
	{
		return sidMap.get(ctx);
	}
	
	/** 初始化ServerMangaer，在每个XxxxSrv启动时调用 */
	public static void initialize(byte sid, MsgDistributor msgDis, Class<? extends AcceptorHandler> AcceptorHandlerClazz, Class<? extends ServerConnListener> ConnListenerClazz)
	{
		thisId = sid;
		msgDistributor = msgDis;
		
		try
		{
			if(ConnListenerClazz != null) { connListener = ConnListenerClazz.newInstance(); }
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		ServerConfig.Configuration sc = ServerConfig.getConfig(thisId);
		Acceptor acceptor = new Acceptor();
		acceptor.initialize(sc.getBindPort(), AcceptorHandlerClazz);
		acceptor.start();
		for(ServerConfig.Configuration tc : sc.getConnectTargetCfgs())
		{
			System.out.println("[tc]"+tc);
			new Connector(sc.getName(), tc.getName(), tc.getBindIp(), tc.getBindPort()).start();
		}
		MsgRecvDriver.getInstance().start();
		MsgSendDriver.getInstance().start();
	}
	
	/** 识别并保存服务器会话
	 * @param needResponse 是否需要应答（如果this是Acceptor则需要应答一下，告诉Connector我是哪个Srv） */
	public static void recogAndRegist(ChannelHandlerContext ctx, byte sid, boolean needResponse)
	{
		logger.info("他说他是" + ServerId.toName(sid));
		InetSocketAddress addr = (InetSocketAddress) ctx.channel().remoteAddress();
		String remoteIp = addr.getAddress().getHostAddress();
		ServerConfig.Configuration sc = ServerConfig.getConfig(thisId);
		boolean allowConn = sc.allow(remoteIp);//是否允许连接
		//System.out.println("是否允许 "+remoteIp+" 连接:" + allowConn);
		if(!allowConn)
		{
			logger.error("一个未经允许的链接：" + remoteIp);
			ctx.close();
		}
		else
		{
			ctxMap.put(sid, ctx);
			sidMap.put(ctx, sid);
			if(needResponse)
			{
				ServerMsg.sendIdRecogResp(ctx);
			}
			//
			if(connListener != null) { connListener.connectedHandler(sid); }
		}
	}
}
