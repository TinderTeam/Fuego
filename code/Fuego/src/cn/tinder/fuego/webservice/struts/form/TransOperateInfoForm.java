/**   
* @Title: TranSubmitInfoForm.java 
* @Package cn.tinder.fuego.webservice.struts.form 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-29 下午11:31:34 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;


/** 
 * @ClassName: TranSubmitInfoForm 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-29 下午11:31:34 
 *  
 */

public class TransOperateInfoForm extends ActionForm
{
	private String operateInfo;
	private String handleUser;
	private String executeName;

	public String getOperateInfo()
	{
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo)
	{
		this.operateInfo = operateInfo;
	}

	public String getHandleUser()
	{
		return handleUser;
	}

	public void setHandleUser(String handleUser)
	{
		this.handleUser = handleUser;
	}

	public String getExecuteName()
	{
		return executeName;
	}

	public void setExecuteName(String executeName)
	{
		this.executeName = executeName;
	}
	

}
