package jack911.pp.game.scene.pool;

import jack911.pp.config.table.FoodTblUnit;
import jack911.pp.game.component.FoodApe;

public class FoodPool extends ApePool
{
	private FoodTblUnit cfg;
	
	public FoodPool(FoodTblUnit cfg)
	{
		this.cfg = cfg;
		this.roomId = cfg.roomId;
		this.frequency = cfg.frequency;
		this.max = cfg.max;
	}
	
	@Override
	protected void createApe()
	{
		FoodApe food = new FoodApe();
		food.setParent(this);
		
		int bornX = (int)(Math.random() * (double)scene.width());
		int bornY = (int)(Math.random() * (double)scene.height());
		food.setX(bornX);
		food.setY(bornY);
		food.setR(cfg.r);
		food.setSpeed(cfg.speed);
		food.setName(cfg.name);
		food.setSkin(cfg.skin);
		scene.add(food);
	}
	
}

