package jack911.pp.centre.login;

import org.apache.log4j.Logger;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.common.vo.PlayerData;
import jack911.pp.message.dp.login.LoginResultDP;

public class CentreLoginHandler
{
	private static Logger logger = Logger.getLogger(CentreLoginHandler.class);
	
	public void handleClientLoginReq(String username, String password, Long cccid)
	{
		logger.debug("username:" + username + "  password:" + password + " cccid="+cccid);
		Centre.msg.login.sendDbVerifyLoginReq(username, password, cccid);
	}
	
	public void handleDbVerifyLoginResp(LoginResultDP dp, Long cccid)
	{
		if(dp.result == LoginResultDP.SUCCESS)
		{
			CentreCB bundle = new CentreCB();
			bundle.cccid = cccid;
			bundle.token = dp.token;
			bundle.setAccount(dp.account);
			Centre.bundle.putBundle(bundle);
		}
		Centre.msg.login.sendClientLoginResp(dp, cccid);
	}
	
	public void handlePlayerBornReq(String name, Long cccid)
	{
		logger.debug("doPlayerBorn name=" + name);
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle == null) { return; }
		if(bundle.getAccount().canCreatePlayer())
		{
			Centre.msg.login.sendDbCreatePlayerReq(bundle.getAccount().uid, name, cccid);
		}
		else
		{
			//已有角色，不能再新建了
		}
	}
	
	public void handleDbCreatePlayerResp(PlayerData pd, Long cccid)
	{
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle==null) { return; }
		bundle.player.data = pd;
		Centre.msg.player.sendPlayerDataInitResp(bundle.player, cccid);
		Centre.lobby.tellLobbyInitData(cccid);
	}
	
	/** 玩家注销/离线 */
	public void handlePlayerLogout(Long cccid)
	{
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle==null) { return; }
		bundle.save();
		Centre.bundle.removeBundle(cccid);
	}
}
