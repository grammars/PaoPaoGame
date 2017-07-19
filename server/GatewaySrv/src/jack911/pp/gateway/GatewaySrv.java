package jack911.pp.gateway;

import jack911.pp.Setup;
import jack911.pp.gateway.msg.GatewayMsgDistributor;
import jack911.pp.server.ServerId;
import jack911.pp.server.ServerManager;

import org.apache.log4j.Logger;

/** 网关服务器 */
public class GatewaySrv
{
	private static Logger logger = Logger.getLogger(GatewaySrv.class);
	
	public static void main(String[] args)
	{
		Setup.execute();
		logger.info("I am GatewaySrv");
		ServerManager.initialize(ServerId.GATEWAY, new GatewayMsgDistributor(), GatewayAcceptorHandler.class, null);
		Gateway.run();
	}

}
