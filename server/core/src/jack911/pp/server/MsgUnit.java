package jack911.pp.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import jack911.netty.BBT;

public class MsgUnit
{
	public ChannelHandlerContext ctx;
	public ByteBuf content;
	
	public MsgUnit(ChannelHandlerContext ctx)
	{
		this.content = BBT.create();
		this.ctx = ctx;
	}
	
	public MsgUnit(ByteBuf content, ChannelHandlerContext ctx)
	{
		this.content = content;
		this.ctx = ctx;
	}
	
	public void send()
	{
		MsgSendDriver.getInstance().put(this);
	}
	
	public Long readCCCID()
	{
		return this.readLong();
	}
	
	public void writeCCCID(Long cccid)
	{
		this.writeLong(cccid);
	}
	
	//---------------------------WRAPPER----------------------
	//======================================================================
	
		public String readString()
		{
			return BBT.readString(content, -1);
		}
		
		public String readString(int bytesLen)
		{
			return BBT.readString(content, bytesLen);
		}
		

		public MsgUnit writeString(String value)
		{
			BBT.writeString(content, value);
			return this;
		}
		
		public MsgUnit writeString(String value, int bytesLen)
		{
			BBT.writeString(content, value, bytesLen);
			return this;
		}
		
		//======================================================================
		
		public byte[] readBytes()
		{
			return BBT.readBytes(content);
		}
		
		public MsgUnit writeBytes(byte[] value)
		{
			BBT.writeBytes(content, value);
			return this;
		}
		
		//======================================================================
		
		public boolean readBoolean()
		{
			return BBT.readBoolean(content);
		}
		
		public MsgUnit writeBoolean(boolean value)
		{
			BBT.writeBoolean(content, value);
			return this;
		}
		
		//======================================================================
		
		public byte readByte()
		{
			return BBT.readByte(content);
		}
		
		public MsgUnit writeByte(byte value)
		{
			BBT.writeByte(content, value);
			return this;
		}
		
		//======================================================================
		
		public char readChar()
		{
			return BBT.readChar(content);
		}
		
		public MsgUnit writeChar(char value)
		{
			BBT.writeChar(content, value);
			return this;
		}
		
		//======================================================================
		
		public short readShort()
		{
			return BBT.readShort(content);
		}
		
		public MsgUnit writeShort(short value)
		{
			BBT.writeShort(content, value);
			return this;
		}
		
		//======================================================================

		public int readInt()
		{
			return BBT.readInt(content);
		}
		
		public MsgUnit writeInt(int value)
		{
			BBT.writeInt(content, value);
			return this;
		}
		
		//======================================================================
		
		public long readLong()
		{
			return BBT.readLong(content);
		}
		
		public MsgUnit writeLong(long value)
		{
			BBT.writeLong(content, value);
			return this;
		}
		
		//======================================================================
		
		public float readFloat()
		{
			return BBT.readFloat(content);
		}
		
		public MsgUnit writeFloat(float value)
		{
			BBT.writeFloat(content, value);
			return this;
		}
		
		//======================================================================
		
		public double readDouble()
		{
			return BBT.readDouble(content);
		}
		
		public MsgUnit writeDouble(double value)
		{
			BBT.writeDouble(content, value);
			return this;
		}
		
		//======================================================================
	
}
