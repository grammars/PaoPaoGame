package jack911.pp.centre.msg.content;

import java.util.ArrayList;
import java.util.List;

import jack911.pp.centre.Centre;
import jack911.pp.centre.goods.GoodsInfo;
import jack911.pp.centre.goods.equip.EquipContainer;
import jack911.pp.common.vo.PlayerData;
import jack911.pp.common.vo.RoomInfo;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.LobbyMsg;
import jack911.pp.message.dp.lobby.EnterRoomResultDp;
import jack911.pp.message.dp.lobby.LobbyInitDp;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class LobbyMsg4Centre extends LobbyMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case ROOMS_INIT_INFO_RESP:
			recvRoomsInitInfoResp(head, msg);
			break;
		case LOBBY_INIT_DATA_REQ:
			recvLobbyInitDataReq(head, msg);
			break;
		case ENTER_ROOM_REQ:
			recvEnterRoomReq(head, msg);
			break;
		case PLAYER_ENTER_GAME_RESP:
			recvPlayerEnterGameResp(head, msg);
			break;
		}
	}
	
	/** send(请求房间初始化信息Centre->Db) */
	public void sendRoomsInitInfoReq()
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, ROOMS_INIT_INFO_REQ, ServerId.DB);
		msg.send();
	}
	
	/** recv(请求房间初始化信息Db->Centre) */
	private void recvRoomsInitInfoResp(MsgHead head, MsgUnit msg)
	{
		List<RoomInfo> infos = new ArrayList<>();
		int size = msg.readInt();
		for(int i = 0; i < size; i++)
		{
			RoomInfo ri = new RoomInfo();
			ri.readFrom(msg);
			infos.add(ri);
		}
		Centre.lobby.digestRoomsInitInfo(infos);
	}
	
	/** recv(大厅初始化数据de返回Client->Centre) */
	private void recvLobbyInitDataReq(MsgHead head, MsgUnit msg)
	{
		Centre.lobby.tellLobbyInitData(head.cccid);
	}
	
	/** send(大厅初始化数据de返回Centre->Client) */
	public void sendLobbyInitDataResp(LobbyInitDp dp, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, LOBBY_INIT_DATA_RESP, cccid);
		dp.writeTo(msg);
		msg.send();
	}
	
	/** recv(选择进入房间de请求Client->Centre) */
	private void recvEnterRoomReq(MsgHead head, MsgUnit msg)
	{
		int roomId = msg.readInt();
		Centre.lobby.putPlayerToGameRoom(roomId, head.cccid);
	}
	
	/** send(选择进入房间de返回Centre->Client) */
	public void sendEnterRoomResp(EnterRoomResultDp dp, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, ENTER_ROOM_RESP, cccid);
		dp.writeTo(msg);
		msg.send();
	}
	
	/** send(角色登场de请求Centre->Game) */
	public void sendPlayerEnterGameReq(PlayerData pData, EquipContainer equip, int roomId, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, PLAYER_ENTER_GAME_REQ, ServerId.GAME);
		pData.writeTo(msg);
		
		//写装备的道具
		msg.writeInt( equip.items.size() );
		for(GoodsInfo gi : equip.items)
		{
			msg.writeInt(gi.cfgId);
		}
		
		msg.writeInt(roomId);
		msg.writeCCCID(cccid);
		msg.send();
	}
	
	/** recv(角色登场de返回Game->Centre) */
	private void recvPlayerEnterGameResp(MsgHead head, MsgUnit msg)
	{
		Long cccid = msg.readCCCID();
		EnterRoomResultDp dp = new EnterRoomResultDp();
		dp.readFrom(msg);
		sendEnterRoomResp(dp, cccid);
	}
}
