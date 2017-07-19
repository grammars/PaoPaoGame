package jack911.pp.game.msg.content;

import java.util.List;

import jack911.pp.game.Game;
import jack911.pp.game.component.Ape;
import jack911.pp.game.msg.dp.scene.SceneInitDp;
import jack911.pp.message.MsgHead;
import jack911.pp.message.MsgUtil;
import jack911.pp.message.content.SceneMsg;
import jack911.pp.message.dp.game.GameResultDp;
import jack911.pp.server.MsgUnit;
import jack911.pp.server.ServerId;

public class SceneMsg4Game extends SceneMsg
{

	@Override
	public void handle(MsgHead head, MsgUnit msg)
	{
		switch(head.minorId)
		{
		case MOVE_PLAYER_REQ:
			recvMovePlayerReq(head, msg);
			break;
		case USE_EQUIP_REQ:
			recvUseEquipReq(head, msg);
			break;
		}
	}
	
	/** send(场景初始化数据de命令Game->Client) */
	public void sendInitCmd(SceneInitDp dp, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, INIT_SCENE_CMD, cccid);
		dp.writeTo(msg);
		msg.send();
	}
	
	/** send(场景内所有ape数据初始化de命令Game->Client) */
	public void sendInitApesCmd(List<Ape> apes, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, INIT_APES_CMD, cccid);
		msg.writeInt(apes.size());
		for(Ape a : apes)
		{
			msg.writeByte(a.getType());
			a.writeTo(msg);
		}
		msg.send();
	}
	
	/** send(场景内添加一个player的命令Game->Client) */
	public void sendAddApeCmd(Ape a, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, ADD_APE_CMD, cccid);
		msg.writeByte(a.getType());
		a.writeTo(msg);
		msg.send();
	}
	
	/** send(场景内移除一个player的命令Game->Client) */
	public void sendRemoveApeCmd(int tid, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, REMOVE_APE_CMD, cccid);
		msg.writeInt(tid);
		msg.send();
	}
	
	/** recv(场景内移动player的请求Client->Game) */
	private void recvMovePlayerReq(MsgHead head, MsgUnit msg)
	{
		float tx = msg.readFloat();
		float ty = msg.readFloat();
		Game.scene.movePlayer(tx, ty, head.cccid);
	}

	/** send(场景内移动player的应答Game->Client) */
	public void sendMovePlayerResp(boolean succ, int moveTime, float tx, float ty, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, MOVE_PLAYER_RESP, cccid);
		msg.writeBoolean(succ);
		msg.writeInt(moveTime);
		msg.writeFloat(tx);
		msg.writeFloat(ty);
		msg.send();
	}
	
	/** send(立即更新ape坐标de命令Game->Client) */
	public void sendUpdatePosImmedCmd(int tid, float x, float y, Long cccid) 
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_POS_IMMED_CMD, cccid);
		msg.writeInt(tid);
		msg.writeFloat(x);
		msg.writeFloat(y);
		msg.send();
	}
	
	/** send(player移动完成de通知Game->Client) */
	public void sendMoveCompleteNotice(Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, MOVE_COMPLETE_NOTICE, cccid);
		msg.send();
	}
	
	/** send(更新ape目标坐标de命令Game->Client) */
	public void sendUpdateTargetPos(int tid, float tx, float ty, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPATE_TARGET_POS_CMD, cccid);
		msg.writeInt(tid);
		msg.writeFloat(tx);
		msg.writeFloat(ty);
		msg.send();
	}
	
	/** send(更新ape目标保护时间de命令Game->Client) */
	public void sendUpdateProtect(int tid, int leftTime, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPATE_PROTECT_CMD, cccid);
		msg.writeInt(tid);
		msg.writeInt(leftTime);
		msg.send();
	}
	
	/** send(游戏结束的结果Game->Client) */
	public void sendGameOverResult(GameResultDp dp)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, GAME_OVER_RESULT, dp.cccid);
		dp.writeTo(msg);
		msg.send();
	}
	
	/** send(更新ape尺寸Game->Client) */
	public void sendUpdateRadiusCmd(int tid, float r, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_RADIUS_CMD, cccid);
		msg.writeInt(tid);
		msg.writeFloat(r);
		msg.send();
	}
	
	/** send(更新ape速度Game->Client) */
	public void sendUpdateSpeedCmd(int tid, float speed, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_SPEED_CMD, cccid);
		msg.writeInt(tid);
		msg.writeFloat(speed);
		msg.send();
	}
	
	/** send(更新玩家步数de命令Game->Client) */
	public void sendUpdateStepCmd(short step, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, UPDATE_STEP_CMD, cccid);
		msg.writeShort(step);
		msg.send();
	}
	
	/** send(报送游戏的结果Game->Centre) */
	public void sendSubmitGameResult(GameResultDp dp)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, SUBMIT_GAME_RESULT, ServerId.CENTRE);
		dp.writeTo(msg);
		msg.send();
	}
	
	/** 玩家请求使用装备道具de请求Client->Game */
	private void recvUseEquipReq(MsgHead head, MsgUnit msg)
	{
		int equipId = msg.readInt();
		Game.scene.useEquip(equipId, head.cccid);
	}
	
	/** send(玩家请求使用装备道具de返回Game->Client) */
	public void sendUseEquipResp(int equipId, Long cccid)
	{
		MsgUnit msg = MsgUtil.createMsgToClient(MAJOR_MID, USE_EQUIP_RESP, cccid);
		msg.writeInt(equipId);
		msg.send();
	}
	
	/** 装备消耗扣除de命令Game->Centre */
	public void sendDeductEquipCmd(Long cccid, int equipId)
	{
		MsgUnit msg = MsgUtil.createMsgToServer(MAJOR_MID, DEDUCT_EQUIP_CMD, ServerId.CENTRE);
		msg.writeCCCID(cccid);
		msg.writeInt(equipId);
		msg.send();
	}
	
}
