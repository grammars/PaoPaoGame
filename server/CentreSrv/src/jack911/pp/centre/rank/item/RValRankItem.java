package jack911.pp.centre.rank.item;

import jack911.pp.centre.player.Player;
import jack911.util.MyUtil;

public class RValRankItem
{
	public long playerUid;
	public String playerName;
	public int roomId;
	public float r;
	public long timestamp;
	
	
	
	@Override
	public String toString()
	{
		return "RValRankItem [playerUid=" + playerUid + ", playerName=" + playerName + ", roomId=" + roomId + ", r=" + r
				+ ", timestamp=" + timestamp + "]";
	}



	public static RValRankItem create(Player player, int roomId, float r)
	{
		RValRankItem item = new RValRankItem();
		item.playerUid = player.uid();
		item.playerName = player.name();
		item.roomId = roomId;
		item.r = r;
		item.timestamp = MyUtil.now();
		return item;
	}
}
