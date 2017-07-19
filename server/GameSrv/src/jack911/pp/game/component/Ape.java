package jack911.pp.game.component;

import jack911.pp.config.Cfg;
import jack911.pp.game.Game;
import jack911.pp.game.scene.GameScene;
import jack911.pp.game.util.GameUtil;
import jack911.pp.server.MsgUnit;
import jack911.util.MyUtil;

public abstract class Ape
{
	public static final byte TYPE_PLAYER = 1;
	public static final byte TYPE_FOOD = 2;
	public static final byte TYPE_MONSTER = 4;
	protected byte type;
	public byte getType() { return type; }
	
	private int id;
	/** 临时id */
	public int getId() { return id; }
	
	/** 位置坐标x */
	protected float x;
	/** 位置坐标y */
	protected float y;
	/** 目标坐标x */
	protected float tx;
	/** 目标坐标y */
	protected float ty;
	/** 半径 */
	protected float r = 30;
	/** 移动中 */
	protected boolean moving = false;
	/** 移动速度 当r=36的时候 每秒移动100像素则 speed=100*sqrt(36) */
	protected float speed = 600.0f;
	/** 侵食比例 */
	protected float eatRat = 1.0f;
	/** 名字 */
	protected String name = "Ape";
	/** 皮肤 */
	protected String skin = "p0000";
	
	public Ape()
	{
		id = MyUtil.createTidInt();
	}
	
	public float getX()
	{
		return x;
	}
	public void setX(float x)
	{
		this.x = x;
	}
	
	public float getY()
	{
		return y;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getTx()
	{
		return tx;
	}
	public void setTx(float tx)
	{
		this.tx = tx;
	}
	
	public float getTy()
	{
		return ty;
	}
	public void setTy(float ty)
	{
		this.ty = ty;
	}
	
	public float getR()
	{
		return r;
	}
	public void setR(float r)
	{
		this.r = r;
	}
	
	public boolean isMoving()
	{
		return moving;
	}
	public void setMoving(boolean moving)
	{
		this.moving = moving;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	
	/** 每毫秒步长 */
	protected float step_ms()
	{
		return (speed/(float)Math.sqrt(r))/1000;
	}
	/** 每帧步长 */
	protected float step_fr() 
	{
		return step_ms() * Cfg.FRAME_MS; 
	}
	
	public float getEatRat()
	{
		return eatRat;
	}
	public void setEatRat(float eatRat)
	{
		this.eatRat = eatRat;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSkin()
	{
		return skin;
	}

	public void setSkin(String skin)
	{
		this.skin = skin;
	}
	
	protected GameScene scene;
	public GameScene getScene() { return this.scene; }
	
	public void enter(GameScene scene)
	{
		this.scene = scene;
	}
	
	public void exit()
	{
		if(scene != null)
		{
			scene.remove(this);
			scene = null;
		}
	}
	
	/** 获取本次移动的剩余耗时ms */
	public int movingTime()
	{
		double dist = GameUtil.distance(tx, ty, x, y);
		return (int)(dist/step_ms());
	}
	
	private long collisionProTime = 0;
	/** 发生碰撞
	 * @param addProTime 增加碰撞保护时间 */
	public void collide(long addProTime)
	{
		changeProtectTime(addProTime);
	}
	/** 改变保护时间ms */
	public void changeProtectTime(long value)
	{
		long now = MyUtil.now();
		if(now > collisionProTime)
		{
			collisionProTime = now + value;
		}
		else
		{
			collisionProTime += value;
		}
	}
	/** 是否处于碰撞保护期间 */
	public boolean collisionProtect()
	{
		long now = MyUtil.now();
		return now < collisionProTime;
	}
	
	public void update()
	{
		if(this.moving)
		{
			double distTar = (tx-x)*(tx-x) + (ty-y)*(ty-y);
			double distStep = step_fr()*step_fr();
			if(distStep > distTar)
			{
				this.setX(tx);
				this.setY(ty);
				this.moving = false;
				updatePosition();
				moveComplete();
			}
			else
			{
				double deltX = 0;
				double deltY = 0;
				double rad = Math.atan2(ty-y, tx-x);
				double rateX = Math.cos(rad);
				double rateY = Math.sin(rad);
				if(tx-x > 0)
				{
					deltX = Math.abs( step_fr() * rateX );
				}
				else
				{
					deltX = -Math.abs( step_fr() * rateX );
				}
				if(ty-y > 0)
				{
					deltY = Math.abs( step_fr() * rateY );
				}
				else
				{
					deltY = -Math.abs( step_fr() * rateY );
				}
				this.setX((float)(x+deltX));
				this.setY((float)(y+deltY));
				updatePosition();
			}
		}
	}
	
	/** 坐标更新了 */
	protected void updatePosition()
	{
		
	}
	
	/** 移动完成了 */
	protected void moveComplete()
	{
		
	}
	
	/** 广播自己的尺寸 */
	public void broadcastR()
	{
		for(PlayerApe player : scene.players)
		{
			Game.msg.scene.sendUpdateRadiusCmd(this.id, this.r, player.cccid());
		}
	}
	
	/** 广播自己的速度 */
	public void broadcastSpeed()
	{
		for(PlayerApe player : scene.players)
		{
			Game.msg.scene.sendUpdateSpeedCmd(this.id, this.speed, player.cccid());
		}
	}
	
	/** 广播自己的移动目标点 */
	public void broadcastTarPos()
	{
		for(PlayerApe player : scene.players)
		{
			Game.msg.scene.sendUpdateTargetPos(this.id, tx, ty, player.cccid());
		}
	}
	
	/** 广播自己的当前位置 */
	public void broadcastCurPos()
	{
		for(PlayerApe player : scene.players)
		{
			Game.msg.scene.sendUpdatePosImmedCmd(this.id, x, y, player.cccid());
		}
	}
	
	/** 广播自己的保护时间 */
	public void broadcastProtect()
	{
		long now = MyUtil.now();
		long leftTime = collisionProTime - now;
		if(leftTime < 0)
		{
			leftTime = 0;
		}
		for(PlayerApe player : scene.players)
		{
			Game.msg.scene.sendUpdateProtect(this.id, (int)leftTime, player.cccid());
		}
	}
	
	/** writeTo */
	public void writeTo(MsgUnit msg)
	{
		msg.writeInt(id);
		msg.writeFloat(x);
		msg.writeFloat(y);
		msg.writeFloat(r);
		msg.writeFloat(speed);
		msg.writeString(name);
		msg.writeString(skin);
	}
	
}
