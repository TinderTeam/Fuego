package cn.tinder.fuego.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.LoginService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.model.convert.ConvertModel;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.form.LoginForm;

public class LoginServiceImpl implements LoginService
{
	Log log = LogFactory.getLog(LoginServiceImpl.class);
	SystemUserDao dao = DaoContext.getInstance().getSystemUserDao();

	@Override
	public SystemUserBo login(LoginForm loginForm)
	{

		// Beans
		SystemUser systemUser = null;

		systemUser = dao.find(loginForm.getUsername());

		if (systemUser == null)
		{
			// No SystemUser
			log.warn("[Service]userName not exist!! LoginUser:" + loginForm.getUsername());
			throw new ServiceException(ExceptionMsg.LOGIN_NO_USER);

		} else
		{
			// SystemUser Exist & Check Password
			if (systemUser.getPassword().equals(loginForm.getPassword()))
			{
				// Success
				log.info("[Service]Successful! LoginUser:" + systemUser.toString());
			} else
			{
				// Password Wrong
				log.warn("[Service]Password Wrong!! LoginUser:" + systemUser.toString());
				throw new ServiceException(ExceptionMsg.LOGIN_PASSWORD_WRONG);

			}

		}

		return ConvertModel.convertSystemUser(systemUser);

	}

}
