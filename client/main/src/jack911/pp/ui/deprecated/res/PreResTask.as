package jack911.pp.ui.deprecated.res
{
	import com.anstu.jload.JLoadTask;
	
	import jack911.pp.config.Cfg;
	
	public class PreResTask extends JLoadTask
	{
		public var tag:String;
		
		public function PreResTask(tag:String, url:String)
		{
			super(JLoadTask.TYPE_DISPLAY_CONTENT, url, Cfg.SAME_DOMAIN, 999);
			this.tag = tag;
			this.onComplete = PreRes.picLoaded;
		}
	}
}