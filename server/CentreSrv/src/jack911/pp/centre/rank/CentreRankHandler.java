package jack911.pp.centre.rank;

import org.apache.log4j.Logger;

import jack911.pp.message.dp.game.GameResultDp;

public class CentreRankHandler
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	private RValRankList dayRL = new RValRankList(2);
	//private WeekRankList weekRL = new WeekRankList();
	
	public void handleGameResult(GameResultDp dp)
	{
		logger.debug("收到游戏结果" + dp);
		dayRL.handle(dp);
		dayRL.printRank();
	}
}
