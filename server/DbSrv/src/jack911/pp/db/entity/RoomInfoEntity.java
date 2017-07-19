package jack911.pp.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jack911.pp.common.vo.RoomInfo;

@Entity
@Table(name="room_tbl")
public class RoomInfoEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="roomId")
	private Integer roomId;
	
	@Column(name="tickets")
	private Integer tickets;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getRoomId()
	{
		return roomId;
	}

	public void setRoomId(Integer roomId)
	{
		this.roomId = roomId;
	}

	public Integer getTickets()
	{
		return tickets;
	}

	public void setTickets(Integer tickets)
	{
		this.tickets = tickets;
	}
	
	public RoomInfo toInfo()
	{
		RoomInfo ri = new RoomInfo();
		ri.id = this.id;
		ri.roomId = this.roomId;
		ri.tickets = this.tickets;
		return ri;
	}
	
}
