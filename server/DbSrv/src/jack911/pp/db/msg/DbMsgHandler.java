package jack911.pp.db.msg;

import jack911.pp.db.msg.content.LobbyMsg4Db;
import jack911.pp.db.msg.content.LoginMsg4Db;
import jack911.pp.db.msg.content.PlayerMsg4Db;

public class DbMsgHandler
{
	public LoginMsg4Db login = new LoginMsg4Db();
	public LobbyMsg4Db lobby = new LobbyMsg4Db();
	public PlayerMsg4Db player = new PlayerMsg4Db();
}
