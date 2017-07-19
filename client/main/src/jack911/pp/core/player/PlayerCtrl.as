package jack911.pp.core.player
{
	import jack911.pp.core.vo.PlayerData;
	import jack911.pp.ui.View;

	public class PlayerCtrl
	{
		private var _data:PlayerData;
		public function set data(value:PlayerData):void
		{
			_data = value;
			View.face.updatePlayerData(_data);
			View.self.updateLevel(_data.level);
		}
		public function get data():PlayerData { return _data; }
		
		public function PlayerCtrl()
		{
		}
		
		public function setLevel(level:int):void
		{
			data.level = level;
			View.face.updatePlayerData(_data);
			View.self.updateLevel(level);
		}
		public function getLevel():int { return _data.level; }
		
		public function setCash(cash:int):void
		{
			data.cash = cash;
			View.face.updatePlayerData(_data);
		}
		public function getCash():int { return _data.cash; }
	}
}