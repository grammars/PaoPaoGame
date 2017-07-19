package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;
import jack911.pp.message.MsgUtil;
import jack911.pp.server.MsgUnit;

public abstract class LoginMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.LOGIN_MSG;
	
	/** 客户端登录请求Client->Centre */
	protected static final short CLIENT_LOGIN_REQ = 1;
	/** 数据库登录验证请求Centre->DB */
	protected static final short DB_VERIFY_LOGIN_REQ = 2;
	/** 数据库登录验证响应DB->Centre */
	protected static final short DB_VERIFY_LOGIN_RESP = 3;
	/** 客户端登录响应Centre->Client */
	protected static final short CLIENT_LOGIN_RESP = 5;
	/** 客户端角色新生请求Client->Centre */
	protected static final short PLAYER_BORN_REQ = 11;
	/** 让Db创建一个新的角色Centre->Db */
	protected static final short DB_CREATE_PLAYER_REQ = 12;
	/** Db创建新的角色之后的返回Db->Centre */
	protected static final short DB_CREATE_PLAYER_RESP = 13;
	
	/** 客户端下线命令Gateway->otherServers */
	protected static final short CLIENT_LOGOUT_CMD = 99;
	
	public static void sendPlayerLogoutNoticeCmd(Long cccid, byte serverId)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, CLIENT_LOGOUT_CMD, serverId);
		msg.writeCCCID(cccid);
		msg.send();
	}
	
}
