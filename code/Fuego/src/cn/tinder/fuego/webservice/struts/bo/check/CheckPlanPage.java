/**   
* @Title: CheckPlanPage.java 
* @Package cn.tinder.fuego.webservice.struts.bo.check 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 下午06:51:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: CheckPlanPage 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 下午06:51:04 
 *  
 */

public class CheckPlanPage
{
	List<CheckPlanInfoBo> planList = new ArrayList<CheckPlanInfoBo>();

	public List<CheckPlanInfoBo> getPlanList()
	{
		return planList;
	}

	public void setPlanList(List<CheckPlanInfoBo> planList)
	{
		this.planList = planList;
	}
	public CheckPlanInfoBo find(CheckPlanInfoBo plan)
	{
		for(CheckPlanInfoBo e : planList)
		{
			if(e.equals(plan))
			{
				return e;
			}
		}
		return null;
	}
	
}
