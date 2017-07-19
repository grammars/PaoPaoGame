package jack911.pp.net
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.handler.IoHandler;
	import com.anstu.jsock.service.SocketService;
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Game;
	
	public class NetIoHandler implements IoHandler
	{
		public function NetIoHandler()
		{
		}
		
		public function sessionOpened(service:SocketService):void
		{
			Log.info("NetIoHandler:sessionOpened");
			Game.login.tryLogin();
		}
		
		public function sessionClosed(service:SocketService):void
		{
			Log.info("NetIoHandler:sessionClosed");
		}
		
		public function exceptionCaught(service:SocketService, err:Error):void
		{
			Log.warn("NetIoHandler:exceptionCaught");
		}
		
		public function messageReceived(service:SocketService, message:Object):void
		{
			Log.print("NetIoHandler:messageReceived");
			var bytes:ByteArray = message as ByteArray;
			var head:MsgHead = new MsgHead();
			head.readFrom(bytes);
			MsgDistributor.handle(head, bytes);
		}
	}
}