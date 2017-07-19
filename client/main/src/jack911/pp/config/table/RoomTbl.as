package jack911.pp.config.table
{
	import flash.utils.Dictionary;
	
	import jack911.pp.config.Tbl;
	
	public class RoomTbl extends Tbl
	{
		public var items:Vector.<RoomTblUnit> = new Vector.<RoomTblUnit>();
		
		public function RoomTbl()
		{
			super();
			this.type = "room";
		}
		
		/** 根据id获取单个配置 */
		public function get(id:int):RoomTblUnit
		{
			return cfgs[id];
		}
		
		/** 解析 */
		override public function parse(dataDic:Dictionary, itemCount:int):void
		{
			for(var i:int = 0; i < itemCount; i++)
			{
				var unit:RoomTblUnit = new RoomTblUnit();
				unit.id = parseInt(dataDic["ID"][i]);
				unit.name = (dataDic["房间名"][i]);
				unit.levMin = parseInt(dataDic["等级下限"][i]);
				unit.levMax = parseInt(dataDic["等级上限"][i]);
				unit.desc = (dataDic["描述"][i]);
				unit.thumbnail = (dataDic["缩略图"][i]);
				unit.width = parseInt(dataDic["房间宽"][i]);
				unit.height = parseInt(dataDic["房间高"][i]);
				unit.stepNum = parseInt(dataDic["步数"][i]);
				unit.playerInitR = parseFloat(dataDic["玩家初始R"][i]);
				unit.ticketPrice = parseInt(dataDic["门票价格"][i]);
				//trace(unit);
				cfgs[unit.id] = unit;
				items.push(unit);
			}
		}
		
	}
}