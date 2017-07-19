package jack911.pp.net.dp.login
{
	import com.anstu.jsock.utils.EndianFacade;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.vo.UserAccount;

	public class LoginResultDP
	{
		/** 登录成功，可以获取UserAccount */
		public static const SUCCESS:int = 0;
		/** 失败:用户名或密码错误 */
		public static const USER_PWD_ERR:int = 1;
		
		public var result:int;
		public var token:String;
		public var account:UserAccount;
		
		public function LoginResultDP()
		{
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			result = EndianFacade.readByte(bytes);
			token = EndianFacade.readString(bytes);
			if(result == SUCCESS)
			{
				account = new UserAccount();
				account.readFrom(bytes);
			}
		}
		
	}
}