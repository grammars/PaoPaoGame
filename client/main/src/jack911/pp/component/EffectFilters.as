package jack911.pp.component
{
	import flash.filters.ColorMatrixFilter;
	import flash.filters.GlowFilter;

	public class EffectFilters
	{
		/** 泡泡经过 */
		public static const PP_OVER:GlowFilter = new GlowFilter(0xFF8C3A, 1, 4, 4, 4, 1);
		
		/** 灰化矩阵 */
		private static const greyMx:Array = [
			0.3086, 0.6094, 0.0820, 0, 0,  
			0.3086, 0.6094, 0.0820, 0, 0,  
			0.3086, 0.6094, 0.0820, 0, 0,  
			0,      0,      0,      1, 0
		]; 
		/** 灰化滤镜 */
		public static const GREY:ColorMatrixFilter = new ColorMatrixFilter(greyMx);
		
		
		/** 特效滤镜 */
		public function EffectFilters()
		{
		}
		
	}
}