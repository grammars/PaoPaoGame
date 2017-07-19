package jack911.pp.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jack911.pp.common.vo.UserAccount;

@Entity
@Table(name="account_tbl")
public class UserAccountEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="uid")
	private Long uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="playerUid")
	private Long playerUid;
	
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public Long getPlayerUid()
	{
		return playerUid;
	}

	public void setPlayerUid(Long playerUid)
	{
		this.playerUid = playerUid;
	}

	public String getDebug()
	{
		return debug;
	}

	public void setDebug(String debug)
	{
		this.debug = debug;
	}
	
	public UserAccount createVO()
	{
		UserAccount vo = new UserAccount();
		vo.uid = this.uid;
		vo.username = this.username;
		vo.password = this.password;
		vo.playerUid = this.playerUid;
		return vo;
	}
}
