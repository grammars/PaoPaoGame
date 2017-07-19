package jack911.pp.common.vo;

import jack911.pp.server.MsgUnit;

public class PlayerData
{
	/** 角色唯一id */
	public long uid;
	/** 角色名 */
	public String name;
	/** 等级 */
	public int level;
	/** 现金泡币 */
	public int cash;
	/** 调试信息 */
	public String debug;
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeLong(this.uid);
		msg.writeString(this.name);
		msg.writeInt(this.level);
		msg.writeInt(this.cash);
		msg.writeString(this.debug);
	}
	
	public void readFrom(MsgUnit msg)
	{
		this.uid = msg.readLong();
		this.name = msg.readString();
		this.level = msg.readInt();
		this.cash = msg.readInt();
		this.debug = msg.readString();
	}
}
