package jack911.pp.db.login;

import org.apache.log4j.Logger;

import jack911.pp.db.Db;
import jack911.pp.db.entity.PlayerEntity;
import jack911.pp.db.entity.UserAccountEntity;
import jack911.pp.db.util.DaoHelper;
import jack911.pp.message.dp.login.LoginResultDP;
import jack911.util.MyUtil;

public class DbLoginHandler
{
	private static Logger logger = Logger.getLogger(DbLoginHandler.class);
	
	public void handleDbVerifyLoginReq(String username, String password, Long cccid)
	{
		logger.debug("进行数据库验证:" + username + " / " + password);
		
		UserAccountEntity entity = DaoHelper.getInstance().read(UserAccountEntity.class, "username=:username AND password=:password", new String[]{"username","password"}, new Object[]{username, password});
		LoginResultDP dp = new LoginResultDP();
		if(entity != null)
		{
			dp.result = LoginResultDP.SUCCESS;
			dp.token = "TEST-Token@911";
			dp.account = entity.createVO();
		}
		else
		{
			//临时方便测试：如果没有注册，就注册一个 密码同用户名======beg=====
			entity = DaoHelper.getInstance().read(UserAccountEntity.class, "username=:username", new String[]{"username"}, new Object[]{username});
			if(entity == null)
			{
				entity = new UserAccountEntity();
				entity.setUid(MyUtil.createUidLong());
				entity.setDebug("auto-create");
				entity.setUsername(username);
				entity.setPassword(username);
				DaoHelper.getInstance().create(entity);
			}
			//==================================================end=====
			dp.result = LoginResultDP.USER_PWD_ERR;
		}
		
		Db.msg.login.sendDbVerifyLoginResp(dp, cccid);
	}
	
	public void handleDbCreatePlayerReq(Long accUid, String name, Long cccid)
	{
		UserAccountEntity accEntity = DaoHelper.getInstance().read(UserAccountEntity.class, "uid", accUid);
		if(accEntity == null)
		{
			logger.debug("账户不存在");
			return;
		}
		
		//因为经过CentreSrv的逻辑判断，因此视为请求始终是合理合法的。
		PlayerEntity entity = new PlayerEntity();
		entity.init(name, MyUtil.createUidLong());
		
		Number pkv = DaoHelper.getInstance().create(entity);
		System.out.println("生成主键的值是===>" + pkv.intValue());
		//entity.setId(pkv.intValue());
		
		accEntity.setPlayerUid(entity.getUid());
		DaoHelper.getInstance().update(accEntity);
		
		Db.msg.login.sendDbCreatePlayerResp(entity.createPlayerData(), cccid);
	}
}
