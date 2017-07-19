package jack911.pp.ui.oper
{
	import flash.display.Shape;
	import flash.events.MouseEvent;
	
	import jack911.pp.component.EffectFilters;
	import jack911.pp.core.Game;
	import jack911.pp.net.msg.SceneMsg;
	import jack911.pp.ui.goods.GoodsIcon;

	public class OperIcon extends GoodsIcon
	{
		public function OperIcon()
		{
			canDrag(false);
			this.addEventListener(MouseEvent.CLICK, __click);
		}
		
		override protected function paintNum():void
		{
			//不画数字
		}
		
		private function __click(e:MouseEvent):void
		{
			//if(enabled)
			{
				SceneMsg.useEquipReq(info.cfgId);
			}
		}
		
		private var enabled:Boolean = true;
		public function setEnabled(value:Boolean):void
		{
			enabled = value;
			if(enabled)
			{
				this.filters = [];
			}
			else
			{
				this.filters = [EffectFilters.GREY];
			}
		}
	}
}