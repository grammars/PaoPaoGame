package jack911.pp.core.vo
{
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;

	public class UserAccount
	{
		/** uid */
		public var uid:Long;
		/** 用户名 */
		public var username:String;
		/** 密码 */
		public var password:String;
		/** 玩家uid */
		public var playerUid:Long;
		
		public function UserAccount()
		{
		}
		
		/** 是否可以创建角色 */
		public function canCreatePlayer():Boolean
		{
			if(playerUid == null || playerUid.toNumber() == 0) { return true; }
			return false;
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			this.uid = EndianFacade.readLong(bytes);
			this.username = EndianFacade.readString(bytes);
			this.password = EndianFacade.readString(bytes);
			this.playerUid = EndianFacade.readLong(bytes);
		}
		
		public function toString():String
		{
			return "UserAccount [uid=" + uid + "username=" + username + ", password=" + password + ", playerId=" + playerUid + "]";
		}
	}
}