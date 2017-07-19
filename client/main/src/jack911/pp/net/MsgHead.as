package jack911.pp.net
{
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.config.ServerId;

	public class MsgHead
	{
		public var source:int;
		public var target:int;
		public var majorId:int;
		public var minorId:int;
		public var cccid:Long;
		
		public function MsgHead(targetServerId:int=0, majorId:int=0, minorId:int=0)
		{
			this.target = targetServerId;
			this.majorId = majorId;
			this.minorId = minorId;
		}
		
		public function writeTo(bytes:ByteArray):void
		{
			EndianFacade.writeByte(bytes, ServerId.CLIENT);
			EndianFacade.writeByte(bytes, target);
			EndianFacade.writeShort(bytes, majorId);
			EndianFacade.writeShort(bytes, minorId);
			EndianFacade.writeLong(bytes, new Long());
		}
		
		public function readFrom(bytes:ByteArray):MsgHead
		{
			this.source = EndianFacade.readByte(bytes);
			this.target = EndianFacade.readByte(bytes);
			this.majorId = EndianFacade.readShort(bytes);
			this.minorId = EndianFacade.readShort(bytes);
			this.cccid = EndianFacade.readLong(bytes);
			return this;
		}
		
		public function toString():String
		{
			return "source=" + source + " target=" + target + " majorId=" + majorId + " minorId=" + minorId + " cccid:" + cccid;
		}
	}
}