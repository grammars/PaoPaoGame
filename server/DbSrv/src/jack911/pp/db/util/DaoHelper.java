package jack911.pp.db.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

//【C】create 【U】update 【R】read(single) query(some) 【D】delete
public class DaoHelper
{
	private static DaoHelper instance;
	public static DaoHelper getInstance()
	{
		if(instance == null) { instance = new DaoHelper(); }
		return instance;
	}
	
	private Log log = LogFactory.getLog(DaoHelper.class);
	
    private NamedParameterJdbcTemplate _jt;
	protected NamedParameterJdbcTemplate jt()
	{
		
		if(_jt == null)
		{
			DataSource ds = C3P0Util.create();
			_jt = new NamedParameterJdbcTemplate(ds);
		}
		return _jt;
	}
	
	public DaoHelper()
	{
		//
	}
	
	/** 获取主键信息 */
	protected AttCol getPK(Class<?> clazz)
	{
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields)
		{
			Id idAnno = f.getAnnotation(Id.class);
			if(idAnno != null)
			{
				Column colAnno = f.getAnnotation(Column.class);
				if(colAnno == null)
				{
					log.warn("" + clazz.getName() + "具有Id注释却没有Column注释，这不应该！");
					continue;
				}
				AttCol ac = new AttCol(f.getName(), colAnno.name());
				return ac;
			}
		}
		return null;
	}
	
	/** 获取表名 */
	protected String getTableName(Class<?> clazz)
	{
		Table table = clazz.getAnnotation(Table.class);
		if(table == null) 
		{
			log.error("请在" + clazz.getName() + "上加上Table注解");
			return null;
		}
		return table.name();
	}
	
	/** 是否是自动设置，不需要手动干预 */
	private boolean isAutoSet(Field f)
	{
		GeneratedValue gv = f.getAnnotation(GeneratedValue.class);
		if(gv != null)
		{
			if(gv.strategy() != GenerationType.AUTO) { return true; }
		}
		return false;
	}
	
	/** 创建一个新的entity */
	public <T> Number create(T entity)
	{
		String tableName = getTableName(entity.getClass());
		if(tableName == null) 
		{
			log.error("请在" + entity.getClass().getName() + "上加上Table注解");
			return null;
		}
		
		boolean ok = false;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		String cols = "";
		String args = "";
		for(Field f : fields)
		{
			Column column = f.getAnnotation(Column.class);
			if(column != null)
			{
				if( !isAutoSet(f) )
				{
					try
					{
						Object value = PropertyUtils.getProperty(entity, f.getName());
						paramMap.put(column.name(), value);
						cols += column.name() + ",";
						args += ":" + column.name() + ",";
						
						ok = true;
					} 
					catch (IllegalAccessException e) { e.printStackTrace(); } 
					catch (InvocationTargetException e) { e.printStackTrace(); } 
					catch (NoSuchMethodException e) { e.printStackTrace(); }
				}
			}
		}
		if(!ok)
		{
			log.error("至少需要一项数据库字段");
			return null;
		}
		cols = cols.substring(0, cols.length()-1);
		args = args.substring(0, args.length()-1);
		String springSql = "insert into "+ tableName +"("+cols+") values("+args+")";
		
//		jt().execute(springSql, paramMap, new PreparedStatementCallback<Boolean>() {
//			@Override
//			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//				return ps.execute();
//			}
//		});
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		SqlParameterSource  paramSource = new MapSqlParameterSource(paramMap);
		jt().update(springSql, paramSource, generatedKeyHolder);
		
		return generatedKeyHolder.getKey();
	}
	
	/** 更新一个已存在的entity
	 * @return true:更新成功 / false:更新失败 */
	public <T> boolean update(T entity)
	{
		String tableName = getTableName(entity.getClass());
		if(tableName == null) 
		{
			log.error("请在" + entity.getClass().getName() + "上加上Table注解");
			return false;
		}
		String springSql = "UPDATE "+tableName+" SET ";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		String setContent = "";
		boolean ok = false;
		for(Field f : fields)
		{
			Column column = f.getAnnotation(Column.class);
			if(column != null)
			{
				if( !isAutoSet(f) )
				{
					try
					{
						Object value = PropertyUtils.getProperty(entity, f.getName());
						paramMap.put(column.name(), value);
						setContent += column.name() + "=:" + column.name() + ",";
						ok = true;
					} 
					catch (IllegalAccessException e) { e.printStackTrace(); } 
					catch (InvocationTargetException e) { e.printStackTrace(); } 
					catch (NoSuchMethodException e) { e.printStackTrace(); }
				}
			}
		}
		if(!ok)
		{
			log.error("至少需要一项数据库字段");
			return false;
		}
		setContent = setContent.substring(0, setContent.length()-1);
		
		AttCol ac = getPK(entity.getClass());
		if(ac == null)
		{
			log.error("必须有主键且用@ID注释");
			return false;
		}
		springSql += setContent + " WHERE " + ac.colname + "=:" + ac.colname;
		Object pkVal = null;
		try
		{
			pkVal = PropertyUtils.getProperty(entity, ac.attname);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		} 
		paramMap.put(ac.colname, pkVal);
		
		int r = jt().update(springSql, paramMap);
		return r > 0;
	}
	
	
	/** 根据主键id读取entity */
	public <T> T read(Class<T> clazz, Object id)
	{
		AttCol ac = getPK(clazz);
		if(ac == null) 
		{
			log.error("" + clazz.getName() + "找不到主键");
			return null;
		}
		return read(clazz, ac.colname, id);
	}
	
	/** 读取一个符合某列等于某值的entity */
	public <T> T read(Class<T> clazz, String colName, Object colVal)
	{
		String conditionSql = " " + colName + "=:" + colName + " ";
		List<T> list = querySome(clazz, conditionSql, new String[]{colName}, new Object[]{colVal});
		if(list.size() > 0) { return list.get(0); }
		return null;
	}
	
	/** 读取一个符合[自定义条件]的entity */
	public <T> T read(Class<T> clazz, String conditionSql, String[] paramCols, Object[] paramVals)
	{
		List<T> list = querySome(clazz, conditionSql, paramCols, paramVals);
		if(list.size() > 0) { return list.get(0); }
		return null;
	}
	
	
	/** 根据主键id删除entity */
	public boolean delete(Class<?> clazz, Object id)
	{
		String tableName = getTableName(clazz);
		if(tableName == null) 
		{
			log.error("请在" + clazz.getName() + "上加上Table注解");
			return false;
		}
		AttCol ac = getPK(clazz);
		if(ac == null) 
		{
			log.error("" + clazz.getName() + "找不到主键,请用@Id注解对应主键");
			return false;
		}
		String springSql = "DELETE FROM "+tableName+" WHERE "+ac.colname+"=:"+ac.colname;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(ac.colname, id);
		int r = jt().update(springSql, paramMap);
		return r > 0;
	}
	
	/** 查询[所有] */
	public <T> List<T> queryAll(Class<T> clazz)
	{
		return querySome(clazz, null, null);
	}
	
	/** 查询[自定义条件] */
	public <T> List<T> querySome(Class<T> clazz, String conditionSql, String[] paramCols, Object[] paramVals)
	{
		String tableName = getTableName(clazz);
		if(tableName == null) 
		{
			log.error("请在" + clazz.getName() + "上加上Table注解");
			return null;
		}
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		for(int i = 0; i < paramCols.length; i++)
		{
			paramMap.put(paramCols[i], paramVals[i]);
		}
		String springSql = "SELECT * FROM " + tableName + " WHERE " + conditionSql;
		return querySome(clazz, springSql, paramMap);
	}
	
	/** 查询[自定义条件] */
	public <T> List<T> querySome(Class<T> clazz, String springSql, Map<String, ?> paramMap)
	{
		if( springSql == null )
		{
			String tableName = getTableName(clazz);
			if(tableName == null) 
			{
				log.error("请在" + clazz.getName() + "上加上Table注解");
				return null;
			}
			springSql = "SELECT * FROM " + tableName;
		}
		if( paramMap == null )
		{
			paramMap = new HashMap<String, Object>();
		}
		
		final Map<String, String> att2col = new LinkedHashMap<String, String>();
		final Map<String, String> col2att = new LinkedHashMap<String, String>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields)
		{
			Column column = f.getAnnotation(Column.class);
			if(column != null)
			{
				att2col.put(f.getName(), column.name());
				col2att.put(column.name(), f.getName());
			}
		}
		ResultSetExtractor<List<T>> rse = new RowMapperResultSetExtractor<T>(new RowMapper<T>() {
			public T mapRow(ResultSet rs, int index) throws SQLException {
				T bean = BeanUtils.instantiate(clazz);
				Iterator<Entry<String, String>> iter = att2col.entrySet().iterator();
				while(iter.hasNext()){
					Entry<String, String> e = iter.next();
					String attName = e.getKey();
					String colName = e.getValue();
					fillBeanWithResult(bean, rs, attName, colName);
				}
				return bean;
			}
		});
		List<T> list = jt().query(springSql, paramMap, rse);
		return list;
	}
	
	private void fillBeanWithResult(Object bean, ResultSet rs, String attName, String colname)
	{
		try 
		{
			Object value = rs.getObject(colname);
			try 
			{
				PropertyUtils.setSimpleProperty(bean, attName, value);
			} 
			catch (IllegalAccessException e) { e.printStackTrace(); } 
			catch (InvocationTargetException e) { e.printStackTrace(); }
			catch (NoSuchMethodException e) { e.printStackTrace(); }
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public int count(Class<?> clazz)
	{
		return count(clazz, null, null, null);
	}
	
	public int count(Class<?> clazz, String conditionSql, String[] paramCols, Object[] paramVals)
	{
		String tableName = getTableName(clazz);
		if(tableName == null) 
		{
			log.error("请在" + clazz.getName() + "上加上Table注解");
			return -1;
		}
		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		String springSql = "SELECT * FROM " + tableName + " ";
		if(conditionSql != null)
		{
			for(int i = 0; i < paramCols.length; i++)
			{
				paramMap.put(paramCols[i], paramVals[i]);
			}
			springSql += " WHERE " + conditionSql;
		}
		return count(springSql, paramMap);
	}
	
	public int count(String springSql, Map<String, ?> paramMap)
	{
		String sql = "SELECT COUNT(0) FROM (" + springSql + ") A";
		return jt().queryForObject(sql, paramMap, Integer.class);
	}
	
}
