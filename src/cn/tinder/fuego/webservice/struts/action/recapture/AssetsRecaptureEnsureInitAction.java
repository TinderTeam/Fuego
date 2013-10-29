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

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.TransPlanService;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.recapture.*;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: AssetsRecaptureEnsureInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-3 上午01:11:55 
*
 */
public class AssetsRecaptureEnsureInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureEnsureInitAction.class);
	private TransPlanService planService = ServiceContext.getInstance().getRecapturePlanService();

    //private AssetsRecoverService  assetsRecoverService = ServiceContext.getInstance().getAssetsRecoverService();
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

	private String handle(ActionForm form, HttpServletRequest request)
	{
    	 
    	log.info(LogKeyConst.INPUT_ACTION+"AssetsRecaptureEnsureInitAction");
 
    	String nextPage = PageNameConst.ASSETS_RECAPTURE_ENSURE_PAGE;
    	String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
    	RecapturePlanBo plan  = (RecapturePlanBo) planService.getPlanByTransID(transID);
		if(null == plan)
		{
	    	plan  = (RecapturePlanBo) request.getSession().getAttribute(RspBoNameConst.RECAPTURE_PLAN);
	    	log.warn("can not get the plan by transaction id "+transID);
		}
		request.getSession().setAttribute(RspBoNameConst.RECAPTURE_PLAN, plan);

		nextPage = controlPageBtnDis(nextPage,request);
 
        return nextPage;	
    }
	
	private String controlPageBtnDis(String nextPage,HttpServletRequest request)
	{
		//control page button display by the step
		String pageCtr = RspBoNameConst.PAGE_CREATE;
		String step = request.getParameter(ParameterConst.PLAN_STEP);
		if(null == step)
		{
			pageCtr = RspBoNameConst.PAGE_CREATE;
		}
		else if(TransactionConst.RECAPTURE_MAX_STEP.equals(step))
		{
			nextPage = PageNameConst.ASSETS_RECAPTURE_CREATE_INIT_PAGE;
		}
		else if(TransactionConst.RECAPTURE_APPROVAL_STEP.equals(step))
		{
			pageCtr = RspBoNameConst.PAGE_APPROVAL;
		}
		else
		{
			pageCtr = RspBoNameConst.PAGE_CONFIRM;
 		}
		request.setAttribute(RspBoNameConst.PAGE_DIS_CTL, pageCtr);
		return nextPage;
	}

}
