package jack911.pp.common.vo;

import jack911.pp.server.MsgUnit;

public class UserAccount
{
	/** 账户的唯一Id */
	public Long uid;
	/** 用户名 */
	public String username;
	/** 密码 */
	public String password;
	/** 玩家uid */
	public Long playerUid;
	
	/** 是否可以创建角色 */
	public boolean canCreatePlayer()
	{
		if(playerUid == null || playerUid == 0) { return true; }
		return false;
	}
	
	public void writeTo(MsgUnit msg)
	{
		if(this.playerUid == null) { this.playerUid = 0l; }
		msg.writeLong(this.uid);
		msg.writeString(this.username);
		msg.writeString(this.password);
		msg.writeLong(this.playerUid);
	}
	
	public void readFrom(MsgUnit msg)
	{
		this.uid = msg.readLong();
		this.username = msg.readString();
		this.password = msg.readString();
		this.playerUid = msg.readLong();
	}
	
	@Override
	public String toString()
	{
		return "UserAccount [username=" + username + ", password=" + password + ", playerUid=" + playerUid + "]";
	}
}
