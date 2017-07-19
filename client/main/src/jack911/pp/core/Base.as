package jack911.pp.core
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.utils.Long;
	
	import flash.display.Sprite;
	import flash.display.Stage;
	import flash.events.Event;
	
	import jack911.pp.bootstrap.BootstrapManage;
	import jack911.pp.config.ConfigManage;
	import jack911.pp.device.DeviceManage;
	import jack911.pp.net.NetManage;
	import jack911.pp.ui.UIManage;

	public class Base
	{
		public static const bootstrap:BootstrapManage = new BootstrapManage();
		public static const config:ConfigManage = new ConfigManage();
		public static const device:DeviceManage = new DeviceManage();
		public static const ui:UIManage = new UIManage();
		public static const net:NetManage = new NetManage();
		
		public static var main:Sprite;
		public static var stage:Stage;
		
		public static var cccid:Long;
		
		public function Base()
		{
		}
		
		public static function startup(pMain:Sprite):void
		{
			main = pMain;
			stage = pMain.stage;
			Layer.setup();
			bootstrap.next(config).next(device).next(ui).next(net).startupCompleteCallback = startupAllDone;
			bootstrap.startup();
		}
		
		private static function startupAllDone():void
		{
			Log.debug("所有管理器启动完毕");
			stage.addEventListener(Event.ENTER_FRAME, __nextFrame);
			
			Game.initialize();
			
			Game.map.changeMap(1001);
		}
		
		private static function __nextFrame(e:Event):void
		{
			Game.ape.nextFrame();
			Game.map.nextFrame();
		}
		
	}
}