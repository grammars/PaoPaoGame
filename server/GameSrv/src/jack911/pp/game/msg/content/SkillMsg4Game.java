package jack911.pp.game.msg.content;

import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.SkillMsg;
import jack911.pp.server.MsgUnit;

public class SkillMsg4Game extends SkillMsg
{
	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		
		}
	}
	
	/** send(使用[隐身术]的命令Game->Client) */
	public void sendUseInvisibleCmd(int tid, int time, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, USE_INVISIBLE_CMD, cccid);
		msg.writeInt(tid);
		msg.writeInt(time);
		msg.send();
	}
}
