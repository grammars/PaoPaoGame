package jack911.pp.core.goods
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.utils.EndianFacade;
	
	
	import flash.utils.ByteArray;
	
	import jack911.pp.config.table.GoodsTblUnit;
	import jack911.pp.core.Base;

	public class GoodsFactory
	{
		public function GoodsFactory()
		{
		}
		
		/** 创建一个物品信息
		 * @param mid 物品的模版表id */
		public static function createInfo(baseCfgId:int):GoodsInfo
		{
			var baseCfg:GoodsTblUnit = Base.config.goods.get(baseCfgId);
			if(baseCfg == null)
			{
				Log.error("找不到baseCfgId=" + baseCfgId + "的物品");
				return null;
			}
			var info:GoodsInfo = create(baseCfg.type);
			info.create(baseCfgId);
			return info;
		}
		
		public static function create(type:int):GoodsInfo
		{
			return new GoodsInfo();
		}
		
		/** 从IoBuffer中读取 */
		public static function readInfo(bytes:ByteArray):GoodsInfo
		{
			var baseCfgId:int = EndianFacade.readInt(bytes);
			var info:GoodsInfo = createInfo(baseCfgId);
			info.read(bytes);
			return info;
		}
		
	}
}