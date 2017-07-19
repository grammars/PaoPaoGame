package jack911.pp.ui.goods.bag
{
	import com.anstu.jui.controls.JPanel;
	
	import jack911.pp.core.goods.GoodsInfo;
	import jack911.pp.core.goods.GoodsType;
	
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.core.Layer;
	import jack911.pp.net.msg.GoodsMsg;
	import jack911.pp.ui.View;
	import jack911.pp.ui.skin.pane.ModernPaneSkin;
	
	public class BagIconMenu extends JPanel
	{
		private static var instance:BagIconMenu;
		public static function getInstance():BagIconMenu
		{
			if(instance==null) { instance = new BagIconMenu(); }
			return instance;
		}
		
		private static const BTN_USE:String = "使用";
		private static const BTN_PUT_ON:String = "穿上";
		private static const BTN_SPLIT:String = "拆分";
		private static const BTN_DROP:String = "丢弃";
		private static const BTN_CANCEL:String = "取消";
		
		private var info:GoodsInfo;
		
		public function BagIconMenu()
		{
			super();
			initialize();
		}
		
		private function initialize():void
		{
			ModernPaneSkin.instance.apply(this);
		}
		
		public function showFor(icon:BagIcon):void
		{
			this.info = icon.info;
			var iconGP:Point = icon.localToGlobal(new Point(0,0));
			this.x = iconGP.x + 20;
			this.y = iconGP.y + 20;
			Layer.ui.addChild(this);
			Base.stage.addEventListener(MouseEvent.MOUSE_DOWN, __stageMouseUp);
			makeBtns();
		}
		
		private function makeBtns():void
		{
			this.clearContent();
			var btnTypes:Array;
			switch(this.info.cfg.type)
			{
			case GoodsType.SYSTEM:
				btnTypes = [BTN_USE, BTN_PUT_ON, BTN_SPLIT, BTN_DROP, BTN_CANCEL];
				break;
			case GoodsType.DRUG:
				btnTypes = [BTN_USE, BTN_SPLIT, BTN_DROP, BTN_CANCEL];
				break;
			case GoodsType.TASK:
				btnTypes = [BTN_USE, BTN_SPLIT, BTN_DROP, BTN_CANCEL];
				break;
			case GoodsType.EQUIP:
				btnTypes = [BTN_PUT_ON, BTN_DROP, BTN_CANCEL];
				break;
			case GoodsType.GEM:
				btnTypes = [BTN_USE, BTN_SPLIT, BTN_DROP, BTN_CANCEL];
				break;
			case GoodsType.GIFT:
				btnTypes = [BTN_USE, BTN_SPLIT, BTN_DROP, BTN_CANCEL];
				break;
			default:
				btnTypes = [];
				break;
			}
			for(var i:int = 0; i < btnTypes.length; i++)
			{
				var bt:String = btnTypes[i];
				var item:BagIconMenuItem = new BagIconMenuItem(bt);
				item.x = 10;
				item.y = 10 + i * 28;
				this.addChild(item);
			}
			this.setSize(120, btnTypes.length*28+20);
		}
		
		public function hide():void
		{
			this.info = null;
			if(this.parent) { this.parent.removeChild(this); }
			Base.stage.removeEventListener(MouseEvent.MOUSE_DOWN, __stageMouseUp);
		}
		
		private function __stageMouseUp(e:MouseEvent):void
		{
			hide();
		}
		
		/** 被BagIconMenuItem执行的 */
		public function execute(exeType:String):void
		{
			switch(exeType)
			{
			case BTN_USE:
				Game.goods.useItem(this.info);
				break;
			case BTN_PUT_ON:
				Game.goods.useItem(this.info);
				break;
			case BTN_SPLIT:
				View.split.open(this.info);
				break;
			case BTN_DROP:
				View.discard.open(this.info);
				break;
			case BTN_CANCEL:
				break;
			}
		}
		
	}
}