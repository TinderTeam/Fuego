/**   
* @Title: CheckPlanBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.check 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 上午03:24:05 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.ArrayList;
import java.util.List;


/** 
 * @ClassName: CheckPlanBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午03:24:05 
 *  
 */

public class CheckPlanBo
{
	private CheckTransBo transInfo = new CheckTransBo();
	private List<CheckPlanInfoBo> planInfo = new  ArrayList<CheckPlanInfoBo>();
	

	public CheckTransBo getTransInfo()
	{
		return transInfo;
	}

	public void setTransInfo(CheckTransBo transInfo)
	{
		this.transInfo = transInfo;
	}

	public List<CheckPlanInfoBo> getPlanInfo()
	{
		return planInfo;
	}

	public void setPlanInfo(List<CheckPlanInfoBo> planInfo)
	{
		this.planInfo = planInfo;
	}
	

}
