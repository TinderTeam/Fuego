/**   
 * @Title: AllocationAssertAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.allocation 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-24 下午11:53:07 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assign;

import java.io.File;

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
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * @ClassName: AllocationAssertAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 下午11:53:07
 * 
 */

public class AssignSubmitAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignSubmitAction.class);
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
		String nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
		AssignPlanBo plan = null;
		plan = (AssignPlanBo) request.getSession().getAttribute(RspBoNameConst.ASSIGN_PLAN_DATA);

		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		if(ParameterConst.SUBMIT_PARA_NAME.equals(submitPara))
		{
			planService.updatePlan(plan);
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());
			request.getSession().setAttribute(RspBoNameConst.ASSIGN_PLAN_DATA, null);
		}
		else if(ParameterConst.AGREE_PARA_NAME.equals(submitPara))
		{
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());
		}
		else if(ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
		{
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());
		}
		else if(ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara))
		{
			File exportFile = planService.getExportFile(plan);
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, exportFile.getAbsolutePath());
			nextPage = PageNameConst.DOWNLOAD_ACTION;
		}
		else if(ParameterConst.CANCEL_PARA_NAME.equals(submitPara))
		{
			planService.deletePlan(plan.getTransInfo().getTransInfo().getTransID());
			nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
		else if(ParameterConst.VIEW_PARA_NAME.equals(submitPara))
		{
		 	nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
		else if(ParameterConst.REFUSE_PARA_NAME.equals(submitPara))
		{
			planService.backward(plan.getTransInfo().getTransInfo().getTransID());
		}
 
		return nextPage;
	}
}
