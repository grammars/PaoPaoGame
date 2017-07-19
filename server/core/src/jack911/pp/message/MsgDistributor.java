package jack911.pp.message;

import jack911.pp.server.MsgUnit;

public interface MsgDistributor
{
	/** [服务器组内部]消息 */
	public static final short SERVER_MSG = 1;
	/** [登录]消息 */
	public static final short LOGIN_MSG = 2;
	/** [大厅]消息 */
	public static final short LOBBY_MSG = 3;
	/** [角色]消息 */
	public static final short PLAYER_MSG = 4;
	/** [场景]消息 */
	public static final short SCENE_MSG = 5;
	/** [排行榜]消息 */
	public static final short RANK_MSG = 6;
	/** [物品]消息 */
	public static final short GOODS_MSG = 7;
	/** [技能]消息 */
	public static final short SKILL_MSG = 8;
	/** [GM]消息 */
	public static final short GM_MSG = 99;
	
	void handle(MsgUnit msg);
}
