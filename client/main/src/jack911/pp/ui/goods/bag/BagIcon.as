package jack911.pp.ui.goods.bag
{
	import jack911.pp.core.goods.GoodsInfo;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.core.Game;
	import jack911.pp.net.msg.GoodsMsg;
	
	import jack911.pp.ui.Drag;
	import jack911.pp.ui.DragAction;
	import jack911.pp.ui.DClick;
	import jack911.pp.ui.goods.GoodsIcon;
	
	public class BagIcon extends GoodsIcon
	{
		public function BagIcon()
		{
			super();
			canDrag(true);
			new DClick(this).setup(__click, __doubleClick);
		}
		
		override protected function doDrag():void
		{
			Drag.getInstance().startQ(this, DragAction.dragBagIcon, info);
		}
		
		private function __click(e:MouseEvent):void
		{
			BagIconMenu.getInstance().showFor(this);
		}
		
		private function __doubleClick(e:MouseEvent):void
		{
			Game.goods.useItem(this.info);
		}
		
	}
}