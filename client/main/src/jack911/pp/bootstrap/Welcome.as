package jack911.pp.bootstrap
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jload.JLoadTask;
	import com.anstu.jload.JLoader;
	
	import flash.display.MovieClip;
	import flash.events.Event;
	
	import jack911.pp.config.Cfg;
	import jack911.pp.core.Base;

	public class Welcome
	{
		private var overFunc:Function;
		private var mv:MovieClip;
		
		public function Welcome(overFunc:Function)
		{
			this.overFunc = overFunc;
			
			var welcomeUrl:String = "http://127.0.0.1/paopao/mv/welcome.swf";
			var l:JLoader = new JLoader();
			l.add(JLoadTask.TYPE_DISPLAY_CONTENT, welcomeUrl, Cfg.SAME_DOMAIN, 0, __welcomeMvLoaded);
			l.start();
		}
		
		private function __welcomeMvLoaded(task:JLoadTask):void
		{
			Log.debug("__welcomeMvLoaded");
			mv = task.result.getMovieClip();
			mv.addEventListener(Event.ENTER_FRAME, __mvEnterFrame);
			Base.main.addChild(mv);
		}
		
		private function __mvEnterFrame(event:Event):void
		{
			if(mv != null)
			{
				//trace(mv.currentFrame +"/"+ mv.totalFrames);
				if(mv.currentFrame >= mv.totalFrames)
				{
					over();
				}
			}
			else
			{
				over();
			}
		}
		
		private function over():void
		{
			if(mv != null)
			{
				mv.removeEventListener(Event.ENTER_FRAME, __mvEnterFrame);
				if(mv.parent) { mv.parent.removeChild(mv); }
				mv.stop();
				mv = null;
			}
			overFunc();
			overFunc = null;
		}
		
	}
}