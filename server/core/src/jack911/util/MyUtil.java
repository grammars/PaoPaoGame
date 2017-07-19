package jack911.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 杂七杂八 的工具类 */
public class MyUtil
{
	/** 让当前线程休眠指定的毫秒数，可以省去try,catch的代�?*/
	public static void delay(long millis)
	{
		try { Thread.sleep(millis); }
		catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	/** 获得现在的绝对时间ms */
	public static long now()
	{
		return System.currentTimeMillis();
	}
	
	private static int autoIncrease = 0;
	/** 生成Long类型的uid[全局唯一性] */
	public static Long createUidLong()
	{
		long ms = Math.abs(System.currentTimeMillis() << 48);
		long ns = System.nanoTime() << 16;
		long ret = ms | ns | autoIncrease;
		autoIncrease ++;
		return ret;
	}
	
	/** 生成Integer类型的tempId[全局唯一性] */
	public static Integer createTidInt()
	{
		return ++autoIncrease;
	}
	
	/** 实例化 */
	public static <T> T newInstance(Class<T> clazz, Object... args)
	{
		try
		{                             
		    Class<?>[] argsClass = new Class[args.length];                                   
		    for (int i = 0, j = args.length; i < j; i++)
		    {                              
		        argsClass[i] = args[i].getClass();                                        
		    }
			Constructor<T> con = clazz.getConstructor(argsClass);
			try
			{
				return con.newInstance(args);
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 格式化成常见格式的日期字符串
     * @param Date
     * @return String
     */
	public static String toDateStr(Date date) 
	{
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		return sdformat.format(date);
	}
	
	/**
     * 格式化成常见格式的时间字符串
     * @param Date
     * @return String
     */
	public static String toTimeStr(Date date) 
	{
		SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
		return sdformat.format(date);
	}
	public static String toTimeStr(Time time)
	{
		Date date = new Date(time.getTime());
		return toTimeStr(date);
	}

	/**
     * 格式化成常见格式的时间字符串
     * @param Date
     * @return String
     */
	public static String toDateTimeStr(Date date)
	{
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdformat.format(date);
	}
	public static String toDateTimeStr(Timestamp timestamp)
	{
		Date date = new Date(timestamp.getTime());
		return toDateTimeStr(date);
	}
	
	public static String getTodayStr()
	{
		return toDateStr(new Date());
	}
	
	public static String getNowStr()
	{
		return toDateTimeStr(new Date());
	}
}
