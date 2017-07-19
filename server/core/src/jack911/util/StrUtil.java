package jack911.util;

public class StrUtil
{
	public static boolean isEmpty(String value)
	{
		if(value == null || "".equals(value.trim())) { return true; }
		return false;
	}
	
	public static boolean isNull(String value)
	{
		if(value == null || "null".equals(value.trim().toLowerCase())) { return true; }
		return false;
	}
}
