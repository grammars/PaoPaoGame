package jack911.pp.game;

import jack911.pp.game.bundle.GameBundleHandler;
import jack911.pp.game.msg.GameMsgHandler;
import jack911.pp.game.scene.GameSceneHandler;

public class Game
{
	public static GameMsgHandler msg = new GameMsgHandler();
	public static GameBundleHandler bundle = new GameBundleHandler();
	public static GameSceneHandler scene = new GameSceneHandler();
	
	public static void run()
	{
		scene.initialize();
	}
}
