package jack911.pp.net.msg
{
	import com.anstu.jsock.utils.EndianFacade;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Game;
	import jack911.pp.core.ape.Ape;
	import jack911.pp.core.scene.skill.InvisibleSkill;
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;

	public class SkillMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.SKILL_MSG;
		
		/** 使用[隐身术]的命令Game->Client */
		protected static const USE_INVISIBLE_CMD:int = 1;
		
		public function SkillMsg()
		{
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			case USE_INVISIBLE_CMD:
				recvUseInvisibleCmd(bytes);
				break;
			}
		}
		
		/** recv(使用[隐身术]的命令Game->Client) */
		private static function recvUseInvisibleCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			var time:int = EndianFacade.readInt(bytes);
			var a:Ape = Game.scene.getApe(id);
			if(a) { new InvisibleSkill(a, time); }
		}
	}
}