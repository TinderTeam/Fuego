package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	@Override
	public String toString()
	{
		String str = "[LoginForm: username=" + username + ";password=" + password + ";";
		return str;

	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

}
