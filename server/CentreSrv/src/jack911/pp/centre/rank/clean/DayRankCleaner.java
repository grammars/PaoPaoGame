package jack911.pp.centre.rank.clean;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class DayRankCleaner implements Job
{
	private static Logger logger = Logger.getLogger(DayRankCleaner.class);

	public static final String CRON = "0 0 0 * * ?";//每天 00:00:00
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		@SuppressWarnings("unused")
		JobKey jobKey = context.getJobDetail().getKey();
		logger.info("DayRankCleaner执行于 " + new Date());
	}
}
