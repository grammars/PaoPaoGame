package jack911.pp.game.component;

import jack911.pp.game.scene.GameScene;
import jack911.pp.game.scene.pool.MonsterPool;
import jack911.util.Timework;

public class MonsterApe extends Ape
{
	/** 所属怪物池 */
	protected MonsterPool parent;
	public void setParent(MonsterPool value)
	{
		this.parent = value;
	}
	public MonsterPool getParent() { return this.parent; }
	
	/** 伤害比例 */
	public float damageRat() { return 0.3f; }
	
	//自动移动
	private Timework mover;
	
	public MonsterApe()
	{
		this.type = TYPE_MONSTER;
		this.eatRat = 0f;
		
		mover =  new Timework()
		{
			@Override
			public void run()
			{
				float tx = (float)Math.random() * MonsterApe.this.scene.width();
				float ty = (float)Math.random() * MonsterApe.this.scene.height();
				MonsterApe.this.setTx(tx);
				MonsterApe.this.setTy(ty);
				MonsterApe.this.setMoving(true);
				MonsterApe.this.broadcastTarPos();
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
