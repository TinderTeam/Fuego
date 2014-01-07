/**   
* @Title: ReceivePlanBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.receive 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-22 上午12:14:01 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.receive;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/** 
 * @ClassName: ReceivePlanBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-22 上午12:14:01 
 *  
 */

public class ReceivePlanBo
{
	private ReceiveTransBo transInfo = new ReceiveTransBo();
 

	private ReceivePlanInfoBo planInfo = new  ReceivePlanInfoBo();
	public ReceiveTransBo getTransInfo()
	{
		return transInfo;
	}
	public void setTransInfo(ReceiveTransBo transInfo)
	{
		this.transInfo = transInfo;
	}
 
	
	public ReceivePlanInfoBo getPlanInfo()
	{
		return planInfo;
	}
	public void setPlanInfo(ReceivePlanInfoBo planInfo)
	{
		this.planInfo = planInfo;
	}
 

}
