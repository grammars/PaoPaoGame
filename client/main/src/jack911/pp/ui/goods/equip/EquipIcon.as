package jack911.pp.ui.goods.equip
{	
	import flash.events.MouseEvent;
	
	import jack911.pp.net.msg.GoodsMsg;
	
	import jack911.pp.ui.Drag;
	import jack911.pp.ui.DragAction;
	import jack911.pp.ui.goods.GoodsIcon;
	
	public class EquipIcon extends GoodsIcon
	{
		public function EquipIcon()
		{
			super();
			canDrag(true);
			this.doubleClickEnabled = true;
			this.addEventListener(MouseEvent.DOUBLE_CLICK, __doubleClick);
		}
		
		override protected function doDrag():void
		{
			Drag.getInstance().startQ(this, DragAction.dragEquipIcon, info);
		}
		
		private function __doubleClick(e:MouseEvent):void
		{
			GoodsMsg.sendTakeOffEquipReq_C2G(info.uid, -1);
		}
		
	}
}