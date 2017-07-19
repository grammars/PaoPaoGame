package jack911.pp.game.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jack911.pp.common.vo.PlayerData;
import jack911.pp.config.ConfigManager;
import jack911.pp.config.table.EquipTblUnit;
import jack911.pp.game.Game;
import jack911.pp.game.bundle.GameCB;
import jack911.pp.game.scene.EquipUseHandler;
import jack911.pp.message.dp.game.GameResultDp;
import jack911.pp.server.MsgUnit;

public class PlayerApe extends Ape
{
	public GameCB bundle;
	
	public Long cccid() { return bundle.cccid; }
	
	public PlayerApe()
	{
		this.type = TYPE_PLAYER;
		this.eatRat = 0.5f;
	}
	
	private PlayerData data;

	public PlayerData getData()
	{
		return data;
	}

	public void setData(PlayerData data)
	{
		this.data = data;
	}
	
	/** 剩余步数 */
	private short step;
	public void setStep(short step)
	{
		this.step = step;
		Game.msg.scene.sendUpdateStepCmd(step, cccid());
	}
	public short getStep() { return this.step; }
	
	@Override
	public String getName() { return this.data.name; }
	
	private Map<Integer, Integer> equipId_useTime_map = new ConcurrentHashMap<>();
	private List<EquipTblUnit> equips = new ArrayList<>();
	/** 初始化装备了的道具 */
	public void initEquips(int[] equipIds)
	{
		equipId_useTime_map.clear();
		equips.clear();
		for(int id : equipIds)
		{
			EquipTblUnit cfg = ConfigManager.equip.get(id);
			equips.add(cfg);
			System.out.println("该玩家装备了" + id);
		}
	}
	
	/** 使用装备道具 */
	public void useEquip(int equipId)
	{
		if(null != equipId_useTime_map.get(equipId))
		{
			alert("每种技能每次挑战仅能使用1次哟");
			return;
		}
		boolean found = false;
		for(EquipTblUnit cfg : equips)
		{
			if(cfg.id == equipId)
			{
				EquipUseHandler.use(this, cfg);
				Integer times = equipId_useTime_map.get(equipId);
				if(times==null) { times = 0; }
				times += 1;
				equipId_useTime_map.put(equipId, times);
				found = true;
				break;
			}
		}
		if(found==false)
		{
			alert("(ˉ▽￣～) 切~~明明没有这个技能嘛");
		}
	}
	
	/** 向客户端发提示 */
	public void alert(String msg)
	{
		Game.msg.player.sendAlertCmd(msg, cccid());
	}
	
	@Override
	protected void updatePosition()
	{

	}
	
	@Override
	protected void moveComplete()
	{
		Game.msg.scene.sendMoveCompleteNotice(cccid());
	}
	
	@Override
	public void exit()
	{
		if(scene != null)
		{
			GameResultDp result = new GameResultDp();
			result.cccid = this.cccid();
			result.r = this.r;
			result.roomId = this.scene.id();
			Game.msg.scene.sendGameOverResult(result);
			Game.msg.scene.sendSubmitGameResult(result);
		}
		super.exit();
	}
	
	@Override
	public void writeTo(MsgUnit msg)
	{
		super.writeTo(msg);
		msg.writeCCCID(bundle.cccid);
		msg.writeInt(data.level);
	}
	
}
