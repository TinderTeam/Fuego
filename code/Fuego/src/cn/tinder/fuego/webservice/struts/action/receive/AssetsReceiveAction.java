package cn.tinder.fuego.webservice.struts.action.receive;



import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceivePlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsListForm;



/**
 * 
* @ClassName: PurchaseWarehousingAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:07:26 
*
 */

public class AssetsReceiveAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsReceiveAction.class);
    private AssetsManageService  assetsManageService = ServiceContext.getInstance().getAssetsManageService();
	private TransPlanService planService = ServiceContext.getInstance().getReceivePlanService();

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
		ReceivePlanBo plan = null ; 
    	String nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
		plan = (ReceivePlanBo) planService.getPlanByTransID(transID);
		AssetsListForm assetsListForm = (AssetsListForm)form; 
		
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
    	
    	if(null == plan.getTransInfo().getChildTransList() || plan.getTransInfo().getChildTransList().isEmpty())
    	{
    		if (ParameterConst.CANCEL_PARA_NAME.equals(submitPara))
    		{
    			nextPage = PageNameConst.INDEX_INIT_ACTION;
    		}
    		else if(ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
    		{
    			plan.getPlanInfo().getAssetsPage().setAssetsList(assetsListForm.getAssetsList());
    			planService.updatePlan(plan);
    			planService.forwardNext(transID);
     		}	
    		else if(ParameterConst.FINISH_PARA_NAME.equals(submitPara))
    		{
    			planService.forwardNext(transID);

    		}
    		if (ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara))
    		{
 				File exportFile = planService.getExportFile(plan);
				request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, exportFile.getAbsolutePath());
				nextPage = PageNameConst.DOWNLOAD_ACTION;
     		}
    		else if(ParameterConst.VIEW_PARA_NAME.equals(submitPara))
    		{
    			nextPage = PageNameConst.INDEX_INIT_ACTION;

    		}
    	}
    	else
    	{
    		if (ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara))
    		{
 				File exportFile = planService.getExportFile(plan);
				request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, exportFile.getAbsolutePath());
				nextPage = PageNameConst.DOWNLOAD_ACTION;
     		}
    		else if(ParameterConst.FINISH_PARA_NAME.equals(submitPara))
    		{
    			planService.forwardNext(transID);
    		}
    		else
    		{
    			nextPage =  PageNameConst.INDEX_INIT_ACTION;
    		}
    	}

		 
 
        return nextPage;
 

    }
	


}
