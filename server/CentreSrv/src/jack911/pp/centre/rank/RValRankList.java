package jack911.pp.centre.rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.centre.player.Player;
import jack911.pp.centre.rank.item.RValRankItem;
import jack911.pp.message.dp.game.GameResultDp;

/** R值排行榜 */
public class RValRankList
{
	//排行榜名数
	private int size = 3;
	
	private Map<Integer, List<RValRankItem>> rankListMap = new HashMap<>();
	
	public RValRankList(int size)
	{
		this.size = size;
		
	}
	
	/** 收到游戏结果数据 */
	public void handle(GameResultDp dp)
	{
		CentreCB bundle = Centre.bundle.getBundle(dp.cccid);
		Player player = bundle.player;
		List<RValRankItem> list = rankListMap.get(dp.roomId);
		if(list == null)
		{
			list = new ArrayList<>();
			rankListMap.put(dp.roomId, list);
		}
		int min = Math.min(size, list.size());
		int no = 0;
		for(int i = 0; i < min; i++)
		{
			RValRankItem rri = list.get(i);
			if(dp.r >= rri.r)
			{
				no = i;
				break;
			}
		}
		if(no < size)
		{
			RValRankItem newRri = RValRankItem.create(player, dp.roomId, dp.r);
			list.add(no, newRri);
			if(list.size() > size)
			{
				list.remove(list.size()-1);
			}
		}
	}
	
	public void printRank()
	{
		Iterator<Entry<Integer, List<RValRankItem>>> iter = rankListMap.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Integer, List<RValRankItem>> ent = iter.next();
			Integer roomId = ent.getKey();
			System.out.println("========["+ roomId +"]排行榜========");
			List<RValRankItem> list = ent.getValue();
			for(int i = 0; i < list.size(); i++)
			{
				System.out.println("第" + i + "名：" + list.get(i));
			}
		}
	}
	
	/** 清理排行榜 */
	public void clear()
	{
		System.out.println();
	}
}
