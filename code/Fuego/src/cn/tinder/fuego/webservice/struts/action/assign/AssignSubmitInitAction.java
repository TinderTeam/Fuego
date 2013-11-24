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
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
 * @ClassName: AssignSubmitInitAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午12:42:06
 * 
 */
public class AssignSubmitInitAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignSubmitInitAction.class);

	private TransPlanService planService = ServiceContext.getInstance().getAssignPlanService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
        log.info(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = null;
    	try
    	{
    		nextPage = handle(form,request);
		} 
    	catch(ServiceException e)
    	{
    		log.warn("opration failed",e);
			request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
			nextPage = PageNameConst.ERROR_PAGE; 
    	}
    	catch (Exception e)
		{
			log.error("system error",e);
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE; 
		}
     
 
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

	
	}
	private String handle(ActionForm form,HttpServletRequest request)
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String nextPage = PageNameConst.ASSIGN_SUBMIT_PAGE;
 
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
		AssignPlanBo plan  = (AssignPlanBo) planService.getPlanByTransID(transID);
		if (null == plan)
		{
			plan = (AssignPlanBo) request.getSession().getAttribute(RspBoNameConst.ASSIGN_PLAN_DATA);
			log.info("can not get the plan by transaction id" + transID);
		} 
		request.getSession().setAttribute(RspBoNameConst.ASSIGN_PLAN_DATA, plan);
		
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

    	   
		nextPage = controlPageBtnDis(plan.getTransInfo().getTransInfo().canOperate(user),nextPage,request);


		return nextPage;

	}
	private String controlPageBtnDis(boolean canOperate,String nextPage,HttpServletRequest request)
	{
		//control page button display by the step
		String pageCtr = RspBoNameConst.PAGE_CREATE;
		String step = request.getParameter(ParameterConst.PLAN_STEP);
		if(!canOperate)
		{
			pageCtr = RspBoNameConst.PAGE_VIEW;
		}
		else
		{
			if(null == step)
			{
				pageCtr = RspBoNameConst.PAGE_CREATE;
			}
			else if(TransactionConst.ASSIGN_MAX_STEP.equals(step))
			{
				nextPage = PageNameConst.ASSIGN_CREATE_INIT_ACTION;
			}
			else if(TransactionConst.ASSIGN_APPROVAL_STEP.equals(step))
			{
				pageCtr = RspBoNameConst.PAGE_APPROVAL;
			}
			else if(TransactionConst.TRANS_FINISH_STEP.equals(step))
			{
				pageCtr = RspBoNameConst.PAGE_FINISH;
			}
			else
			{
				pageCtr = RspBoNameConst.PAGE_CONFIRM;
	 		}
		}

		request.setAttribute(RspBoNameConst.PAGE_DIS_CTL, pageCtr);
		return nextPage;
	}
	
}
