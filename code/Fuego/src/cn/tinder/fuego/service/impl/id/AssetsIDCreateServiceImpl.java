/**   
* @Title: AssetsIDCreateServiceImpl.java 
* @Package cn.tinder.fuego.service.impl.id 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-12 上午12:09:46 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl.id;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.dao.AssetsTypeDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.AssetsType;
import cn.tinder.fuego.service.IDCreateService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;

/** 
 * @ClassName: AssetsIDCreateServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-12 上午12:09:46 
 *  
 */

public class AssetsIDCreateServiceImpl implements IDCreateService
{
	AssetsTypeDao assetsTypeDao = DaoContext.getInstance().getAssetsTypeDao();
	private int IDLength = 11;
 
 	private synchronized String getCurrentID(String type)
	{
		AssetsType assetsType = assetsTypeDao.getByTypeName(type);
		
		String id = "";
		try{
		id = BasicIDCreator.getPrefixID(assetsType.getPrefix(), IDLength, assetsType.getCurrentID());
		}catch(Exception ex){
			throw new ServiceException(ExceptionMsg.TYPE_NULL);
		}
		assetsType.setCurrentID(assetsType.getCurrentID()+1);
		assetsTypeDao.saveOrUpdate(assetsType);
		return id;
	}
	
	public synchronized List<String> createIDList(String type,int idCount)
	{
		AssetsType assetsType = assetsTypeDao.getByTypeName(type);

		int currentID = assetsType.getCurrentID();
		List<String> idList = new ArrayList<String>();
 		for(int i =0;i<idCount;i++)
		{
 		  String	id = BasicIDCreator.getPrefixID(assetsType.getPrefix(), IDLength, currentID+i);
 		 idList.add(id);
		}
 		assetsType.setCurrentID(currentID+idCount);
		assetsTypeDao.saveOrUpdate(assetsType);

 		return idList;
	}
	
	private String getPrefixByType(String type)
	{
		String prefix = "";
		
		return prefix; 
	}

}
