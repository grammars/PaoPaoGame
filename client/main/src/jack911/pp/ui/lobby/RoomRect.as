package jack911.pp.ui.lobby
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JLabel;
	import com.anstu.jui.controls.JPanel;
	import com.anstu.jui.controls.JPushButton;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.config.table.RoomTblUnit;
	import jack911.pp.net.msg.LobbyMsg;
	import jack911.pp.ui.View;
	import jack911.pp.ui.ViewWnd;
	
	public class RoomRect extends ViewWnd
	{
		private var imgCtn:JPanel;
		private var titleLabel:JLabel;
		private var descLabel:JLabel;
		private var enterBtn:JPushButton;
		private var rankBtn:JPushButton;
		
		private var cfg:RoomTblUnit;
		
		public function RoomRect()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("roomRect");
			pane = uiPack.getCtrl("root");
			imgCtn = uiPack.getPanel("imgCtn");
			titleLabel = uiPack.getLabel("titleLabel");
			descLabel = uiPack.getLabel("descLabel");
			enterBtn = uiPack.getPushButton("enterBtn");
			rankBtn = uiPack.getPushButton("rankBtn");
			enterBtn.addEventListener(MouseEvent.CLICK, __enterBtn);
			rankBtn.addEventListener(MouseEvent.CLICK, __rankBtn);
		}
		
		private function __enterBtn(e:MouseEvent):void
		{
			LobbyMsg.sendEnterRoomReq(cfg.id);
		}
		
		private function __rankBtn(e:MouseEvent):void
		{
			View.roomRank.loadData(cfg.id);
			View.roomRank.show();
		}
		
		public function present(cfg:RoomTblUnit):void
		{
			this.cfg = cfg;
			titleLabel.text = cfg.name + "("+cfg.levMin+"-"+cfg.levMax+")";
			descLabel.text = cfg.desc + "步数：" + cfg.stepNum + " 门票价格：" + cfg.ticketPrice + "泡币";
		}
	}
}