package jack911.pp.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/** 服务器身份标志，<=0 视为 client */
public class ServerId 
{
	public static final byte UNKNOWN = -1;
	public static final byte CLIENT = 0;
	public static final byte GATEWAY = 1;
	public static final byte CENTRE = 2;
	public static final byte GAME = 3;
	public static final byte WORLD = 4;
	public static final byte DB = 5;
	
	public static String toName(byte id)
	{
		switch(id)
		{
		case CLIENT: return "client";
		case GATEWAY: return "gateway";
		case CENTRE: return "centre";
		case GAME: return "game";
		case WORLD: return "world";
		case DB: return "db";
		case UNKNOWN: return "unknown";
		default: return null;
		}
	}
	
	public static byte toId(String name)
	{
		String[] names = {"client","gateway","centre","game","world","db"};
		List<String> nl = new ArrayList<>();
		CollectionUtils.addAll(nl, names);
		return (byte)nl.indexOf(name) ;
	}
}
