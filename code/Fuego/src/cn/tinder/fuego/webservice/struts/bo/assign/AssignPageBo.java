/**   
 * @Title: AssignBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-25 下午10:12:27 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assign;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;

/**
 * @ClassName: AssignBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-25 下午10:12:27
 * 
 */

public class AssignPageBo
{
	List<DeptInfoBo> deptInfoList;
	AssignPlanBo assignPlan = new AssignPlanBo();

	public List<DeptInfoBo> getDeptInfoList()
	{
		return deptInfoList;
	}

	public void setDeptInfoList(List<DeptInfoBo> deptInfoList)
	{
		this.deptInfoList = deptInfoList;
	}

	public AssignPlanBo getAssignPlan()
	{
		return assignPlan;
	}

	public void setAssignPlan(AssignPlanBo assignPlan)
	{
		this.assignPlan = assignPlan;
	}

}
