/**   
 * @Title: AssignDeptForm.java 
 * @Package cn.tinder.fuego.webservice.struts.form 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-25 上午12:15:49 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * @ClassName: AssignDeptForm
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-25 上午12:15:49
 * 
 */

public class AssignDeptForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String inDept;
	private String outDept;

	public String getInDept()
	{
		return inDept;
	}

	public void setInDept(String inDept)
	{
		this.inDept = inDept;
	}

	public String getOutDept()
	{
		return outDept;
	}

	public void setOutDept(String outDept)
	{
		this.outDept = outDept;
	}

	@Override
	public String toString()
	{
		return "AssignDeptForm [inDept=" + inDept + ", outDept=" + outDept + "]";
	}

}
