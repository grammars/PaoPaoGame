package jack911.pp.ui.skin.pane
{
	import com.anstu.jui.assets.JResource;
	import com.anstu.jui.skin.JPanelSkin;
	
	import jack911.pp.ui.skin.SkinCfg;
	
	public class FrameBackPaneSkin extends JPanelSkin
	{
		private static var _instance:FrameBackPaneSkin;
		public static function get instance():FrameBackPaneSkin
		{
			if(_instance == null) { _instance = new FrameBackPaneSkin(); }
			return _instance;
		}
		
		public function FrameBackPaneSkin()
		{
			super();
			background_ns = SkinCfg.NS;
			background_cls = "bg_frameBack$png";
			background_opt = JResource.OPT_TILE_SINGLE;
		}
	}
}