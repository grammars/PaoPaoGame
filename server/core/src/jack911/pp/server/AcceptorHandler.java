package jack911.pp.server;

import java.io.IOException;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class AcceptorHandler extends ChannelHandlerAdapter
{
	private static Logger logger = Logger.getLogger(AcceptorHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx)
	{
		logger.debug("连接已连通");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx)
	{
		logger.debug("连接已失联");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
	{
		ByteBuf buf = (ByteBuf) msg;
		MsgRecvDriver.getInstance().put(new MsgUnit(buf, ctx));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		if(cause instanceof IOException)
		{
			logger.debug("连接IO异常");
		}
		else
		{
			cause.printStackTrace();
		}
		ctx.close();// 发生异常，关闭链路
	}
}
