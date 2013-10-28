package cn.tinder.fuego.service;

import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.form.LoginForm;

public interface LoginService
{
	public SystemUserBo login(LoginForm loginForm);

}
