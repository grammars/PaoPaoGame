package jack911.pp.device
{
	import com.anstu.jcommon.log.Log;
	
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import jack911.pp.core.AbstractManage;
	import jack911.pp.core.Base;
	import jack911.pp.ui.View;
	
	public class DeviceManage extends AbstractManage
	{
		public function DeviceManage()
		{
			super();
		}
		
		/** 启动运行 */
		override public function startup():void
		{
			Log.debug("DeviceManage:startup()");
			Base.stage.addEventListener(KeyboardEvent.KEY_DOWN, keyDownHandler);
			Base.stage.addEventListener(KeyboardEvent.KEY_UP, keyUpHandler);
			startupComplete();
		}
		
		private function keyDownHandler(e:KeyboardEvent):void
		{
			
		}
		
		private function keyUpHandler(e:KeyboardEvent):void
		{
			switch(e.keyCode)
			{
			case Keyboard.X:
				View.gm.showOrHide();
				break;
			}
		}
	}
}