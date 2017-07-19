package jack911.pp.game.scene.skill;

import jack911.pp.game.Game;
import jack911.pp.game.component.PlayerApe;

public class InvisibleSkill extends GameSkill
{
	private final int invisibleTime = 5000;//5000ms隐身时间
	
	@Override
	void handle(PlayerApe user)
	{
		for(PlayerApe player : user.getScene().players)
		{
			Game.msg.skill.sendUseInvisibleCmd(user.getId(), invisibleTime, player.cccid());
		}
	}
	
}
