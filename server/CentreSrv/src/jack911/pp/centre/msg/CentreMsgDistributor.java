package jack911.pp.centre.msg;

import jack911.pp.centre.Centre;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.ServerMsg;
import jack911.pp.server.MsgUnit;

public class CentreMsgDistributor implements MsgDistributor
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
			Centre.msg.login.handle(head, msg);
			break;
		case LOBBY_MSG:
			Centre.msg.lobby.handle(head, msg);
			break;
		case PLAYER_MSG:
			Centre.msg.player.handle(head, msg);
			break;
		case SCENE_MSG:
			Centre.msg.scene.handle(head, msg);
			break;
		case GOODS_MSG:
			Centre.msg.goods.handle(head, msg);
			break;
		case GM_MSG:
			Centre.msg.gm.handle(head, msg);
			break;
		}
	}

}