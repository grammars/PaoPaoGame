package jack911.pp.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jack911.pp.common.vo.PlayerData;

@Entity
@Table(name="player_tbl")
public class PlayerEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="uid")
	private Long uid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="level")
	private Integer level;
	
	@Column(name="cash")
	private Integer cash;

	@Column(name="bag")
	private String bag;

	@Column(name="equip")
	private String equip;
	
	@Column(name="debug")
	private String debug;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Long getUid()
	{
		return uid;
	}

	public void setUid(Long uid)
	{
		this.uid = uid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public Integer getLevel()
	{
		return level;
	}

	public void setLevel(Integer level)
	{
		this.level = level;
	}
	
	public Integer getCash()
	{
		return cash;
	}

	public void setCash(Integer cash)
	{
		this.cash = cash;
	}
	
	public String getBag()
	{
		return bag;
	}

	public void setBag(String bag)
	{
		this.bag = bag;
	}

	public String getEquip()
	{
		return equip;
	}

	public void setEquip(String equip)
	{
		this.equip = equip;
	}

	public String getDebug()
	{
		return debug;
	}

	public void setDebug(String debug)
	{
		this.debug = debug;
	}
	
	public void init(String name, Long uid)
	{
		this.name = name;
		this.uid = uid;
		this.level = 0;
		this.cash = 20;
		this.debug = "player-init-debug";
	}
	
	public PlayerData createPlayerData()
	{
		PlayerData vo = new PlayerData();
		vo.uid = this.uid;
		vo.name = this.name;
		vo.level = this.level;
		vo.cash = this.cash;
		return vo;
	}
	
	public void setPlayerData(PlayerData vo)
	{
		this.uid = vo.uid;
		this.name = vo.name;
		this.level = vo.level;
		this.cash = vo.cash;
	}
	
}
