package jack911.pp.ui.gm
{
	import com.anstu.jui.controls.JComboBox;
	import com.anstu.jui.controls.JPanel;
	import com.anstu.jui.controls.JTitle;
	import com.anstu.jui.define.JuiConst;
	
	import flash.events.Event;
	
	import jack911.pp.ui.View;
	import jack911.pp.ui.skin.comboBox.DefaultComboBoxSkin;
	import jack911.pp.ui.skin.font.DefaultFont;
	import jack911.pp.ui.skin.pane.GreyShadowPaneSkin;
	
	public class GmParamGroup extends JPanel
	{
		private var titleLabel:JTitle;
		private var operCombo:JComboBox;
		
		private static const MARGIN:int = 5;
		
		public static const W:int = 248;
		public static const H:int = 33;
		
		public function GmParamGroup(title:String, cmds:Array)
		{
			super();
			
			GreyShadowPaneSkin.instance.apply(this);
			this.setSize(W, H);
			
			titleLabel = new JTitle(title);
			titleLabel.textFormat = DefaultFont.create();
			titleLabel.setSize(100, 20);
			titleLabel.move(MARGIN, MARGIN);
			titleLabel.valign = JuiConst.UP;
			this.addChild(titleLabel);
			
			operCombo = new JComboBox("请选择命令", cmds);
			DefaultComboBoxSkin.instance.apply(operCombo);
			operCombo.width = 180;
			operCombo.move(60, MARGIN);
			this.addChild(operCombo);
			operCombo.addEventListener(Event.SELECT, __operComboSelect);
		}
		
		private function __operComboSelect(e:Event):void
		{
			var cmd:GmParamCmd = operCombo.selectedItem as GmParamCmd;
			
			View.gm.mainCmdIdInput.text = cmd.mainCmdId.toString();
			View.gm.subCmdIdInput.text = cmd.subCmdId.toString();
			
			View.gm.byte0Label.text = cmd.byte0Label;
			View.gm.byte1Label.text = cmd.byte1Label;
			View.gm.int0Label.text = cmd.int0Label;
			View.gm.int1Label.text = cmd.int1Label;
			View.gm.int2Label.text = cmd.int2Label;
			View.gm.float0Label.text = cmd.float0Label;
			View.gm.double0Label.text = cmd.double0Label;
			View.gm.long0Label.text = cmd.long0Label;
			View.gm.long1Label.text = cmd.long1Label;
			View.gm.str0Label.text = cmd.str0Label;
			View.gm.str1Label.text = cmd.str1Label;
			
			View.gm.byte0Input.text = cmd.byte0Input;
			View.gm.byte1Input.text = cmd.byte1Input;
			View.gm.int0Input.text = cmd.int0Input;
			View.gm.int1Input.text = cmd.int1Input;
			View.gm.int2Input.text = cmd.int2Input;
			View.gm.float0Input.text = cmd.float0Input;
			View.gm.double0Input.text = cmd.double0Input;
			View.gm.long0Input.text = cmd.long0Input;
			View.gm.long1Input.text = cmd.long1Input;
			View.gm.str0Input.text = cmd.str0Input;
			View.gm.str1Input.text = cmd.str1Input;
		}
		
	}
}