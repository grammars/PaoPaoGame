package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;

public abstract class GmMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.GOODS_MSG;
	
	/** client向game请求执行参数化的GM命令 */
	protected static final int PARAMS_CMD_C2G = 0;
	
	protected static final int TYPE_OTHERS = 0;
	protected static final int TYPE_PLAYER = 1;
	protected static final int TYPE_GOODS = 3;
	protected static final int TYPE_TASK = 4;
	
	
	/** 改变玩家等级 */
	protected static final int PLAYER_CHANGE_LEVEL = 1;
	/** 改变玩家现金 */
	protected static final int PLAYER_CHANGE_CASH = 2;
	
	/** 制造物品 */
	protected static final int GOODS_MAKE_GOODS = 1;
	/** 清空包裹 */
	protected static final int GOODS_CLEAR_BAG = 2;
}
