package jack911.pp.message.content;

import jack911.pp.message.IMsgHandler;
import jack911.pp.message.MsgDistributor;

public abstract class RankMsg implements IMsgHandler
{
	protected static final short MAJOR_MID = MsgDistributor.RANK_MSG;
	
	/** Game向CentreClient->Centre */
	protected static final short XXXXXX_REQ = 1;

}
