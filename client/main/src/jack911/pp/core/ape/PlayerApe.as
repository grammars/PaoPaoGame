package jack911.pp.core.ape
{
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Base;

	public class PlayerApe extends Ape
	{
		public var cccid:Long;
		public var level:int;
		
		public function PlayerApe()
		{
			super();
		}
		
		override public function isHero():Boolean
		{
			return Base.cccid.euqals(cccid);
		}
		
		override public function paint():void
		{
			super.paint();
		}
		
		override public function readFrom(bytes:ByteArray):void
		{
			super.readFrom(bytes);
			this.cccid = EndianFacade.readLong(bytes);
			this.level = EndianFacade.readInt(bytes);
			
			paint();
		}
	}
}