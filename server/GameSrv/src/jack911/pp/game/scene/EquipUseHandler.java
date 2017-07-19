package jack911.pp.game.scene;

import jack911.pp.config.table.EquipTblUnit;
import jack911.pp.game.Game;
import jack911.pp.game.component.PlayerApe;
import jack911.pp.game.scene.skill.GameSkill;

public class EquipUseHandler
{
	public static void use(PlayerApe player, EquipTblUnit cfg)
	{
		System.out.println("" + player.getName() + " 正在使用 " + cfg.name);
		
		if(cfg.rVal > 0)
		{
			player.setR( player.getR() + cfg.rVal );
			player.broadcastR();
		}
		if(cfg.rRat > 0)
		{
			player.setR( player.getR() * (1.0f + cfg.rRat) );
			player.broadcastR();
		}
		
		if(cfg.speedVal > 0)
		{
			player.setSpeed( player.getSpeed() + cfg.speedVal );
			player.broadcastSpeed();
		}
		if(cfg.speedRat > 0)
		{
			player.setSpeed( player.getSpeed() * (1.0f + cfg.speedRat) );
			player.broadcastSpeed();
		}
		
		if(cfg.stepVal > 0)
		{
			player.setStep( (short)(player.getStep() + cfg.stepVal) );
		}
		if(cfg.stepRat > 0)
		{
			player.setStep( (short)(player.getStep() * (1.0f + cfg.stepRat)) );
		}
		
		if(cfg.protectTime > 0)
		{
			player.changeProtectTime(cfg.protectTime);
			player.broadcastProtect();
		}
		
		if(cfg.skillId > 0)
		{
			GameSkill.handleSkill(player, cfg.skillId);
		}
		
		Game.msg.scene.sendUseEquipResp(cfg.id, player.cccid());
		Game.msg.scene.sendDeductEquipCmd(player.cccid(), cfg.id);
	}
}
