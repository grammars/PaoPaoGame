package jack911.pp.ui.rank
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JLabel;
	import com.anstu.jui.controls.JPushButton;
	import com.anstu.jui.controls.JRadioButton;
	import com.anstu.jui.controls.JScrollPanel;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.ui.ViewWnd;
	
	public class RoomRankPane extends ViewWnd
	{
		private var scroll:JScrollPanel;
		private var dayTab:JRadioButton;
		private var weekTab:JRadioButton;
		private var payTab:JRadioButton;
		private var roomLabel:JLabel;
		private var closeBtn:JPushButton;
		
		//private var dayItems:
		
		public function RoomRankPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("roomRankPane");
			pane = uiPack.getCtrl("root");
			scroll = uiPack.getScrollPanel("scroll");
			dayTab = uiPack.getRadioButton("dayTab");
			weekTab = uiPack.getRadioButton("weekTab");
			payTab = uiPack.getRadioButton("payTab");
			roomLabel = uiPack.getLabel("roomLabel");
			closeBtn = uiPack.getPushButton("closeBtn");
			
			closeBtn.addEventListener(MouseEvent.CLICK, __closeBtn);
		}
		
		private function __closeBtn(e:MouseEvent):void
		{
			hide();
		}
		
		/** 加载排行榜数据 */
		public function loadData(roomId:int):void
		{
			
		}
	}
}