package jack911.pp.centre.lobby;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.common.vo.RoomInfo;
import jack911.pp.config.ConfigManager;
import jack911.pp.config.table.RoomTblUnit;
import jack911.pp.message.dp.lobby.EnterRoomResultDp;
import jack911.pp.message.dp.lobby.LobbyInitDp;

public class CentreLobbyHandler
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	private List<LobbyRoom> rooms = new ArrayList<>();
	private LobbyRoom getRoom(int roomId)
	{
		for(LobbyRoom r : rooms)
		{
			if(roomId == r.roomId()) { return r; }
		}
		return null;
	}
	
	public void initialize()
	{
		for(RoomTblUnit cfg : ConfigManager.room.items)
		{
			LobbyRoom room = new LobbyRoom(cfg);
			rooms.add(room);
		}
		
	}
	
	public void dbSrvConnected()
	{
		Centre.msg.lobby.sendRoomsInitInfoReq();
	}
	
	/** 处理db返回的房间初始化信息 */
	public void digestRoomsInitInfo(List<RoomInfo> infos)
	{
		for(RoomInfo ri : infos)
		{
			//System.out.println("哈哈哈哈=" + ri.roomId);
			LobbyRoom room = getRoom(ri.roomId);
			if(room != null)
			{
				room.digestDbInitInfo(ri);
			}
			else
			{
				logger.error("digestRoomsInitInfo 收到无法处理的 RoomInfo roomId=" + ri.roomId);
			}
		}
	}
	
	/** 回复客户端大厅初始化数据Centre->Client */
	public void tellLobbyInitData(Long cccid)
	{
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle == null) { return; }
		LobbyInitDp dp = new LobbyInitDp();
		Centre.msg.lobby.sendLobbyInitDataResp(dp, cccid);
	}
	
	/** 让玩家进入游戏房间 */
	public void putPlayerToGameRoom(int roomId, Long cccid)
	{
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		if(bundle == null) { return; }
		RoomTblUnit cfg = ConfigManager.room.get(roomId);
		if(cfg == null) { return; }
		
		int level = bundle.player.data.level;
		if(level < cfg.levMin || level >= cfg.levMax)
		{
			EnterRoomResultDp dp = new EnterRoomResultDp();
			dp.errCode = EnterRoomResultDp.EC_LEV_LMT;
			Centre.msg.lobby.sendEnterRoomResp(dp, cccid);
			return;
		}
		int cash = bundle.player.data.cash;
		if(cash < cfg.ticketPrice)
		{
			EnterRoomResultDp dp = new EnterRoomResultDp();
			dp.errCode = EnterRoomResultDp.EC_CASH_LESS;
			Centre.msg.lobby.sendEnterRoomResp(dp, cccid);
			return;
		}
		
		//-------------以下是经过判断，玩家符合进入房间的要求-------------
		//扣除门票钱
		bundle.player.cash( bundle.player.cash()-cfg.ticketPrice );
		
		//向GameSrv通知
		Centre.msg.lobby.sendPlayerEnterGameReq(bundle.player.data, bundle.player.equip, roomId, cccid);
	}
}
