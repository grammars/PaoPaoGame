package jack911.pp.config.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jack911.pp.config.Tbl;

public class RoomTbl extends Tbl
{
	public List<RoomTblUnit> items = new ArrayList<>();
	/** 根据id获取单个配置 */
	public RoomTblUnit get(int id)
	{
		for(RoomTblUnit unit : items)
		{
			if(unit.id == id) { return unit; }
		}
		return null;
	}
	
	public RoomTbl()
	{
		this.type = "room";
	}

	@Override
	public void parse(HashMap<String, String[]> dataDic, int itemCount)
	{
		for(int i = 0; i < itemCount; i++)
		{
			RoomTblUnit unit = new RoomTblUnit();
			unit.id = Integer.parseInt( dataDic.get("ID")[i] );
			unit.name = dataDic.get("房间名")[i];
			unit.levMin = Integer.parseInt( dataDic.get("等级下限")[i] );
			unit.levMax = Integer.parseInt( dataDic.get("等级上限")[i] );
			unit.desc = dataDic.get("描述")[i];
			unit.thumbnail = dataDic.get("缩略图")[i];
			unit.width = Integer.parseInt( dataDic.get("房间宽")[i] );
			unit.height = Integer.parseInt( dataDic.get("房间高")[i] );
			unit.stepNum = Short.parseShort( dataDic.get("步数")[i] );
			unit.playerInitR = Float.parseFloat( dataDic.get("玩家初始R")[i] );
			unit.ticketPrice = Integer.parseInt( dataDic.get("门票价格")[i] );
			
			System.out.println(unit);
			items.add(unit);
		}
	}
}
