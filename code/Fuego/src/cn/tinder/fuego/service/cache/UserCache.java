/**   
* @Title: UserCache.java 
* @Package cn.tinder.fuego.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2013-11-24 下午02:21:25 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.cache;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.SystemUser;

/** 
 * @ClassName: UserCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-11-24 下午02:21:25 
 *  
 */

public class UserCache
{
	private static final Log log = LogFactory.getLog(UserCache.class);

	private static UserCache  instance = null;
	private List<SystemUser> userList = null;
	
	public static void reCatch(){
		instance = new UserCache();
	}
	
	private UserCache()
	{
		load();
	}
	public synchronized static UserCache getInstance()
	{
		if(null == instance)
		{
			instance = new UserCache();
		}
		return instance;
	}
	public List<SystemUser> getAllUser()
	{
		return this.userList;
		
	}
	public void load()
	{
		userList = DaoContext.getInstance().getSystemUserDao().getAllSystemUser();
		log.info(userList);
	}
	
	public String getManageByUser(String userName)
	{
		for(SystemUser user : userList)
		{
			if(user.getUserName().equals(userName))
			{
				return user.getManageName();
			}
		}
		
		return "";
	}
	public SystemUser getUserByName(String userName)
	{
		for(SystemUser user : userList)
		{
			if(user.getUserName().equals(userName))
			{
				return user;
			}
		}
		
		return null;
	}
	
	public List<SystemUser> getUserListByRole(String role)
	{
		List<SystemUser> resultList = new ArrayList<SystemUser>();
		for(SystemUser user : userList)
		{
			if(user.getRole().equals(role))
			{
				resultList.add(user);
			}
		}
		
		return resultList;
	}
}
