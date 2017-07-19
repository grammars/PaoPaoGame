package jack911.pp.core
{
	import jack911.pp.core.alert.AlertCtrl;
	import jack911.pp.core.ape.ApeCtrl;
	import jack911.pp.core.chat.ChatCtrl;
	import jack911.pp.core.goods.GoodsCtrl;
	import jack911.pp.core.lobby.LobbyCtrl;
	import jack911.pp.core.login.LoginCtrl;
	import jack911.pp.core.map.MapCtrl;
	import jack911.pp.core.player.PlayerCtrl;
	import jack911.pp.core.scene.SceneCtrl;

	public class Game
	{
		/** 登录控制器 */
		public static const login:LoginCtrl = new LoginCtrl();
		/** 大厅控制器 */
		public static const lobby:LobbyCtrl = new LobbyCtrl();
		/** 警告控制器 */
		public static const alert:AlertCtrl = new AlertCtrl();
		/** 场景控制器 */
		public static const scene:SceneCtrl = new SceneCtrl();
		/** ape控制器 */
		public static const ape:ApeCtrl = new ApeCtrl();
		/** map控制器 */
		public static const map:MapCtrl = new MapCtrl();
		/** 物品控制器 */
		public static const goods:GoodsCtrl = new GoodsCtrl();
		/** 玩家控制器 */
		public static const player:PlayerCtrl = new PlayerCtrl();
		/** 聊天控制器 */
		public static const chat:ChatCtrl = new ChatCtrl();
		
		public function Game()
		{
		}
		
		public static function initialize():void
		{
			map.initialize();
			goods.initialize();
		}
	}
}