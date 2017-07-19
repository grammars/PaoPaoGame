package jack911.pp.ui.login
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JInputText;
	import com.anstu.jui.controls.JLabel;
	import com.anstu.jui.controls.JPushButton;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.ui.ViewWnd;
	
	public class BornPane extends ViewWnd
	{
		private var nameInput:JInputText;
		private var goBtn:JPushButton;
		private var msgLabel:JLabel;
		
		public function BornPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("bornPane");
			pane = uiPack.getCtrl("root");
			nameInput = uiPack.getInputText("nameInput");
			goBtn = uiPack.getPushButton("goBtn");
			msgLabel = uiPack.getLabel("msgLabel");
			canBringTop(true);
			putCentre();
			
			goBtn.addEventListener(MouseEvent.CLICK, __goBtn);
		}
		
		private function __goBtn(e:MouseEvent):void
		{
			var playerName:String = nameInput.text;
			Game.login.tryBorn(playerName);
		}
		
		public function warn(msg:String):void
		{
			msgLabel.text = msg;
		}
		
	}
}