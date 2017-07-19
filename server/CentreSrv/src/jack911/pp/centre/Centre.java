package jack911.pp.centre;

import jack911.pp.centre.bundle.CentreBundleHandler;
import jack911.pp.centre.goods.CentreGoodsHandler;
import jack911.pp.centre.lobby.CentreLobbyHandler;
import jack911.pp.centre.login.CentreLoginHandler;
import jack911.pp.centre.msg.CentreMsgHandler;
import jack911.pp.centre.player.CentrePlayerHandler;
import jack911.pp.centre.rank.CentreRankHandler;

/** Centre */
public class Centre
{
	public static CentreBundleHandler bundle = new CentreBundleHandler();
	public static CentreMsgHandler msg = new CentreMsgHandler();
	
	public static CentreLoginHandler login = new CentreLoginHandler();
	public static CentreLobbyHandler lobby = new CentreLobbyHandler();
	public static CentrePlayerHandler player = new CentrePlayerHandler();
	public static CentreRankHandler rank = new CentreRankHandler();
	public static CentreGoodsHandler goods = new CentreGoodsHandler();
	
	public static void run()
	{
		lobby.initialize();
	}
}
