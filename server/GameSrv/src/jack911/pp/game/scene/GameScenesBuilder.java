package jack911.pp.game.scene;

import java.util.ArrayList;
import java.util.List;

import jack911.pp.config.ConfigManager;
import jack911.pp.config.table.FoodTblUnit;
import jack911.pp.config.table.MonsterTblUnit;
import jack911.pp.config.table.RoomTblUnit;
import jack911.pp.game.scene.pool.FoodPool;
import jack911.pp.game.scene.pool.MonsterPool;

public class GameScenesBuilder
{
	public List<GameScene> build()
	{
		List<GameScene> scenes = new ArrayList<>();
		
		for( RoomTblUnit cfg : ConfigManager.room.items)
		{
			GameScene scene = new GameScene();
			scene.initialize(cfg);
			scenes.add(scene);
		}
		
		return scenes;
	}
	
	public void buildMonster()
	{
		for( MonsterTblUnit cfg : ConfigManager.monster.items )
		{
			MonsterPool pool = new MonsterPool(cfg);
			pool.start();
		}
	}
	
	public void buildFood()
	{
		for( FoodTblUnit cfg : ConfigManager.food.items )
		{
			FoodPool pool = new FoodPool(cfg);
			pool.start();
		}
	}
}
