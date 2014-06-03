/**   
* @Title: QuotaCache.java 
* @Package cn.tinder.fuego.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2013-11-28 上午12:36:46 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.AssetsQuota;

/** 
 * @ClassName: QuotaCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-11-28 上午12:36:46 
 *  
 */

public class QuotaCache
{
	private static final Log log = LogFactory.getLog(QuotaCache.class);

	private static QuotaCache  instance = null;
	private List<AssetsQuota> assetsQuotaList = null;
	private QuotaCache()
	{
		load();
	}
	public synchronized static QuotaCache getInstance()
	{
		if(null == instance)
		{
			instance = new QuotaCache();
		}
		return instance;
	}
	public void load()
	{
		assetsQuotaList = DaoContext.getInstance().getAssetsQuotaDao().getAllAssetsQuota();
		log.info(assetsQuotaList);
	}
	public List<AssetsQuota> getAllQuota()
	{
		return assetsQuotaList;
	}
	public List<AssetsQuota> getQuataByDept(String dept)
	{
		List<AssetsQuota> quotaList = new ArrayList<AssetsQuota>();
		for(AssetsQuota quota : this.assetsQuotaList)
		{
			if(quota.getDuty().equals(dept))
			{	
				quotaList.add(quota);
			}
		}
		return quotaList;
	}
	public static void reCatch()
	{
		instance = new QuotaCache();
	}
	
}
