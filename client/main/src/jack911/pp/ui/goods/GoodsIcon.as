package jack911.pp.ui.goods
{
	import com.anstu.jui.controls.JLabel;
	import com.anstu.jui.define.JuiConst;
	
	import flash.display.Bitmap;
	import flash.display.Shape;
	import flash.display.Sprite;
	import flash.events.MouseEvent;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Layer;
	import jack911.pp.core.goods.GoodsInfo;
	import jack911.pp.ui.Drag;
	import jack911.pp.ui.deprecated.res.PreRes;
	import jack911.pp.ui.deprecated.res.Res;
	import jack911.pp.ui.skin.font.DefaultFont;
	
	public class GoodsIcon extends Sprite
	{
		public static const W:int = 48;
		public static const H:int = 48;
		
		public var info:GoodsInfo;
		
		/** icon图片 */
		private var pic:Bitmap = new Bitmap();
		/** 数量标签 */
		private var numLabel:JLabel = new JLabel();
		
		override public function get width():Number { return W; }
		override public function get height():Number { return H; }
		
		public function GoodsIcon()
		{
			super();
			paintDefault();
			prepare();
		}
		
		protected function prepare():void
		{
			var shape:Shape = new Shape();
			shape.graphics.beginFill(0xff0000);
			shape.graphics.drawRoundRect(0, 0, W, H, 12);
			shape.graphics.endFill();
			this.addChild(shape);
			this.mask = shape;
			
			this.graphics.beginFill(0xffff00, 1);
			this.graphics.drawRect(0, 0, W, H);
			this.graphics.endFill();
			
			this.addChild(pic);
			
			numLabel.textFormat = DefaultFont.create();
			numLabel.useStroke = true;
			numLabel.align = JuiConst.RIGHT;
			numLabel.setSize(W, 0.3*H);
			numLabel.move(0, 0.6*H);
			this.addChild(numLabel);
			
			this.addEventListener(MouseEvent.MOUSE_OVER, __mouseOver);
			this.addEventListener(MouseEvent.MOUSE_MOVE, __mouseMove);
			this.addEventListener(MouseEvent.MOUSE_OUT, __mouseOut);
		}
		
		protected function __mouseOver(e:MouseEvent):void
		{
			GoodsTip.getInstance().update( info );
			Layer.ui.addChild( GoodsTip.getInstance() );
		}
		
		protected function __mouseMove(e:MouseEvent):void
		{
			GoodsTip.getInstance().move( Base.stage.mouseX + 10, Base.stage.mouseY + 10 );
		}
		
		protected function __mouseOut(e:MouseEvent):void
		{
			GoodsTip.getInstance().removeFromParent();
		}
		
		/** 是否支持拖拽 */
		public function canDrag(value:Boolean):void
		{
			if(value==true)
			{
				this.addEventListener(MouseEvent.MOUSE_DOWN, __preDrag);
				this.addEventListener(MouseEvent.MOUSE_UP, __stopDrag);
			}
			else
			{
				this.removeEventListener(MouseEvent.MOUSE_DOWN, __preDrag);
				this.removeEventListener(MouseEvent.MOUSE_MOVE, __startDrag);
				this.removeEventListener(MouseEvent.MOUSE_UP, __stopDrag);
			}
		}
		
		private function __preDrag(e:MouseEvent):void
		{
			this.addEventListener(MouseEvent.MOUSE_MOVE, __startDrag);
			e.stopImmediatePropagation();
		}
		
		private function __startDrag(e:MouseEvent):void
		{
			this.removeEventListener(MouseEvent.MOUSE_MOVE, __startDrag);
			doDrag();
		}
		
		private function __stopDrag(e:MouseEvent):void
		{
			this.removeEventListener(MouseEvent.MOUSE_MOVE, __startDrag);
			cancelDrag();
		}
		
		protected function doDrag():void
		{
			//TODO
		}
		
		protected function cancelDrag():void
		{
			Drag.getInstance().finish();
		}
		
		public function setup(info:GoodsInfo):void
		{
			this.info = info;
			paintMain();
			paintNum();
		}
		
		protected function paintDefault():void
		{
			pic.bitmapData = PreRes.icon;
		}
		
		protected function paintMain():void
		{
			Res.fillIcon(pic, this.info.getIcon());
		}
		
		protected function paintNum():void
		{
			numLabel.text = this.info.num.toString();
		}
		
	}
}