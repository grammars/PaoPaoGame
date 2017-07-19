package jack911.pp.centre.player;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.server.MsgUnit;

public class CentrePlayerHandler
{
	public void handlePlayerDataInitReq(Long cccid)
	{
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle==null || bundle.getAccount()==null) { return; }
		Centre.msg.player.sendQueryPlayerDataReq(bundle.getAccount().playerUid, cccid);
	}
	
	public void handleQueryPlayerDataResp(MsgUnit msg, Long cccid)
	{
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle==null) { return; }
		bundle.player.readFromDb(msg);
		Centre.msg.player.sendPlayerDataInitResp(bundle.player, cccid);
	}
}
