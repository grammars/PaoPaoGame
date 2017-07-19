package jack911.pp.centre.goods;

import jack911.pp.centre.Centre;
import jack911.pp.centre.player.Player;

public class GoodsUseHandler
{
	/** 使用物品(但不作扣除数量处理) */
	public static GoodsOperEnum use(Player player, GoodsInfo item, int useNum)
	{
		if(!item.available()) { return GoodsOperEnum.FAIL; }
		if(item.num < useNum) { return GoodsOperEnum.NUM_LESS; }
		GoodsOperEnum oper =  GoodsOperEnum.FAIL;
		switch(item.cfg.type)
		{
		case GoodsType.DRUG:
			//player.alert("暂不支持使用DRUG物品");
			oper = useDrugItem(player, item, useNum);
			break;
		case GoodsType.EQUIP:
			//player.alert("暂不支持使用EQUIP物品");
			oper = useEquipItem(player, item, useNum);
			break;
		}
		if(oper != GoodsOperEnum.SUCC)
		{
			player.bag.operExceptionHandler(oper);
		}
		return oper;
	}
	
	/** useDrugItem */
	private static GoodsOperEnum useDrugItem(Player player, GoodsInfo gi, int useNum)
	{
		//使用实现一些效果
		return GoodsOperEnum.SUCC;
	}
	
	/** useEquipItem */
	private static GoodsOperEnum useEquipItem(Player player, GoodsInfo gi, int useNum)
	{
		GoodsOperEnum oper = Centre.goods.putOnEquip(player, gi.uid);
		return oper;
	}
	
}
