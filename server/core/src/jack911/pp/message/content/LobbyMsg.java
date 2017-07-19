package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;

public abstract class LobbyMsg implements IMsgHandler
{

	protected static final short MAJOR_MID = MsgDistributor.LOBBY_MSG;
	
	/** 请求房间初始化信息Centre->Db */
	protected static final short ROOMS_INIT_INFO_REQ = 90;
	/** 请求房间初始化信息Db->Centre */
	protected static final short ROOMS_INIT_INFO_RESP = 91;
	
	/** 大厅初始化数据de请求Client->Centre */
	protected static final short LOBBY_INIT_DATA_REQ = 1;
	/** 大厅初始化数据de返回Centre->Client */
	protected static final short LOBBY_INIT_DATA_RESP = 2;
	/** 选择进入房间de请求Client->Centre */
	protected static final short ENTER_ROOM_REQ = 3;
	/** 选择进入房间de返回Centre->Client */
	protected static final short ENTER_ROOM_RESP = 4;
	/** 角色登场de请求Centre->Game */
	protected static final short PLAYER_ENTER_GAME_REQ = 5;
	/** 角色登场de请求Game->Centre */
	protected static final short PLAYER_ENTER_GAME_RESP = 6;
	

}
