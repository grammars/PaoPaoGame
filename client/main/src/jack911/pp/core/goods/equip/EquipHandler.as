package jack911.pp.core.goods.equip
{	
	import com.anstu.jsock.utils.Long;
	
	import jack911.pp.core.goods.GoodsInfo;
	
	import jack911.pp.ui.View;
	
	import jack911.pp.ui.deprecated.res.Res;

	public class EquipHandler
	{
		public function EquipHandler()
		{
		}
		
		/** 清空装备 */
		public function clearItems():void
		{
			View.goods.equipField.clearIcons();
		}
		
		/** 初始化装备 */
		public function initItems():void
		{
			View.goods.equipField.initIcons();
		}
		
		/** 穿上装备 */
		public function addItem(item:GoodsInfo):void
		{
			View.goods.equipField.addIcon(item);
		}
		
		/** 卸下装备 */
		public function removeItem(itemUid:Long):void
		{
			View.goods.equipField.removeIcon(itemUid);
		}
		
		/** 更新物品在装备 */
		public function updateItem(item:GoodsInfo):void
		{
			View.goods.equipField.updateIcon(item);
		}
		
	}
}