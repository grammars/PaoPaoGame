package jack911.pp.core.ape
{
	public class ApeFactory
	{
		public function ApeFactory()
		{
		}
		
		public static function create(type:int):Ape
		{
			switch(type)
			{
			case Ape.TYPE_FOOD:
				return new FoodApe();
				break;
			case Ape.TYPE_PLAYER:
				return new PlayerApe();
				break;
			case Ape.TYPE_MONSTER:
				return new MonsterApe();
				break;
			}
			return new Ape();
		}
			
	}
}