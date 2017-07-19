package jack911.pp.db.msg;

import jack911.pp.db.Db;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.ServerMsg;
import jack911.pp.server.MsgUnit;

public class DbMsgDistributor implements MsgDistributor
{

	@Override
	public void handle(MsgUnit msg)
	{
		MsgHead head = new MsgHead().readFrom(msg);
		switch(head.majorId)
		{
		case SERVER_MSG:
			ServerMsg.handle(head, msg);
			break;
		case LOGIN_MSG:
			Db.msg.login.handle(head, msg);
			break;
		case LOBBY_MSG:
			Db.msg.lobby.handle(head, msg);
			break;
		case PLAYER_MSG:
			Db.msg.player.handle(head, msg);
			break;
		}
	}

}