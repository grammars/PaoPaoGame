package jack911.pp.core.goods.bag
{
	import com.anstu.jsock.utils.Long;
	
	import jack911.pp.core.goods.GoodsContainer;
	import jack911.pp.core.goods.GoodsInfo;
	
	import jack911.pp.core.Game;

	public class BagContainer extends GoodsContainer
	{
		public function BagContainer()
		{
			super();
		}
		
		/** 清空物品handler */
		override protected function clearItemsHandler():void
		{
			Game.goods.bagHandler.clearItems();
		}
		
		/** 初始化物品handler */
		override protected function initItemsHandler():void
		{
			Game.goods.bagHandler.initItems();
		}
		
		/**  添加物品handler */
		override protected function addItemHandler(item:GoodsInfo):void
		{
			Game.goods.bagHandler.addItem(item);
		}
		
		/** 移除物品handler */
		override protected function removeItemHandler(item:GoodsInfo):void
		{
			Game.goods.bagHandler.removeItem(item);
		}
		
		/** 更新物品handler */
		override protected function updateItemHandler(item:GoodsInfo):void
		{
			Game.goods.bagHandler.updateItem(item);
		}
		
		/** build */
		override protected function build():void
		{
			super.build();
		}
		
	}
}