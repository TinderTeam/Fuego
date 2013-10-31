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
import cn.tinder.fuego.webservice.struts.bo.assign.AssignTransBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
 * @ClassName: AssignCreateAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午12:27:22
 * 
 */
public class AssignCreateAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignCreateAction.class);

	private TransPlanService planService = ServiceContext.getInstance().getAssignPlanService(TransactionConst.ASSIGN_PLAN_TYPE);

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
		String nextPage = PageNameConst.INDEX_INIT_ACTION;
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);

		AssignPlanBo plan = (AssignPlanBo) request.getSession().getAttribute(RspBoNameConst.ASSIGN_PLAN_DATA);
		if (null == plan)
		{
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE;
		} else
		{
			if (ParameterConst.ADD_PARA_NAME.equals(submitPara))
			{

				nextPage = PageNameConst.ASSIGN_SELECT_ASSETS_INIT_ACTION;
			} else if (ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
			{
				planService.validate(plan);
				nextPage = PageNameConst.ASSIGN_SUBMIT_INIT_ACTION;
			} else
			{
				log.warn("the submit buttion value is " + submitPara);
				planService.deletePlan(plan.getTransInfo().getTransInfo().getTransID());
				nextPage = PageNameConst.INDEX_INIT_ACTION;
			}
		}
		return nextPage;
	}

}
