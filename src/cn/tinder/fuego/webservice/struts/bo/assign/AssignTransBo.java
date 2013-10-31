/**   
 * @Title: AssignTransBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-1 下午08:45:26 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assign;

import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/**
 * @ClassName: AssignTransBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-1 下午08:45:26
 * 
 */

public class AssignTransBo
{
	private TransactionBaseInfoBo transInfo;
	private String outDept;
	private String inDept;
	private String creatorStatus;
	private String outDeptStatus;
	private String inDeptStatus;

	public TransactionBaseInfoBo getTransInfo()
	{
		return transInfo;
	}

	public void setTransInfo(TransactionBaseInfoBo transInfo)
	{
		this.transInfo = transInfo;
	}

	public String getOutDept()
	{
		return outDept;
	}

	public void setOutDept(String outDept)
	{
		this.outDept = outDept;
	}

	public String getInDept()
	{
		return inDept;
	}

	public void setInDept(String inDept)
	{
		this.inDept = inDept;
	}

	public String getCreatorStatus()
	{
		return creatorStatus;
	}

	public void setCreatorStatus(String creatorStatus)
	{
		this.creatorStatus = creatorStatus;
	}

	public String getOutDeptStatus()
	{
		return outDeptStatus;
	}

	public void setOutDeptStatus(String outDeptStatus)
	{
		this.outDeptStatus = outDeptStatus;
	}

	public String getInDeptStatus()
	{
		return inDeptStatus;
	}

	public void setInDeptStatus(String inDeptStatus)
	{
		this.inDeptStatus = inDeptStatus;
	}

}
