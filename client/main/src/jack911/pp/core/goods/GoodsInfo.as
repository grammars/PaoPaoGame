package jack911.pp.core.goods
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	
	import flash.utils.ByteArray;
	
	import jack911.pp.config.table.GoodsTblUnit;
	import jack911.pp.core.Base;

	public class GoodsInfo
	{
		/** 唯一id */
		public var uid:Long;
		/** 物品位置 */
		public var index:int = 0;
		/** 物品配置id */
		public var cfgId:int = 0;
		/** 物品数量 */
		public var num:int = 0;
		
		/** 物品配置 */
		public var cfg:GoodsTblUnit;
		
		public function equipType():int { return cfg.equipType; }
		
		public function getIcon():String
		{
			switch(cfg.type)
			{
			case GoodsType.SYSTEM: return "goods/system/"+cfg.icon+".jpg";
			case GoodsType.DRUG: return "goods/drug/"+cfg.icon+".jpg";
			case GoodsType.TASK: return "goods/task/"+cfg.icon+".jpg";
			case GoodsType.EQUIP: return "goods/equip/"+cfg.icon+".jpg";
			case GoodsType.GEM: return "goods/gem/"+cfg.icon+".jpg";
			case GoodsType.GIFT: return "goods/gift/"+cfg.icon+".jpg";
			}
			return "";
		}
		
		public function isDrug():Boolean { return cfg.type == GoodsType.DRUG; }
		public function isTask():Boolean { return cfg.type == GoodsType.TASK; }
		public function isEquip():Boolean { return cfg.type == GoodsType.EQUIP; }
		
		/** 数据是否合法可用 */
		public function available():Boolean
		{
			return cfg != null;
		}
		
		public function GoodsInfo()
		{
		}
		
		public function create(baseCfgId:int):void
		{
			this.cfgId = baseCfgId;
			build();
		}
		
		protected function build():void
		{
			this.cfg = Base.config.goods.get(cfgId);
			if(cfg == null)
			{
				Log.error("找不到mid=" + cfgId + "的物品");
			}
		}
		
		public function read(bytes:ByteArray):void
		{
			uid = EndianFacade.readLong(bytes);
			index = EndianFacade.readInt(bytes);
			cfgId = EndianFacade.readInt(bytes);
			num = EndianFacade.readInt(bytes);
			build();
		}
		
		public function copy(source:GoodsInfo):void
		{
			this.uid.clone(source.uid);
			this.index = source.index;
			this.cfgId = source.cfgId;
			this.num = source.num;
			build();
		}
		
	}
}