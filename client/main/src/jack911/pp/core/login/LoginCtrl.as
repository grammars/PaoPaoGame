package jack911.pp.core.login
{
	import com.anstu.jcommon.def.Macro;
	import com.anstu.jcommon.utils.StringUtils;
	
	import jack911.pp.core.vo.UserAccount;
	import jack911.pp.net.dp.login.LoginResultDP;
	import jack911.pp.net.msg.LobbyMsg;
	import jack911.pp.net.msg.LoginMsg;
	import jack911.pp.net.msg.PlayerMsg;
	import jack911.pp.ui.View;

	public class LoginCtrl
	{
		private var token:String;//登录令牌
		private var account:UserAccount;
		
		public function LoginCtrl()
		{
		}
		
		/** 发送用户名密码来登录 */
		public function tryLogin():void
		{
			var u:String = View.login.getUsername();
			var p:String = View.login.getPassword();
			LoginMsg.sendClientLoginReq(u, p);
		}
		
		/** 处理登录返回结果 */
		public function loginBack(dp:LoginResultDP):void
		{
			if(dp.result == LoginResultDP.SUCCESS)
			{
				this.token = dp.token;
				this.account = dp.account;
				View.login.hide();
				if( dp.account.canCreatePlayer() )
				{
					//新玩家
					View.born.show();
				}
				else
				{
					//老玩家
					PlayerMsg.sendPlayerDataInitReq();
					LobbyMsg.sendLobbyInitDataReq();
				}
			}
			else if(dp.result == LoginResultDP.USER_PWD_ERR)
			{
				View.login.setTip("用户名或密码错误");
			}
		}
		
		/** 发送角色新生请求 */
		public function tryBorn(name:String):void
		{
			if(this.account == null)
			{
				View.born.hide();
				View.login.show();
				View.login.setTip("请先登录!");
				return;
			}
			LoginMsg.sendPlayerBornReq(name);
		}
	}
}