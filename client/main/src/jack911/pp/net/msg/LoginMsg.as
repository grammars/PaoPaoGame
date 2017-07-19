package jack911.pp.net.msg
{
	import com.anstu.jcommon.log.Log;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;
	import jack911.pp.net.MsgSender;
	import jack911.pp.net.dp.login.LoginResultDP;

	public class LoginMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.LOGIN_MSG;
		
		/** 客户端登录请求Client->Centre */
		private static const CLIENT_LOGIN_REQ:int = 1;
		/** 客户端登录响应Centre->Client */
		private static const CLIENT_LOGIN_RESP:int = 5;
		/** 客户端角色新生请求Client->Centre */
		private static const PLAYER_BORN_REQ:int = 11;
		
		/** 客户端下线命令Gateway->otherServers */
		private static const CLIENT_LOGOUT_CMD:int = 99;
		
		public function LoginMsg()
		{
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			case CLIENT_LOGIN_RESP:
				Base.cccid = head.cccid;
				recvClientLoginResp(bytes);
				break;
			}
		}
		
		/** send(客户端登录请求) */
		public static function sendClientLoginReq(username:String, password:String):void
		{
			var ms:MsgSender = MsgSender.toCentre(MAJOR_MID, CLIENT_LOGIN_REQ);
			ms.writeString(username);
			ms.writeString(password);
			ms.send();
		}
		
		/** recv(客户端登录响应) */
		private static function recvClientLoginResp(bytes:ByteArray):void
		{
			Log.debug("recvClientLoginResp");
			var dp:LoginResultDP = new LoginResultDP();
			dp.readFrom(bytes);
			Game.login.loginBack(dp);
		}
		
		/** send(客户端角色新生请求) */
		public static function sendPlayerBornReq(name:String):void
		{
			var ms:MsgSender = MsgSender.toCentre(MAJOR_MID, PLAYER_BORN_REQ);
			ms.writeString(name);
			ms.send();
		}
	}
}