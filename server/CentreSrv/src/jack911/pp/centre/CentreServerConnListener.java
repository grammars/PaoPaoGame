package jack911.pp.centre;

import jack911.pp.server.ServerConnListener;
import jack911.pp.server.ServerId;

public class CentreServerConnListener implements ServerConnListener
{

	@Override
	public void connectedHandler(byte serverId)
	{
		switch(serverId)
		{
		case ServerId.DB:
			dbSrvConnected();
			break;
		case ServerId.GAME:
			gameSrvConnected();
			break;
		}
	}
	
	private void dbSrvConnected()
	{
		System.out.println("DB 连接上了");
		Centre.lobby.dbSrvConnected();
	}
	
	private void gameSrvConnected()
	{
		System.out.println("GAME 连接上了");
	}

}
