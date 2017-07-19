package jack911.pp.net.msg
{	
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import jack911.pp.core.goods.GoodsFactory;
	import jack911.pp.core.goods.GoodsInfo;
	import jack911.pp.core.goods.GoodsOperEnum;
	import jack911.pp.core.goods.StGoodsContainer;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Game;
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;
	import jack911.pp.net.MsgSender;
	
	public class GoodsMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.GOODS_MSG;
		
		/** game向client通知物品操作异常 */
		private static const OPER_EXCEPTION_G2C:int = 1;
		
		/** game向client通知背包已清空 */
		private static const CLEAR_ITEMS_IN_BAG_G2C:int = 10;
		/** game向client发送初始化背包物品 */
		private static const INIT_ITEMS_TO_BAG_G2C:int = 11;
		/** game向client通知添加物品到背包 */
		private static const ADD_ITEM_TO_BAG_G2C:int = 12;
		/** game向client通知移除物品从背包 */
		private static const REMOVE_ITEM_FROM_BAG_G2C:int = 13;
		/** game向client通知更新背包物品 */
		private static const UPDATE_ITEM_IN_BAG_G2C:int = 14;
		
		/** game向client通知装备已清空 */
		private static const CLEAR_ITEMS_IN_EQUIP_G2C:int = 20;
		/** game向client发送初始化装备 */
		private static const INIT_ITEMS_TO_EQUIP_G2C:int = 21;
		/** game向client通知穿上装备 */
		private static const ADD_ITEM_TO_EQUIP_G2C:int = 22;
		/** game向client通知脱下装备 */
		private static const REMOVE_ITEM_FROM_EQUIP_G2C:int = 23;
		/** game向client通知更新装备物品 */
		private static const UPDATE_ITEM_IN_EQUIP_G2C:int = 24;
		
		/** client请求移动物品：从背包到背包 */
		private static const MOVE_ITEM_BAG_TO_BAG_REQ_C2G:int = 41;
		
		/** client请求丢弃背包物品 */
		private static const DROP_ITEM_FROM_BAG_REQ_C2G:int = 51;
		/** client请求拆分背包物品 */
		private static const SPLIT_ITEM_IN_BAG_REQ_C2G:int = 52;
		
		/** client请求穿上装备 */
		private static const PUT_ON_EQUIP_REQ_C2G:int = 61;
		/** client请求脱下装备 */
		private static const TAKE_OFF_EQUIP_REQ_C2G:int = 62;
		
		/** client请求使用物品 */
		private static const USE_ITEM_REQ_C2G:int = 71;
		
		public function GoodsMsg()
		{
			super();
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			case OPER_EXCEPTION_G2C:
				recvOperException_G2C(bytes);
				break;
			
			case CLEAR_ITEMS_IN_BAG_G2C:
				recvClearItemsInBag_G2C(bytes);
				break;
			case INIT_ITEMS_TO_BAG_G2C:
				recvInitItemsToBag_G2C(bytes);
				break;
			case ADD_ITEM_TO_BAG_G2C:
				recvAddItemToBag_G2C(bytes);
				break;
			case REMOVE_ITEM_FROM_BAG_G2C:
				recvRemoveItemFromBag_G2C(bytes);
				break;
			case UPDATE_ITEM_IN_BAG_G2C:
				recvUpdateItemInBag_G2C(bytes);
				break;
			
			case CLEAR_ITEMS_IN_EQUIP_G2C:
				recvClearItemsInEquip_G2C(bytes);
				break;
			case INIT_ITEMS_TO_EQUIP_G2C:
				recvInitItemsToEquip_G2C(bytes);
				break;
			case ADD_ITEM_TO_EQUIP_G2C:
				recvAddItemToEquip_G2C(bytes);
				break;
			case REMOVE_ITEM_FROM_EQUIP_G2C:
				recvRemoveItemFromEquip_G2C(bytes);
				break;
			case UPDATE_ITEM_IN_EQUIP_G2C:
				recvUpdateItemInEquip_G2C(bytes);
				break;
			}
		}
		
		/** recv( game向client通知物品操作异常 ) */
		private static function recvOperException_G2C(bytes:ByteArray):void
		{
			var errCode:int = EndianFacade.readByte(bytes);
			var containerType:int = EndianFacade.readByte(bytes);
			Game.alert.show( GoodsOperEnum.errMsg(errCode, containerType) );
		}
		
		//=========================Bag=====↓=====================================
		
		/** recv( game向client通知背包已清空 ) */
		private static function recvClearItemsInBag_G2C(bytes:ByteArray):void
		{
			Game.goods.bag.clearItems();
		}
		
		/** recv( game向client发送初始化背包物品 ) */
		private static function recvInitItemsToBag_G2C(bytes:ByteArray):void
		{
			var data:StGoodsContainer = new StGoodsContainer();
			data.read(bytes);
			Game.goods.bag.importData(data);
		}
		
		/** recv( game向client通知添加物品到背包 ) */
		private static function recvAddItemToBag_G2C(bytes:ByteArray):void
		{
			var info:GoodsInfo = GoodsFactory.readInfo(bytes);
			Game.goods.bag.addItem(info);
		}
		
		/** recv( game向client通知移除物品从背包 ) */
		private static function recvRemoveItemFromBag_G2C(bytes:ByteArray):void
		{
			var itemUid:Long = EndianFacade.readLong(bytes);
			Game.goods.bag.removeItem(itemUid);
		}
		
		/** recv( game向client通知更新背包物品 ) */
		private static function recvUpdateItemInBag_G2C(bytes:ByteArray):void
		{
			var info:GoodsInfo = GoodsFactory.readInfo(bytes);
			Game.goods.bag.updateItem(info);
		}
		
		//=========================Equip=====↓=====================================
		
		/** recv( game向client通知装备已清空 ) */
		private static function recvClearItemsInEquip_G2C(bytes:ByteArray):void
		{
			Game.goods.equip.clearItems();
		}
		
		/** recv( game向client发送初始化装备 ) */
		private static function recvInitItemsToEquip_G2C(bytes:ByteArray):void
		{
			var data:StGoodsContainer = new StGoodsContainer();
			data.read(bytes);
			Game.goods.equip.importData(data);
		}
		
		/** recv( game向client通知穿上装备 ) */
		private static function recvAddItemToEquip_G2C(bytes:ByteArray):void
		{
			var info:GoodsInfo = GoodsFactory.readInfo(bytes);
			Game.goods.equip.addItem(info);
		}
		
		/** recv( game向client通知脱下装备 ) */
		private static function recvRemoveItemFromEquip_G2C(bytes:ByteArray):void
		{
			var itemUid:Long = EndianFacade.readLong(bytes);
			Game.goods.equip.removeItem(itemUid);
		}
		
		/** recv( game向client通知更新装备物品 ) */
		private static function recvUpdateItemInEquip_G2C(bytes:ByteArray):void
		{
			var info:GoodsInfo = GoodsFactory.readInfo(bytes);
			Game.goods.equip.updateItem(info);
		}
		
		//=========================Xxxx=====↓=====================================
		
		/** send( client请求移动物品：从背包到背包 ) */
		public static function sendMoveItemBagToBagReq_C2G(srcUid:Long, tarInd:int):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, MOVE_ITEM_BAG_TO_BAG_REQ_C2G);
			msg.writeLong(srcUid);
			msg.writeInt(tarInd);
			msg.send();
		}
		
		/** send( client请求丢弃背包物品 ) */
		public static function sendDropItemFromBagReq_C2G(itemUid:Long):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, DROP_ITEM_FROM_BAG_REQ_C2G);
			msg.writeLong(itemUid);
			msg.send();
		}
		
		/** send( client请求穿上装备 ) */
		public static function sendPutOnEquipReq_C2G(srcUid:Long):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, PUT_ON_EQUIP_REQ_C2G);
			msg.writeLong(srcUid);
			msg.send();
		}
		
		/** send( client请求拆分背包物品 ) */
		public static function sendSplitItemInBagReq_C2G(itemUid:Long, spNum:int):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, SPLIT_ITEM_IN_BAG_REQ_C2G);
			msg.writeLong(itemUid);
			msg.writeInt(spNum);
			msg.send();
		}
		
		/** send( client请求脱下装备 ) */
		public static function sendTakeOffEquipReq_C2G(srcUid:Long, index:int):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, TAKE_OFF_EQUIP_REQ_C2G);
			msg.writeLong(srcUid);
			msg.writeInt(index);
			msg.send();
		}
		
		/** send( client请求使用物品 ) */
		public static function sendUseItemReq_C2G(itemUid:Long, useNum:int):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, USE_ITEM_REQ_C2G);
			msg.writeLong(itemUid);
			msg.writeInt(useNum);
			msg.send();
		}
		
	}
}