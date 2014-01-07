/**   
* @Title: ReceiveTransBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.receive 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-22 上午12:13:47 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.receive;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/** 
 * @ClassName: ReceiveTransBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-22 上午12:13:47 
 *  
 */

public class ReceiveTransBo
{
	private TransactionBaseInfoBo transInfo;
	private List<ReceiveTransBo> childTransList;
	public TransactionBaseInfoBo getTransInfo()
	{
		return transInfo;
	}
	public void setTransInfo(TransactionBaseInfoBo transInfo)
	{
		this.transInfo = transInfo;
	}
	public List<ReceiveTransBo> getChildTransList()
	{
		return childTransList;
	}
	public void setChildTransList(List<ReceiveTransBo> childTransList)
	{
		this.childTransList = childTransList;
	}
	
	
}
