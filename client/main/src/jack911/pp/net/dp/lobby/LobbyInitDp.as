package jack911.pp.net.dp.lobby
{
	import com.anstu.jsock.utils.EndianFacade;
	
	import flash.utils.ByteArray;

	public class LobbyInitDp
	{
		public var debug:String;
		
		public function LobbyInitDp()
		{
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			debug = EndianFacade.readString(bytes);
		}
	}
}