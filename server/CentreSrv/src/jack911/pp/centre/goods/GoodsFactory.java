package jack911.pp.centre.goods;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import jack911.pp.config.ConfigManager;
import jack911.pp.config.table.GoodsTblUnit;
import jack911.pp.server.MsgUnit;
import jack911.util.JsonUtil;

public class GoodsFactory
{
	private static Logger logger = Logger.getLogger(GoodsFactory.class);
	
	/** 创建一个物品信息
	 * @param mid 物品的模版表id */
	public static GoodsInfo createInfo(int cfgId)
	{
		GoodsTblUnit cfg = ConfigManager.goods.get(cfgId);
		if(cfg == null)
		{
			logger.error("找不到cfgId=" + cfgId + "的物品");
			return null;
		}
		GoodsInfo info = create(cfg.type);
		info.create(cfgId);
		return info;
	}
	
	public static GoodsInfo create(int type)
	{
		return new GoodsInfo();
	}
	
	/** 从IoBuffer中读取 */
	public static GoodsInfo readInfo(MsgUnit msg)
	{
		int baseCfgId = msg.readInt();
		GoodsInfo info = createInfo(baseCfgId);
		info.read(msg);
		return info;
	}
	
	/** 写入到IoBuffer */
	public static void writeInfo(GoodsInfo info, MsgUnit msg)
	{
		msg.writeInt(info.cfgId);
		info.write(msg);
	}
	
	/** 克隆一个GoodsInfo */
	public static GoodsInfo cloneInfo(GoodsInfo source)
	{
		int type = source.cfg.type;
		GoodsInfo info = create(type);
		info.copy(source);
		return info;
	}
	
	public static JSONObject encodeInfo(GoodsInfo info)
	{
		JSONObject jso = new JSONObject();
		JsonUtil.putInt(jso, "cfgId", info.cfgId);
		JsonUtil.putJSONObject(jso, "info", info.encode());
		return jso;
	}
	
	public static GoodsInfo decodeInfo(JSONObject jso)
	{
		int cfgId = JsonUtil.getInt(jso, "cfgId");
		GoodsInfo info = createInfo(cfgId);
		info.decode( JsonUtil.getJSONObject(jso, "info") );
		return info;
	}
	
	
}
