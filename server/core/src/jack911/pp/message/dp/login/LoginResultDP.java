package jack911.pp.message.dp.login;

import jack911.pp.common.vo.UserAccount;
import jack911.pp.server.MsgUnit;

public class LoginResultDP
{
	/** 登录成功，可以获取UserAccount */
	public static final byte SUCCESS = 0;
	/** 失败:用户名或密码错误 */
	public static final byte USER_PWD_ERR = 1;
	
	public byte result;
	public String token;
	public UserAccount account;
	
	public void writeTo(MsgUnit msg)
	{
		msg.writeByte(result);
		msg.writeString(token);
		if(result == SUCCESS) 
		{ 
			account.writeTo(msg); 
		}
	}
	
	public void readFrom(MsgUnit msg)
	{
		result = msg.readByte();
		token = msg.readString();
		if(result == SUCCESS)
		{
			account = new UserAccount();
			account.readFrom(msg);
		}
	}
	
}
