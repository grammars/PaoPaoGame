package jack911.pp.net.dp.scene
{
	import com.anstu.jsock.utils.EndianFacade;
	
	import flash.utils.ByteArray;

	public class SceneInitDp
	{
		public var roomId:int;
		
		public function SceneInitDp()
		{
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			roomId = EndianFacade.readInt(bytes);
		}
		
		public function toString():String
		{
			return "[SceneInitDp] roomId=" + roomId;
		}
		
		
	}
}