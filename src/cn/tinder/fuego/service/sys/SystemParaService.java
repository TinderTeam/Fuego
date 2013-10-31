package cn.tinder.fuego.service.sys;


import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.form.PasswordResetupForm;
import cn.tinder.fuego.webservice.struts.form.UserPasswordSetupForm;

public interface SystemParaService {
	
/**
 * Setup userPassword!
 * @param userPasswordSetupForm
 * @throws ServiceException 
 * @return
 */
	public void setPassword(UserPasswordSetupForm pdForm,SystemUserBo orignUser);

	/**
	 * ReSetup the Password from UserName
	 * 
	 * @param passwordResetupForm
	 */

	public void resetPassword(PasswordResetupForm pdForm,SystemUserBo orignUser);
	
}
