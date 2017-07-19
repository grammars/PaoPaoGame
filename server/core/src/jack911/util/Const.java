package jack911.util;

import java.io.File;

import jack911.pp.server.ServerId;

public class Const
{
	/** c3p0-config.properties */
	public static String c3p0_config_path()
	{
		return "../config" + File.separator + "c3p0-config.properties";
	}
	
	public static String log4j_config_path()
	{
		return "../config" + File.separator + "log4j.properties";
	}
	
	public static String server_config_path(byte sid)
	{
		return "../config" + File.separator  + "server" + File.separator + ServerId.toName(sid) + ".properties";
	}
	
	public static String config_table_path(String type)
	{
		return "../config" + File.separator + "tables" + File.separator + "buf" + File.separator + type + ".cfgbuf";
	}
	
}
