package jack911.pp.config
{
	import flash.utils.Dictionary;

	public class Tbl
	{	
		public var type:String = "";
		
		protected var cfgs:Dictionary = new Dictionary();
		
		
		public function Tbl()
		{
		}
		
		/** 解析 to be override */
		public function parse(dataDic:Dictionary, itemCount:int):void
		{
			//TODO
		}
	}
}