package jack911.pp.message.dp.lobby;

import jack911.pp.server.MsgUnit;

public class LobbyInitDp
{
	public String debug = "大厅初始化de消息";
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeString(debug);
	}
}
