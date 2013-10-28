/**   
* @Title: PurchaseTransBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.purchaseplan 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 上午01:59:46 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/** 
 * @ClassName: PurchaseTransBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午01:59:46 
 *  
 */

public class PurchaseTransBo
{
	private TransactionBaseInfoBo transInfo =new TransactionBaseInfoBo();

	public TransactionBaseInfoBo getTransInfo()
	{
		return transInfo;
	}

	public void setTransInfo(TransactionBaseInfoBo transInfo)
	{
		this.transInfo = transInfo;
	}
	
	

}
