package jack911.pp.game;

import jack911.pp.Setup;
import jack911.pp.config.ConfigManager;
import jack911.pp.game.msg.GameMsgDistributor;
import jack911.pp.server.AcceptorHandler;
import jack911.pp.server.ServerId;
import jack911.pp.server.ServerManager;

import org.apache.log4j.Logger;

/** 处理具体房间内游戏事务 */
public class GameSrv 
{
	private static Logger logger = Logger.getLogger(GameSrv.class);
	
	public static void main(String[] args) 
	{
		Setup.execute();
		ConfigManager.load();
		logger.info("I am GameSrv");
		ServerManager.initialize(ServerId.GAME, new GameMsgDistributor(), AcceptorHandler.class, null);
		Game.run();
	}

}
