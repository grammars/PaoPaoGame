package jack911.pp.core.map
{
	import flash.display.Sprite;
	
	public class MoveTarget extends Sprite
	{
		public function MoveTarget()
		{
			super();
			
			this.graphics.beginFill(0x00ffff, 0.8);
			this.graphics.drawRect(-25, -25, 50, 50);
			this.graphics.endFill();
		}
	}
}