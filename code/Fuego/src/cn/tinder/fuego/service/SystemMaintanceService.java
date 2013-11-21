/**   
* @Title: SystemMaintanceService.java 
* @Package cn.tinder.fuego.service 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-5 下午10:56:02 
* @version V1.0   
*/ 
package cn.tinder.fuego.service;

import java.io.File;

import cn.tinder.fuego.domain.po.SystemUser;

/** 
 * @ClassName: SystemMaintanceService 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-5 下午10:56:02 
 *  
 */
public interface SystemMaintanceService
{
	public void addGasStation(String userName,String userID,String mannageName);
	
	public void modifyUserInfo(String userName,String mannageName );

	public String searchUserInfo(String userName);
	
    public SystemUser deleteUserInfo(String userName);
    
    public SystemUser saveUserInfo(String userName,String deptName);

	public void importPriceAssest(File uploadFile);

	public File getPriceFile();

}
