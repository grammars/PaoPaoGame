package jack911.pp.ui.face
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JLabel;
	
	import flash.events.Event;
	
	import jack911.pp.core.vo.PlayerData;
	import jack911.pp.ui.ViewWnd;
	
	public class FacePane extends ViewWnd
	{
		private var nameLabel:JLabel;
		private var levelLabel:JLabel;
		private var cashLabel:JLabel;
		
		public function FacePane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("facePane");
			pane = uiPack.getCtrl("root");
			nameLabel = uiPack.getLabel("nameLabel");
			levelLabel = uiPack.getLabel("levelLabel");
			cashLabel = uiPack.getLabel("cashLabel");
			
			canDrag(true);
			canBringTop(true);
		}
		
		/** 放到默认位置 */
		override public function putDefaultPos(event:Event=null):void
		{
			if(pane)
			{
				pane.x = 0;
				pane.y = 0;
			}
		}
		
		public function updatePlayerData(pd:PlayerData):void
		{
			nameLabel.text = pd.name;
			levelLabel.text = "Lv." + pd.level;
			cashLabel.text = pd.cash + " 泡币";
		}
	}
}