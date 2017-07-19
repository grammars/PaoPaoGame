package jack911.pp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import jack911.netty.codec.BytesDecoder;
import jack911.netty.codec.BytesEncoder;

public class Acceptor extends Thread
{
	private int port;
	private Class<? extends AcceptorHandler>  AcceptorHandlerClazz;
	
	public Acceptor()
	{
		
	}
	
	public void initialize(int port, Class<? extends AcceptorHandler> AcceptorHandlerClazz)
	{
		this.port = port;
		this.AcceptorHandlerClazz = AcceptorHandlerClazz;
		this.setName("AcceptorThread");
	}

	@Override
	public void run()
	{
		// 配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.SO_BACKLOG, 100);
			b.handler(new LoggingHandler(LogLevel.INFO));
			b.childHandler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				public void initChannel(SocketChannel ch)
				{
					ch.pipeline().addLast(new BytesEncoder());
					ch.pipeline().addLast(new BytesDecoder());
					AcceptorHandler handler = null;
					try
					{
						handler = AcceptorHandlerClazz.newInstance();
					}
					catch (InstantiationException | IllegalAccessException e)
					{
						e.printStackTrace();
					}
					ch.pipeline().addLast(handler);
				}
			});

			// 绑定端口，同步等待成功
			ChannelFuture f = b.bind(this.port).sync();

			// 等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			// 优雅退出，释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
}
