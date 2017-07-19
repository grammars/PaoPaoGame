package jack911.pp.config.table;

public class RoomTblUnit
{
	/** ID */
	public int id;
	/** 房间名 */
	public String name;
	/** 等级下限 */
	public int levMin;
	/** 等级上限 */
	public int levMax;
	/** 描述 */
	public String desc;
	/** 缩略图 */
	public String thumbnail;
	/** 房间宽 */
	public int width;
	/** 房间高 */
	public int height;
	/** 步数 */
	public short stepNum;
	/** 玩家初始R */
	public float playerInitR;
	/** 门票价格 */
	public int ticketPrice;
	
	@Override
	public String toString()
	{
		return "[RoomTblUnit] id:" + id + " name:" + name
			+ " lev(" + levMin + " - " + levMax + ") desc=" + desc
			+ " thumbnail=" + thumbnail + " width=" + width + " height=" + height
			+ " stepNum=" + stepNum + " playerInitR=" + playerInitR + " ticketPrice=" + ticketPrice;
	}
}
