package jack911.pp.core.ape
{
	import jack911.pp.core.Layer;

	public class ApeCtrl
	{
		public function ApeCtrl()
		{
		}
		
		public function nextFrame():void
		{
			Layer.scene.avatar.nextFrame();
		}
	}
}