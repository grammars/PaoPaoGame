package jack911.pp.utils
{
	
	import jack911.pp.core.ape.Ape;

	public class GeomUtils
	{
		public function GeomUtils()
		{
		}
		
		/** 计算距离 */
		public static function distance(fromX:Number, fromY:Number, toX:Number, toY:Number):Number
		{
			var sq:Number = Math.pow(toX-fromX, 2) + Math.pow(toY-fromY, 2);
			return Math.sqrt(sq);
		}
		
	}
}