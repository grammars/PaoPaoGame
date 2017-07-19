package jack911.pp.config.table
{
	import flash.utils.Dictionary;
	
	import jack911.pp.config.Tbl;

	public class GoodsTbl extends Tbl
	{
		public var items:Vector.<GoodsTblUnit> = new Vector.<GoodsTblUnit>();
		
		public function GoodsTbl()
		{
			super();
			this.type = "goods";
		}
		
		/** 根据id获取单个配置 */
		public function get(id:int):GoodsTblUnit
		{
			return cfgs[id];
		}
		
		/** 解析 */
		override public function parse(dataDic:Dictionary, itemCount:int):void
		{
			for(var i:int = 0; i < itemCount; i++)
			{
				var unit:GoodsTblUnit = new GoodsTblUnit();
				unit.id = parseInt(dataDic["ID"][i]);
				unit.name = (dataDic["物品名"][i]);
				unit.type = parseInt(dataDic["类型"][i]);
				unit.icon =  (dataDic["icon"][i]);
				unit.reqLevMin = parseInt(dataDic["要求等级下限"][i]);
				unit.reqLevMax = parseInt(dataDic["要求等级上限"][i]);
				unit.desc = (dataDic["说明"][i]);
				unit.maxHeap = parseInt(dataDic["最大堆叠数"][i]);
				unit.equipType = parseInt(dataDic["武器类型"][i]);
				//trace(unit);
				cfgs[unit.id] = unit;
				items.push(unit);
			}
		}
	}
}