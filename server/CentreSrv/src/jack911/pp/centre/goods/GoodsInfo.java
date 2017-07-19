package jack911.pp.centre.goods;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import jack911.pp.config.ConfigManager;
import jack911.pp.config.table.GoodsTblUnit;
import jack911.pp.server.MsgUnit;
import jack911.util.JsonUtil;
import jack911.util.MyUtil;

public class GoodsInfo
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** 唯一id */
	public long uid = 0;
	/** 物品位置 */
	public int index = 0;
	/** 物品配置id */
	public int cfgId = 0;
	/** 物品数量 */
	public int num = 0;
	
	/** 物品基础配置 */
	public GoodsTblUnit cfg;
	
	/** 可供再增加的数目 */
	public int numRoom() { return this.cfg.maxHeap - this.num; }
	
	/** 数据是否合法可用 */
	public boolean available()
	{
		return cfg != null;
	}
	
	public boolean isEquip() { return cfg.type == GoodsType.EQUIP; }
	
	/** 作为武器，所处的位置 */
	public int equipPos() 
	{ 
		switch(cfg.equipType)
		{
		case EquipType.HELMET: return 0;
		case EquipType.NECKLACE: return 1;
		case EquipType.WEAPON: return 2;
		case EquipType.CLOTHES: return 3;
		case EquipType.BELT: return 4;
		case EquipType.SHOES: return 5;
		default: return -1;
		}
	}
	
	public GoodsInfo()
	{
		//
	}
	
	/** 创建新的uid */
	public void createUid()
	{
		this.uid = MyUtil.createUidLong();
	}
	
	public void create(int cfgId)
	{
		this.cfgId = cfgId;
		createUid();
		build();
	}
	
	protected void build()
	{
		this.cfg = ConfigManager.goods.get(cfgId);
		if(cfg == null)
		{
			logger.error("找不到mid=" + cfgId + "的物品");
		}
	}
	
	public void write(MsgUnit msg)
	{
		msg.writeLong(uid);
		msg.writeInt(index);
		msg.writeInt(cfgId);
		msg.writeInt(num);
	}
	
	public void read(MsgUnit msg)
	{
		uid = msg.readLong();
		index = msg.readInt();
		cfgId = msg.readInt();
		num = msg.readInt();
		build();
	}
	
	/** 拷贝 */
	public void copy(GoodsInfo source)
	{
		this.uid = source.uid;
		this.index = source.index;
		this.cfgId = source.cfgId;
		this.num = source.num;
		build();
	}
	
	/** 数据库存储编码 */
	public JSONObject encode()
	{
		JSONObject jso = new JSONObject();
		JsonUtil.putLong(jso, "uid", uid);
		JsonUtil.putInt(jso, "index", index);
		JsonUtil.putInt(jso, "cfg", cfgId);
		JsonUtil.putInt(jso, "num", num);
		return jso;
	}
	
	/** 数据库存储解码 */
	public void decode(JSONObject jso)
	{
		uid = JsonUtil.getLong(jso, "uid");
		index = JsonUtil.getInt(jso, "index");
		cfgId = JsonUtil.getInt(jso, "cfg");
		num = JsonUtil.getInt(jso, "num");
	}
	
}
