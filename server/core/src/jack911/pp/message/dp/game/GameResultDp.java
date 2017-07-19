package jack911.pp.message.dp.game;

import jack911.pp.server.MsgUnit;

public class GameResultDp
{
	/** 玩家cccid */
	public long cccid;
	/** 玩家结果r值 */
	public float r;
	/** 房间id */
	public int roomId;
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeLong(cccid);
		msg.writeFloat(r);
		msg.writeInt(roomId);
	}
	
	public void readFrom(MsgUnit msg)
	{
		this.cccid = msg.readLong();
		this.r = msg.readFloat();
		this.roomId = msg.readInt();
	}

	@Override
	public String toString()
	{
		return "GameResultDp [cccid=" + cccid + ", r=" + r + ", roomId=" + roomId + "]";
	}
}
