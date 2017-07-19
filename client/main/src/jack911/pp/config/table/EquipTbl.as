package jack911.pp.config.table
{
	import flash.utils.Dictionary;
	
	import jack911.pp.config.Tbl;

	public class EquipTbl extends Tbl
	{
		public var items:Vector.<EquipTblUnit> = new Vector.<EquipTblUnit>();
		
		public function EquipTbl()
		{
			super();
			this.type = "equip";
		}
		
		/** 根据id获取单个配置 */
		public function get(id:int):EquipTblUnit
		{
			return cfgs[id];
		}
		
		/** 解析 */
		override public function parse(dataDic:Dictionary, itemCount:int):void
		{
			for(var i:int = 0; i < itemCount; i++)
			{
				var unit:EquipTblUnit = new EquipTblUnit();
				unit.id = parseInt(dataDic["ID"][i]);
				unit.name = dataDic["物品名"][i];
				unit.rVal = parseFloat( dataDic["R值"][i] );
				unit.rRat = parseFloat( dataDic["R比例"][i] );
				unit.speedVal = parseFloat( dataDic["速度值"][i] );
				unit.speedRat = parseFloat( dataDic["速度比例"][i] );
				unit.stepVal = parseInt( dataDic["步数值"][i] );
				unit.stepRat = parseFloat( dataDic["步数比例"][i] );
				unit.protectTime = parseInt( dataDic["保护期"][i] );
				unit.skillId = parseInt( dataDic["特技id"][i] );
				unit.skillDesc = dataDic["特技描述"][i];
				//trace(unit);
				cfgs[unit.id] = unit;
				items.push(unit);
			}
		}
	}
}