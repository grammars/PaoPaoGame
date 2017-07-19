package jack911.pp.server;

import java.io.IOException;

import org.apache.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import jack911.netty.codec.BytesDecoder;
import jack911.netty.codec.BytesEncoder;
import jack911.pp.message.content.ServerMsg;
import jack911.util.MyUtil;

public class Connector extends Thread
{
	private Logger logger;

	private byte fromSid;
	private byte toSid;
	private String host;
	private int port;

	private Bootstrap bootstrap;
	private ChannelFuture future;

	public Connector(String fromSn, String toSn, String host, int port)
	{
		this.fromSid = ServerId.toId(fromSn);
		this.toSid = ServerId.toId(toSn);
		this.host = host;
		this.port = port;
		logger = Logger.getLogger("Connector[" + fromSn + "=>" + toSn + "]");
		this.setName("ConnectorThread");
	}
	
	private void tryConnect()
	{
		future = bootstrap.connect(host, port);
		future.addListener(new ReconnListener());
	}
	
	private class ReconnListener implements ChannelFutureListener
	{
		@Override
		public void operationComplete(ChannelFuture future) throws Exception
		{
			if (!future.isSuccess())
			{
                logger.info("服务端无法正常连接， 客户端正在尝试重连");
                MyUtil.delay(1000);
                future.channel().close();
                bootstrap.connect(Connector.this.host, Connector.this.port).addListener(this);
            }
		}
	}

	@Override
	public void run()
	{
		// 配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>()
				{
					@Override
					public void initChannel(SocketChannel ch) throws Exception
					{
						ch.pipeline().addLast(new BytesDecoder());
						ch.pipeline().addLast(new BytesEncoder());
						ch.pipeline().addLast(new ConnectorHandler());
					}
				});
		tryConnect();
	}

	public class ConnectorHandler extends ChannelHandlerAdapter
	{
		public ConnectorHandler()
		{
		}

		@Override
		public void channelActive(ChannelHandlerContext ctx)
		{
			logger.debug("ConnectorHandler::channelActive");
			//testEcho(ctx);
			ServerMsg.sendIdRecogReq(Connector.this.fromSid, Connector.this.toSid, ctx);
		}
		
		@SuppressWarnings("unused")
		private void testEcho(ChannelHandlerContext ctx)
		{
			new Thread()
			{
				public void run()
				{
					for (int i = 0; i < 5; i++)
					{
						for (int j = 0; j < 4097; j++)
						{
							ServerMsg.sendEchoCmd(ctx, "HeyServer!你好，服务器!我刚刚连接上");
						}
						MyUtil.delay(100);
					}
				};
			}.start();
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception
		{
			logger.debug("ConnectorHandler::channelInactive");
			Connector.this.tryConnect();
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
		{
			ByteBuf buf = (ByteBuf) msg;
			MsgRecvDriver.getInstance().put(new MsgUnit(buf, ctx));
		}

		@Override
		public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
		{
			ctx.flush();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
		{
			if(cause instanceof IOException)
			{
				logger.debug("ConnectorHandler:断开连接");
			}
			else
			{
				cause.printStackTrace();
			}
			ctx.close();
		}
	}
}
