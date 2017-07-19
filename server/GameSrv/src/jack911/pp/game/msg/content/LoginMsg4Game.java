package jack911.pp.game.msg.content;

import org.apache.log4j.Logger;

import jack911.pp.game.Game;
import jack911.pp.game.bundle.GameCB;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.LoginMsg;
import jack911.pp.server.MsgUnit;

public class LoginMsg4Game extends LoginMsg
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case CLIENT_LOGOUT_CMD:
			recvClientLogoutCmd(head, msg);
			break;
		}
	}

	/** recv(客户端下线命令Gateway->otherServers) */
	private void recvClientLogoutCmd(MsgHead head, MsgUnit msg)
	{
		Long cccid = msg.readCCCID();
		logger.info("cccid=" + cccid + " 的角色已经离线");
		GameCB bundle = Game.bundle.getBundle(cccid);
		if(bundle != null)
		{
			bundle.player.exit();
		}
	}
}
