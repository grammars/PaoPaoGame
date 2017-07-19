package jack911.pp.config
{
	public class Cfg
	{
		/** 是否同域 */
		public static const SAME_DOMAIN:Boolean = true;
		/** 命名空间 */
		public static const NS:String = "paopao";
		/** 资源根目录 */
		public static const RES_ROOT:String = "http://127.0.0.1/paopao/";
		/** 每帧耗时ms */
		public static const FRAME_MS:Number = 33;
		
		/** 游戏数据配置表的URL */
		public static function tablesUrl():String
		{
			return RES_ROOT + "tables/release.cfgbin";
		}
		
		/** 泡泡皮肤图片 */
		public static function ppSkinUrl(skinId:String):String
		{
			return RES_ROOT + "pp/"+skinId+".png";
		}
		
		public function Cfg()
		{
		}
		
		/** 是否开启Avatar监控模式 */
		public static var AVATAR_MONITOR_DEBUG:Boolean = true;
	}
}