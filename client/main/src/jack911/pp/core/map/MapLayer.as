package jack911.pp.core.map
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	
	import jack911.pp.core.Game;
	import jack911.pp.net.msg.SceneMsg;
	
	public class MapLayer extends Sprite
	{
		public function MapLayer()
		{
			super();
			
			this.graphics.beginFill(0x00ff00, 0.6);
			this.graphics.drawRect(0, 0, 800, 480);
			this.graphics.endFill();
			
			this.addEventListener(MouseEvent.CLICK, __click);
		}
		
		private function __click(e:MouseEvent):void
		{
			//Game.alert.show("点击了地图：" + e.localX + "," + e.localY);
			SceneMsg.sendMovePlayerReq(e.localX, e.localY);
//			if(Game.scene.canMove)
//			{
//				Game.scene.hero.move(e.localX, e.localY);
//				Game.scene.canMove = false;
//			}
		}
	}
}