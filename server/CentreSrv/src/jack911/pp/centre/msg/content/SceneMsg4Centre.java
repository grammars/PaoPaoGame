package jack911.pp.centre.msg.content;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.message.MsgHead;
import jack911.pp.message.content.SceneMsg;
import jack911.pp.message.dp.game.GameResultDp;
import jack911.pp.server.MsgUnit;

public class SceneMsg4Centre extends SceneMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case SUBMIT_GAME_RESULT:
			recvSubmitGameResult(head, msg);
			break;
		case DEDUCT_EQUIP_CMD:
			recvDeductEquipCmd(head, msg);
			break;
		}
	}
	
	/** recv(报送游戏的结果Game->Centre) */
	private void recvSubmitGameResult(MsgHead head, MsgUnit msg)
	{
		GameResultDp dp = new GameResultDp();
		dp.readFrom(msg);
		Centre.rank.handleGameResult(dp);
	}

	/** recv(装备消耗扣除de命令Game->Centre) */
	private void recvDeductEquipCmd(MsgHead head, MsgUnit msg)
	{
		Long cccid = msg.readCCCID();
		int equipId = msg.readInt();
		CentreCB bundle = Centre.bundle.getBundle(cccid);
		bundle.player.equip.deductItem(equipId, 1);
	}
}
