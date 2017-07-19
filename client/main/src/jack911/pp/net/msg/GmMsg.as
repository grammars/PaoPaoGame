package jack911.pp.net.msg
{
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;
	import jack911.pp.net.MsgSender;
	import jack911.pp.ui.gm.GmParamCmd;
	
	public class GmMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.GM_MSG;
		
		/** client向game请求执行参数化的GM命令 */
		private static const PARAMS_CMD_C2G:int = 0;
		
		public function GmMsg()
		{
			super();
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			}
		}
		
		/** send( client向game请求执行参数化的GM命令 ) */
		public static function sendParamsCmd_C2G(cmd:GmParamCmd):void
		{
			var msg:MsgSender;
			
			msg = MsgSender.toCentre(MAJOR_MID, PARAMS_CMD_C2G);
			fill(msg, cmd);
			msg.send();
			
			msg = MsgSender.toGame(MAJOR_MID, PARAMS_CMD_C2G);
			fill(msg, cmd);
			msg.send();
		}
		
		private static function fill(msg:MsgSender, cmd:GmParamCmd):void
		{
			msg.writeInt(cmd.mainCmdId);
			msg.writeInt(cmd.subCmdId);
			msg.writeByte(parseInt(cmd.byte0Input));
			msg.writeByte(parseInt(cmd.byte1Input));
			msg.writeInt(parseInt(cmd.int0Input));
			msg.writeInt(parseInt(cmd.int1Input));
			msg.writeInt(parseInt(cmd.int2Input));
			msg.writeFloat(parseFloat(cmd.float0Input));
			msg.writeDouble(parseFloat(cmd.double0Input));
			msg.writeLong( new Long().fromString(cmd.long0Input) );
			msg.writeLong( new Long().fromString(cmd.long1Input) );
			msg.writeString(cmd.str0Input);
			msg.writeString(cmd.str1Input);
		}
		
	}
}