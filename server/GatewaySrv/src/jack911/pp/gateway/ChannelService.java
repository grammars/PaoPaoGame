package jack911.pp.gateway;

import java.util.LinkedHashMap;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import jack911.util.MyUtil;

public class ChannelService
{
	private static Map<Long, ChannelHandlerContext> clientId2Ctx = new LinkedHashMap<>();
	
	private static Map<ChannelHandlerContext, Long> clientCtx2Id = new LinkedHashMap<>();
	
	//private static boolean has
	
	/** ClientChannelContextID of gateway */
	public static Long getCCCID(ChannelHandlerContext ctx)
	{
		return clientCtx2Id.get(ctx);
	}
	
	private static Long generateCCCID()
	{
		return MyUtil.createUidLong();
	}
	
	public static void setContext(ChannelHandlerContext ctx)
	{
		Long cccid = getCCCID(ctx);
		if(cccid == null)
		{
			cccid = generateCCCID();
			register(cccid, ctx);
		}
	}
	
	private static void register(Long cccid, ChannelHandlerContext ctx)
	{
		clientId2Ctx.put(cccid, ctx);
		clientCtx2Id.put(ctx, cccid);
	}
	
	private static void unregister(Long cccid, ChannelHandlerContext ctx)
	{
		clientId2Ctx.remove(cccid, ctx);
		clientCtx2Id.remove(ctx, cccid);
	}
	
	public static ChannelHandlerContext getContext(Long cccid)
	{
		return clientId2Ctx.get(cccid);
	}
	
	public static void removeContext(ChannelHandlerContext ctx)
	{
		Long cccid = getCCCID(ctx);
		if(cccid != null)
		{
			unregister(cccid, ctx);
		}
	}
}
