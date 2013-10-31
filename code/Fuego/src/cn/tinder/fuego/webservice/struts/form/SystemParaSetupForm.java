/**   
* @Title: SystemParaSetupForm.java 
* @Package cn.tinder.fuego.webservice.struts.form 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-5 上午12:28:00 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

/** 
 * @ClassName: SystemParaSetupForm 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-5 上午12:28:00 
 *  
 */
public class SystemParaSetupForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gasname;
	private String username;
	private String dept1;
	private String gasaccount;
	private String dept2;
	/**
	 * @return the gasname
	 */
	public String getGasname()
	{
		return gasname;
	}
	/**
	 * @param gasname the gasname to set
	 */
	public void setGasname(String gasname)
	{
		this.gasname = gasname;
	}
	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	/**
	 * @return the dept1
	 */
	public String getDept1()
	{
		return dept1;
	}
	/**
	 * @param dept1 the dept1 to set
	 */
	public void setDept1(String dept1)
	{
		this.dept1 = dept1;
	}
	/**
	 * @return the gasaccount
	 */
	public String getGasaccount()
	{
		return gasaccount;
	}
	/**
	 * @param gasaccount the gasaccount to set
	 */
	public void setGasaccount(String gasaccount)
	{
		this.gasaccount = gasaccount;
	}
	/**
	 * @return the dept2
	 */
	public String getDept2()
	{
		return dept2;
	}
	/**
	 * @param dept2 the dept2 to set
	 */
	public void setDept2(String dept2)
	{
		this.dept2 = dept2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemParaSetupForm [dept1=" + dept1 + ", dept2=" + dept2
				+ ", gasaccount=" + gasaccount + ", gasname=" + gasname
				+ ", username=" + username + "]";
	}
	


}
