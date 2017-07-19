package jack911.pp.core
{
	import flash.display.Sprite;
	
	import jack911.pp.core.scene.GameScene;

	public class Layer
	{
		public static const scene:GameScene = new GameScene();
		public static const ui:Sprite = new Sprite();
		public static const tip:Sprite = new Sprite();
		
		public function Layer()
		{
		}
		
		public static function setup():void
		{
			Base.main.addChild(scene);
			Base.main.addChild(ui);
			Base.main.addChild(tip);
		}
		
		/** 欢迎模式 */
		public static const MODE_WELCOME:String = "welcome";
		/** 登录模式 */
		public static const MODE_LOGIN:String = "login";
		/** 大厅模式 */
		public static const MODE_LOBBY:String = "lobby";
		/** 游戏模式 */
		public static const MODE_GAME:String = "game";
		
		private static var _mode:String = MODE_WELCOME;
		public static function setMode(mode:String):void
		{
			_mode = mode;
			switch(mode)
			{
			case MODE_WELCOME:
				scene.visible = false;
				ui.visible = false;
				tip.visible = false;
				break;
			case MODE_LOGIN:
				scene.visible = false;
				ui.visible = true;
				tip.visible = true;
				break;
			case MODE_LOBBY:
				scene.visible = false;
				ui.visible = true;
				tip.visible = true;
				break;
			case MODE_GAME:
				scene.visible = true;
				ui.visible = true;
				tip.visible = true;
				break;
			}
		}
	}
}