package jack911.pp.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import io.netty.buffer.ByteBuf;

import org.apache.log4j.Logger;

import jack911.netty.BBT;
import jack911.pp.Setup;

public class PPTest
{
	private static Logger logger = Logger.getLogger(PPTest.class);
	
	private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(4096);
	
	public static void main(String[] args) //throws InterruptedException
	{
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add(0, "new");
		System.out.println(list);
	}
	
	
	
	@SuppressWarnings("unused")
	private static void xxx()
	{
		Setup.execute();
		logger.debug("调试");
		
		ByteBuf bb = BBT.create();
		SB(bb, "刚创建");
		
		bb.writeInt(17173);
		SB(bb, "写入了int");
		System.out.println( "bb.readInt()==>" + bb.readInt() );
		SB(bb, "读取了int");
		
		BBT.writeString(bb, "中文abc123");
		SB(bb, "写入了字符串");
		System.out.println( "BBT.readString(bb)==>" + BBT.readString(bb) );
		SB(bb, "读取了字符串");
		
		try
		{
			Thread.sleep(10000);
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		queue.add("a");
		queue.add("b");
		
		new Thread(){
			public void run() {
				System.out.println("新的线程开始了，但是要睡眠2秒");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("即将加一坨屎");
				queue.add("shit");
				System.out.println("填了一坨屎");
			};
		}.start();
		
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
	
	private static void SB(ByteBuf bb, String msg)
	{
		System.out.println("" + msg  + " capacity=" + bb.capacity() + "  reader=" + bb.readerIndex() +"  writer=" + bb.writerIndex() + "  available=" + bb.readableBytes());
	}

}
