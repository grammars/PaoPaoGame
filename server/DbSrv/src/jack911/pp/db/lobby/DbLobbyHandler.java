package jack911.pp.db.lobby;

import java.util.ArrayList;
import java.util.List;

import jack911.pp.common.vo.RoomInfo;
import jack911.pp.db.Db;
import jack911.pp.db.entity.RoomInfoEntity;
import jack911.pp.db.util.DaoHelper;

public class DbLobbyHandler
{
	/** 受理Centre请求的房间初始化信息 */
	public void handleRoomsInitInfoReq()
	{
		List<RoomInfoEntity> entities = DaoHelper.getInstance().queryAll(RoomInfoEntity.class);
		List<RoomInfo> infos = new ArrayList<>();
		for(RoomInfoEntity entity : entities)
		{
			infos.add(entity.toInfo());
		}
		Db.msg.lobby.sendRoomsInitInfoResp(infos);
	}
}
