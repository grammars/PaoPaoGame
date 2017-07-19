package jack911.pp.ui.goods.equip
{
	import com.anstu.jsock.utils.Long;
	import com.anstu.jui.controls.JPanel;
	
	import jack911.pp.core.goods.GoodsInfo;
	
	import jack911.pp.core.Game;

	public class EquipField
	{
		private var equipCells:Vector.<EquipCell> = new Vector.<EquipCell>();
		
		public function EquipField()
		{
		}
		
		public function pushCell(cell:JPanel, type:int):void
		{
			equipCells.push( new EquipCell(cell, type) );
		}
		
		/** 初始化装备Icon */
		public function initIcons():void
		{
			clearIcons();
			var num:int = Game.goods.equip.items.length;
			for(var i:int = 0; i < num; i++)
			{
				var item:GoodsInfo = Game.goods.equip.items[i];
				addIcon(item);
			}
		}
		
		/** 添加装备Icon */
		public function addIcon(item:GoodsInfo):void
		{
			var icon:EquipIcon = new EquipIcon();
			icon.setup(item);
			putIcon(icon);
		}
		
		/** 处置装备icon */
		private function putIcon(icon:EquipIcon):void
		{
			var equipType:int = icon.info.equipType();
			for(var i:int = 0; i < equipCells.length; i++)
			{
				var cell:EquipCell = equipCells[i];
				if(cell.type == equipType)
				{
					cell.addIcon(icon);
					break;
				}
			}
		}
		
		/** 移除装备Icon */
		public function removeIcon(uid:Long):EquipIcon
		{
			for(var i:int = equipCells.length-1; i >= 0; i--)
			{
				var cell:EquipCell = equipCells[i];
				if( cell.icon && uid.euqals( cell.icon.info.uid ) )
				{
					var tmpIcon:EquipIcon = cell.icon;
					cell.removeIcon();
					return tmpIcon;
				}
			}
			return null;
		}
		
		/** 更新装备Icon */
		public function updateIcon(newItem:GoodsInfo):void
		{
			for(var i:int = equipCells.length-1; i >= 0; i--)
			{
				var cell:EquipCell = equipCells[i];
				if( cell.icon && newItem.uid.euqals( cell.icon.info.uid ) )
				{
					cell.icon.setup(newItem);
					break;
				}
			}
		}
		
		/** 清除装备Icon */
		public function clearIcons():void
		{
			for(var i:int = 0; i < equipCells.length; i++)
			{
				var cell:EquipCell = equipCells[i];
				cell.removeIcon();
			}
		}
		
	}
}