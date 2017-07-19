package jack911.pp.db.msg.content;

import jack911.pp.common.vo.PlayerData;
import jack911.pp.db.Db;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.LoginMsg;
import jack911.pp.message.dp.login.LoginResultDP;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class LoginMsg4Db extends LoginMsg
{
	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case DB_VERIFY_LOGIN_REQ:
			recvDbVerifyLoginReq(head, msg);
			break;
		case DB_CREATE_PLAYER_REQ:
			recvDbCreatePlayerReq(head, msg);
			break;
		}
	}
	
	/** recv(数据库登录验证请求Centre->DB) */
	private void recvDbVerifyLoginReq(MsgHead head, MsgUnit msg)
	{
		String username = msg.readString();
		String password = msg.readString();
		Long cccid = msg.readCCCID();
		Db.login.handleDbVerifyLoginReq(username, password, cccid);
	}
	
	/** send(数据库登录验证响应DB->Centre) */
	public void sendDbVerifyLoginResp(LoginResultDP dp, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, DB_VERIFY_LOGIN_RESP, ServerId.CENTRE);
		dp.writeTo(msg);
		msg.writeCCCID(cccid);
		msg.send();
	}
	
	/** recv(让Db创建一个新的角色Centre->Db) */
	private void recvDbCreatePlayerReq(MsgHead head, MsgUnit msg)
	{
		Long accUid = msg.readLong();
		String name = msg.readString();
		Long cccid = msg.readCCCID();
		Db.login.handleDbCreatePlayerReq(accUid, name, cccid);
	}
	
	/** send(Db创建新的角色之后的返回Db->Centre) */
	public void sendDbCreatePlayerResp(PlayerData pd, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, DB_CREATE_PLAYER_RESP, ServerId.CENTRE);
		pd.writeTo(msg);
		msg.writeCCCID(cccid);
		msg.send();
	}
}
