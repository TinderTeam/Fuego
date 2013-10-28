/**   
* @Title: CheckTransBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.check 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 上午03:25:47 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/** 
 * @ClassName: CheckTransBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午03:25:47 
 *  
 */

public class CheckTransBo
{
	private TransactionBaseInfoBo transInfo;
	private List<CheckTransBo> childTransList;

	public TransactionBaseInfoBo getTransInfo()
	{
		return transInfo;
	}

	public void setTransInfo(TransactionBaseInfoBo transInfo)
	{
		this.transInfo = transInfo;
	}

	public List<CheckTransBo> getChildTransList()
	{
		return childTransList;
	}

	public void setChildTransList(List<CheckTransBo> childTransList)
	{
		this.childTransList = childTransList;
	}

 
	
}
