package jack911.pp.game.scene;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import jack911.pp.config.Cfg;
import jack911.pp.config.table.RoomTblUnit;
import jack911.pp.game.Game;
import jack911.pp.game.component.Ape;
import jack911.pp.game.component.MonsterApe;
import jack911.pp.game.component.PlayerApe;
import jack911.pp.game.msg.dp.scene.SceneInitDp;

public class GameScene implements Runnable
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	private RoomTblUnit cfg;
	
	public ScheduledExecutorService service;
	
	private CollisionResolver collision = new CollisionResolver();
	
	public List<Ape> apes = new CopyOnWriteArrayList<>();
	public List<PlayerApe> players = new CopyOnWriteArrayList<>();
	public List<MonsterApe> monsters = new CopyOnWriteArrayList<>();
	
	public int id()
	{
		return cfg.id;
	}
	
	public int width() { return cfg.width; }
	public int height() { return cfg.height; }
	
	public GameScene()
	{
		service = Executors.newScheduledThreadPool(1);
	}
	
	public void initialize(RoomTblUnit cfg)
	{
		this.cfg = cfg;
		service.scheduleAtFixedRate(this, 10000, Cfg.FRAME_MS, TimeUnit.MILLISECONDS);
	}
	
	/** 停止场景服务 */
	public void stop()
	{
		service.shutdown();
	}
	
	public void add(Ape a)
	{
		a.enter(this);
		a.collide(3000);//进入场景之后默认增加3秒碰撞保护
		//坐标随机化
		a.setX( (float)(width() * Math.random()) );
		a.setY( (float)(height() * Math.random()) );
		
		for(PlayerApe p : players)
		{
			Game.msg.scene.sendAddApeCmd(a, p.cccid());
		}
		if(a instanceof PlayerApe)
		{
			PlayerApe p = (PlayerApe)a;
			p.setR(30);
			p.setStep(this.cfg.stepNum);
			players.add(p);
		}
		else if(a instanceof MonsterApe)
		{
			monsters.add((MonsterApe)a);
		}
		apes.add(a);
	}
	
	public void remove(Ape a)
	{
		boolean has = apes.remove(a);
		if(has)
		{
			for(PlayerApe p : players)
			{
				Game.msg.scene.sendRemoveApeCmd(a.getId(), p.cccid());
			}
		}
		players.remove(a);
		monsters.remove(a);
	}
	
	@Override
	public void run()
	{
		long begTime = System.currentTimeMillis();
		
		try
		{	
			Iterator<Ape> iter = apes.iterator();
			while(iter.hasNext())
			{
				Ape a = iter.next();
				a.update();
			}
			//
			int num = apes.size();
			for(int i = 0; i < num; i++)
			{
				Ape a = apes.get(i);
				for(int j = i+1; j < num; j++)
				{
					Ape b = apes.get(j);
					collision.addTest(a, b);
				}
			}
			collision.doTest();
		}
		catch(Exception e)
		{
			//不try catch的话，一旦 try中抛出异常，会使ScheduledExecutorService停止
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		long costTime = endTime - begTime;
		
		if(costTime > Cfg.FRAME_MS)
		{
			logger.warn("场景更新消耗超时：" + costTime);
		}
	}
	
	public SceneInitDp toInitDp()
	{
		SceneInitDp dp = new SceneInitDp();
		dp.roomId = id();
		return dp;
	}
}
