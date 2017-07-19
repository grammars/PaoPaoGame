package jack911.pp.config.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jack911.pp.config.Tbl;

public class GoodsTbl extends Tbl
{
	public List<GoodsTblUnit> items = new ArrayList<>();
	/** 根据id获取单个配置 */
	public GoodsTblUnit get(int id)
	{
		for(GoodsTblUnit unit : items)
		{
			if(unit.id == id) { return unit; }
		}
		return null;
	}
	
	public GoodsTbl()
	{
		this.type = "goods";
	}
	
	@Override
	public void parse(HashMap<String, String[]> dataDic, int itemCount)
	{
		for(int i = 0; i < itemCount; i++)
		{
			GoodsTblUnit unit = new GoodsTblUnit();
			unit.id = Integer.parseInt( dataDic.get("ID")[i] );
			unit.name = dataDic.get("物品名")[i];
			unit.type = Integer.parseInt( dataDic.get("类型")[i] );
			unit.icon = dataDic.get("icon")[i];
			unit.reqLevMin = Integer.parseInt( dataDic.get("要求等级下限")[i] );
			unit.reqLevMax = Integer.parseInt( dataDic.get("要求等级上限")[i] );
			unit.desc = dataDic.get("说明")[i];
			unit.maxHeap = Integer.parseInt( dataDic.get("最大堆叠数")[i] );
			unit.equipType = Integer.parseInt( dataDic.get("武器类型")[i] );
			
			System.out.println(unit);
			items.add(unit);
		}
	}

}
