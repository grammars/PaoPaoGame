package jack911.pp.game.util;

public class GameUtil
{
	public static double distance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );
	}
}
