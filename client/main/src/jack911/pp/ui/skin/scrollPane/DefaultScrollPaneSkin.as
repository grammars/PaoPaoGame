package jack911.pp.ui.skin.scrollPane
{
	import com.anstu.jui.skin.JScrollPanelSkin;
	
	import jack911.pp.ui.skin.SkinCfg;
	import jack911.pp.ui.skin.scrollBar.DefaultScrollBarSkin;
	
	public class DefaultScrollPaneSkin extends JScrollPanelSkin
	{
		private static var _instance:DefaultScrollPaneSkin;
		public static function get instance():DefaultScrollPaneSkin
		{
			if(_instance == null) { _instance = new DefaultScrollPaneSkin(); }
			return _instance;
		}
		
		
		public function DefaultScrollPaneSkin()
		{
			super();
			base_ns = SkinCfg.NS;
			background_cls = "base_transparant$png";
			scrollBarSkin = DefaultScrollBarSkin.instance;
		}
		
	}
}