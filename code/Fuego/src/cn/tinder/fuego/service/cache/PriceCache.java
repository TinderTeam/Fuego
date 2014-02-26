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
import cn.tinder.fuego.domain.po.AssetsPrice;
import cn.tinder.fuego.domain.po.AssetsQuota;

/** 
 * @ClassName: QuotaCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-11-28 上午12:36:46 
 *  
 */

public class PriceCache
{
	private static final Log log = LogFactory.getLog(PriceCache.class);

	private static PriceCache  instance = null;
	private List<AssetsPrice> assetPriceList = null;
	private PriceCache()
	{
		load();
	}
	public synchronized static PriceCache getInstance()
	{
		if(null == instance)
		{
			instance = new PriceCache();
		}
		return instance;
	}
	public void load()
	{
		assetPriceList = DaoContext.getInstance().getAssetsPriceDao().getAll();
		log.info(assetPriceList);
	}
	public List<AssetsPrice> getAllQuota()
	{
		return assetPriceList;
	}
	public AssetsPrice getPriceByModel(AssetsPrice model)
	{

		
		for(AssetsPrice ap : this.assetPriceList)
		{
			if(ap.equals(model))
			{	
				return ap;
			}
		}
		return null;
	}
	
}
