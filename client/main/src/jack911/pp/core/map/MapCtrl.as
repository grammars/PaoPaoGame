package jack911.pp.core.map
{
	import flash.events.Event;
	
	import jack911.pp.core.map.events.MapConfigEvent;
	import jack911.pp.core.map.events.MapMonitor;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Layer;
	import jack911.pp.core.ape.PlayerApe;

	public class MapCtrl
	{
		public function MapCtrl()
		{
		}
		
		private var hero:PlayerApe;
		public function setHero(m:PlayerApe):void
		{
			this.hero = m;
			//this.hero.isHero = true;
		}
		public function getHero():PlayerApe { return hero; }
		
		
		/** 初始化 */
		public function initialize():void
		{
			MapMonitor.getInstance().addEventListener(MapConfigEvent.FULL_DONE, __ConfigDone);
			Base.stage.addEventListener(Event.RESIZE, __stageResize);
		}
		
		/** ENTER_FRAME */
		public function nextFrame():void
		{
			MapLoader.getInstance().nextFrame();
		}
		
		/** stageResize */
		private function __stageResize(e:Event):void
		{
			focusHero();
		}
		
		private function focusHero():void
		{
			if(hero)
			{
				Layer.scene.focusTarget(hero);
			}
		}
		
		/** 地图配置集<br>
		 * key:mapId(int) <br>
		 * value:对应的配置(MapConfig) */
		private var configDic:Object = new Object();
		/** 插入地图配置 */
		public function insertConfig(mapId:int, config:MapConfig):void
		{
			configDic[mapId] = config;
		}
		/** 获取地图配置 */
		public function retrieveConfig(mapId:int):MapConfig
		{
			if(configDic[mapId] == null) { configDic[mapId] = new MapConfig(); }
			return configDic[mapId];
		}
		/** 窥探地图配置，如果没有该配置，则返回null */
		public function peekConfig(mapId:int):MapConfig
		{
			return configDic[mapId];
		}
		
		/** 加载地图配置(但不会应用此配置) */
		public function loadConfig(mapId:int):void
		{
			MapLoader.getInstance().loadConfig(mapId);
		}
		
		/** 某一组metadata和.map部署完毕 */
		private function __ConfigDone(e:MapConfigEvent):void
		{
			trace("__ConfigDone", e.config);
			if(needLoadCfg && e.config.getMapId() == curMapId)
			{
				MapLoader.getInstance().changeMap(Layer.scene.map, e.config);
				needLoadCfg = false;
				focusHero();
			}
		}
		
		/** 获取当前MapConfig */
		public function getCurConfig():MapConfig
		{
			return peekConfig(curMapId);
		}
		
		/** 当前地图像素宽 */
		public function get curMapWidth():int
		{
			var cfg:MapConfig = getCurConfig();
			if(cfg) { return cfg.getMapWidth(); }
			return 0;
		}
		/** 当前地图像素高 */
		public function get curMapHeight():int
		{
			var cfg:MapConfig = getCurConfig();
			if(cfg) { return cfg.getMapHeight(); }
			return 0;
		}
		
		/** 是否需要下载地图配置 */
		private var needLoadCfg:Boolean = false;
		/** 当前地图ID */
		private var curMapId:int = 0;
		/** 获取当前地图ID */
		public function getCurMapId():int { return curMapId; }
		
		/** 切换地图 */
		public function changeMap(mapId:int):void
		{
			if(curMapId == mapId) { return; }
			curMapId = mapId;
			if(peekConfig(mapId))
			{
				var cfg:MapConfig = peekConfig(mapId);
				MapConfigEvent.dispatchMapConfigFullDone(cfg);
				MapLoader.getInstance().changeMap(Layer.scene.map, cfg);
			}
			else
			{
				needLoadCfg = true;
				loadConfig(mapId);
			}
		}
		
	}
}