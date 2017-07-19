package jack911.pp.core.goods.bag
{
	import com.anstu.jsock.utils.Long;
	
	import jack911.pp.core.goods.GoodsInfo;
	
	import jack911.pp.ui.View;

	public class BagHandler
	{
		public function BagHandler()
		{
		}
		
		/** 清空背包物品 */
		public function clearItems():void
		{
			View.goods.iconField.clearIcons();
			//CommHandler.bagUpdate();
		}
		
		/** 初始化背包物品 */
		public function initItems():void
		{
			View.goods.iconField.initIcons();
			//CommHandler.bagUpdate();
		}
		
		/** 添加物品到背包 */
		public function addItem(item:GoodsInfo):void
		{
			View.goods.iconField.addIcon(item);
			//CommHandler.bagUpdate();
		}
		
		/** 移除物品从背包 */
		public function removeItem(item:GoodsInfo):void
		{
			View.goods.iconField.removeIcon(item.uid);
			//CommHandler.bagUpdate();
		}
		
		/** 更新物品在背包 */
		public function updateItem(item:GoodsInfo):void
		{
			View.goods.iconField.updateIcon(item);
			//CommHandler.bagUpdate();
		}
	}
}