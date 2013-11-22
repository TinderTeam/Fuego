package cn.tinder.fuego.webservice.struts.action.discard;



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
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: DiscardAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:14:32 
*
 */
public class DiscardSureAction extends Action
{
    private static final Log log = LogFactory.getLog(DiscardSureAction.class);
	private TransPlanService planService = ServiceContext.getInstance().getDiscardPlanService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
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
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		DiscardPlanBo plan = (DiscardPlanBo) request.getSession().getAttribute(RspBoNameConst.DISCARD_PLAN_INFO);

		if (ParameterConst.SUBMIT_PARA_NAME.equals(submitPara))
		{
			planService.updatePlan(plan);
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());
			
 			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
		}
		else if (ParameterConst.AGREE_PARA_NAME.equals(submitPara))
		{
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());

		}
		else if(ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
		{	
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());
		}
			
		else if (submitPara.equals(ParameterConst.BACK_PARA_NAME))
		{
			nextPage = PageNameConst.DISCARD_SEARCH_INIT;
		}
		else if (submitPara.equals(ParameterConst.DOWNLOAD_PARA_NAME))
		{
			//下载页面
			nextPage = PageNameConst.DOWNLOAD_ACTION;
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, planService.getExportFile(plan).getAbsolutePath());
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
