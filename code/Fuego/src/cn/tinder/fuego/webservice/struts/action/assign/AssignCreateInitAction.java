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
import cn.tinder.fuego.stub.strust.bo.assign.AssignBoStub;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignTransBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
 * @ClassName: AssignCreateAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午12:27:22
 * 
 */
public class AssignCreateInitAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignCreateInitAction.class);

	private TransPlanService planService = ServiceContext.getInstance().getAssignPlanService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String nextPage = PageNameConst.ASSIGN_CREATE_PAGE;

		AssignPlanBo plan = (AssignPlanBo) request.getSession().getAttribute(RspBoNameConst.ASSIGN_PLAN_DATA);
		if (null == plan)
		{
			log.error("the transaction information is null");
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE;
		} else
		{
 			AssignPageBo assignPage = new AssignPageBo();
			assignPage.setAssignPlan(plan);

			request.setAttribute(RspBoNameConst.ASSIGN_PLAN_DATA, plan);
		}

		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

	}

}
