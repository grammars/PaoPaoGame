package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;

public abstract class SceneMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.SCENE_MSG;
	
	/** 场景初始化数据de命令Game->Client */
	protected static final short INIT_SCENE_CMD = 1;
	/** 场景内所有ape数据初始化de命令Game->Client */
	protected static final short INIT_APES_CMD = 2;
	
	/** 场景内添加一个ape的命令Game->Client */
	protected static final short ADD_APE_CMD = 11;
	/** 场景内移除一个ape的命令Game->Client */
	protected static final short REMOVE_APE_CMD = 12;
	/** 场景内移动player的请求Client->Game */
	protected static final short MOVE_PLAYER_REQ = 13;
	/** 场景内移动player的应答Game->Client */
	protected static final short MOVE_PLAYER_RESP = 14;
	/** 立即更新ape坐标de命令Game->Client */
	protected static final short UPDATE_POS_IMMED_CMD = 15;
	/** player移动完成de通知Game->Client */
	protected static final short MOVE_COMPLETE_NOTICE = 16;
	/** 更新ape尺寸de命令Game->Client */
	protected static final short UPDATE_RADIUS_CMD = 17;
	/** 更新ape速度de命令Game->Client */
	protected static final short UPDATE_SPEED_CMD = 18;
	/** 更新ape目标坐标de命令Game->Client */
	protected static final short UPATE_TARGET_POS_CMD = 19;
	/** 更新玩家步数de命令Game->Client */
	protected static final short UPDATE_STEP_CMD = 20;
	/** 更新玩家保护时间de命令Game->Client */
	protected static final short UPATE_PROTECT_CMD = 21;
	
	/** 玩家请求使用装备道具de请求Client->Game */
	protected static final short USE_EQUIP_REQ = 31;
	/** 玩家请求使用装备道具de返回Game->Client */
	protected static final short USE_EQUIP_RESP = 32;
	/** 装备消耗扣除de命令Game->Centre */
	protected static final short DEDUCT_EQUIP_CMD = 33;
	
	/** 游戏结束的结果Game->Client */
	protected static final short GAME_OVER_RESULT = 41;
	/** 报送游戏的结果Game->Centre */
	protected static final short SUBMIT_GAME_RESULT = 42;
}
