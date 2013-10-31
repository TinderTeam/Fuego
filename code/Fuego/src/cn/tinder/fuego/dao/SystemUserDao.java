package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.SystemUser;

/**
 * 
 * @version 0.0.1.130916_Base
 */
public interface SystemUserDao
{

	/**
	 * <h3>CopyRright (c) 2008-Tinder</h3> <h3>Interface Infornation</h3>
	 * <p>
	 * Project: Fuego <br>
	 * Module ID: <br>
	 * Comments: SystemUser Dao interface <br>
	 * JDK version used: JDK1.6 <br>
	 * Namespace: cn.tinder.feugo.dao; <br>
	 * Create Date: 2013-9-16
	 * 
	 * <h3>Modified Information</h3>
	 * <br>
	 * Modified By: Nan Bowen <br>
	 * Modified Date: 2013-9-16 <br>
	 * Why & What is modified:
	 * 
	 * 
	 * @author Nan Bowen
	 * @version 0.0.1.130916_Base
	 */
	/**
	 * Create SystemUser
	 * 
	 * @param SystemUser
	 */
	public void create(SystemUser user);

	/**
	 * saveOrUpdate SystemUser
	 * 
	 * @param SystemUser
	 */
	public void saveOrUpdate(SystemUser user);

	/**
	 * delete SystemUser
	 * 
	 * @param SystemUser
	 */
	public void delete(SystemUser user);

	/**
	 * update SystemUser
	 * 
	 * @param SystemUser
	 */

	public SystemUser find(String username);

	/**
	 * 
	 * @param field
	 * @param value
	 * @return List<SystemUser>
	 */
	public List<SystemUser> find(String field, Object value);
    public List<SystemUser> getUserByDept(String dept);
	public List<SystemUser> getAllSystemUser();
	public List<SystemUser> getUserByRole(String role);

}
