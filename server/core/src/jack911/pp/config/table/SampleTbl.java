package jack911.pp.config.table;

import java.util.HashMap;

import jack911.pp.config.Tbl;

public class SampleTbl extends Tbl
{
	private HashMap<Integer, SampleTblUnit> cfgs = new HashMap<>();
	/** 根据id获取单个配置 */
	public SampleTblUnit get(int id)
	{
		return cfgs.get(id);
	}
	
	public SampleTbl()
	{
		this.type = "sample";
	}

	@Override
	public void parse(HashMap<String, String[]> dataDic, int itemCount)
	{
		for(int i = 0; i < itemCount; i++)
		{
			SampleTblUnit unit = new SampleTblUnit();
			unit.id = Integer.parseInt( dataDic.get("整数字段")[i] );
			unit.name = dataDic.get("字符串字段")[i];
			unit.score = Double.parseDouble( dataDic.get("浮点字段")[i] );
			//System.out.println(unit);
			cfgs.put(unit.id, unit);
		}
	}

}
