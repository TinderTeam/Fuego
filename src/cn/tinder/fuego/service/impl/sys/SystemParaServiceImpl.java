/**   
* @Title: SystemParaServiceImpl.java 
* @Package cn.tinder.fuego.service.impl 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-6 ÏÂÎç06:28:01 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl.sys;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.form.PasswordResetupForm;
import cn.tinder.fuego.webservice.struts.form.UserPasswordSetupForm;

/** 
 * @ClassName: SystemParaServiceImpl 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-6 ÏÂÎç06:28:01 
 *  
 */
public class SystemParaServiceImpl implements SystemParaService
{
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.sys.SystemParaService#resetPassword(java.lang.String)
	 */

	public void resetPassword(PasswordResetupForm pdForm ,SystemUserBo orignUser)
	{
		SystemUser currentUser=new SystemUser();
		if(pdForm.getUserName()==null||pdForm.getUserName().trim().isEmpty())
		{
			throw new ServiceException(ExceptionMsg.INPUT_EMPUTY);
		}
		else
		{
			currentUser.setPassword(UserRoleConst.DEFUALT_PASSWORD);
		    currentUser.setManageName(orignUser.getManageName());
		      
		    currentUser.setDepartmentID(orignUser.getDeptID());
		    currentUser.setDepartment(orignUser.getDeptName());
		    currentUser.setRole(orignUser.getRole());
		    currentUser.setUserName(orignUser.getUserID());
		    
		    systemUserDao.saveOrUpdate(currentUser);
		}


	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.sys.SystemParaService#setPassword(cn.tinder.fuego.webservice.struts.form.UserPasswordSetupForm)
	 */
	
	public void setPassword(UserPasswordSetupForm pdForm ,SystemUserBo orignUser)
	{       
		  SystemUser currentUser=new SystemUser();
		  String orignPassword=orignUser.getPassword();
		  String oldpd=pdForm.getOldpassword();
		  String newpd1=pdForm.getNewpassword1();
		  String newpd2=pdForm.getNewpassword2();
			
		  if(oldpd==null||oldpd.trim().isEmpty()||newpd1==null||newpd1.trim().isEmpty()||newpd2==null||newpd2.trim().isEmpty())
		  {
			  throw new ServiceException(ExceptionMsg.INPUT_EMPUTY);
		  }
			  
		  else if(!oldpd.equals(orignPassword))
		          {
			            throw new ServiceException(ExceptionMsg.OLD_PASSWORD_ERROR);
		          }
		        else if(!newpd1.equals(newpd2))
			     {
				    throw new ServiceException(ExceptionMsg.NEW_PASSWORD_DIFFER);
			     }
			
		            else 
		              {
		        	      currentUser.setPassword(newpd2);
		        	      currentUser.setManageName(orignUser.getManageName());
		        	      
		        	      currentUser.setDepartmentID(orignUser.getDeptID());
		        	      currentUser.setDepartment(orignUser.getDeptName());
		        	      currentUser.setRole(orignUser.getRole());
		        	      currentUser.setUserName(orignUser.getUserID());
		        	      
		        	      systemUserDao.saveOrUpdate(currentUser);
		            	
		              }
 
		  
	}



}
