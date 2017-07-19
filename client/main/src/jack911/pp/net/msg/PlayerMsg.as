package jack911.pp.net.msg
{
	import com.anstu.jcommon.log.Log;
	import com.anstu.jsock.utils.EndianFacade;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.core.Game;
	import jack911.pp.core.goods.StGoodsContainer;
	import jack911.pp.core.vo.PlayerData;
	import jack911.pp.net.MsgDistributor;
	import jack911.pp.net.MsgHead;
	import jack911.pp.net.MsgSender;

	public class PlayerMsg
	{
		private static const MAJOR_MID:int = MsgDistributor.PLAYER_MSG;
		
		/** 角色初始化数据de请求Client->Centre */
		protected static const PLAYER_DATA_INIT_REQ:int = 1;
		/** 角色初始化数据de返回Centre->Client */
		protected static const PLAYER_DATA_INIT_RESP:int = 2;
		
		/** 角色等级改变通知Centre->Client */
		protected static const UPDATE_LEVEL_CMD:int = 11;
		/** 角色现金改变通知Centre->Client */
		protected static const UPDATE_CASH_CMD:int = 12;
		
		/** 发送给客户端的警告消息Centre/Game->Client */
		protected static const ALERT_CMD:int = 99;
		
		public function PlayerMsg()
		{
		}
		
		public static function handle(head:MsgHead, bytes:ByteArray):void
		{
			switch(head.minorId)
			{
			case PLAYER_DATA_INIT_RESP:
				recvPlayerDataInitResp(bytes);
				break;
			case UPDATE_LEVEL_CMD:
				recvUpdateLevelCmd(bytes);
				break;
			case UPDATE_CASH_CMD:
				recvUpdateCashCmd(bytes);
				break;
			case ALERT_CMD:
				recvAlertCmd(bytes);
				break;
			}
		}
		
		/** send(角色初始化数据de请求Client->Centre) */
		public static function sendPlayerDataInitReq():void
		{
			var ms:MsgSender = MsgSender.toCentre(MAJOR_MID, PLAYER_DATA_INIT_REQ);
			ms.send();
		}
		
		/** recv(角色初始化数据de返回Centre->Client) */
		private static function recvPlayerDataInitResp(bytes:ByteArray):void
		{
			var playerData:PlayerData = new PlayerData();
			playerData.readFrom(bytes);
			var bagData:StGoodsContainer = new StGoodsContainer();
			bagData.read(bytes);
			var equipData:StGoodsContainer = new StGoodsContainer();
			equipData.read(bytes);
			
			Game.player.data = playerData;
			Game.goods.bag.importData(bagData);
			Game.goods.equip.importData(equipData);
			
			Log.debug("角色初始化数据de返回Centre->Client:" + playerData);
		}
		
		/** recv(角色等级改变通知Centre->Client) */
		private static function recvUpdateLevelCmd(bytes:ByteArray):void
		{
			var level:int = EndianFacade.readInt(bytes);
			Game.player.setLevel(level);
		}
		
		/** recv(角色现金改变通知Centre->Client) */
		private static function recvUpdateCashCmd(bytes:ByteArray):void
		{
			var cash:int = EndianFacade.readInt(bytes);
			Game.player.setCash(cash);
		}
		
		/** recv(发送给客户端的警告消息Centre/Game->Client) */
		private static function recvAlertCmd(bytes:ByteArray):void
		{
			var words:String = EndianFacade.readString(bytes);
			Game.alert.show(words);
		}
	}
}