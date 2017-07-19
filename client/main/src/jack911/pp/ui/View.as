package jack911.pp.ui
{
	import jack911.pp.ui.chat.ChatboxPane;
	import jack911.pp.ui.face.FacePane;
	import jack911.pp.ui.gm.GmFrame;
	import jack911.pp.ui.goods.DiscardPane;
	import jack911.pp.ui.goods.GoodsFrame;
	import jack911.pp.ui.goods.SplitPane;
	import jack911.pp.ui.lobby.RoomsPane;
	import jack911.pp.ui.login.BornPane;
	import jack911.pp.ui.login.LoginPane;
	import jack911.pp.ui.oper.OperPane;
	import jack911.pp.ui.over.OverPane;
	import jack911.pp.ui.rank.RoomRankPane;
	import jack911.pp.ui.self.SelfFrame;
	import jack911.pp.ui.status.StatusPane;

	public class View
	{
		/** 登录面板 */
		public static var login:LoginPane;
		/** 创建角色面板 */
		public static var born:BornPane;
		/** 选房间面板 */
		public static var rooms:RoomsPane;
		/** 状态面板 */
		public static var status:StatusPane;
		/** 游戏结束面板 */
		public static var over:OverPane;
		/** 我的属性窗体 */
		public static var self:SelfFrame;
		/** 背包道具窗体 */
		public static var goods:GoodsFrame;
		/** 拆分物品窗体 */
		public static var split:SplitPane;
		/** 丢弃物品窗体 */
		public static var discard:DiscardPane;
		/** 聊天盒子面板 */
		public static var chatbox:ChatboxPane;
		/** face面板 */
		public static var face:FacePane;
		/** 操作栏面板 */
		public static var oper:OperPane;
		/** 房间排行榜 */
		public static var roomRank:RoomRankPane;
		/** GM窗体 */
		public static var gm:GmFrame;
		
		public function View()
		{
		}
		
		public static function setup():void
		{
			login = new LoginPane();
			born = new BornPane();
			rooms = new RoomsPane();
			status = new StatusPane();
			over = new OverPane();
			self = new SelfFrame();
			goods = new GoodsFrame();
			split = new SplitPane();
			discard = new DiscardPane();
			chatbox = new ChatboxPane();
			face = new FacePane();
			oper = new OperPane();
			roomRank = new RoomRankPane();
			gm = new GmFrame();
		}
	}
}