package jack911.pp.game.bundle;

import jack911.pp.common.bundle.ClientBundle;
import jack911.pp.game.component.PlayerApe;

public class GameCB extends ClientBundle
{
	public PlayerApe player;
	
	public void bindPlayer(PlayerApe player)
	{
		this.player = player;
		this.player.bundle = this;
	}
}
