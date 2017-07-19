package jack911.pp.game.msg;

import jack911.pp.game.Game;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.ServerMsg;
import jack911.pp.server.MsgUnit;

public class GameMsgDistributor implements MsgDistributor
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
			Game.msg.login.handle(head, msg);
			break;
		case LOBBY_MSG:
			Game.msg.lobby.handle(head, msg);
			break;
		case SCENE_MSG:
			Game.msg.scene.handle(head, msg);
			break;
		case PLAYER_MSG:
			Game.msg.player.handle(head, msg);
			break;
		case SKILL_MSG:
			Game.msg.skill.handle(head, msg);
			break;
		case GM_MSG:
			Game.msg.gm.handle(head, msg);
			break;
		}
	}

}
