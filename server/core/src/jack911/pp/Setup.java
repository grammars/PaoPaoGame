package jack911.pp;

import jack911.pp.server.ServerConfig;
import jack911.util.Const;

import org.apache.log4j.PropertyConfigurator;

/** 通用全局设置 */
public class Setup 
{
	public static void execute()
	{
		//设置log配置文件地址
		PropertyConfigurator.configure(Const.log4j_config_path());
		//服务器配置加载
		ServerConfig.load();
	}
}
