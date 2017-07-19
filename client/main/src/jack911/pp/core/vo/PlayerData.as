package jack911.pp.core.vo
{
	import com.anstu.jsock.utils.EndianFacade;
	import com.anstu.jsock.utils.Long;
	
	import flash.utils.ByteArray;
	
	import jack911.pp.ui.View;

	public class PlayerData
	{
		/** 角色唯一id */
		public var uid:Long;
		/** 角色名 */
		public var name:String;
		/** 等级 */
		public var level:int;
		/** 现金泡币 */
		public var cash:int;
		/** 调试信息 */
		public var debug:String;
		
		public function PlayerData()
		{
		}
		
		public function readFrom(bytes:ByteArray):void
		{
			this.uid = EndianFacade.readLong(bytes);
			this.name = EndianFacade.readString(bytes);
			this.level = EndianFacade.readInt(bytes);
			this.cash = EndianFacade.readInt(bytes);
			this.debug = EndianFacade.readString(bytes);
			handleView();
		}
		
		private function handleView():void
		{
			View.self.updateName(this.name);
			View.self.updateLevel(this.level);
		}
		
		public function toString():String
		{
			return "[PlayerData] uid=" + uid + " name=" + name + " level=" + level + " debug=" + debug;
		}
	}
}