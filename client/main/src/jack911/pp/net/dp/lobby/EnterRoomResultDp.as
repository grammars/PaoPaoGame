package jack911.pp.net.dp.lobby
{
	import com.anstu.jsock.utils.EndianFacade;
	
	import flash.utils.ByteArray;

	public class EnterRoomResultDp
	{
		/** 错误码：成功 */
		public static const EC_SUCC:int = 0;
		/** 错误码：等级限制 */
		public static const EC_LEV_LMT:int = 1;
		/** 错误码：现金不够 */
		public static const EC_CASH_LESS:int = 2;
		
		public var errCode:int;
		public var roomId:int;
		
		public function EnterRoomResultDp()
		{
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			errCode = EndianFacade.readByte(bytes);
			roomId = EndianFacade.readInt(bytes);
		}
		
		public function getMessage():String
		{
			switch(errCode)
			{
			case EC_SUCC: return "进入房间成功~.~V";
			case EC_LEV_LMT: return "进入房间失败，等级限制！";
			case EC_CASH_LESS: return "进入房间失败，现金不够！";
			}
			return "";
		}
	}
}