package jack911.pp.centre;

import jack911.pp.Setup;
import jack911.pp.centre.msg.CentreMsgDistributor;
import jack911.pp.config.ConfigManager;
import jack911.pp.server.AcceptorHandler;
import jack911.pp.server.ServerId;
import jack911.pp.server.ServerManager;

import org.apache.log4j.Logger;

/** 中心服务器，处理帐号，登陆，充值等 */
public class CentreSrv
{
	private static Logger logger = Logger.getLogger(CentreSrv.class);
	
	public static void main(String[] args) 
	{
		Setup.execute();
		ConfigManager.load();
		logger.info("I am CentreSrv");
		ServerManager.initialize(ServerId.CENTRE, new CentreMsgDistributor(), AcceptorHandler.class, CentreServerConnListener.class);
		Centre.run();
	}

}
