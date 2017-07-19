package jack911.pp.config.table
{
	public class SampleTblUnit
	{
		/** 整数字段 */
		public var id:int = 0;
		/** 字符串字段 */
		public var name:String = "";
		/** 浮点字段 */
		public var score:Number = 0;
		
		public function SampleTblUnit()
		{
		}
		
		public function toString():String
		{
			return "[SampleTblUnit] id:" + id + " name:" + name
				+ " score=" + score;
		}
	}
}