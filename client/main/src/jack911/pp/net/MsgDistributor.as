package jack911.pp.net
{
	import flash.utils.ByteArray;
	
	import jack911.pp.net.msg.GmMsg;
	import jack911.pp.net.msg.GoodsMsg;
	import jack911.pp.net.msg.LobbyMsg;
	import jack911.pp.net.msg.LoginMsg;
	import jack911.pp.net.msg.PlayerMsg;
	import jack911.pp.net.msg.SceneMsg;
	import jack911.pp.net.msg.SkillMsg;

	public class MsgDistributor
	{
		/** [登录]消息 */
		public static const LOGIN_MSG:int = 2;
		/** [大厅]消息 */
		public static const LOBBY_MSG:int = 3;
		/** [角色]消息 */
		public static const PLAYER_MSG:int = 4;
		/** [场景]消息 */
		public static const SCENE_MSG:int = 5;
		/** [排行榜]消息 */
		public static const RANK_MSG:int = 6;
		/** [物品]消息 */
		public static const GOODS_MSG:int = 7;
		/** [技能]消息 */
		public static const SKILL_MSG:int = 8;
		/** [GM]消息 */
		public static const GM_MSG:int = 99;
		
		public function MsgDistributor()
		{
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.majorId)
			{
			case LOGIN_MSG:
				LoginMsg.handle(head, bytes);
				break;
			case LOBBY_MSG:
				LobbyMsg.handle(head, bytes);
				break;
			case PLAYER_MSG:
				PlayerMsg.handle(head, bytes);
				break;
			case SCENE_MSG:
				SceneMsg.handle(head, bytes);
				break;
			case RANK_MSG:
				//
				break;
			case GOODS_MSG:
				GoodsMsg.handle(head, bytes);
				break;
			case SKILL_MSG:
				SkillMsg.handle(head, bytes);
				break;
			case GM_MSG:
				GmMsg.handle(head, bytes);
				break;
			}
		}
	}
}