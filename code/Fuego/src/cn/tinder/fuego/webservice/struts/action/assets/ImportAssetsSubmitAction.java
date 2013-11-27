package cn.tinder.fuego.webservice.struts.action.assets;



import java.io.File;
import java.sql.BatchUpdateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.exception.ConstraintViolationException;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceivePlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: PurchaseWarehousingAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:07:26 
*
 */

public class ImportAssetsSubmitAction extends Action
{
    private static final Log log = LogFactory.getLog(ImportAssetsSubmitAction.class);
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
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		if(ParameterConst.SUBMIT_PARA_NAME.equals(submitPara))
		{
	    	AssetsPageBo assetsPage = (AssetsPageBo) request.getSession().getAttribute(RspBoNameConst.ASSETS_PAGE_DATA);

			plan = (ReceivePlanBo) planService.createPlan(user.getUserID(),assetsManageService.getUserListByAssestList(assetsPage.getAssetsList()));
			plan.getPlanInfo().setAssetsPage(assetsPage);
			try{
				planService.updatePlan(plan);
			}
			catch(ConstraintViolationException ex)
			{
				//delete the transaction when import assets list failed.
				//Issue 37
				planService.deletePlan(plan.getTransInfo().getTransInfo().getTransID());
				if(ex.getCause().getClass().equals(BatchUpdateException.class))
				{
					log.error("导入数据主键重复："+ex.getCause().getMessage());
					String errid = ex.getCause().getMessage().split(" ")[2];
					String errMsg= ExceptionMsg.ASSETS_NAME_ISEXIST+errid;
					
					request.setAttribute(RspBoNameConst.OPERATE_EXCEPION,errMsg);
					nextPage = PageNameConst.ERROR_PAGE; 
				}
				else
				{
					throw ex;
				}
				
			}
		}

		else if(ParameterConst.BACK_PARA_NAME.equals(submitPara))
		{	
			nextPage = PageNameConst.IMPORT_ASSETS_INIT_ACTION;
		}
		else if (ParameterConst.CANCEL_PARA_NAME.equals(submitPara))
		{
			nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
		else if(ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
		{
			planService.forwardNext(plan.getTransInfo().getTransInfo().getTransID());
		}
		else if(ParameterConst.VIEW_PARA_NAME.equals(submitPara))
		{
			nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
 
        return nextPage;
 

    }
	


}
