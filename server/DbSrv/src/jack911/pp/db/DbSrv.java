package jack911.pp.db;

import org.apache.log4j.Logger;

import jack911.pp.Setup;
import jack911.pp.db.msg.DbMsgDistributor;
import jack911.pp.server.AcceptorHandler;
import jack911.pp.server.ServerId;
import jack911.pp.server.ServerManager;

/** 数据库服务器 */
public class DbSrv 
{
	private static Logger logger = Logger.getLogger(DbSrv.class);
	
	public static void main(String[] args) 
	{
		Setup.execute();
		logger.info("I am DbSrv");
		ServerManager.initialize(ServerId.DB, new DbMsgDistributor(), AcceptorHandler.class, null);
		Db.run();
	}

}
