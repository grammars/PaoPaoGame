package jack911.pp.game.msg;

import jack911.pp.game.msg.content.GmMsg4Game;
import jack911.pp.game.msg.content.LobbyMsg4Game;
import jack911.pp.game.msg.content.LoginMsg4Game;
import jack911.pp.game.msg.content.PlayerMsg4Game;
import jack911.pp.game.msg.content.SceneMsg4Game;
import jack911.pp.game.msg.content.SkillMsg4Game;

public class GameMsgHandler
{
	public LoginMsg4Game login = new LoginMsg4Game();
	public LobbyMsg4Game lobby = new LobbyMsg4Game();
	public SceneMsg4Game scene = new SceneMsg4Game();
	public PlayerMsg4Game player = new PlayerMsg4Game();
	public SkillMsg4Game skill = new SkillMsg4Game();
	public GmMsg4Game gm = new GmMsg4Game();
}
