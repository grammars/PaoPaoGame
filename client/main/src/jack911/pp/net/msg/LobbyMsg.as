package jack911.pp.net.msg
{
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Game;
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;
	import jack911.pp.net.MsgSender;
	import jack911.pp.net.dp.lobby.EnterRoomResultDp;
	import jack911.pp.net.dp.lobby.LobbyInitDp;

	public class LobbyMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.LOBBY_MSG;
		
		/** 大厅初始化数据de请求Client->Centre */
		protected static const LOBBY_INIT_DATA_REQ:int = 1;
		/** 大厅初始化数据de返回Centre->Client */
		protected static const LOBBY_INIT_DATA_RESP:int = 2;
		/** 选择进入房间de请求Client->Centre */
		protected static const ENTER_ROOM_REQ:int = 3;
		/** 选择进入房间de返回Centre->Client */
		protected static const ENTER_ROOM_RESP:int = 4;
		
		public function LobbyMsg()
		{
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			case LOBBY_INIT_DATA_RESP:
				recvLobbyInitDataResp(bytes);
				break;
			case ENTER_ROOM_RESP:
				recvEnterRoomResp(bytes);
				break;
			}
		}
		
		/** send(大厅初始化数据de请求Client->Centre) */
		public static function sendLobbyInitDataReq():void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, LOBBY_INIT_DATA_REQ);
			msg.send();
		}
		
		/** recv(大厅初始化数据de返回Centre->Client) */
		private static function recvLobbyInitDataResp(bytes:ByteArray):void
		{
			var dp:LobbyInitDp = new LobbyInitDp();
			dp.readFrom(bytes);
			trace("大厅初始化数据de返回Centre->Client" + dp.debug);
			Game.lobby.initLobby(dp);
		}
		
		/** send(选择进入房间de请求Client->Centre) */
		public static function sendEnterRoomReq(roomId:int):void
		{
			var msg:MsgSender = MsgSender.toCentre(MAJOR_MID, ENTER_ROOM_REQ);
			msg.writeInt(roomId);
			msg.send();
		}
		
		/** recv(选择进入房间de返回Centre->Client) */
		private static function recvEnterRoomResp(bytes:ByteArray):void
		{
			var dp:EnterRoomResultDp = new EnterRoomResultDp();
			dp.readFrom(bytes);
			Game.lobby.handleEnterRoom(dp);
		}
		
	}
}