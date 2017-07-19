package jack911.pp.game.msg.content;

import jack911.pp.game.Game;
import jack911.pp.game.bundle.GameCB;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.GmMsg;
import jack911.pp.server.MsgUnit;

public class GmMsg4Game extends GmMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case PARAMS_CMD_C2G:
			recvParamsCmd(head, msg);
			break;
		}
	}
	
	/** 请求执行参数化的GM命令
	 *  _cmdId_byte_byte_int_int_int_float_double_long_long_str_str_  */
	private void recvParamsCmd(MsgHead head, MsgUnit msg)
	{
		GameCB bundle = Game.bundle.getBundle(head.cccid);
		if(bundle == null) { return; }
		int mainCmdId = msg.readInt();
		int subCmdId = msg.readInt();
		byte byte0 = msg.readByte();
		byte byte1 = msg.readByte();
		int int0 = msg.readInt();
		int int1 = msg.readInt();
		int int2 = msg.readInt();
		float float0 = msg.readFloat();
		double double0 = msg.readDouble();
		long long0 = msg.readLong();
		long long1 = msg.readLong();
		String str0 = msg.readString();
		String str1 = msg.readString();
		switch(mainCmdId)
		{
		case TYPE_OTHERS:
			cmd_handle_other(subCmdId, bundle, byte0, byte1, int0, int1, int2, float0, double0, long0, long1, str0, str1);
			break;
		case TYPE_PLAYER:
			cmd_handle_player(subCmdId, bundle, byte0, byte1, int0, int1, int2, float0, double0, long0, long1, str0, str1);
			break;
		case TYPE_GOODS:
			cmd_handle_goods(subCmdId, bundle, byte0, byte1, int0, int1, int2, float0, double0, long0, long1, str0, str1);
			break;
		case TYPE_TASK:
			cmd_handle_task(subCmdId, bundle, byte0, byte1, int0, int1, int2, float0, double0, long0, long1, str0, str1);
			break;
		}
	}
	
	//----------------other-----------------
	private void cmd_handle_other(int subCmdId, GameCB bundle, byte byte0, byte byte1, 
			int int0, int int1, int int2, float float0, double double0, 
			long long0, long long1, String str0, String str1)
	{
		
	}
	
	//----------------player-----------------
	private void cmd_handle_player(int subCmdId, GameCB bundle, byte byte0, byte byte1, 
			int int0, int int1, int int2, float float0, double double0, 
			long long0, long long1, String str0, String str1)
	{
		
	}
	
	//----------------goods-----------------
	private void cmd_handle_goods(int subCmdId, GameCB bundle, byte byte0, byte byte1, 
			int int0, int int1, int int2, float float0, double double0, 
			long long0, long long1, String str0, String str1)
	{
		
	}
	
	//----------------task-----------------
	private void cmd_handle_task(int subCmdId, GameCB bundle, byte byte0, byte byte1, 
			int int0, int int1, int int2, float float0, double double0, 
			long long0, long long1, String str0, String str1)
	{
		
	}
	
}
