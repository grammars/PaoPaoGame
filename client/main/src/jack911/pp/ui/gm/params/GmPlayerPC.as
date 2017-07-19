package jack911.pp.ui.gm.params
{
	import jack911.pp.ui.gm.GmParamCmd;

	public class GmPlayerPC extends GmParamCmd
	{
		/** 改变玩家等级 */
		private static const CHANGE_LEVEL:int = 1;
		/** 改变玩家现金 */
		private static const CHANGE_CASH:int = 2;
		
		public function GmPlayerPC(label:String="", mainCmdId:int=0, subCmdId:int=0)
		{
			super(label, mainCmdId, subCmdId);
		}
		
		override protected function cmdHandler():void
		{
			switch(subCmdId)
			{
			case CHANGE_LEVEL:
				int0Label = "等级:";
				int0Input = "24";
				break;
			case CHANGE_CASH:
				int0Label = "现金:";
				int0Input = "100";
				break;
			}
		}
		
		/** 获得所有选项值 */
		public static function items():Array
		{
			var arr:Array = [];
			arr.push(new GmPlayerPC("改变玩家等级", GmParamCmd.TYPE_PLAYER, CHANGE_LEVEL));
			arr.push(new GmPlayerPC("改变玩家现金", GmParamCmd.TYPE_PLAYER, CHANGE_CASH));
			return arr;
		}
		
	}
}