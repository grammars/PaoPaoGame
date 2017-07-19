package jack911.pp.ui.skin.pane
{
	import com.anstu.jui.skin.JPanelSkin;
	
	import jack911.pp.ui.skin.SkinCfg;
	
	public class ModernPaneSkin extends JPanelSkin
	{
		private static var _instance:ModernPaneSkin;
		public static function get instance():ModernPaneSkin
		{
			if(_instance == null) { _instance = new ModernPaneSkin(); }
			return _instance;
		}
		
		public function ModernPaneSkin()
		{
			super();
			background_ns = SkinCfg.NS;
			background_cls = "bg_modern$png";
			background_opt = null;
		}
	}
}