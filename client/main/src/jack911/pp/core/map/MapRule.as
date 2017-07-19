package jack911.pp.core.map
{	
	import jack911.pp.config.Cfg;

	public class MapRule
	{
		public function MapRule()
		{
		}
		
		public static function getMetaAddr(mapId:int):String
		{
			return Cfg.RES_ROOT + "/map/" + mapId + "/metadata";
		}
		
		public static function getCfgAddr(mapId:int):String
		{
			return Cfg.RES_ROOT + "/map/" + mapId + "/" + mapId + ".map";
		}
		
		public static function getTilesFolder(mapId:int):String
		{
			return Cfg.RES_ROOT + "/map/" + mapId + "/tiles/";
		}
		
		public static function getTileAddr(mapId:int, indX:int, indY:int):String
		{
			return Cfg.RES_ROOT + "/map/" + mapId + "/tiles/" + indY + "_" + indX + ".jpg";
		}
		
		public static function getPreviewAddr(mapId:int):String
		{
			return Cfg.RES_ROOT + "/map/" + mapId + "/preview.jpg";
		}
		
	}
}