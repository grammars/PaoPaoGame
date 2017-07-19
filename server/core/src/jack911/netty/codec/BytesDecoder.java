package jack911.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class BytesDecoder extends LengthFieldBasedFrameDecoder 
{
	public BytesDecoder() 
	{
		super(64*1024, 0, 4, 0, 4);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception 
	{
		Object frame = super.decode(ctx, in);
		if (frame == null) 
		{
			return null;
		}
		//ByteBuf result = (ByteBuf)frame;
		//return result;
		return frame;
	}
}
