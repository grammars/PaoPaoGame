package jack911.pp.config.table
{
	public class RoomTblUnit
	{
		/** ID */
		public var id:int;
		/** 房间名 */
		public var name:String;
		/** 等级下限 */
		public var levMin:int;
		/** 等级上限 */
		public var levMax:int;
		/** 描述 */
		public var desc:String;
		/** 缩略图 */
		public var thumbnail:String;
		/** 房间宽 */
		public var width:int;
		/** 房间高 */
		public var height:int;
		/** 步数 */
		public var stepNum:int;
		/** 玩家初始R */
		public var playerInitR:Number;
		/** 门票价格 */
		public var ticketPrice:int;
		
		public function toString():String
		{
			return "[RoomTblUnit] id:" + id + " name:" + name
				+ " lev(" + levMin + " - " + levMax + ") desc=" + desc
				+ " thumbnail=" + thumbnail + " width=" + width + " height=" + height
				+ " stepNum=" + stepNum + " playerInitR=" + playerInitR + " ticketPrice=" + ticketPrice;
		}
		
	}
}