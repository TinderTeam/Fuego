/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.recapture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.action.index.IndexAction;
import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;
import cn.tinder.fuego.webservice.struts.bo.recapture.RecapturePlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: AssetsRecaptureEnsureAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:23:29 
*
 */

public class AssetsRecaptureEnsureAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureEnsureAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
	private TransPlanService planService = ServiceContext.getInstance().getRecapturePlanService();

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
		log.info(LogKeyConst.INPUT_ACTION);
    	String nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
    	
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		RecapturePlanBo plan = (RecapturePlanBo) request.getSession().getAttribute(RspBoNameConst.RECAPTURE_PLAN);
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
			nextPage = PageNameConst.ASSETS_RECAPTURE_CREATE_INIT_PAGE;
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
		else if(ParameterConst.REFUSE_PARA_NAME.equals(submitPara))
		{
			planService.backward(plan.getTransInfo().getTransInfo().getTransID());
		}
		return nextPage;
	}

}
