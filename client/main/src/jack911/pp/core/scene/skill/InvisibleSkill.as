package jack911.pp.core.scene.skill
{
	import flash.utils.setTimeout;
	
	import jack911.pp.core.ape.Ape;

	public class InvisibleSkill
	{
		private var ape:Ape;
		
		public function InvisibleSkill(ape:Ape, time:int)
		{
			this.ape = ape;
			if(this.ape.isHero())
			{
				this.ape.alpha = 0.5;
				this.ape.visible = true;
			}
			else
			{
				this.ape.alpha = 1.0;
				this.ape.visible = false;
			}
			setTimeout(finish, time);
		}
		
		private function finish():void
		{
			this.ape.alpha = 1.0;
			this.ape.visible = true;
			this.ape = null;
		}
	}
}