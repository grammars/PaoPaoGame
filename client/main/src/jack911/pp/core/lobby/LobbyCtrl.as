package jack911.pp.core.lobby
{
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.net.dp.lobby.EnterRoomResultDp;
	import jack911.pp.net.dp.lobby.LobbyInitDp;
	import jack911.pp.ui.View;
	import jack911.pp.ui.lobby.RoomRect;

	public class LobbyCtrl
	{
		public function LobbyCtrl()
		{
		}
		
		/** 初始化大厅 */
		public function initLobby(dp:LobbyInitDp):void
		{
			View.born.hide();
			View.rooms.clear();
			for(var i:int = 0; i < Base.config.room.items.length; i++)
			{
				var rect:RoomRect = new RoomRect();
				rect.present(Base.config.room.items[i]);
				View.rooms.putRect(rect);
			}
			View.face.show();
			View.rooms.show();
			View.chatbox.show();
			//test
			View.goods.show();
		}
		
		/** 处理进入房间的返回结果 */
		public function handleEnterRoom(dp:EnterRoomResultDp):void
		{
			Game.alert.show(dp.getMessage());
			if(dp.errCode == EnterRoomResultDp.EC_SUCC)
			{
				View.face.hide();
				View.rooms.hide();
				View.chatbox.hide();
				View.goods.hide();
			}
		}
	}
}