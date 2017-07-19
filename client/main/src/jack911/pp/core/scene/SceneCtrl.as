package jack911.pp.core.scene
{
	import com.anstu.jsock.utils.Long;
	
	import jack911.pp.core.Base;
	import jack911.pp.core.Game;
	import jack911.pp.core.Layer;
	import jack911.pp.core.ape.Ape;
	import jack911.pp.core.ape.PlayerApe;
	import jack911.pp.net.dp.game.GameResultDp;
	import jack911.pp.net.dp.scene.SceneInitDp;
	import jack911.pp.net.msg.LobbyMsg;
	import jack911.pp.ui.View;

	public class SceneCtrl
	{
		private var apes:Vector.<Ape> = new Vector.<Ape>();
		private var players:Vector.<PlayerApe> = new Vector.<PlayerApe>();
		
		public var hero:PlayerApe;
		
		private var _canMove:Boolean = true;
		public function set canMove(value:Boolean):void
		{
			this._canMove = value;
			if(value==true)
			{
				Game.alert.show("移动完成，可以再次发起移动命令");
			}
		}
		public function get canMove():Boolean { return this._canMove; }
		
		public function SceneCtrl()
		{
		}
		
		public function initScene(dp:SceneInitDp):void
		{
			Game.alert.show("场景初始化数据de命令Game->Client:" + dp);
			Layer.setMode(Layer.MODE_GAME);
			Layer.scene.setEnabled(true);
			Layer.scene.init();
			View.status.show();
			View.oper.show();
		}
		
		public function exitScene():void
		{
			apes.length = 0;
			players.length = 0;
			View.over.hide();
			View.status.hide();
			View.oper.hide();
			View.rooms.show();
			Layer.scene.clear();
			Layer.setMode(Layer.MODE_LOBBY);
			LobbyMsg.sendLobbyInitDataReq();
		}
		
		public function handleGameResult(result:GameResultDp):void
		{
			Game.alert.show("游戏结束" + result);
			Layer.scene.setEnabled(false);
			View.over.setRusult(result);
			View.over.show();
		}
		
		public function add(a:Ape):void
		{
			if(a is PlayerApe)
			{
				var player:PlayerApe = a as PlayerApe;
				if(player.isHero())
				{
					hero = player;
					Layer.scene.focusTarget(hero);
					Layer.scene.background.setTarget(hero.x, hero.y);
				}
				players.push(player);
			}
			apes.push(a);
			Layer.scene.addApe(a);
		}
		
		public function remove(id:int):void
		{
			var a:Ape = null;
			var i:int = 0;
			for(i = 0; i < apes.length; i++)
			{
				var a2d:Ape = apes[i];
				if(id == a2d.getId())
				{
					apes.splice(i, 1);
					a = a2d;
					break;
				}
			}
			for(i = 0; i < players.length; i++)
			{
				var p:PlayerApe = players[i];
				if(id == p.getId())
				{
					players.splice(i, 1);
					break;
				}
			}
			if(a)
			{
				Layer.scene.removeApe(a);
			}
		}
		
		public function getPlayer(id:int):PlayerApe
		{
			for(var i:int = 0; i < players.length; i++)
			{
				var p:PlayerApe = players[i];
				if(id == p.getId())
				{
					return p;
				}
			}
			return null;
		}
		
		public function getApe(id:int):Ape
		{
			for(var i:int = 0; i < apes.length; i++)
			{
				var a:Ape = apes[i];
				if(id == a.getId())
				{
					return a;
				}
			}
			return null;
		}
		
	}
}