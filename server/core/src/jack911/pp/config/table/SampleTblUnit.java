package jack911.pp.config.table;

public class SampleTblUnit
{
	/** 整数字段 */
	public int id = 0;
	/** 字符串字段 */
	public String name = "";
	/** 浮点字段 */
	public double score = 0;
	
	@Override
	public String toString()
	{
		return "[SampleTblUnit] id:" + id + " name:" + name
				+ " score=" + score;
	}
}
