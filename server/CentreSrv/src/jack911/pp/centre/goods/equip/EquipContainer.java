package jack911.pp.centre.goods.equip;

import java.util.List;

import jack911.pp.centre.Centre;
import jack911.pp.centre.goods.GoodsContainer;
import jack911.pp.centre.goods.GoodsInfo;
import jack911.pp.centre.goods.GoodsOperEnum;
import jack911.pp.centre.player.Player;

public class EquipContainer extends GoodsContainer
{	
	public EquipContainer(Player owner)
	{
		super(owner);
		this.type = TYPE_EQUIP;
		capacity = 6;
	}
	
	/** 应用装备属性到玩家身上 */
	public void applyAttris()
	{
		for(GoodsInfo item : items)
		{
			if(!item.available()) { continue; }
		}
	}
	
	/** 扣除物品 */
	public GoodsOperEnum deductItem(int baseCfgId, int dNum)
	{
		int hasNum = this.getTotalNum(baseCfgId);
		if(hasNum < dNum) { return GoodsOperEnum.NUM_LESS; }
		List<GoodsInfo> gis = getSameItems(baseCfgId);
		for(int i = 0; i < gis.size(); i++)
		{
			GoodsInfo item = gis.get(i);
			if(item.num <= dNum)
			{
				dNum -= item.num;
				removeItem(item.uid);
			}
			else
			{
				item.num -= dNum;
				updateItemHandler(item);
				dNum = 0;
			}
			if(dNum <= 0)
			{
				break;
			}
		}
		return GoodsOperEnum.SUCC;
	}
	
	@Override
	public void clearItemsHandler()
	{
		Centre.msg.goods.sendClearItemsInEquip_G2C(owner.getBundle().cccid);
	}
	
	@Override
	public void initItemsHandler()
	{
		Centre.msg.goods.sendInitItemsToEquip_G2C(this, owner.getBundle().cccid);
	}
	
	@Override
	protected void addItemHandler(GoodsInfo item)
	{
		Centre.msg.goods.sendAddItemToEquip_G2C(item, owner.getBundle().cccid);
	}
	
	@Override
	protected void removeItemHandler(GoodsInfo item)
	{
		Centre.msg.goods.sendRemoveItemFromEquip_G2C(item.uid, owner.getBundle().cccid);
	}
	
	@Override
	protected void updateItemHandler(GoodsInfo item)
	{
		Centre.msg.goods.sendUpdateItemInEquip_G2C(item, owner.getBundle().cccid);
	}
	
}
