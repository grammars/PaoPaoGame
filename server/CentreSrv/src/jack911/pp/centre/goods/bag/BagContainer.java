package jack911.pp.centre.goods.bag;

import jack911.pp.centre.player.Player;
import jack911.pp.centre.Centre;
import jack911.pp.centre.goods.GoodsContainer;
import jack911.pp.centre.goods.GoodsInfo;
import jack911.pp.centre.goods.GoodsOperEnum;
import jack911.pp.centre.goods.GoodsUseHandler;

import java.util.List;

public class BagContainer extends GoodsContainer
{
	private static final int CAPACITY_MIN = 72;
	//private static final int CAPACITY_MAX = 84;
	
	public BagContainer(Player owner)
	{
		super(owner);
		this.type = TYPE_BAG;
		capacity = CAPACITY_MIN;
	}
	
	/** 使用物品 */
	public GoodsOperEnum useItem(long itemUid, int useNum)
	{
		GoodsInfo item = getItemByUid(itemUid);
		GoodsOperEnum oper = GoodsUseHandler.use(owner, item, useNum);
		if(oper == GoodsOperEnum.SUCC)
		{
			if(item.isEquip()) { return oper; }//如果是 装备 则不扣除数量
			
			item.num -= useNum;
			if(item.num > 0)
			{
				updateItemHandler(item);
			}
			else
			{
				removeItem(item.uid);
			}
		}
		return oper;
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
	public GoodsOperEnum addItem(GoodsInfo item)
	{
		heapItem(item);
		return addItemAt(item, -1);
	}
	
	@Override
	public void clearItemsHandler()
	{
		Centre.msg.goods.sendClearItemsInBag_G2C(owner.getBundle().cccid);
	}
	
	@Override
	public void initItemsHandler()
	{
		Centre.msg.goods.sendInitItemsToBag_G2C(this, owner.getBundle().cccid);
	}
	
	@Override
	protected void addItemHandler(GoodsInfo item)
	{
		Centre.msg.goods.sendAddItemToBag_G2C(item, owner.getBundle().cccid);
		//TaskManager.handleCollect(owner, item);
	}
	
	@Override
	protected void removeItemHandler(GoodsInfo item)
	{
		Centre.msg.goods.sendRemoveItemFromBag_G2C(item.uid, owner.getBundle().cccid);
		//TaskManager.handleCollect(owner, item);
	}
	
	@Override
	protected void updateItemHandler(GoodsInfo item)
	{
		Centre.msg.goods.sendUpdateItemInBag_G2C(item, owner.getBundle().cccid);
		//TaskManager.handleCollect(owner, item);
	}
}
