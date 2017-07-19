package jack911.pp.game.component;

import jack911.pp.game.scene.GameScene;
import jack911.pp.game.scene.pool.FoodPool;
import jack911.util.Timework;

public class FoodApe extends Ape
{
	/** 所属食物池 */
	protected FoodPool parent;
	public void setParent(FoodPool value)
	{
		this.parent = value;
	}
	public FoodPool getParent() { return this.parent; }
	
	//自动移动
	private Timework mover;
	
	public FoodApe()
	{
		this.type = TYPE_FOOD;
		this.eatRat = 1.0f;
		
		mover =  new Timework()
		{
			@Override
			public void run()
			{
				float tx = (float)Math.random() * FoodApe.this.scene.width();
				float ty = (float)Math.random() * FoodApe.this.scene.height();
				FoodApe.this.setTx(tx);
				FoodApe.this.setTy(ty);
				FoodApe.this.setMoving(true);
				FoodApe.this.broadcastTarPos();
			}
		};
	}
	
	@Override
	public void enter(GameScene scene)
	{
		super.enter(scene);
		final long INTERVAL = 5000;
		
		mover.setInterval((long)(Math.random()*(double)INTERVAL), INTERVAL);
	}
	
	@Override
	public void exit()
	{
		super.exit();
		mover.clearInterval();
		this.parent.decreaseActive();
	}
	
	@Override
	public void update()
	{
		super.update();
	}
}
