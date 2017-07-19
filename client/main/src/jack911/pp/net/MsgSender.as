package jack911.pp.net
{
	import com.anstu.jcommon.def.CharsetConst;
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.config.ServerId;
	import jack911.pp.core.Base;

	public class MsgSender
	{
		private var bytes:ByteArray = new ByteArray();
		
		public function MsgSender()
		{
		}
		
		public function writeHead(serverId:int, majorId:int, minorId:int):void
		{
			var head:MsgHead = new MsgHead(serverId, majorId, minorId);
			head.writeTo(bytes);
		}
		
		//-------------------wrapper---beg-------------------------
		public function writeInt(value:int):void
		{
			EndianFacade.writeInt(bytes, value);
		}
		
		public function writeUnsignedInt(value:int):void
		{
			EndianFacade.writeUnsignedInt(bytes, value);
		}

		public function writeShort(value:int) : void
		{
			EndianFacade.writeShort(bytes, value);
		}
		
		public function writeBytes(value:*, length:int=0):void
		{
			EndianFacade.writeBytes(bytes, value);
		}

		public function writeByte(value:int):void
		{
			EndianFacade.writeByte(bytes, value);
		}

		public function writeBoolean(value:Boolean):void
		{
			EndianFacade.writeBoolean(bytes, value);
		}
		
		public function writeFloat(value:Number):void
		{
			EndianFacade.writeFloat(bytes, value);
		}
		
		public function writeDouble(value:Number):void
		{
			EndianFacade.writeDouble(bytes, value);
		}

		public function writeLong(value:Long):void
		{
			EndianFacade.writeLong(bytes, value);
		}
		
		public function writeString(value:String, length:int=-1, charset:String=CharsetConst.UTF8):void
		{
			EndianFacade.writeString(bytes, value, length, charset);
		}
		//-------------------wrapper---end-------------------------
		
		public function send():void
		{
			Base.net.send(bytes);
		}
		
		private static function toServer(serverId:int, majorId:int, minorId:int):MsgSender
		{
			var ms:MsgSender = new MsgSender();
			ms.writeHead(serverId, majorId, minorId);
			return ms;
		}
		
		public static function toCentre(majorId:int, minorId:int):MsgSender
		{
			return toServer(ServerId.CENTRE, majorId, minorId);
		}
		
		public static function toGame(majorId:int, minorId:int):MsgSender
		{
			return toServer(ServerId.GAME, majorId, minorId);
		}
		
		public static function toWorld(majorId:int, minorId:int):MsgSender
		{
			return toServer(ServerId.WORLD, majorId, minorId);
		}
	}
}