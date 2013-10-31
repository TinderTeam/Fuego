/**   
* @Title: SystemMaintanceServiceImpl.java 
* @Package cn.tinder.fuego.service.impl 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-5 下午11:02:02 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.SystemMaintanceService;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;

/** 
 * @ClassName: SystemMaintanceServiceImpl 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-5 下午11:02:02 
 *  
 */
public class SystemMaintanceServiceImpl implements SystemMaintanceService
{
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.SystemMaintanceService#addGasStation(java.lang.String, java.lang.String)
	 */
	@Override
	public void addGasStation(String userName, String userID,String manageName)
	{
		SystemUser user = new SystemUser();
		if(null != systemUserDao.find(userName))
		{
			throw new ServiceException(ExceptionMsg.USER_ALREADY_EXISTED);
		}
		
		if(null == userName || userName.trim().isEmpty()||null==userID||userID.trim().isEmpty()||null == manageName||manageName.trim().isEmpty())
		{
			throw new ServiceException(ExceptionMsg.INPUT_EMPUTY);

		}
		 
		user.setUserName(userName);  //加油站名
		user.setDepartment(userID);  //用户名
		user.setManageName(manageName); //经管部
		user.setDepartmentID("new");
		user.setPassword(UserRoleConst.DEFUALT_PASSWORD); 
		user.setRole(UserRoleConst.GASSTATION);
		
		systemUserDao.create(user);

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.SystemMaintanceService#modifyUserInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public  String searchUserInfo(String userName)
	{  
		String managename=null;
		
		managename=systemUserDao.find(userName).getManageName();
		 
          return managename;
	}
    public SystemUser deleteUserInfo(String userName)
    {
    	SystemUser gas=new SystemUser();
    	gas=systemUserDao.find(userName);
    	systemUserDao.delete(gas);
    	
    	return gas;
    	
    }
    public SystemUser  saveUserInfo(String userName,String deptName)
    {
    	SystemUser gas=new SystemUser();
    	gas.setDepartment(userName);
    	gas.setDepartmentID("New");
    	gas.setManageName(deptName);
    	gas.setPassword(UserRoleConst.DEFUALT_PASSWORD);
    	gas.setRole(UserRoleConst.GASSTATION);
    	gas.setUserName(userName);
    	
    	systemUserDao.saveOrUpdate(gas);
    	return gas;
    }
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.SystemMaintanceService#modifyUserInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyUserInfo(String userName, String mannageName)
	{
		// TODO Auto-generated method stub
		
	}

}
