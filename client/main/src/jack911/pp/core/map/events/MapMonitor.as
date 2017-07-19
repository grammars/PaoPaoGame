package jack911.pp.core.map.events
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	[Event(name="MapConfigEvent.FULL_DONE", type="jack911.pp.core.map.events.MapConfigEvent")]
	[Event(name="MapEvent.CLICK", type="jack911.pp.core.map.events.MapEvent")]
	[Event(name="MapEvent.CHANGE", type="jack911.pp.core.map.events.MapEvent")]
	
	public class MapMonitor extends EventDispatcher
	{
		private static var instance:MapMonitor;
		
		public static function getInstance():MapMonitor
		{
			if(instance == null) { instance = new MapMonitor(); }
			return instance;
		}
		
		public function MapMonitor(target:IEventDispatcher=null)
		{
			super(target);
		}
		
	}
}