package jack911.pp.config.table;

public class FoodTblUnit
{
	/** ID */
	public int id;
	/** 所属房间 */
	public int roomId;
	/** 食物名字 */
	public String name;
	/** 最大数量 */
	public int max;
	/** 半径 */
	public float r;
	/** 移动速度 */
	public float speed;
	/** 刷新频率 */
	public long frequency;
	/** 皮肤 */
	public String skin;
	
	@Override
	public String toString()
	{
		return "[FoodTblUnit] id:" + id + " roomId:" + roomId + " r=" + r + " skin=" + skin
				+ "name=" + name + "max=" + max + " speed=" + speed + " frequency=" + frequency;
	}
}
