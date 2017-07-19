package jack911.pp.config.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jack911.pp.config.Tbl;

public class MonsterTbl extends Tbl
{
	public List<MonsterTblUnit> items = new ArrayList<>();
	/** 根据id获取单个配置 */
	public MonsterTblUnit get(int id)
	{
		for(MonsterTblUnit unit : items)
		{
			if(unit.id == id) { return unit; }
		}
		return null;
	}
	
	public MonsterTbl()
	{
		this.type = "monster";
	}
	
	@Override
	public void parse(HashMap<String, String[]> dataDic, int itemCount)
	{
		for(int i = 0; i < itemCount; i++)
		{
			MonsterTblUnit unit = new MonsterTblUnit();
			unit.id = Integer.parseInt( dataDic.get("ID")[i] );
			unit.roomId = Integer.parseInt( dataDic.get("所属房间")[i] );
			unit.name = dataDic.get("怪物名字")[i];
			unit.max = Integer.parseInt( dataDic.get("最大数量")[i] );
			unit.r = Float.parseFloat( dataDic.get("半径")[i] );
			unit.speed = Float.parseFloat( dataDic.get("移动速度")[i] );
			unit.frequency = Long.parseLong( dataDic.get("刷新频率")[i] );
			unit.skin = dataDic.get("皮肤")[i];
			
			System.out.println(unit);
			items.add(unit);
		}
	}
}
