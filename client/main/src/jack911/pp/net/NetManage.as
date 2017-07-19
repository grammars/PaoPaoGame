package jack911.pp.net
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.JSock;
	import com.anstu.jsock.proxy.filter.FreeByteCodecFactory;
	import com.anstu.jsock.service.SocketService;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.AbstractManage;
	
	public class NetManage extends AbstractManage
	{
		private var service:SocketService;
		
		public function NetManage()
		{
			super();
		}
		
		/** 启动运行 */
		override public function startup():void
		{
			Log.debug("NetManage:startup()");
			
			service = JSock.createService(new FreeByteCodecFactory(), new NetIoHandler());
			
			startupComplete();
		}
		
		public function connect():void
		{
			service.connect("127.0.0.1", 8001);
		}
		
		public function send(bytes:ByteArray):void
		{
			service.sendMessage(bytes);
		}
	}
}