package jack911.pp.db.player;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jack911.pp.common.vo.PlayerData;
import jack911.pp.db.Db;
import jack911.pp.db.entity.PlayerEntity;
import jack911.pp.db.util.DaoHelper;

public class DbPlayerHandler
{
	private Log log = LogFactory.getLog(DbPlayerHandler.class);
	
	public void handleQueryPlayerDataReq(Long playerUid, Long cccid)
	{
		PlayerEntity entity = DaoHelper.getInstance().read(PlayerEntity.class, "uid", playerUid);
		if(entity == null)
		{
			log.warn("不存在uid=" + playerUid + "的角色");
			return;
		}
		Db.msg.player.sendQueryPlayerDataResp(entity.createPlayerData(), entity.getBag(), entity.getEquip(), cccid);
	}
	
	public void handleSavePlayer(Long cccid, PlayerData playerData, String bagData, String equipData)
	{
		PlayerEntity entity = DaoHelper.getInstance().read(PlayerEntity.class, "uid", playerData.uid);
		if(entity == null)
		{
			log.error("不应该此时都不存在uid="+playerData.uid+"的角色");
			return;
		}
		entity.setPlayerData(playerData);
		entity.setBag(bagData);
		entity.setEquip(equipData);
		boolean r = DaoHelper.getInstance().update(entity);
		System.out.println("保存成功？" + r);
		//照理说应该返回给Centre的保存操作结果，但是临时先这样吧
	}
}
