package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;

public abstract class SkillMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.SKILL_MSG;
	
	/** 使用[隐身术]的命令Game->Client */
	protected static final short USE_INVISIBLE_CMD = 1;
}