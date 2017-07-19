package jack911.pp.server;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class MsgRecvDriver extends Thread
{
	private static Logger logger = Logger.getLogger(MsgRecvDriver.class);
	
	private volatile boolean running = true;
	
	private LinkedBlockingQueue<MsgUnit> queue = new LinkedBlockingQueue<>(4096);
	
	private static MsgRecvDriver instance = new MsgRecvDriver();
	public static MsgRecvDriver getInstance() { return instance; }
	private MsgRecvDriver() { this.setName("MsgRecvDriver"); }
	
	/** 放入一个消息 */
	public void put(MsgUnit msg)
	{
		queue.add(msg);
	}
	
	/** 取出一个消息 */
	public MsgUnit get()
	{
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void run() 
	{
		while(running)
		{
			MsgUnit msg = get();
			try
			{
				ServerManager.msgDistributor.handle(msg);		
			}
			catch(IndexOutOfBoundsException ex)
			{
				logger.debug("消息长度异常", ex);
				msg.ctx.close();
			}
		}
	}
	
	/** 停止消息驱动 */
	public void shutdown()
	{
		running = false;
	}
}
