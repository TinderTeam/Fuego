package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

public class PasswordResetupForm extends ActionForm{

	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "PasswordResetupForm [userName=" + userName + "]";
	}



	
}
