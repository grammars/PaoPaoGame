package jack911.pp.ui.self
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JImage;
	import com.anstu.jui.controls.JLabel;
	
	import jack911.pp.ui.ViewWnd;
	
	public class SelfFrame extends ViewWnd
	{
		private var nameLabel:JLabel;
		private var levelLabel:JLabel;
		
		public function SelfFrame()
		{
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("selfFrame");
			pane = uiPack.getCtrl("root");
			nameLabel = uiPack.getLabel("nameLabel");
			levelLabel = uiPack.getLabel("levelLabel");
			
			canDrag(true);
			canBringTop(true);
		}
		
		public function updateName(name:String):void
		{
			nameLabel.text = name;
		}
		
		public function updateLevel(level:int):void
		{
			levelLabel.text = level + "级";
		}
	}
}