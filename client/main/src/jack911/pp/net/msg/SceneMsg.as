package jack911.pp.net.msg
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Game;
	import jack911.pp.core.Layer;
	import jack911.pp.core.ape.Ape;
	import jack911.pp.core.ape.ApeFactory;
	import jack911.pp.core.ape.PlayerApe;
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;
	import jack911.pp.net.MsgSender;
	import jack911.pp.net.dp.game.GameResultDp;
	import jack911.pp.net.dp.scene.SceneInitDp;
	import jack911.pp.ui.View;

	public class SceneMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.SCENE_MSG;
		
		/** 场景初始化数据de命令Game->Client */
		protected static const INIT_SCENE_CMD:int = 1;
		/** 场景内所有ape数据初始化de命令Game->Client */
		protected static const INIT_APES_CMD:int = 2;
		
		/** 场景内添加一个ape的命令Game->Client */
		protected static const ADD_APE_CMD:int = 11;
		/** 场景内移除一个ape的命令Game->Client */
		protected static const REMOVE_APE_CMD:int = 12;
		/** 场景内移动player的请求Client->Game */
		protected static const MOVE_PLAYER_REQ:int = 13;
		/** 场景内移动player的应答Game->Client */
		protected static const MOVE_PLAYER_RESP:int = 14;
		/** 立即更新ape坐标de命令Game->Client */
		protected static const UPDATE_POS_IMMED_CMD:int = 15;
		/** player移动完成de通知Game->Client */
		protected static const MOVE_COMPLETE_NOTICE:int = 16;
		/** 更新ape尺寸de命令Game->Client */
		protected static const UPDATE_RADIUS_CMD:int = 17;
		/** 更新ape速度de命令Game->Client */
		protected static const UPDATE_SPEED_CMD:int = 18;
		/** 更新ape目标坐标de命令Game->Client */
		protected static const UPATE_TARGET_POS_CMD:int = 19;
		/** 更新玩家步数de命令Game->Client */
		protected static const UPDATE_STEP_CMD:int = 20;
		/** 更新玩家保护时间de命令Game->Client */
		protected static const UPATE_PROTECT_CMD:int = 21;
		
		/** 玩家请求使用装备道具de请求Client->Game */
		protected static const USE_EQUIP_REQ:int = 31;
		/** 玩家请求使用装备道具de返回Game->Client */
		protected static const USE_EQUIP_RESP:int = 32;
		
		/** 游戏结束的结果Game->Client */
		protected static const GAME_OVER_RESULT:int = 41;
		
		public function SceneMsg()
		{
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			case INIT_SCENE_CMD:
				recvInitSceneCmd(bytes);
				break;
			case INIT_APES_CMD:
				recvInitApesCmd(bytes);
				break;
			case ADD_APE_CMD:
				recvAddApeCmd(bytes);
				break;
			case REMOVE_APE_CMD:
				recvRemoveApeCmd(bytes);
				break;
			case MOVE_PLAYER_RESP:
				recvMovePlayerResp(bytes);
				break;
			case UPDATE_POS_IMMED_CMD:
				recvUpdatePosImmedCmd(bytes);
				break;
			case MOVE_COMPLETE_NOTICE:
				recvMoveCompleteNotice(bytes);
				break;
			case UPDATE_RADIUS_CMD:
				recvUpdateRadiusCmd(bytes);
				break;
			case UPDATE_SPEED_CMD:
				recvUpdateSpeedCmd(bytes);
				break;
			case UPATE_TARGET_POS_CMD:
				recvUpdateTargetPosCmd(bytes);
				break;
			case UPATE_PROTECT_CMD:
				recvUpdateProtectCmd(bytes);
				break;
			case GAME_OVER_RESULT:
				recvGameOverResult(bytes);
				break;
			case UPDATE_STEP_CMD:
				recvStepCmd(bytes);
				break;
			case USE_EQUIP_RESP:
				recvUseEquipResp(bytes)
				break;
			}
		}
		
		
		/** recv(场景初始化数据de命令Game->Client) */
		private static function recvInitSceneCmd(bytes:ByteArray):void
		{
			var dp:SceneInitDp = new SceneInitDp();
			dp.readFrom(bytes);
			Game.scene.initScene(dp);
		}
		
		/** recv(场景内所有ape数据初始化de命令Game->Client) */
		private static function recvInitApesCmd(bytes:ByteArray):void
		{
			var num:int = EndianFacade.readInt(bytes);
			for(var i:int = 0; i < num; i++)
			{
				var type:int = EndianFacade.readByte(bytes);
				var a:Ape = ApeFactory.create(type);
				a.readFrom(bytes);
				Game.scene.add(a);
			}
			Game.alert.show("场景内所有玩家数据初始化de命令Game->Client: 玩家数量" + num);
		}
		
		/** recv(场景内添加一个ape的命令Game->Client) */
		private static function recvAddApeCmd(bytes:ByteArray):void
		{
			Game.alert.show("场景内添加一个ape的命令Game->Client");
			var type:int = EndianFacade.readByte(bytes);
			var a:Ape = ApeFactory.create(type);
			a.readFrom(bytes);
			Game.scene.add(a);
		}
		
		/** recv(场景内移除一个player的命令Game->Client) */
		private static function recvRemoveApeCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			Log.debug("移除ape (" + id + ")");
			Game.scene.remove(id);
		}
		
		/** 场景内移动player的请求Client->Game */
		public static function sendMovePlayerReq(tx:Number, ty:Number):void
		{
			var ms:MsgSender = MsgSender.toGame(MAJOR_MID, MOVE_PLAYER_REQ);
			ms.writeFloat(tx);
			ms.writeFloat(ty);
			ms.send();
		}
		
		/** recv(场景内移动player的应答Game->Client) */
		private static function recvMovePlayerResp(bytes:ByteArray):void
		{
			var succ:Boolean = EndianFacade.readBoolean(bytes);
			var moveTime:int = EndianFacade.readInt(bytes);
			var tx:int = EndianFacade.readFloat(bytes);
			var ty:int = EndianFacade.readFloat(bytes);
			if(succ)
			{
				Game.scene.hero.move(tx, ty);
				Layer.scene.background.setTarget(tx, ty);
				View.status.moveCountdown(moveTime);
			}
			Game.scene.canMove = false;
			
			Game.alert.show("移动应答:" + succ + " 剩余时间：" + moveTime);
		}
		
		/** recv(立即更新ape坐标de命令Game->Client) */
		private static function recvUpdatePosImmedCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			var px:int = EndianFacade.readFloat(bytes);
			var py:int = EndianFacade.readFloat(bytes);
			var a:Ape = Game.scene.getApe(id);
			if(a)
			{
				a.x = px;
				a.y = py;
			}
		}
		
		/** recv(player移动完成de通知Game->Client) */
		private static function recvMoveCompleteNotice(bytes:ByteArray):void
		{
			Game.scene.canMove = true;
		}
		
		/** recv(更新ape尺寸de命令Game->Client) */
		private static function recvUpdateRadiusCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			var r:Number = EndianFacade.readFloat(bytes);
			var a:Ape = Game.scene.getApe(id);
			if(a)
			{
				a.setR(r);
				if(a.isHero())
				{
					View.status.updateR(r);
				}
			}
		}
		
		/** recv(更新ape速度de命令Game->Client) */
		private static function recvUpdateSpeedCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			var speed:Number = EndianFacade.readFloat(bytes);
			var a:Ape = Game.scene.getApe(id);
			if(a)
			{
				a.setSpeed(speed);
				if(a.isHero())
				{
					//View.status.updateSpeed(speed);
				}
			}
		}
		
		/** recv(更新ape目标坐标de命令Game->Client) */
		private static function recvUpdateTargetPosCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			var tx:int = EndianFacade.readFloat(bytes);
			var ty:int = EndianFacade.readFloat(bytes);
			var a:Ape = Game.scene.getApe(id);
			if(a)
			{
				a.move(tx, ty);
			}
		}
		
		/** recv(更新玩家保护时间de命令Game->Client) */
		private static function recvUpdateProtectCmd(bytes:ByteArray):void
		{
			var id:int = EndianFacade.readInt(bytes);
			var leftTime:int = EndianFacade.readInt(bytes);
			var a:Ape = Game.scene.getApe(id);
			if(a)
			{
				a.setProtectTime(leftTime);
			}
		}
		
		/** recv(游戏结束的结果Game->Client) */
		private static function recvGameOverResult(bytes:ByteArray):void
		{
			var result:GameResultDp = new GameResultDp();
			result.readFrom(bytes);
			Game.scene.handleGameResult(result);
		}
		
		/** recv(更新玩家步数de命令Game->Client) */
		private static function recvStepCmd(bytes:ByteArray):void
		{
			var step:int = EndianFacade.readShort(bytes);
			View.status.updateStep(step);
		}
		
		/** 玩家请求使用装备道具de请求Client->Game */
		public static function useEquipReq(equipId:int):void
		{
			var ms:MsgSender = MsgSender.toGame(MAJOR_MID, USE_EQUIP_REQ);
			ms.writeInt(equipId);
			ms.send();
		}
		
		/** recv(玩家请求使用装备道具de返回Game->Client) */
		private static function recvUseEquipResp(bytes:ByteArray):void
		{
			var equipId:int = EndianFacade.readInt(bytes);
			View.oper.disableEquip(equipId);
		}
	}
}