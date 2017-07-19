package jack911.pp.centre.msg.content;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.centre.goods.GoodsFactory;
import jack911.pp.centre.goods.GoodsInfo;
import jack911.pp.centre.goods.StGoodsContainer;
import jack911.pp.centre.player.Player;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.GoodsMsg;
import jack911.pp.server.MsgUnit;

public class GoodsMsg4Centre extends GoodsMsg
{
	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		CentreCB bundle = Centre.bundle.getBundle(head.cccid);
		if(bundle == null) { return; }
		switch(head.minorId)
		{
		case MOVE_ITEM_BAG_TO_BAG_REQ_C2G:
			recvMoveItemBagToBagReq_C2G(head, msg, bundle.player);
			break;
		
		case DROP_ITEM_FROM_BAG_REQ_C2G:
			recvDropItemFromBagReq_C2G(head, msg, bundle.player);
			break;
		case SPLIT_ITEM_IN_BAG_REQ_C2G:
			recvSplitItemInBagReq_C2G(head, msg, bundle.player);
			break;
			
		case PUT_ON_EQUIP_REQ_C2G:
			recvPutOnEquipReq_C2G(head, msg, bundle.player);
			break;
		case TAKE_OFF_EQUIP_REQ_C2G:
			recvTakeOffEquipReq_C2G(head, msg, bundle.player);
			break;
			
		case USE_ITEM_REQ_C2G:
			recvUseItemReq_C2G(head, msg, bundle.player);
			break;
		}
	}
	
	/** send( game向client通知物品操作异常 ) */
	public void sendOperException_G2C(byte errCode, byte containerType, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, OPER_EXCEPTION_G2C, cccid);
		msg.writeByte(errCode);
		msg.writeByte(containerType);
		msg.send();
	}
	
	//=========================Bag=====↓=====================================
	
	/** send( game向client通知背包已清空 ) */
	public void sendClearItemsInBag_G2C(long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, CLEAR_ITEMS_IN_BAG_G2C, cccid);
		msg.send();
	}
	
	/** send( game向client发送初始化背包物品 ) */
	public void sendInitItemsToBag_G2C(StGoodsContainer bag, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, INIT_ITEMS_TO_BAG_G2C, cccid);
		bag.write(msg);
		msg.send();
	}
	
	/** send( game向client通知添加物品到背包 ) */
	public void sendAddItemToBag_G2C(GoodsInfo item, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, ADD_ITEM_TO_BAG_G2C, cccid);
		GoodsFactory.writeInfo(item, msg);
		msg.send();
	}
	
	/** send( game向client通知移除物品从背包 ) */
	public void sendRemoveItemFromBag_G2C(long itemUid, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, REMOVE_ITEM_FROM_BAG_G2C, cccid);
		msg.writeLong(itemUid);
		msg.send();
	}
	
	/** send( game向client通知更新背包物品 ) */
	public void sendUpdateItemInBag_G2C(GoodsInfo item, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_ITEM_IN_BAG_G2C, cccid);
		GoodsFactory.writeInfo(item, msg);
		msg.send();
	}

	//=========================Equip=====↓=====================================
	
	/** send( game向client通知装备已清空 ) */
	public void sendClearItemsInEquip_G2C(long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, CLEAR_ITEMS_IN_EQUIP_G2C, cccid);
		msg.send();
	}
	
	/** send( game向client发送初始化装备 ) */
	public void sendInitItemsToEquip_G2C(StGoodsContainer equip, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, INIT_ITEMS_TO_EQUIP_G2C, cccid);
		equip.write(msg);
		msg.send();
	}
	
	/** send( game向client通知穿上装备 ) */
	public void sendAddItemToEquip_G2C(GoodsInfo item, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, ADD_ITEM_TO_EQUIP_G2C, cccid);
		GoodsFactory.writeInfo(item, msg);
		msg.send();
	}
	
	/** send( game向client通知脱下装备 ) */
	public void sendRemoveItemFromEquip_G2C(long itemUid, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, REMOVE_ITEM_FROM_EQUIP_G2C, cccid);
		msg.writeLong(itemUid);
		msg.send();
	}
	
	/** send( game向client通知更新装备物品 ) */
	public void sendUpdateItemInEquip_G2C(GoodsInfo item, long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_ITEM_IN_EQUIP_G2C, cccid);
		GoodsFactory.writeInfo(item, msg);
		msg.send();
	}
	
	//=========================Xxxx=====↓=====================================
	
	/** recv( client请求移动物品：从背包到背包 ) */
	private void recvMoveItemBagToBagReq_C2G(MsgHead head, MsgUnit msg, Player player)
	{
		
		long srcUid = msg.readLong();
		int tarInd = msg.readInt();
		Centre.goods.moveBagItemToBag(player, srcUid, tarInd);
	}
	
	/** recv( client请求丢弃背包物品 ) */
	private void recvDropItemFromBagReq_C2G(MsgHead head, MsgUnit msg, Player player)
	{
		long itemUid = msg.readLong();
		Centre.goods.dropItemFromBag(player, itemUid);
	}
	
	/** recv( client请求拆分背包物品 ) */
	private void recvSplitItemInBagReq_C2G(MsgHead head, MsgUnit msg, Player player)
	{
		long itemUid = msg.readLong();
		int spNum = msg.readInt();
		Centre.goods.splitItemInBag(player, itemUid, spNum);
	}
	
	/** recv( client请求穿上装备 ) */
	private void recvPutOnEquipReq_C2G(MsgHead head, MsgUnit msg, Player player)
	{
		long srcUid = msg.readLong();
		Centre.goods.putOnEquip(player, srcUid);
	}
	
	/** recv( client请求脱下装备 ) */
	private void recvTakeOffEquipReq_C2G(MsgHead head, MsgUnit msg, Player player)
	{
		long srcUid = msg.readLong();
		int index = msg.readInt();
		Centre.goods.takeOffEquip(player, srcUid, index);
	}
	
	/** recv( client请求使用物品 ) */
	private void recvUseItemReq_C2G(MsgHead head, MsgUnit msg, Player player)
	{
		long itemUid = msg.readLong();
		int useNum = msg.readInt();
		player.bag.useItem(itemUid, useNum);
	}
	
}
