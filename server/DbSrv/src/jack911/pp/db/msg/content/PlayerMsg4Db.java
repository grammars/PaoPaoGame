package jack911.pp.db.msg.content;

import jack911.pp.common.vo.PlayerData;
import jack911.pp.db.Db;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.PlayerMsg;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class PlayerMsg4Db extends PlayerMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case QUERY_PLAYER_DATA_REQ:
			recvQueryPlayerDataReq(head, msg);
			break;
		case SAVE_TO_DB_REQ:
			recvSaveToDbReq(head, msg);
			break;
		}
	}
	
	/** recv(向Db查询角色的完整信息de请求Centre->Db) */
	private void recvQueryPlayerDataReq(MsgHead head, MsgUnit msg)
	{
		Long playerUid = msg.readLong();
		Long cccid = msg.readCCCID();
		Db.player.handleQueryPlayerDataReq(playerUid, cccid);
	}
	
	/** send(向Db查询角色的完整信息de返回Db->Centre) */
	public void sendQueryPlayerDataResp(PlayerData playerData, String bagData, String equipData, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, QUERY_PLAYER_DATA_RESP, ServerId.CENTRE);
		msg.writeCCCID(cccid);
		playerData.writeTo(msg);
		msg.writeString(bagData);
		msg.writeString(equipData);
		msg.send();
	}
	
	/** recv(保存角色数据到数据库de请求Centre->DB) */
	private void recvSaveToDbReq(MsgHead head, MsgUnit msg)
	{
		Long cccid = msg.readCCCID();
		PlayerData playerData = new PlayerData();
		playerData.readFrom(msg);
		String bagData = msg.readString();
		String equipData = msg.readString();
		Db.player.handleSavePlayer(cccid, playerData, bagData, equipData);
	}

}
