package jack911.pp.config.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jack911.pp.config.Tbl;

public class EquipTbl extends Tbl
{
	public List<EquipTblUnit> items = new ArrayList<>();
	/** 根据id获取单个配置 */
	public EquipTblUnit get(int id)
	{
		for(EquipTblUnit unit : items)
		{
			if(unit.id == id) { return unit; }
		}
		return null;
	}
	
	public EquipTbl()
	{
		this.type = "equip";
	}
	
	@Override
	public void parse(HashMap<String, String[]> dataDic, int itemCount)
	{
		for(int i = 0; i < itemCount; i++)
		{
			EquipTblUnit unit = new EquipTblUnit();
			unit.id = Integer.parseInt( dataDic.get("ID")[i] );
			unit.name = dataDic.get("物品名")[i];
			unit.rVal = Float.parseFloat( dataDic.get("R值")[i] );
			unit.rRat = Float.parseFloat( dataDic.get("R比例")[i] );
			unit.speedVal = Float.parseFloat( dataDic.get("速度值")[i] );
			unit.speedRat = Float.parseFloat( dataDic.get("速度比例")[i] );
			unit.stepVal = Short.parseShort( dataDic.get("步数值")[i] );
			unit.stepRat = Float.parseFloat( dataDic.get("步数比例")[i] );
			unit.protectTime = Integer.parseInt( dataDic.get("保护期")[i] );
			unit.skillId = Integer.parseInt( dataDic.get("特技id")[i] );
			unit.skillDesc = dataDic.get("特技描述")[i];
			
			System.out.println(unit);
			items.add(unit);
		}
	}
}
