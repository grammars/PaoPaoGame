package jack911.pp.core.ape
{
	import com.anstu.jload.JLoadTask;
	import com.anstu.jload.JLoader;
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jui.controls.JLabel;
	import com.greensock.TweenMax;
	import com.greensock.easing.Linear;
	
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.geom.Matrix;
	import flash.geom.Point;
	import flash.utils.ByteArray;
	import flash.utils.setTimeout;
	
	import jack911.pp.component.EffectFilters;
	import jack911.pp.config.Cfg;
	import jack911.pp.core.Game;
	import jack911.pp.core.Layer;
	import jack911.pp.utils.GeomUtils;
	
	public class Ape extends Sprite
	{
		public static const TYPE_PLAYER:int = 1;
		public static const TYPE_FOOD:int = 2;
		public static const TYPE_MONSTER:int = 4;
		
		private var id:int;
		public function getId():int { return id; }
		
		protected var r:Number = 30;
		public function setR(value:Number):void
		{
			this.r = value;
			paint();
		}
		public function getR():Number { return this.r; }
		
		protected var skin:String = null;
		public function setSkin(value:String):void
		{
			this.skin = value;
			if(skin==null  || skin=="")
			{
				skinBmd = null;
				paint();
			}
			else
			{
				var skinUrl:String = Cfg.ppSkinUrl(skin);
				jl.add(JLoadTask.TYPE_DISPLAY_CONTENT, skinUrl, Cfg.SAME_DOMAIN, 0, __skinLoaded);
				jl.start();
			}
		}
		public function getSkin():String { return this.skin; }
		
		protected static const jl:JLoader = new JLoader(4);
		
		protected var skinBmd:BitmapData = null;
		
		private var pos:Point = new Point();
		override public function set x(value:Number):void
		{
			pos.x = value;
			super.x = Math.round(value);
		}
		override public function get x():Number { return pos.x; }
		override public function set y(value:Number):void
		{
			pos.y = value;
			super.y = Math.round(value);
		}
		override public function get y():Number { return pos.y; }
		
		/** 移动速度 当r=36的时候 每秒移动100像素则 speed=100*sqrt(36) */
		protected var speed:Number = 0;
		public function setSpeed(value:Number):void
		{
			this.speed = value;
		}
		public function getSpeed():Number { return this.speed; }
		/** 每毫秒步长 */
		protected function step_ms():Number
		{
			return (speed/Math.sqrt(r))/1000;
		}
		/** 每帧步长 */
		protected function step_fr():Number
		{
			return step_ms() * Cfg.FRAME_MS; 
		}
		
		/** 剩余保护时间 */
		protected var protectTime:int = 0;
		public function setProtectTime(value:int):void
		{
			this.protectTime = value;
			if(this.protectTime > 0)
			{
				this.filters = [EffectFilters.PP_OVER];
				setTimeout(function():void{ setProtectTime(0); }, protectTime);
			}
			else
			{
				this.filters = [];
			}
		}
		public function getProtectTime():int { return this.protectTime; }
		
		public var theName:String;
		protected var nameLabel:JLabel = new JLabel();
		
		public function Ape()
		{
			super();
			this.mouseChildren = false;
			paint();
			this.alpha = 0.7;
		}
		
		public function isHero():Boolean
		{
			return false;
		}
		
		private function __skinLoaded(task:JLoadTask):void
		{
			trace("__skinLoaded" + task.result.getBmp());
			skinBmd = task.result.getBmp().bitmapData;
			paint();
		}
		
		public function paint():void
		{
			this.graphics.clear();
			if(skinBmd)
			{
				var mtx:Matrix = new Matrix();
				mtx.scale( 2*r / skinBmd.width, 2*r / skinBmd.height );
				mtx.tx = mtx.ty = -r;
				this.graphics.beginBitmapFill(skinBmd, mtx);
				this.graphics.drawRect(-r, -r, 2*r, 2*r);
			}
			else
			{
				this.graphics.beginFill(0xffff00, 0.5);
				this.graphics.drawCircle(0, 0, r);
			}
			this.graphics.beginFill(0xff0000, 0.9);
			this.graphics.drawRect(-2, -2, 4, 4);
			this.graphics.endFill();
			
			nameLabel.text = this.theName;
			this.addChild(nameLabel);
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			this.id = EndianFacade.readInt(bytes);
			this.x = EndianFacade.readFloat(bytes);
			this.y = EndianFacade.readFloat(bytes);
			this.setR( EndianFacade.readFloat(bytes) );
			this.speed = EndianFacade.readFloat(bytes);
			this.theName = EndianFacade.readString(bytes);
			this.setSkin( EndianFacade.readString(bytes) );
		}
		
		public function move(tx:Number, ty:Number):void
		{
			var costTime:Number = GeomUtils.distance(tx, ty, x, y) / step_ms();
			TweenMax.to(this, costTime/1000, {x:tx, y:ty, ease: Linear.easeNone, onUpdate: __tweening, onComplete: __tweenOver});
		}
		
		/** __tweening */
		private function __tweening():void
		{
			if( isHero() )
			{
				Layer.scene.focusTarget(this);
			}
		}
		
		/** __tweenOver */
		private function __tweenOver():void
		{
			
		}
		
		/** 是否被鼠标判定为事实经过 */
		public var mouseOver:Boolean = false;
		
		/** 是否包含该全局点 */
		public function includeGP(gp:Point):Boolean
		{
			var lp:Point = this.globalToLocal(gp);
			if(lp.x * lp.x + lp.y * lp.y < r * r)
			{
				return true;
			}
			return false;
		}
		
		/** [Interface]clickHandler */
		public function clickHandler(e:MouseEvent):void
		{
			
		}
		
		/** [Interface]overHandler */
		public function overHandler(e:MouseEvent):void
		{
			this.filters = [EffectFilters.PP_OVER];
		}
		
		/** [Interface]outHandler */
		public function outHandler(e:MouseEvent):void
		{
			this.filters = [];
		}
		
	}
}