package jack911.pp.centre.msg;

import jack911.pp.centre.msg.content.GmMsg4Centre;
import jack911.pp.centre.msg.content.GoodsMsg4Centre;
import jack911.pp.centre.msg.content.LobbyMsg4Centre;
import jack911.pp.centre.msg.content.LoginMsg4Centre;
import jack911.pp.centre.msg.content.PlayerMsg4Centre;
import jack911.pp.centre.msg.content.SceneMsg4Centre;

public class CentreMsgHandler
{
	public LoginMsg4Centre login = new LoginMsg4Centre();
	public LobbyMsg4Centre lobby = new LobbyMsg4Centre();
	public PlayerMsg4Centre player = new PlayerMsg4Centre();
	public SceneMsg4Centre scene = new SceneMsg4Centre();
	public GoodsMsg4Centre goods = new GoodsMsg4Centre();
	public GmMsg4Centre gm = new GmMsg4Centre();
}