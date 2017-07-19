package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgUtil;
import jack911.pp.server.MsgUnit;

public abstract class PlayerMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.PLAYER_MSG;
	
	/** 角色初始化数据de请求Client->Centre */
	protected static final short PLAYER_DATA_INIT_REQ = 1;
	/** 角色初始化数据de返回Centre->Client */
	protected static final short PLAYER_DATA_INIT_RESP = 2;
	/** 向Db查询角色的完整信息de请求Centre->Db */
	protected static final short QUERY_PLAYER_DATA_REQ = 3;
	/** 向Db查询角色的完整信息de返回Db->Centre */
	protected static final short QUERY_PLAYER_DATA_RESP = 4;
	/** 保存角色数据到数据库de请求Centre->DB */
	protected static final short SAVE_TO_DB_REQ = 5;
	/** 保存角色数据到数据库de返回DB->Centre */
	protected static final short SAVE_TO_DB_RESP = 6;
	
	/** 角色等级改变通知Centre->Client */
	protected static final short UPDATE_LEVEL_CMD = 11;
	/** 角色现金改变通知Centre->Client */
	protected static final short UPDATE_CASH_CMD = 12;
	
	/** 发送给客户端的警告消息Centre/Game->Client */
	protected static final short ALERT_CMD = 99;
	
	/** send(发送给客户端的警告消息Centre/Game->Client) */
	public void sendAlertCmd(String words, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, ALERT_CMD, cccid);
		msg.writeString(words);
		msg.send();
	}
}
