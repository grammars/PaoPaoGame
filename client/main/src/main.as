package
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jcommon.log.LogLevel;
	import com.anstu.jload.JLoadLog;
	
	import flash.display.MovieClip;
	import flash.display.Sprite;
	
	import jack911.pp.core.Base;
	
	[SWF(backgroundColor = "#6C2535", frameRate = "30", width="960", height="640")]
	public class main extends Sprite
	{
		public function main()
		{
			Log.setLevel(LogLevel.ALL);
			JLoadLog.USE_LOG = false;
			
			Base.startup(this);
		}
		
	}
}