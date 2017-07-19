package jack911.pp.server;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class MsgSendDriver extends Thread
{
	private static Logger logger = Logger.getLogger(MsgSendDriver.class);
	
	private volatile boolean running = true;
	
	private LinkedBlockingQueue<MsgUnit> queue = new LinkedBlockingQueue<>(4096);
	
	private static MsgSendDriver instance = new MsgSendDriver();
	public static MsgSendDriver getInstance() { return instance; }
	private MsgSendDriver() { this.setName("MsgSendDriver"); }
	
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
			if( msg.ctx != null )
			{
				msg.ctx.writeAndFlush(msg.content);
			}
			else
			{
				logger.error("msg.ctx==null");
			}
		}
	}
	
	
	/** 停止消息驱动 */
	public void shutdown()
	{
		running = false;
	}
}