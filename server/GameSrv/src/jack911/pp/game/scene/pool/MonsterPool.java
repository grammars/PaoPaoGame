package jack911.pp.game.scene.pool;

import jack911.pp.config.table.MonsterTblUnit;
import jack911.pp.game.component.MonsterApe;

public class MonsterPool extends ApePool
{
	private MonsterTblUnit cfg;
	
	public MonsterPool(MonsterTblUnit cfg)
	{
		this.cfg = cfg;
		this.roomId = cfg.roomId;
		this.frequency = cfg.frequency;
		this.max = cfg.max;
	}
	
	@Override
	protected void createApe()
	{
		MonsterApe monster = new MonsterApe();
		monster.setParent(this);
		
		int bornX = (int)(Math.random() * (double)scene.width());
		int bornY = (int)(Math.random() * (double)scene.height());
		monster.setX(bornX);
		monster.setY(bornY);
		monster.setR(cfg.r);
		monster.setSpeed(cfg.speed);
		monster.setName(cfg.name);
		monster.setSkin(cfg.skin);
		scene.add(monster);
	}
	
}

