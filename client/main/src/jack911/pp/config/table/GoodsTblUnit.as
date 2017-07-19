package jack911.pp.config.table
{
	public class GoodsTblUnit
	{
		/** ID */
		public var id:int = 0;
		/** 物品名 */
		public var name:String = "";
		/** 类型 */
		public var type:int = 0;
		/** icon */
		public var icon:String = "";
		/** 要求等级下限 */
		public var reqLevMin:int = 0;
		/** 要求等级上限 */
		public var reqLevMax:int = 0;
		/** 说明 */
		public var desc:String = "";
		/** 最大堆叠数 */
		public var maxHeap:int = 1;
		/** 武器类型 */
		public var equipType:int = 0;
		
		public function GoodsTblUnit()
		{
		}
	}
}