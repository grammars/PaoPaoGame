package jack911.pp.game.scene.skill;

import org.apache.log4j.Logger;

import jack911.pp.game.component.PlayerApe;

public abstract class GameSkill
{	
	/** 隐身术 */
	public static final int ID_INVISIBLE = 69000001;
	
	public static final InvisibleSkill invisible = new InvisibleSkill();
	
	public static void handleSkill(PlayerApe player, int skillId)
	{
		switch(skillId)
		{
		case ID_INVISIBLE: invisible.handle(player); break;
		}
	}
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	abstract void handle(PlayerApe user);
}
