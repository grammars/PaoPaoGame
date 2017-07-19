package jack911.pp.centre.msg.content;

import jack911.pp.centre.Centre;
import jack911.pp.centre.player.Player;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.PlayerMsg;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class PlayerMsg4Centre extends PlayerMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case PLAYER_DATA_INIT_REQ:
			recvPlayerDataInitReq(head, msg);
			break;
		case QUERY_PLAYER_DATA_RESP:
			recvQueryPlayerDataResp(head, msg);
			break;
		}
	}

	/** recv(角色初始化数据de请求Client->Centre) */
	private void recvPlayerDataInitReq(MsgHead head, MsgUnit msg)
	{
		Centre.player.handlePlayerDataInitReq(head.cccid);
	}
	
	/** send(角色初始化数据de返回Centre->Client) */
	public void sendPlayerDataInitResp(Player player, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, PLAYER_DATA_INIT_RESP, cccid);
		player.writeToClient(msg);
		msg.send();
	}
	
	/** send(向Db查询角色的完整信息de请求Centre->Db) */
	public void sendQueryPlayerDataReq(Long playerUid, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, QUERY_PLAYER_DATA_REQ, ServerId.DB);
		msg.writeLong(playerUid);
		msg.writeCCCID(cccid);
		msg.send();
	}
	
	/** recv(向Db查询角色的完整信息de返回Centre->Db) */
	private void recvQueryPlayerDataResp(MsgHead head, MsgUnit msg)
	{
		Long cccid = msg.readCCCID();
		Centre.player.handleQueryPlayerDataResp(msg, cccid);
	}
	
	/** send(保存角色数据到数据库de请求Centre->DB) */
	public void sendSaveToDbReq(Player player, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, SAVE_TO_DB_REQ, ServerId.DB);
		msg.writeCCCID(cccid);
		player.writeToDb(msg);
		msg.send();
	}
	
	/** send(角色等级改变通知Centre->Client) */
	public void sendUpdateLevelCmd(int level, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_LEVEL_CMD, cccid);
		msg.writeInt(level);
		msg.send();
	}
	
	/** send(角色现金改变通知Centre->Client) */
	public void sendUpdateCashCmd(int cash, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_CASH_CMD, cccid);
		msg.writeInt(cash);
		msg.send();
	}
}
