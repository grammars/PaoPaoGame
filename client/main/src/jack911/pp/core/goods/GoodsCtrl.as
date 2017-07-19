package jack911.pp.core.goods
{
	import jack911.pp.core.goods.bag.BagContainer;
	import jack911.pp.core.goods.equip.EquipContainer;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.core.Base;
	import jack911.pp.net.msg.GoodsMsg;
	import jack911.pp.ui.View;
	
	import jack911.pp.core.goods.bag.BagHandler;
	import jack911.pp.core.goods.equip.EquipHandler;
	
	import jack911.pp.ui.Drag;
	import jack911.pp.ui.DragAction;

	public class GoodsCtrl
	{
		/** 背包 */
		public var bag:BagContainer = new BagContainer();
		/** 装备 */
		public var equip:EquipContainer = new EquipContainer();
		
		/** 背包处理 */
		public var bagHandler:BagHandler = new BagHandler();
		/** 装备处理 */
		public var equipHandler:EquipHandler = new EquipHandler();
		
		public function GoodsCtrl()
		{
		}
		
		public function initialize():void
		{
			Base.stage.addEventListener(MouseEvent.MOUSE_UP, __dropItemHandler);
		}
		
		/** 丢物品处理 */
		private function __dropItemHandler(e:MouseEvent):void
		{
			if( Drag.getInstance().isDoing(DragAction.dragBagIcon) )
			{
				var srcBag:GoodsInfo = Drag.getInstance().getData() as GoodsInfo;
				View.discard.open(srcBag);
			}
			Drag.getInstance().dispose();
		}
		
		/** 请求使用物品 */
		public function useItem(info:GoodsInfo, useNum:int=1):void
		{
			GoodsMsg.sendUseItemReq_C2G(info.uid, useNum);
		}
	}
}