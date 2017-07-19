package jack911.pp.centre.lobby;

import jack911.pp.common.vo.RoomInfo;
import jack911.pp.config.table.RoomTblUnit;

public class LobbyRoom
{
	private RoomTblUnit cfg;
	
	private RoomInfo info = new RoomInfo();
	
	public int roomId() { return cfg.id; }
	
	public LobbyRoom(RoomTblUnit cfg)
	{
		this.cfg = cfg;
	}
	
	/** 处理收到的db初始化房间信息 */
	public void digestDbInitInfo(RoomInfo dbInfo)
	{
		info.tickets += dbInfo.tickets;
	}
}
