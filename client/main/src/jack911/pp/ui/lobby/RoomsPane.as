package jack911.pp.ui.lobby
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JScrollPanel;
	
	import flash.events.Event;
	import flash.geom.Point;
	
	import jack911.pp.core.Base;
	import jack911.pp.ui.ViewWnd;
	
	public class RoomsPane extends ViewWnd
	{
		private var roomsScr:JScrollPanel;
		
		public function RoomsPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("roomsPane");
			pane = uiPack.getCtrl("root");
			roomsScr = uiPack.getScrollPanel("roomsScr");
		}
		
		/** 放到默认位置 */
		override public function putDefaultPos(event:Event=null):void
		{
			if(pane)
			{
				pane.x = (Base.stage.stageWidth - pane.width);
				pane.y = (Base.stage.stageHeight - pane.height) / 2;
			}
		}
		
		/** 清理 */
		public function clear():void
		{
			rectPos.x = rectPos.y = 10;
			roomsScr.clearContent();
		}
		
		private var rectPos:Point = new Point(10, 10);
		/** putRect */
		public function putRect(rect:RoomRect):void
		{
			rect.setParent(roomsScr);
			rect.setX( rectPos.x );
			rect.setY( rectPos.y );
			rectPos.y += 130;
		}
		
	}
}