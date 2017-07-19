package jack911.pp.db;

import jack911.pp.db.lobby.DbLobbyHandler;
import jack911.pp.db.login.DbLoginHandler;
import jack911.pp.db.msg.DbMsgHandler;
import jack911.pp.db.player.DbPlayerHandler;

public class Db
{
	public static DbMsgHandler msg = new DbMsgHandler();
	
	public static DbLoginHandler login = new DbLoginHandler();
	public static DbLobbyHandler lobby = new DbLobbyHandler();
	public static DbPlayerHandler player = new DbPlayerHandler();
	
	public static void run()
	{
		
	}
}
