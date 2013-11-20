/**   
 * @Title: AllocationAssertAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.allocation 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-24 下午11:53:07 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssignDeptForm;

/**
 * @ClassName: AllocationAssertAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 下午11:53:07
 * 
 */

public class AssignAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignAction.class);

	private TransPlanService planService = ServiceContext.getInstance().getAssignPlanService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String pageName = PageNameConst.ASSIGN_CREATE_INIT_ACTION;

		AssignDeptForm deptForm = (AssignDeptForm) form;
		// get department information list
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		if (null == user || null == deptForm)
		{
			log.error("the user is " + user);
			log.error("the dept form is " + deptForm);
			pageName = PageNameConst.LOGIN_PAGE;
		} else
		{
			AssignPlanBo planInfo = (AssignPlanBo) planService.createPlan(user.getUserID());

			planInfo.getTransInfo().setInDept(deptForm.getInDept());
			planInfo.getTransInfo().setOutDept(deptForm.getOutDept());

			request.getSession().setAttribute(RspBoNameConst.ASSIGN_PLAN_DATA, planInfo);
		}

		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}
}
