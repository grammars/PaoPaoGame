package jack911.pp.db.msg.content;

import java.util.List;

import jack911.pp.common.vo.RoomInfo;
import jack911.pp.db.Db;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.LobbyMsg;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class LobbyMsg4Db extends LobbyMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case ROOMS_INIT_INFO_REQ:
			recvRoomsInitInfoReq(head, msg);
			break;
		}
	}
	
	/** recv(请求房间初始化信息Centre->Db) */
	private void recvRoomsInitInfoReq(MsgHead head, MsgUnit msg)
	{
		Db.lobby.handleRoomsInitInfoReq();
	}

	/** send(请求房间初始化信息Db->Centre) */
	public void sendRoomsInitInfoResp(List<RoomInfo> roomInfos)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, ROOMS_INIT_INFO_RESP, ServerId.CENTRE);
		msg.writeInt(roomInfos.size());
		for(RoomInfo ri : roomInfos)
		{
			ri.writeTo(msg);
		}
		msg.send();
	}
}
