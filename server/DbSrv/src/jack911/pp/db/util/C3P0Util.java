package jack911.pp.db.util;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import jack911.util.Const;

public class C3P0Util
{
	private static Config cfg = null;
	
	public static ComboPooledDataSource create()
	{
		if(cfg == null)
		{
			cfg = new Config();
			//System.err.println(cfg);
		}
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser( cfg.user );       
        dataSource.setPassword( cfg.password );       
        dataSource.setJdbcUrl( cfg.jdbcUrl ); 
        try
		{
			dataSource.setDriverClass( cfg.driverClass );
		} catch (PropertyVetoException e)
		{
			e.printStackTrace();
		}
        dataSource.setInitialPoolSize( cfg.initialPoolSize ); 
        dataSource.setMinPoolSize( cfg.minPoolSize ); 
        dataSource.setMaxPoolSize( cfg.maxPoolSize ); 
        dataSource.setMaxStatements( cfg.maxStatements ); 
        dataSource.setMaxIdleTime( cfg.maxIdleTime );       
		return dataSource;
	}
	
	private static class Config
	{
		public String driverClass;
		public String jdbcUrl;
		public String user;
		public String password;
		public int initialPoolSize;
		public int minPoolSize;
		public int maxPoolSize;
		public int maxStatements;
		public int maxIdleTime;
		
		public Config()
		{
			File file = new File( Const.c3p0_config_path() );
			FileInputStream inputStream;
			Properties p = new Properties();
			try
			{
				inputStream = new FileInputStream(file);
				p.load(inputStream);
				this.driverClass = p.getProperty("driverClass");
				this.jdbcUrl = p.getProperty("jdbcUrl");
				this.user = p.getProperty("user");
				this.password = p.getProperty("password");
				this.initialPoolSize = Integer.parseInt( p.getProperty("initialPoolSize") );
				this.minPoolSize = Integer.parseInt( p.getProperty("minPoolSize") );
				this.maxPoolSize = Integer.parseInt( p.getProperty("maxPoolSize") );
				this.maxStatements = Integer.parseInt( p.getProperty("maxStatements") );
				this.maxIdleTime = Integer.parseInt( p.getProperty("maxIdleTime") );
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		@Override
		public String toString()
		{
			return "Config [driverClass=" + driverClass + ", jdbcUrl=" + jdbcUrl + ", user=" + user + ", password="
					+ password + ", initialPoolSize=" + initialPoolSize + ", minPoolSize=" + minPoolSize
					+ ", maxPoolSize=" + maxPoolSize + ", maxStatements=" + maxStatements + ", maxIdleTime="
					+ maxIdleTime + "]";
		}
		
	}
}
