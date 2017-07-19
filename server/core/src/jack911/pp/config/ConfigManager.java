package jack911.pp.config;

import jack911.pp.config.table.EquipTbl;
import jack911.pp.config.table.FoodTbl;
import jack911.pp.config.table.GoodsTbl;
import jack911.pp.config.table.MonsterTbl;
import jack911.pp.config.table.RoomTbl;
import jack911.pp.config.table.SampleTbl;

public class ConfigManager
{
	public static SampleTbl sample = new SampleTbl();
	public static RoomTbl room = new RoomTbl();
	public static MonsterTbl monster = new MonsterTbl();
	public static FoodTbl food = new FoodTbl();
	public static GoodsTbl goods = new GoodsTbl();
	public static EquipTbl equip = new EquipTbl();
	
	public static void load()
	{
		sample.load();
		room.load();
		monster.load();
		food.load();
		goods.load();
		equip.load();
	}
}
