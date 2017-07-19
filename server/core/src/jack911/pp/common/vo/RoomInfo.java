package jack911.pp.common.vo;

import jack911.pp.server.MsgUnit;

public class RoomInfo
{
	public int id;
	
	public int roomId;
	
	public int tickets;
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeInt(id);
		msg.writeInt(roomId);
		msg.writeInt(tickets);
	}
	
	public void readFrom(MsgUnit msg)
	{
		id = msg.readInt();
		roomId = msg.readInt();
		tickets = msg.readInt();
	}
}
