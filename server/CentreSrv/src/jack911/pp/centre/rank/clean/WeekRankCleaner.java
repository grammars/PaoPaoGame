package jack911.pp.centre.rank.clean;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class WeekRankCleaner implements Job
{
	private static Logger logger = Logger.getLogger(DayRankCleaner.class);

	public static final String CRON = "0 0 0 ? * MON";//每周一 00:00:00
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		@SuppressWarnings("unused")
		JobKey jobKey = context.getJobDetail().getKey();
		logger.info("WeekRankCleaner执行于 " + new Date());
	}
}
