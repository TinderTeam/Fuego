package cn.tinder.fuego.webservice.struts.action.gasstationcheck;

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
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsModifyForm;

/**
 * 
 * @ClassName: GasStationCheckStatusEnsureInitAction
 * @Description: TODO
 * @author Kong Jingliang
 * @date 2013-10-1 下午07:09:49
 * 
 */
public class GasStationCheckStatusEnsureInitAction extends Action
{
	private static final Log log = LogFactory
			.getLog(GasStationCheckStatusEnsureInitAction.class);

	private TransPlanService planService = ServiceContext.getInstance().getCheckPlanService();
    private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();

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
		String nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_ENSURE_PAGE;
		
		CheckPlanBo plan;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
        plan = (CheckPlanBo) planService.getPlanByTransID(transID);
        AssetsModifyForm assetsForm = (AssetsModifyForm) form;
		if(null ==plan )
		{
	        log.info("can not get plan by transaction id." + transID);
			plan = (CheckPlanBo) request.getAttribute(RspBoNameConst.CHECK_PLAN_DATA);
		}
 
		//the check transaction is child, so jump to assets check status
		if(null == plan.getTransInfo().getChildTransList() || plan.getTransInfo().getChildTransList().isEmpty())
		{
			nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_ENSURE_PAGE;
			if(needCreateNewAssets(assetsForm))
			{
				AssetsInfoBo newAssets = assetsService.getNewAssetsByAssetsID(null);
				newAssets.getAssets().setAssetsName(assetsForm.getAssetsInfo().getAssets().getAssetsName());
				newAssets.getAssets().setManufacture(assetsForm.getAssetsInfo().getAssets().getManufacture());
				newAssets.getAssets().setSpec(assetsForm.getAssetsInfo().getAssets().getSpec());
				newAssets.getExtAttr().setCheckState(AssetsConst.CHECK_STATUS_MORE);
				plan.getPlanInfo().getAssetsPage().getAssetsList().add(newAssets);
				planService.updatePlan(plan);
			}
		}
		else
		{
			nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_PAGE;
		}
		

		plan.getPlanInfo().getAssetsPage().setShowNote(true);
		plan.getPlanInfo().getAssetsPage().setShowCheckState(true);

		
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

 	   
		nextPage = controlPageBtnDis(plan.getTransInfo().getTransInfo().canOperate(user),nextPage,request);

        request.setAttribute(RspBoNameConst.CHECK_PLAN_DATA, plan);
        request.getSession().setAttribute(RspBoNameConst.CHECK_TRANS_ID, plan.getTransInfo().getTransInfo().getTransID());
     	
		return nextPage;
	}
	
	private boolean needCreateNewAssets(AssetsModifyForm assetsForm )
	{
		if(null != assetsForm 
		  && null != assetsForm.getAssetsInfo().getAssets().getAssetsName()
		  && !assetsForm.getAssetsInfo().getAssets().getAssetsName().trim().isEmpty() )
		{
			return true;
		}
		
		return false; 
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
			else if(TransactionConst.CHECK_MAX_STEP.equals(step))
			{
				pageCtr = RspBoNameConst.PAGE_CREATE;
			}
			else if(TransactionConst.TRANS_LAST_STEP.equals(step))
			{
				pageCtr = RspBoNameConst.PAGE_FINISH;
			}
			else if(TransactionConst.TRANS_FINISH_STEP.equals(step))
			{
				pageCtr = RspBoNameConst.PAGE_VIEW;
	 		}
			else
			{
				pageCtr = RspBoNameConst.PAGE_FINISH;
	 		}
		}

		request.setAttribute(RspBoNameConst.PAGE_DIS_CTL, pageCtr);
		return nextPage;
	}

}
