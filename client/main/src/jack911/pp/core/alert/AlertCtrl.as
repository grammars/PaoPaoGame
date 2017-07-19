package jack911.pp.core.alert
{
	import com.anstu.jcommon.log.Log;
	
	import jack911.pp.ui.alert.AlertTip;

	public class AlertCtrl
	{
		public function AlertCtrl()
		{
		}
		
		public function show(msg:String):void
		{
			Log.warn(msg);
			new AlertTip().show(msg);
		}
	}
}