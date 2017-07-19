package jack911.pp.ui.oper
{
	import com.anstu.jui.build.JFactory;
	
	import flash.display.Sprite;
	import flash.events.Event;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.core.goods.GoodsInfo;
	import jack911.pp.ui.ViewWnd;
	
	public class OperPane extends ViewWnd
	{
		private var iconCtn:Sprite = new Sprite();
		private var icons:Vector.<OperIcon> = new Vector.<OperIcon>();
		
		public function OperPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("operPane");
			pane = uiPack.getCtrl("root");
			
			canDrag(false);
			canBringTop(false);
			
			iconCtn.x = 23;
			iconCtn.y = 23;
			pane.addChild(iconCtn);
		}
		
		override public function putDefaultPos(event:Event=null):void
		{
			if(pane)
			{ 
				pane.x = (Base.stage.stageWidth - pane.width)/2;
				pane.y = Base.stage.stageHeight - pane.height; 
			}
		}
		
		override public function show():void
		{
			super.show();
			iconCtn.removeChildren();
			icons.length = 0;
			for(var i:int = 0; i < Game.goods.equip.items.length; i++)
			{
				var gi:GoodsInfo = Game.goods.equip.items[i];
				var icon:OperIcon = new OperIcon();
				icon.setup(gi);
				icon.x = i * 70;
				iconCtn.addChild(icon);
				icons.push(icon);
			}
		}
		
		/** 禁用指定装备 */
		public function disableEquip(equipId:int):void
		{
			for(var i:int = 0; i < icons.length; i++)
			{
				var icon:OperIcon = icons[i];
				if(icon.info.cfgId == equipId)
				{
					icon.setEnabled(false);	
					break;
				}
			}
		}
	}
}