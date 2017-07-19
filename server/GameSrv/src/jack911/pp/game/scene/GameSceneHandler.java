package jack911.pp.game.scene;

import java.util.List;

import jack911.pp.common.vo.PlayerData;
import jack911.pp.game.Game;
import jack911.pp.game.bundle.GameCB;
import jack911.pp.game.component.PlayerApe;
import jack911.pp.message.dp.lobby.EnterRoomResultDp;

public class GameSceneHandler
{
	private List<GameScene> scenes;
	public GameScene getScene(int id)
	{
		for(GameScene scene : scenes)
		{
			if(scene.id() == id) { return scene; }
		}
		return null;
	}
	
	/** 初始化 */
	public void initialize()
	{
		GameScenesBuilder sb = new GameScenesBuilder();
		scenes = sb.build();
		sb.buildMonster();
		sb.buildFood();
	}
	
	/** 处理一个角色登场 */
	public void handlePlayerEnter(PlayerData pData, int[] equipIds, int roomId, Long cccid)
	{
		PlayerApe player = new PlayerApe();
		player.setData(pData);
		player.initEquips(equipIds);
		
		GameScene scene = getScene(roomId);
		if(scene == null) { return; }
		
		GameCB bundle = new GameCB();
		bundle.cccid = cccid;
		bundle.bindPlayer(player);
		Game.bundle.putBundle(bundle);
		
		scene.add(player);
		
		EnterRoomResultDp dp = new EnterRoomResultDp();
		dp.errCode = EnterRoomResultDp.EC_SUCC;
		dp.roomId = roomId;
		Game.msg.lobby.sendPlayerEnterGameResp(dp, cccid);
		
		Game.msg.scene.sendInitCmd(scene.toInitDp(), cccid);
		Game.msg.scene.sendInitApesCmd(scene.apes, cccid);
	}
	
	public void movePlayer(float tx, float ty, Long cccid)
	{
		GameCB bundle = Game.bundle.getBundle(cccid);
		if(bundle == null) { return; }
		PlayerApe player = bundle.player;
		
		if(player.getStep() <= 0)
		{
			player.exit();
			return;
		}
		
		boolean succ;
		if(player.isMoving())
		{
			succ = false;
		}
		else
		{
			if( player.getScene() != null )
			{
				player.setStep( (short)(player.getStep()-1) );
				player.setTx(tx);
				player.setTy(ty);
				player.setMoving(true);
				player.broadcastTarPos();
				succ = true;
			}
			else
			{
				succ = false;
			}
		}
		Game.msg.scene.sendMovePlayerResp(succ, player.movingTime(), tx, ty, player.cccid());
	}
	
	/** 玩家请求使用装备道具 */
	public void useEquip(int equipId, Long cccid)
	{
		GameCB bundle = Game.bundle.getBundle(cccid);
		if(bundle == null) { return; }
		PlayerApe player = bundle.player;
		player.useEquip(equipId);
	}
}
