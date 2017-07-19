package jack911.pp.centre.goods;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import jack911.pp.server.MsgUnit;
import jack911.util.JsonUtil;

public class StGoodsContainer
{
	/** 当前容量 */
	public int capacity = 18;
	
	/** 物品们 */
	public List<GoodsInfo> items = new ArrayList<>();

	public void write(MsgUnit msg)
	{
		int num = items.size();
		msg.writeInt(num);
		for(int i = 0; i < num; i++)
		{
			GoodsInfo item = items.get(i);
			GoodsFactory.writeInfo(item, msg);
		}
	}

	public void read(MsgUnit msg)
	{
		items.clear();
		int num = msg.readInt();
		for(int i = 0; i < num; i++)
		{
			GoodsInfo item = GoodsFactory.readInfo(msg);
			items.add(item);
		}
	}

	public JSONArray encode()
	{
		JSONArray jarr = new JSONArray();
		int num = items.size();
		for(int i = 0; i < num; i++)
		{
			GoodsInfo item = items.get(i);
			JsonUtil.putJSONObject(jarr, GoodsFactory.encodeInfo(item) );
		}
		return jarr;
	}

	public void decode(JSONArray jarr)
	{
		items.clear();
		int num = jarr.length();
		for(int i = 0; i < num; i++)
		{
			JSONObject jso = JsonUtil.getJSONObject(jarr, i);
			GoodsInfo item = GoodsFactory.decodeInfo(jso);
			items.add(item);
		}
	}
}
