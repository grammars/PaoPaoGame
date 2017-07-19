package jack911.pp.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import jack911.util.Const;

public class ServerConfig
{
	private static Map<Byte, Configuration> configs = new HashMap<>();
	
	public static Configuration getConfig(Byte sid) { return configs.get(sid); }
	
	public static boolean load()
	{
		loadSub(ServerId.GATEWAY);
		loadSub(ServerId.CENTRE);
		loadSub(ServerId.GAME);
		loadSub(ServerId.WORLD);
		loadSub(ServerId.DB);
		return true;
	}
	
	/** 加载单个服务器配置，并存入configs
	 * @param sid ServerId */
	private static Configuration loadSub(byte sid)
	{
		File file = new File( Const.server_config_path(sid) );
		FileInputStream inputStream;
		Properties p = new Properties();
		Configuration cfg = null;
		try
		{
			inputStream = new FileInputStream(file);
			p.load(inputStream); 
			cfg = new Configuration(p);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		configs.put(sid, cfg);
		return cfg;
	}
	
	public static class Configuration
	{
		private static Logger logger = Logger.getLogger(Configuration.class);
		
		private static final String SPL = ",";//分隔符
		
		private String sz_name;
		private String sz_allow;
		private String sz_connTo;
		private String sz_bindIp;
		private String sz_bindPort;
		
		private Configuration(Properties p)
		{
			sz_name = p.getProperty("name");
			sz_allow = p.getProperty("allow");
			sz_connTo = p.getProperty("connTo");
			sz_bindIp = p.getProperty("bindIp");
			sz_bindPort = p.getProperty("bindPort");
			logger.debug(this.toString());
		}
		
		public String getName() { return sz_name; }
		public String getBindIp() { return sz_bindIp; }
		public int getBindPort() { return Integer.parseInt(sz_bindPort); }
		public boolean allow(String connectorIp)
		{
			if(sz_allow.contains("all")) { return true; }
			if(sz_allow.contains(connectorIp)) { return true; }
			return false;
		}
		
		public List<Configuration> getConnectTargetCfgs()
		{
			List<Configuration> cfgs = new ArrayList<ServerConfig.Configuration>();
			String[] names = sz_connTo.split(SPL);
			for(int i = 0; i < names.length; i++)
			{
				Configuration c = ServerConfig.getConfig(ServerId.toId(names[i]));
				if(c != null) { cfgs.add(c); }
			}
			return cfgs;
		}

		@Override
		public String toString() 
		{
			return "Configuration [sz_name="+sz_name+", sz_allow=" + sz_allow + ", sz_connTo=" + sz_connTo + ", sz_bindIp=" + sz_bindIp + ", sz_bindPort=" + sz_bindPort + "]";
		}
		
	}
	
}
