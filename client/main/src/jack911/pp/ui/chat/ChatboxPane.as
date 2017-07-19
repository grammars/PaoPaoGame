package jack911.pp.ui.chat
{
	import com.anstu.jui.build.JFactory;
	
	import flash.events.Event;
	
	import jack911.pp.core.Base;
	import jack911.pp.ui.ViewWnd;
	
	public class ChatboxPane extends ViewWnd
	{
		public function ChatboxPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("chatboxPane");
			pane = uiPack.getCtrl("root");
			//closeBtn = uiPack.getPushButton("closeBtn");
			
			canDrag(true);
			canBringTop(true);
		}
		
		/** 放到默认位置 */
		override public function putDefaultPos(event:Event=null):void
		{
			if(pane)
			{
				pane.x = 0;
				pane.y = Base.stage.stageHeight - pane.height;
			}
		}
	}
}