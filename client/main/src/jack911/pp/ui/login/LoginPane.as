package jack911.pp.ui.login
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JInputText;
	import com.anstu.jui.controls.JLabel;
	import com.anstu.jui.controls.JPushButton;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.core.Base;
	import jack911.pp.net.msg.LoginMsg;
	import jack911.pp.ui.ViewWnd;
	
	public class LoginPane extends ViewWnd
	{
		private var usernameInput:JInputText;
		private var passwordInput:JInputText;
		private var tipLabel:JLabel;
		private var loginBtn:JPushButton;
		
		public function LoginPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("loginPane");
			pane = uiPack.getCtrl("root");
			usernameInput = uiPack.getInputText("usernameInput");
			passwordInput = uiPack.getInputText("passwordInput");
			tipLabel = uiPack.getLabel("tipLabel");
			loginBtn = uiPack.getPushButton("loginBtn");
			canDrag(true);
			canBringTop(true);
			putCentre();
			
			loginBtn.addEventListener(MouseEvent.CLICK, __loginBtn);
		}
		
		private function __loginBtn(e:MouseEvent):void
		{
			Base.net.connect();
		}
		
		public function getUsername():String
		{
			return usernameInput.text;
		}
		
		public function getPassword():String
		{
			return passwordInput.text;
		}
		
		public function setTip(tip:String):void
		{
			tipLabel.text = tip;
		}
		
	}
}