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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.dom4jimpl.Dom4jConstant;
import cn.tinder.fuego.domain.po.AssetsQuota;

/**
 * TASK #18 提供资产类型与归口管理部门的对应关系查询模块
* @ClassName: HandelDepartmentAssetsStyleCache 
* @Description: TODO
* @author Nan Bowen
* @date 2013-12-26 上午12:00:02 
*
 */

public class HandelDepartmentAssetsStyleCache
{
	private static final Log log = LogFactory.getLog(HandelDepartmentAssetsStyleCache.class);

	private static HandelDepartmentAssetsStyleCache  instance = null;
	private Map<String,String> handelDepartmentAssetsStyleCacheMap = new HashMap<String,String>();
	private HandelDepartmentAssetsStyleCache()
	{
		load();
	}
	public synchronized static HandelDepartmentAssetsStyleCache getInstance()
	{
		if(null == instance)
		{
			instance = new HandelDepartmentAssetsStyleCache();
		}
		return instance;
	}
	public void load()
	{
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(Dom4jConstant.HANDEL_DEPT_TYPE_CONFIG);
			
			List list = document.selectNodes("//inf" );
			Iterator iter=list.iterator();
			iter=list.iterator();
			while(iter.hasNext()){
				Element element=(Element)iter.next();	
				handelDepartmentAssetsStyleCacheMap.put(element.attributeValue("type"),element.getText());
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

	public Map<String,String> getHandelDepartmentAssetsStyleCache()
	{
		return handelDepartmentAssetsStyleCacheMap;
	}
	public List<String> getTypeByDept(String dept)
	{
		List<String> list = new ArrayList<String>();
		Iterator it = handelDepartmentAssetsStyleCacheMap.entrySet().iterator();
		while(it.hasNext()){
			java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
			String value=(String)entry.getValue();
			if(value.equals(dept)){
				list.add((String)entry.getKey());
			}
		}
		
		return list;
	} 

	public String getDeptByType(String type)
	{
		return handelDepartmentAssetsStyleCacheMap.get(type);	
	}
}
