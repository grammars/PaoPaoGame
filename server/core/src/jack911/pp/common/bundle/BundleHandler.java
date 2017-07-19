package jack911.pp.common.bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BundleHandler<T extends ClientBundle>
{
	private Map<Long, T> bundlesMap = new HashMap<>();
	private List<T> bundlesList = new LinkedList<>();
	
	/** 置入一个bundle，会去重 */
	public void putBundle(T bundle)
	{
		T old = bundlesMap.get(bundle.cccid);
		if(old != null)
		{
			Iterator<T> iter = bundlesList.iterator();
			while(iter.hasNext())
			{
				T e = iter.next();
				if(e.cccid == old.cccid)
				{
					iter.remove();
					break;
				}
			}
		}
		bundlesMap.put(bundle.cccid, bundle);
		bundlesList.add(bundle);
	}
	
	/** 根据cccid获取一个bundle */
	public T getBundle(Long cccid)
	{
		return bundlesMap.get(cccid);
	}
	
	/** 根据cccid移除一个bundle */
	public void removeBundle(Long cccid)
	{
		bundlesMap.remove(cccid);
	}
	
}
