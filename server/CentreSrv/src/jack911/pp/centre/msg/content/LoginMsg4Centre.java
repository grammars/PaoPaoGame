package jack911.pp.centre.msg.content;

import org.apache.log4j.Logger;

import jack911.netty.BBT;
import jack911.pp.centre.Centre;
import jack911.pp.common.vo.PlayerData;
import jack911.pp.message.MsgDef;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.LoginMsg;
import jack911.pp.message.dp.login.LoginResultDP;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class LoginMsg4Centre extends LoginMsg
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case CLIENT_LOGIN_REQ:
			recvClientLoginReq(head, msg);
			break;
		case DB_VERIFY_LOGIN_RESP:
			recvDbVerifyLoginResp(head, msg);
			break;
		case PLAYER_BORN_REQ:
			recvPlayerBornReq(head, msg);
			break;
		case DB_CREATE_PLAYER_RESP:
			recvDbCreatePlayerResp(head, msg);
			break;
		case CLIENT_LOGOUT_CMD:
			recvClientLogoutCmd(head, msg);
			break;
		}
	}
	
	/** recv(客户端登录请求Client->Centre) */
	private void recvClientLoginReq(MsgHead head, MsgUnit msg)
	{
		try
		{
			String username = BBT.readString(msg.content);
			String password = BBT.readString(msg.content);
			Centre.login.handleClientLoginReq(username, password, head.cccid);
		}
		catch(IndexOutOfBoundsException ex)
		{
			logger.error("recvClientLoginReq"+MsgDef.MSG_LENGTH_WARN);
		}
	}
	
	/** send(数据库登录验证请求Centre->DB) */
	public void sendDbVerifyLoginReq(String username, String password, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, DB_VERIFY_LOGIN_REQ, ServerId.DB);
		msg.writeString(username);
		msg.writeString(password);
		msg.writeCCCID(cccid);
		msg.send();
	}
	
	/** recv(数据库登录验证响应DB->Centre) */
	private void recvDbVerifyLoginResp(MsgHead head, MsgUnit msg)
	{
		LoginResultDP dp = new LoginResultDP();
		dp.readFrom(msg);
		Long cccid = msg.readCCCID();
		//
		Centre.login.handleDbVerifyLoginResp(dp, cccid);
	}
	
	/** send(客户端登录响应Centre->Client) */
	public void sendClientLoginResp(LoginResultDP dp, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, CLIENT_LOGIN_RESP, cccid);
		dp.writeTo(msg);
		msg.send();
	}
	
	/** recv(客户端角色新生请求Client->Centre) */
	private void recvPlayerBornReq(MsgHead head, MsgUnit msg)
	{
		try
		{
			String name = BBT.readString(msg.content);
			Centre.login.handlePlayerBornReq(name, head.cccid);
		}
		catch(IndexOutOfBoundsException ex)
		{
			logger.error("recvPlayerBornReq"+MsgDef.MSG_LENGTH_WARN);
		}
	}
	
	/** send(让Db创建一个新的角色Centre->Db) */
	public void sendDbCreatePlayerReq(Long accUid, String name, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, DB_CREATE_PLAYER_REQ, ServerId.DB);
		msg.writeLong(accUid);
		msg.writeString(name);
		msg.writeCCCID(cccid);
		msg.send();
	}
	
	/** recv(Db创建新的角色之后的返回Db->Centre) */
	private void recvDbCreatePlayerResp(MsgHead head, MsgUnit msg)
	{
		PlayerData pd = new PlayerData();
		pd.readFrom(msg);
		Long cccid = msg.readCCCID();
		Centre.login.handleDbCreatePlayerResp(pd, cccid);
	}
	
	/** recv(客户端下线命令Gateway->otherServers) */
	private void recvClientLogoutCmd(MsgHead head, MsgUnit msg)
	{
		Long cccid = msg.readCCCID();
		logger.info("cccid=" + cccid + " 的玩家已经离线");
		Centre.login.handlePlayerLogout(cccid);
	}
	
}
