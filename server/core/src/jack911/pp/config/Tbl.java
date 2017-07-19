package jack911.pp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import jack911.util.Const;

public abstract class Tbl
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	public String type;
	
	/** 解析 */
	abstract public void parse(HashMap<String, String[]> dataDic, int itemCount);
	
	public void load()
	{
		File f = new File(Const.config_table_path(type));
		try
		{
			@SuppressWarnings("resource")
			FileInputStream in = new FileInputStream(f);
			byte[] buff;
			try
			{
				buff = new byte[in.available()];
				in.read(buff);
				String content = new String(buff, "UTF-8");
				//System.out.println("该cfgbuf的内容\r"+content+"\r");
				digest(content);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/** 解析 */
	private void digest(String content)
	{
		final String LINE_SEP = "\n";
		final String WORD_SEP = "┼";
		
		HashMap<String, String[]> dataDic = new HashMap<>();//key:keyname value:valuesArr
		
		String[] lineArr = content.split(LINE_SEP);
		int itemCount = lineArr.length - 1;//-1是因为首行是字段名，并非可用数据
		String keynamesStr = lineArr[0];
		String[] keynamesArr = keynamesStr.split(WORD_SEP);
		int i = 0;
		String keyname;
		String[] valuesArr;
		String[][] index2valuesArr = new String[keynamesArr.length][];
		for(i = 0; i < keynamesArr.length; i++)
		{
			keyname = keynamesArr[i];
			//System.out.println("keyname=" + keyname);
			valuesArr = new String[itemCount];
			dataDic.put(keyname, valuesArr);
			index2valuesArr[i] = valuesArr;
		}
		for(i = 1; i <= itemCount; i++)
		{
			String lineStr = lineArr[i];
			if(lineStr.equals("")) { itemCount--; continue; }
			String[] valuesLine = lineStr.split(WORD_SEP);
			for(int ind = 0; ind < valuesLine.length; ind++)
			{
				String value = valuesLine[ind];
				valuesArr = index2valuesArr[ind];
				valuesArr[i-1] = value;
			}
		}
		
		try
		{
			parse(dataDic, itemCount);
		}
		catch(NullPointerException e)
		{
			logger.error("配置表"+type+"解析发生错误，可能字段名与配置表不符");
		}
	}
	
}
