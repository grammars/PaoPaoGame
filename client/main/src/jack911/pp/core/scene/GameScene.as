package jack911.pp.core.scene
{
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	
	import jack911.pp.component.EffectFilters;
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.core.ape.Ape;
	import jack911.pp.core.ape.ApeLayer;
	import jack911.pp.core.map.MapLayer;
	
	public class GameScene extends Sprite
	{
		/** 前景层 */
		public const foreground:ForegroundLayer = new ForegroundLayer();
		/** avatar层 */
		public const avatar:ApeLayer = new ApeLayer();
		/** 背景层 */
		public const background:BackgroundLayer = new BackgroundLayer();
		/** map层 */
		public const map:MapLayer = new MapLayer();
		
		private var enabled:Boolean = true;
		public function setEnabled(value:Boolean):void
		{
			this.enabled = value;
			if(this.enabled)
			{
				this.mouseChildren = this.mouseEnabled = true;
				this.filters = [];
			}
			else
			{
				this.mouseChildren = this.mouseEnabled = false;
				this.filters = [ EffectFilters.GREY ];
			}
		}
		
		public function GameScene()
		{
			super();
			
			this.addChild(map);
			this.addChild(background);
			this.addChild(avatar);
			this.addChild(foreground);
		}
		
		public function init():void
		{
			clear();
		}
		
		public function clear():void
		{
			avatar.clear();
		}
		
		public function addApe(ape:Ape):void
		{
			avatar.append(ape);	
		}
		
		public function removeApe(ape:Ape):void
		{
			if(ape.parent) { ape.parent.removeChild(ape); }
		}
		
		public function focus(fx:Number, fy:Number):void
		{
			var tx:Number = -fx;
			var ty:Number = -fy;
			if(tx > 0) { tx = 0; }
			else if(tx < -Game.map.curMapWidth+Base.stage.stageWidth)
			{ tx = -Game.map.curMapWidth+Base.stage.stageWidth; }
			if(ty > 0) { ty = 0; }
			else if(ty < -Game.map.curMapHeight+Base.stage.stageHeight)
			{ ty = -Game.map.curMapHeight+Base.stage.stageHeight; }
			
			this.x = Math.round(tx);
			this.y = Math.round(ty);
		}
		
		public function focusTarget(tar:Ape):void
		{
			var fx:Number = tar.x - Base.stage.stageWidth/2;
			var fy:Number = tar.y - Base.stage.stageHeight/2;
			focus(fx, fy);
		}
		
	}
}