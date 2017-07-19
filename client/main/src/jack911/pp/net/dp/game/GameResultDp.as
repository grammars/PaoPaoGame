package jack911.pp.net.dp.game
{
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;

	public class GameResultDp
	{
		/** 玩家cccid */
		public var cccid:Long;
		/** 玩家结果r值 */
		public var r:Number;
		/** 房间id */
		public var roomId:int;
		
		public function GameResultDp()
		{
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			this.cccid = EndianFacade.readLong(bytes);
			this.r = EndianFacade.readFloat(bytes);
			this.roomId = EndianFacade.readInt(bytes);
		}
		
		public function toString():String
		{
			return "GameResultDp [cccid=" + cccid + ", r=" + r + ", roomId=" + roomId + "]";
		}
	}
}