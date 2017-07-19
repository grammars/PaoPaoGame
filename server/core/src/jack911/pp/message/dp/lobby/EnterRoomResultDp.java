package jack911.pp.message.dp.lobby;

import jack911.pp.server.MsgUnit;

public class EnterRoomResultDp
{
	/** 错误码：成功 */
	public static final byte EC_SUCC = 0;
	/** 错误码：等级限制 */
	public static final byte EC_LEV_LMT = 1;
	/** 错误码：现金不够 */
	public static final byte EC_CASH_LESS = 2;
	
	public byte errCode;
	public int roomId;
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeByte(errCode);
		msg.writeInt(roomId);
	}
	
	public void readFrom(MsgUnit msg)
	{
		errCode = msg.readByte();
		roomId = msg.readInt();
	}
}
