package jack911.pp.ui.status
{
	import com.anstu.jui.build.JFactory;
	import com.anstu.jui.controls.JImage;
	import com.anstu.jui.controls.JLabel;
	import com.greensock.TweenMax;
	import com.greensock.easing.Linear;
	
	import flash.display.Shape;
	import flash.events.Event;
	
	import jack911.pp.ui.ViewWnd;
	
	public class StatusPane extends ViewWnd
	{
		private var rLabel:JLabel;
		private var stepLabel:JLabel;
		private var timeline:JImage;
		
		private var timelineMask:Shape = new Shape();
		
		public function StatusPane()
		{
			super();
		}
		
		/** 初始化 */
		override protected function init():void
		{
			uiPack = JFactory.create("statusPane");
			pane = uiPack.getCtrl("root");
			rLabel = uiPack.getLabel("rLabel");
			stepLabel = uiPack.getLabel("stepLabel");
			timeline = uiPack.getImage("timeline");
			
			canDrag(false);
			canBringTop(true);
			
			timelineMask.graphics.beginFill(0x0);
			timelineMask.graphics.drawRect(0, 0, rLabel.width, rLabel.height);
			timelineMask.graphics.endFill();
			timeline.addChild(timelineMask);
			timeline.mask = timelineMask;
		}
		
		override public function putDefaultPos(event:Event=null):void
		{
			if(pane) { pane.x = pane.y = 0; }
		}
		
		/** 移动倒计时 */
		public function moveCountdown(time:Number):void
		{
			timelineMask.width = timeline.width;
			TweenMax.to(timelineMask, time/1000, {width:0, ease: Linear.easeNone, onComplete: __timelineOver});//onUpdate: __tweening
		}
		private function __timelineOver():void
		{
			
		}
		
		public function updateR(value:Number):void
		{
			rLabel.text = value.toString();
		}
		
		public function updateStep(value:int):void
		{
			stepLabel.text = value.toString();
		}
	}
}