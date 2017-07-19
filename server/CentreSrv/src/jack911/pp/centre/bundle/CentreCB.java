package jack911.pp.centre.bundle;

import jack911.pp.centre.player.Player;
import jack911.pp.common.bundle.ClientBundle;
import jack911.pp.common.vo.UserAccount;

public class CentreCB extends ClientBundle
{
	/** 用户账号 */
	private UserAccount account;
	
	public UserAccount getAccount()
	{
		return account;
	}

	public void setAccount(UserAccount account)
	{
		this.account = account;
	}
	
	public Player player = new Player(this);
	
	/** 保存到数据库 */
	public void save()
	{
		player.saveToDb();
	}
}
