package jack911.pp.centre.player;

import org.json.JSONArray;

import jack911.pp.centre.Centre;
import jack911.pp.centre.bundle.CentreCB;
import jack911.pp.centre.goods.bag.BagContainer;
import jack911.pp.centre.goods.equip.EquipContainer;
import jack911.pp.common.vo.PlayerData;
import jack911.pp.server.MsgUnit;
import jack911.util.StrUtil;

public class Player
{
	private CentreCB bundle;
	public CentreCB getBundle() { return bundle; }
	public Long cccid() { return bundle.cccid; }
	
	public PlayerData data;
	
	/** 包裹 */
	public BagContainer bag;
	/** 装备 */
	public EquipContainer equip;
	
	public long uid() { if(data==null) { return -1; } return data.uid; }
	
	public int level() { if(data==null) { return -1; } return data.level; }
	public void level(int level) 
	{ 
		if(data==null) { return; } 
		data.level = level;
		Centre.msg.player.sendUpdateLevelCmd(level, cccid());
	}
	
	public int cash() { if(data==null) { return -1; } return data.cash; }
	public void cash(int cash) 
	{ 
		if(data==null) { return; } 
		data.cash = cash;
		Centre.msg.player.sendUpdateCashCmd(cash, cccid());
	}
	
	public String name() { if(data==null) { return "NULL"; } return data.name; }
	
	public Player(CentreCB bundle)
	{
		this.bundle = bundle;
		data = new PlayerData();
		bag = new BagContainer(this);
		equip = new EquipContainer(this);
	}
	
	public void alert(String msg)
	{
		System.out.println("Player:alert " + msg);
	}
	
	public void saveToDb()
	{
		Centre.msg.player.sendSaveToDbReq(this, bundle.cccid);
	}
	
	public void writeToClient(MsgUnit msg)
	{
		data.writeTo(msg);
		bag.write(msg);
		equip.write(msg);
	}
	
	public void writeToDb(MsgUnit msg)
	{
		data.writeTo(msg);
		msg.writeString(bag.encode().toString());
		msg.writeString(equip.encode().toString());
	}
	
	public void readFromDb(MsgUnit msg)
	{
		data.readFrom(msg);
		String bagData = msg.readString();
		String equipData = msg.readString();
		if(!StrUtil.isEmpty(bagData))
		{
			JSONArray bagJarr = new JSONArray(bagData);
			bag.decode(bagJarr);
		}
		if(!StrUtil.isEmpty(equipData))
		{
			JSONArray equipJarr = new JSONArray(equipData);
			equip.decode(equipJarr);
		}
	}
}
