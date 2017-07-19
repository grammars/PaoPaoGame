package jack911.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class BBT
{
	public static final String CHARSET = "UTF-8";
	
	public static Charset cs;
	public static CharsetDecoder dec;
	public static CharsetEncoder enc;
	
	static
	{
		setCharset(CHARSET);
	}
	
	/** 获取BBT的版本号 */
	public static String getVersion()
	{
		return "1.0.0";
	}
	
	/** 设置字符编码 */
	public static void setCharset(String cname)
	{
		cs = Charset.forName(cname);
		dec = cs.newDecoder();
		enc = cs.newEncoder();
	}
	
	public static ByteBuf create()
	{
		return UnpooledByteBufAllocator.DEFAULT.buffer();
	}
	
	public static ByteBuf create(int initialCapacity)
	{
		return  UnpooledByteBufAllocator.DEFAULT.buffer(initialCapacity);
	}
	
	//======================================================================
	
	public static String readString(ByteBuf src)
	{
		return readString(src, -1);
	}
	
	public static String readString(ByteBuf src, int bytesLen)
	{
		int len = src.readInt();
		String str = "";
		try
		{
			if(bytesLen > 0)
			{
				len = bytesLen;
			}
			if(len > 2048) 
			{ 
				return "err:bytesLen>2048";
			}
			byte[] tmp = new byte[len];
			src.readBytes(tmp);
			str = new String(tmp, CHARSET);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return str;
	}
	

	public static ByteBuf writeString(ByteBuf dest, String value)
	{
		return writeString(dest, value, -1);
	}
	
	public static ByteBuf writeString(ByteBuf dest, String value, int bytesLen)
	{
		if(value == null)
		{
			value = "";
		}
		int len = bytesLen;
		if(bytesLen <= 0)
		{
			try
			{
				byte[] tmp = value.getBytes(CHARSET);
				len = tmp.length; 
				dest.writeInt(len);
				dest.writeBytes(tmp);
			}
			catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		}
		return dest;
	}
	
	//======================================================================
	
	public static byte[] readBytes(ByteBuf src)
	{
		int size = src.readInt();
		byte[] bytes = new byte[size];
		src.readBytes(bytes, 0, size);
		return bytes;
	}
	
	public static ByteBuf writeBytes(ByteBuf dest, byte[] value)
	{
		dest.writeInt(value.length);
		dest.writeBytes(value);
		return dest;
	}
	
	//======================================================================
	
	public static boolean readBoolean(ByteBuf src)
	{
		return src.readBoolean();
	}
	
	public static ByteBuf writeBoolean(ByteBuf dest, boolean value)
	{
		dest.writeBoolean(value);
		return dest;
	}
	
	//======================================================================
	
	public static byte readByte(ByteBuf src)
	{
		return src.readByte();
	}
	
	public static ByteBuf writeByte(ByteBuf dest, byte value)
	{
		dest.writeByte(value);
		return dest;
	}
	
	//======================================================================
	
	public static char readChar(ByteBuf src)
	{
		return src.readChar();
	}
	
	public static ByteBuf writeChar(ByteBuf dest, char value)
	{
		dest.writeChar(value);
		return dest;
	}
	
	//======================================================================
	
	public static short readShort(ByteBuf src)
	{
		return src.readShort();
	}
	
	public static ByteBuf writeShort(ByteBuf dest, short value)
	{
		dest.writeShort(value);
		return dest;
	}
	
	//======================================================================

	public static int readInt(ByteBuf src)
	{
		return src.readInt();
	}
	
	public static ByteBuf writeInt(ByteBuf dest, int value)
	{
		dest.writeInt(value);
		return dest;
	}
	
	//======================================================================
	
	public static long readLong(ByteBuf src)
	{
		return src.readLong();
	}
	
	public static ByteBuf writeLong(ByteBuf dest, long value)
	{
		dest.writeLong(value);
		return dest;
	}
	
	//======================================================================
	
	public static float readFloat(ByteBuf src)
	{
		return src.readFloat();
	}
	
	public static ByteBuf writeFloat(ByteBuf dest, float value)
	{
		dest.writeFloat(value);
		return dest;
	}
	
	//======================================================================
	
	public static double readDouble(ByteBuf src)
	{
		return src.readDouble();
	}
	
	public static ByteBuf writeDouble(ByteBuf dest, double value)
	{
		dest.writeDouble(value);
		return dest;
	}
	
	//======================================================================
	
	public static void clear(ByteBuf buffer)
	{
		buffer.clear();
	}
}
