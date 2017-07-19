package jack911.pp.game.scene.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import jack911.pp.game.Game;
import jack911.pp.game.scene.GameScene;

public abstract class ApePool implements Runnable
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 所属场景 */
	protected GameScene scene;
	
	ScheduledExecutorService service;
	
	/** 活着的ape数量 */
	private int activeApeCount = 0;
	
	/** 房间ID */
	protected int roomId = -1;
	/** 刷新频率 */
	protected long frequency = -1;
	/** 最大数量 */
	protected int max = -1;
	
	/** 池开始运作 */
	public void start()
	{
		scene = Game.scene.getScene(roomId);
		if(scene == null)
		{
			logger.error("池所需要的Scene不存在");
			return;
		}
		
		createInit();
		
		service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(this, frequency, frequency, TimeUnit.MILLISECONDS);
	}
	
	/** 池停止运作 */
	public void stop()
	{
		if(service != null)
		{
			service.shutdownNow();
			service = null;
		}
	}
	
	/** 增加活跃ape数 */
	private void increaseActive()
	{
		activeApeCount++;
	}
	/** 减少活跃ape数 */
	public void decreaseActive()
	{
		activeApeCount--;
	}
	/** 已达到最大活跃数 */
	private boolean reachMaxActive()
	{
		return activeApeCount >= max;
	}
	
	@Override
	public void run()
	{
		//logger.debug("Pool==>running");
		if( reachMaxActive() )
		{
			return;
		}
		logger.debug("Pool==>creating");
		
		createApe();
		
		increaseActive();
	}
	
	/** 创建初始化 */
	private void createInit()
	{
		while( !reachMaxActive() )
		{
			createApe();
			increaseActive();
		}
	}
	
	/** 创建一个ape */
	abstract protected void createApe();
	
}

