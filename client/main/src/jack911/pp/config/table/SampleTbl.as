package jack911.pp.config.table
{
	import flash.utils.Dictionary;
	
	import jack911.pp.config.Tbl;
	
	public class SampleTbl extends Tbl
	{
		
		public function SampleTbl()
		{
			super();
			this.type = "sample";
		}
		
		/** 根据id获取单个配置 */
		public function get(id:int):SampleTblUnit
		{
			return cfgs[id];
		}
		
		/** 解析 */
		override public function parse(dataDic:Dictionary, itemCount:int):void
		{
			for(var i:int = 0; i < itemCount; i++)
			{
				var unit:SampleTblUnit = new SampleTblUnit();
				unit.id = parseInt(dataDic["整数字段"][i]);
				unit.name = (dataDic["字符串字段"][i]);
				unit.score = parseFloat(dataDic["浮点字段"][i]);
				//trace(unit);
				cfgs[unit.id] = unit;
			}
		}
		
	}
}