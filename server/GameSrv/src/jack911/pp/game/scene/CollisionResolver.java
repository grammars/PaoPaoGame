package jack911.pp.game.scene;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import jack911.pp.game.component.Ape;
import jack911.pp.game.component.FoodApe;
import jack911.pp.game.component.MonsterApe;
import jack911.pp.game.component.PlayerApe;

public class CollisionResolver
{
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static class Pair
	{
		public Ape a, b;
		public Pair(Ape a, Ape b)
		{
			this.a = a; this.b = b;
		}
	}
	
	private List<Pair> pairs = new ArrayList<>();
	
	public void addTest(Ape a, Ape b)
	{
		pairs.add(new Pair(a, b));
	}
	
	public void doTest()
	{
		for(Pair p : pairs)
		{
			testSingle(p.a, p.b);
		}
		pairs.clear();
	}
	
	private void testSingle(Ape a, Ape b)
	{
		if( a.getType() == Ape.TYPE_MONSTER && b.getType() == Ape.TYPE_MONSTER ) { return; }
		if( a.getType() == Ape.TYPE_FOOD && b.getType() == Ape.TYPE_FOOD ) { return; }
		
		if( (a.getType() == Ape.TYPE_PLAYER && b.getType() == Ape.TYPE_PLAYER) ||
				(a.getType() + b.getType() ==  Ape.TYPE_MONSTER + Ape.TYPE_PLAYER) )
		{
			if( a.collisionProtect() || b.collisionProtect() ) { return; }
		}
		
		double distSq = ( a.getX()-b.getX() ) * ( a.getX()-b.getX() ) + ( a.getY()-b.getY() ) * ( a.getY()-b.getY() );
		double min = ( a.getR()+b.getR() ) * ( a.getR()+b.getR() );
		if( distSq < min )
		{	
			if(a.getType() == Ape.TYPE_PLAYER && b.getType() == Ape.TYPE_PLAYER)
			{
				logger.debug("[玩家]碰撞[玩家]");
				a.collide(3000);
				b.collide(3000);
				
				Ape win;
				Ape los;
				if(a.getR() > b.getR())
				{
					win = a;
					los = b;
				}
				else
				{
					win = b;
					los = a;
				}
				float nwr = (float)Math.sqrt( los.getR()*los.getR()*los.getEatRat() + win.getR()*win.getR() );
				win.setR( nwr );
				los.setR( los.getR() * (1.0f-los.getEatRat()) );
				win.broadcastR();
				los.broadcastR();
				handlePlayerHurt(los);
			}
			else if( a.getType() + b.getType() ==  Ape.TYPE_MONSTER + Ape.TYPE_PLAYER )
			{
				logger.debug("[玩家]碰撞[怪物]");
				MonsterApe monster;
				PlayerApe player;
				if(a.getType() == Ape.TYPE_MONSTER)
				{
					monster = (MonsterApe)a;
					player = (PlayerApe)b;
				}
				else
				{
					monster = (MonsterApe)b;
					player = (PlayerApe)a;
				}
				player.collide(3000);
				player.setR( player.getR() * (1.0f-monster.damageRat()) );
				player.broadcastR();
				handlePlayerHurt(player);
			}
			else if( a.getType() + b.getType() ==  Ape.TYPE_FOOD + Ape.TYPE_PLAYER )
			{
				FoodApe food;
				PlayerApe player;
				if(a.getType() == Ape.TYPE_FOOD)
				{
					food = (FoodApe)a;
					player = (PlayerApe)b;
				}
				else
				{
					food = (FoodApe)b;
					player = (PlayerApe)a;
				}
				float nwr = (float)Math.sqrt( food.getR()*food.getR() + player.getR()*player.getR() );
				player.setR( nwr );
				player.broadcastR();
				food.exit();
			}
		}
	}
	
	/** 处理可能是玩家受伤的情况 */
	private void handlePlayerHurt(Ape a)
	{
		if(a.getR() < 10f)
		{
			a.exit();
		}
	}
}
