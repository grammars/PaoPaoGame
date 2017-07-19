package jack911.pp.core.ape
{
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Layer;
	import jack911.pp.debug.AvtMonitor;
	import jack911.pp.utils.IntervalCounter;
	
	public class ApeLayer extends Sprite
	{
		private var enable:Boolean = true;
		public function setEnable(value:Boolean):void
		{
			this.enable = value;
			this.mouseChildren = this.mouseEnabled = this.enable;
		}
		public function getEnable():Boolean { return this.enable; }
		
		/** 更新显示深度排序间隔 */
		private var updateDisDepthIC:IntervalCounter = new IntervalCounter(30);
		
		public function ApeLayer()
		{
			super();
			this.addEventListener(MouseEvent.CLICK, __mouseClick);
			this.addEventListener(MouseEvent.MOUSE_MOVE, __mouseMove);
			this.addEventListener(MouseEvent.MOUSE_OUT, __mouseOut);
		}
		
		/** __mouseClick */
		private function __mouseClick(e:MouseEvent):void
		{
			//trace("AvatarLayer::__mouseClick");
			var findAvt:Boolean = false;
			var gp:Point = new Point(Base.stage.mouseX, Base.stage.mouseY);
			var children:Vector.<Ape> = getChildren();
			for(var i:int = children.length-1; i >= 0; i--)
			{
				if( children[i].mouseEnabled && children[i].includeGP(gp) )
				{
					children[i].clickHandler(e);
					findAvt = true;
					break;
				}
			}
			//if(!findAvt)
			{
				var lp2map:Point = Layer.scene.map.globalToLocal(gp);
				e.localX = lp2map.x;
				e.localY = lp2map.y;
				Layer.scene.map.dispatchEvent(e);
			}
		}
		
		/** __mouseMove */
		private function __mouseMove(e:MouseEvent):void
		{
			//trace("AvatarLayer::__mouseMove");
			var gp:Point = new Point(Base.stage.mouseX, Base.stage.mouseY);
			var children:Vector.<Ape> = getChildren();
			var targetGot:Boolean = false;//目标已找寻到
			for(var i:int = children.length-1; i >= 0; i--)
			{
				var ch:Ape = children[i];
				if( targetGot==false && ch.mouseEnabled && ch.includeGP(gp) )
				{
					if(ch.mouseOver == false)
					{
						ch.overHandler(e);
						ch.mouseOver = true;
					}
					targetGot = true;
				}
				else
				{
					if(ch.mouseOver == true)
					{
						ch.outHandler(e);
						ch.mouseOver = false;
					}
				}
			}
		}
		
		/** __mouseOut */
		private function __mouseOut(e:MouseEvent):void
		{
			var children:Vector.<Ape> = getChildren();
			for(var i:int = children.length-1; i >= 0; i--)
			{
				var ch:Ape = children[i];
				if(ch.mouseOver == true)
				{
					ch.outHandler(e);
					ch.mouseOver = false;
				}
			}
		}
		
		/** 获取鼠标经过的AvatarDisplay */
		public function getMouseOverTarget():Ape
		{
			var gp:Point = new Point(Base.stage.mouseX, Base.stage.mouseY);
			var children:Vector.<Ape> = getChildren();
			for(var i:int = children.length-1; i >= 0; i--)
			{
				var ch:Ape = children[i];
				if( ch.mouseEnabled && ch.includeGP(gp) )
				{
					return ch;
				}
			}
			return null;
		}
		
		/** 清理 */
		public function clear():void
		{
			this.removeChildren();
		}
		
		/** 添加AvatarDisplay */
		public function append(dis:Ape):Ape
		{
			super.addChild(dis);
			updateDisDepth();
			return dis;
		}
		
		
		override public function addChild(child:DisplayObject):DisplayObject
		{
			throw new Error("ApeLayer::addChild is not allowed");
			return child;
		}
		
		/** ENTER_FRAME */
		public function nextFrame():void
		{
			if(updateDisDepthIC.trigger())
			{
				updateDisDepth();
			}
		}
		
		/** 获取所有的avatar对象 */
		public function getChildren():Vector.<Ape>
		{
			var chs:Vector.<Ape> = new Vector.<Ape>();
			var i:int = 0;
			for(i = 0; i < this.numChildren; i++)
			{
				if(this.getChildAt(i) is Ape)
				{
					chs.push(this.getChildAt(i));
				}
			}
			return chs;
		}
		
		/** 更新显示深度排序 */
		private function updateDisDepth():void
		{
			var children:Vector.<Ape> = getChildren();
			children.sort(compareDepth);
			for(var i:int = 0; i < children.length; i++)
			{
				if(this.getChildIndex(children[i]) != i)
				{
					this.setChildIndex(children[i], i);
				}
			}
			
			AvtMonitor.getInstance().setNum(children.length);//顺便在此处加上调试器
		}
		private function compareDepth(a:Ape, b:Ape):int
		{
			if(a.y > b.y) { return 1; }
			else if (a.y == b.y) { return 0; }
			else { return -1; }
		}
		private function compareRealDepth(a:Ape, b:Ape):int
		{
			var aY:int = a.localToGlobal(new Point()).y;
			var bY:int = b.localToGlobal(new Point()).y;
			if(aY > bY) { return 1; }
			else if (aY == bY) { return 0; }
			else { return -1; }
		}
		
	}
}