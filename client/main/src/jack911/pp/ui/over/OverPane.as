package jack911.pp.ui.over
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JLabel;
	import com.anstu.jui.controls.JPushButton;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.config.Cfg;
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.core.Layer;
	import jack911.pp.net.dp.game.GameResultDp;
	import jack911.pp.net.msg.LobbyMsg;
	import jack911.pp.ui.View;
	import jack911.pp.ui.ViewWnd;
	
	public class OverPane extends ViewWnd
	{
		private var rLabel:JLabel;
		private var roomLabel:JLabel;
		private var retBtn:JPushButton;
		
		public function OverPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("overPane");
			pane = uiPack.getCtrl("root");
			rLabel = uiPack.getLabel("rLabel");
			roomLabel = uiPack.getLabel("roomLabel");
			retBtn = uiPack.getPushButton("retBtn");
			
			canBringTop(true);
			putCentre();
			
			retBtn.addEventListener(MouseEvent.CLICK, __retBtn);
		}
		
		private function __retBtn(e:MouseEvent):void
		{
			Game.scene.exitScene();
		}
		
		public function setRusult(result:GameResultDp):void
		{
			rLabel.text = result.r.toString();
			roomLabel.text = Base.config.room.get(result.roomId).name;
		}
	}
}