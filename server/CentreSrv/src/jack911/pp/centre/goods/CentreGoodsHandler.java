package jack911.pp.centre.goods;

import jack911.pp.centre.player.Player;

public class CentreGoodsHandler
{
	/** 移动物品：从背包到背包 */
	public void moveBagItemToBag(Player player, long srcUid, int tarInd)
	{
		player.bag.moveItem(srcUid, tarInd);
	}
	
	/** 丢弃背包物品 */
	public void dropItemFromBag(Player player, long itemUid)
	{
		player.bag.removeItem(itemUid);
		//player.alert("丢弃背包物品请求已受理");
	}
	
	/** 拆分背包物品 */
	public void splitItemInBag(Player player, long itemUid, int spNum)
	{
		player.bag.splitItem(itemUid, spNum);
		//player.alert("拆分背包物品请求已受理");
	}
	
	/** 穿上装备 */
	public GoodsOperEnum putOnEquip(Player player, long srcUid)
	{
		GoodsInfo equipment = player.bag.getItemByUid(srcUid);
		if(equipment == null)
		{
			player.bag.operExceptionHandler(GoodsOperEnum.ITEM_NULL);
		}
		if(equipment.isEquip() == false)
		{
			player.bag.operExceptionHandler(GoodsOperEnum.NOT_EQUIP);
		}
		if(!equipment.available())
		{
			player.alert("装备数据不合法");
			return GoodsOperEnum.FAIL;
		}
		if(equipment.cfg.reqLevMin > 0 && player.level() < equipment.cfg.reqLevMin)
		{
			player.alert("需要"+equipment.cfg.reqLevMin+"级以上才能穿戴");
			return GoodsOperEnum.PLAYER_LEV_TOO_MIN;
		}
		if(equipment.cfg.reqLevMax > 0 && player.level() > equipment.cfg.reqLevMax)
		{
			player.alert("您的等级已经超过穿戴等级上限");
			return GoodsOperEnum.PLAYER_LEV_TOO_MAX;
		}
		GoodsInfo oldItem = player.equip.getItemByIndex(equipment.equipPos());
		int indexInBag = equipment.index;
		GoodsOperEnum result;
		if(oldItem == null)
		{
			result = player.equip.addItemAt(equipment, equipment.equipPos());
			if(result == GoodsOperEnum.SUCC)
			{
				player.bag.removeItem(equipment.uid);
			}
		}
		else
		{
			player.equip.removeItem(oldItem.uid);
			player.equip.addItemAt(equipment, equipment.equipPos());
			player.bag.removeItem(equipment.uid);
			player.bag.addItemAt(oldItem, indexInBag);
		}
		player.equip.applyAttris();
		return GoodsOperEnum.SUCC;
	}
	
	/** 脱下装备 */
	public void takeOffEquip(Player player, long srcUid, int index)
	{
		if(player.bag.isFull())
		{
			player.alert("背包已满，无法脱下装备");
			return;
		}
		GoodsInfo item = player.equip.getItemByUid(srcUid);
		if(item == null)
		{
			player.alert("所请求脱下的装备并不存在");
			return;
		}
		if(index < 0)
		{
			player.equip.removeItem(item.uid);
			player.bag.addItem(item);
		}
		else
		{
			if( null != player.bag.getItemByIndex(index) )
			{
				player.alert("指定位置已有物品，请挑一个空格脱下装备");
				return;
			}
			else
			{
				player.equip.removeItem(item.uid);
				player.bag.addItemAt(item, index);
			}
		}
		player.equip.applyAttris();
	}
}
