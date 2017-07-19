package jack911.pp.message;

import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class MsgHead
{
	/** 无效的消息头 */
	public static final MsgHead invalid = new MsgHead(ServerId.UNKNOWN, ServerId.UNKNOWN, (short)-1, (short)-1, null);
	
	/** 消息来源[取自ServerId] */
	public byte source;
	/** 消息目标[取自ServerId] */
	public byte target;
	/** 主消息号 */
	public short majorId;
	/** 次消息号 */
	public short minorId;
	/** ClientChannelContextID<br>
	 * 当从Client-->Server的时候：表示来源Client的cccid<br>
	 * 当从Server-->Client的时候：表示目标Client的cccid<br> */
	public Long cccid;
	
	public MsgHead()
	{
		//
	}
	
	public MsgHead(byte source, byte target, short majorId, short minorId, Long cccid)
	{
		this.source = source;
		this.target = target;
		this.majorId = majorId;
		this.minorId = minorId;
		this.cccid = cccid;
	}
	
	/** 读取 */
	public MsgHead readFrom(MsgUnit msg)
	{
		if( msg == null || msg.content == null ) return this;
		if( msg.content.readableBytes() < 6 ) return this;
		this.source = msg.readByte();
		this.target = msg.readByte();
		this.majorId = msg.readShort();
		this.minorId = msg.readShort();
		if( source == ServerId.CLIENT || target == ServerId.CLIENT )
		{
			this.cccid = msg.readCCCID();
		}
		return this;
	}
	
	/** 写入 */
	public void writeTo(MsgUnit msg)
	{
		if( msg == null || msg.content == null ) return;
		msg.writeByte(this.source);
		msg.writeByte(this.target);
		msg.writeShort(this.majorId);
		msg.writeShort(this.minorId);
		if( source == ServerId.CLIENT || target == ServerId.CLIENT )
		{
			msg.writeCCCID(this.cccid);
		}
	}
	
	/** 设置MsgUnit中的cccid */
	public static void setCCCID(MsgUnit msg, Long cccid)
	{
		msg.content.setLong(6, cccid);
	}
	
}
