package jack911.pp.ui.goods.bag
{	
	import com.anstu.jui.controls.JPushButton;
	
	import flash.events.MouseEvent;
	
	import jack911.pp.ui.skin.pushButton.CommOrangeBtnSkin;
	
	public class BagIconMenuItem extends JPushButton
	{
		/** 执行类型 */
		private var exeType:String;
		
		public function BagIconMenuItem(exeType:String)
		{
			super(exeType, clickHandler);
			this.exeType = exeType;
			this.setSize(100, 26);
			CommOrangeBtnSkin.instance.apply(this);
			this.addEventListener(MouseEvent.MOUSE_DOWN, __mouseDown);
		}
		
		private function clickHandler(e:MouseEvent):void
		{
			BagIconMenu.getInstance().execute(exeType);
			BagIconMenu.getInstance().hide();
		}
		
		private function __mouseDown(e:MouseEvent):void
		{
			e.stopPropagation();
		}
		
	}
}