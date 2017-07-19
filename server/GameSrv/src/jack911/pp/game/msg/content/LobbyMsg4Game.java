package jack911.pp.game.msg.content;

import jack911.pp.common.vo.PlayerData;
import jack911.pp.game.Game;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.LobbyMsg;
import jack911.pp.message.dp.lobby.EnterRoomResultDp;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class LobbyMsg4Game extends LobbyMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case PLAYER_ENTER_GAME_REQ:
			recvPlayerEnterGameReq(head, msg);
			break;
		}
	}
	
	/** recv(角色登场de请求Centre->Game) */
	private void recvPlayerEnterGameReq(MsgHead head, MsgUnit msg)
	{
		PlayerData pData = new PlayerData();
		pData.readFrom(msg);
		
		//读装备的道具
		int equipSize = msg.readInt();
		int[] equipIds = new int[equipSize]; 
		for(int i = 0; i < equipSize; i++)
		{
			equipIds[i] = msg.readInt();
		}
		
		int roomId = msg.readInt();
		Long cccid = msg.readCCCID();
		//
		Game.scene.handlePlayerEnter(pData, equipIds, roomId, cccid);
	}
	
	/** send(角色登场de请求Game->Centre) */
	public void sendPlayerEnterGameResp(EnterRoomResultDp dp, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, PLAYER_ENTER_GAME_RESP, ServerId.CENTRE);
		msg.writeCCCID(cccid);
		dp.writeTo(msg);
		msg.send();
	}

}
