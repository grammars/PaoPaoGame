package jack911.pp.ui.skin.list
{
	import com.anstu.jui.skin.JListSkin;
	import jack911.pp.ui.skin.font.DefaultFont;
	import jack911.pp.ui.skin.scrollBar.DefaultScrollBarSkin;
	
	public class DefaultListSkin extends JListSkin
	{
		private static var _instance:DefaultListSkin;
		public static function get instance():DefaultListSkin
		{
			if(_instance == null) { _instance = new DefaultListSkin(); }
			return _instance;
		}
		
		public function DefaultListSkin()
		{
			super();
			base_ns = "fate";
			item_textFormat = DefaultFont.instance;
			scrollBarSkin = DefaultScrollBarSkin.instance;
		}
	}
}