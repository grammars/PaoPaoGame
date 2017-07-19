package jack911.pp.bootstrap
{
	import com.anstu.jcommon.log.Log;
	
	import jack911.pp.core.AbstractManage;
	import jack911.pp.core.Layer;
	
	public class BootstrapManage extends AbstractManage
	{
		private var welcome:Welcome;
		
		public function BootstrapManage()
		{
			super();
		}
		
		/** 启动运行 */
		override public function startup():void
		{
			Log.debug("BootstrapManage:startup()");
			Layer.setMode(Layer.MODE_WELCOME);
			welcome = new Welcome(__welcomeOver);
			startupComplete();
		}
		
		private function __welcomeOver():void
		{
			welcome = null;
		}
	}
}