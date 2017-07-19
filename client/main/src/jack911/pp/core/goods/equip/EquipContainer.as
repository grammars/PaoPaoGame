package jack911.pp.core.goods.equip
{
	import com.anstu.jsock.utils.Long;
	
	import jack911.pp.core.goods.GoodsContainer;
	import jack911.pp.core.goods.GoodsInfo;
	import jack911.pp.core.goods.EquipType;
	
	import jack911.pp.core.Game;

	public class EquipContainer extends GoodsContainer
	{
		public function EquipContainer()
		{
			super();
		}
		
		/** 清空物品handler */
		override protected function clearItemsHandler():void
		{
			Game.goods.equipHandler.clearItems();
		}
		
		/** 初始化物品handler */
		override protected function initItemsHandler():void
		{
			Game.goods.equipHandler.initItems();
		}
		
		/**  添加物品handler */
		override protected function addItemHandler(item:GoodsInfo):void
		{
			Game.goods.equipHandler.addItem(item);
		}
		
		/** 移除物品handler */
		override protected function removeItemHandler(item:GoodsInfo):void
		{
			Game.goods.equipHandler.removeItem(item.uid);
		}
		
		/** 更新物品handler */
		override protected function updateItemHandler(item:GoodsInfo):void
		{
			Game.goods.equipHandler.updateItem(item);
		}
		
		/** build */
		override protected function build():void
		{
			super.build();
		}
		
	}
}