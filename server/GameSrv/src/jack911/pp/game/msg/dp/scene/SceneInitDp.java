package jack911.pp.game.msg.dp.scene;

import jack911.pp.server.MsgUnit;

/** 游戏场景初始化数据包 */
public class SceneInitDp
{
	public int roomId;
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeInt(roomId);
	}
	
	public void readFrom(MsgUnit msg)
	{
		roomId = msg.readInt();
	}
}
