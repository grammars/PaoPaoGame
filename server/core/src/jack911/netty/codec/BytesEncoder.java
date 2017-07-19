package jack911.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class BytesEncoder extends MessageToByteEncoder<ByteBuf> 
{
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception
    {
    	//System.out.println("BytesEncoder  writerIndex="+msg.writerIndex() + "  readerIndex=" + msg.readerIndex() + "  readableBytes=" + msg.readableBytes());
        out.writeInt(msg.readableBytes());
        out.writeBytes(msg, msg.readableBytes());
    }
}
