package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;

public abstract class GoodsMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.GOODS_MSG;
	
	/** game向client通知物品操作异常 */
	protected static final short OPER_EXCEPTION_G2C = 1;
	
	/** game向client通知背包已清空 */
	protected static final short CLEAR_ITEMS_IN_BAG_G2C = 10;
	/** game向client发送初始化背包物品 */
	protected static final short INIT_ITEMS_TO_BAG_G2C = 11;
	/** game向client通知添加物品到背包 */
	protected static final short ADD_ITEM_TO_BAG_G2C = 12;
	/** game向client通知移除物品从背包 */
	protected static final short REMOVE_ITEM_FROM_BAG_G2C = 13;
	/** game向client通知更新背包物品 */
	protected static final short UPDATE_ITEM_IN_BAG_G2C = 14;
	
	
	/** game向client通知装备已清空 */
	protected static final short CLEAR_ITEMS_IN_EQUIP_G2C = 20;
	/** game向client发送初始化装备 */
	protected static final short INIT_ITEMS_TO_EQUIP_G2C = 21;
	/** game向client通知穿上装备 */
	protected static final short ADD_ITEM_TO_EQUIP_G2C = 22;
	/** game向client通知脱下装备 */
	protected static final short REMOVE_ITEM_FROM_EQUIP_G2C = 23;
	/** game向client通知更新装备物品 */
	protected static final short UPDATE_ITEM_IN_EQUIP_G2C = 24;
	
	/** client请求移动物品：从背包到背包 */
	protected static final short MOVE_ITEM_BAG_TO_BAG_REQ_C2G = 41;
	
	/** client请求丢弃背包物品 */
	protected static final short DROP_ITEM_FROM_BAG_REQ_C2G = 51;
	/** client请求拆分背包物品 */
	protected static final short SPLIT_ITEM_IN_BAG_REQ_C2G = 52;
	
	/** client请求穿上装备 */
	protected static final short PUT_ON_EQUIP_REQ_C2G = 61;
	/** client请求脱下装备 */
	protected static final short TAKE_OFF_EQUIP_REQ_C2G = 62;
	
	/** client请求使用物品 */
	protected static final short USE_ITEM_REQ_C2G = 71;
}
