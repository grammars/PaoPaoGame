package jack911.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class ScheUtil
{
	private static Scheduler getScheduler() throws SchedulerException
	{
		 SchedulerFactory sf = new StdSchedulerFactory();
		 return sf.getScheduler();
	}
	
	@SuppressWarnings("unused")
	private static void demo()
	{
		String cron;
		cron = "0/7 * * * * ?";//every 7 seconds
		//执行于 Sun Dec 13 20:44:56 CST 2015
		//执行于 Sun Dec 13 20:45:00 CST 2015
		//执行于 Sun Dec 13 20:45:07 CST 2015
		
		cron = "15 0/2 * * * ?";//every other minute (at 15 seconds past the minute)
		//执行于 Sun Dec 13 20:40:15 CST 2015
		//执行于 Sun Dec 13 20:42:15 CST 2015
		//执行于 Sun Dec 13 20:44:15 CST 2015
		
		cron = "0 0/2 8-17 * * ?";//every other minute but only between 8am and 5pm
		
		cron = "0 0/3 17-23 * * ?";//every three minutes but only between 5pm and 11pm
		//执行于 Sun Dec 13 20:48:00 CST 2015
		//执行于 Sun Dec 13 20:51:00 CST 2015
		
		cron = "0 0 10am 1,15 * ?";//at 10am on the 1st and 15th days of the month
		//执行于 Tue Dec 15 10:00:00 CST 2015
		
		cron = "0,8 * * ? * MON-FRI";//every 8 seconds but only on Weekdays (Monday through Friday)
		//执行于 Tue Dec 15 10:08:00 CST 2015
		//执行于 Tue Dec 15 10:08:08 CST 2015
		//执行于 Tue Dec 15 10:09:00 CST 2015
		//执行于 Tue Dec 15 10:09:08 CST 2015
		
		cron = "0,8 * * ? * SAT,SUN";//every 8 seconds but only on Weekends (Saturday and Sunday)
		
		cron = "0 0 0 ? * MON";//每周一 00:00:00
		
		cron = "0 0 0 * * ?";//每天 00:00:00
	}
	
	public static void runJob(Class<? extends Job> JobClazz, String cron)
	{
		JobDetail job = newJob(JobClazz).withIdentity("job_"+JobClazz.getName(), "group1").build();

	    CronTrigger trigger = newTrigger().withIdentity("trigger_"+JobClazz.getName(), "group1").withSchedule(cronSchedule(cron))
	        .build();

	    Scheduler sched;
		try
		{
			sched = getScheduler();
			sched.scheduleJob(job, trigger);
			sched.start();
		}
		catch (SchedulerException e)
		{
			e.printStackTrace();
		}
	}
}
