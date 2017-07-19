package jack911.pp.core.scene
{
	import flash.display.Sprite;
	
	import jack911.pp.core.map.MoveTarget;
	
	public class BackgroundLayer extends Sprite
	{
		private var mt:MoveTarget = new MoveTarget();
		
		public function BackgroundLayer()
		{
			super();
			this.mouseEnabled = this.mouseChildren = false;
			this.addChild(mt);
		}
		
		public function setTarget(tx:int, ty:int):void
		{
			mt.x = tx;
			mt.y = ty;
		}
		
	}
}