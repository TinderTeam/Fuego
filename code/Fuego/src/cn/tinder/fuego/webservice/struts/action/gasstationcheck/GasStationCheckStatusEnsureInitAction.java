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
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
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
		
		CheckPlanBo checkPlan;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
        checkPlan = (CheckPlanBo) planService.getPlanByTransID(transID);
        AssetsModifyForm assetsForm = (AssetsModifyForm) form;
		if(null ==checkPlan )
		{
	        log.info("can not get plan by transaction id." + transID);
			checkPlan = (CheckPlanBo) request.getAttribute(RspBoNameConst.CHECK_PLAN_DATA);
		}
 
		//the check transaction is child, so jump to assets check status
		if(null == checkPlan.getTransInfo().getChildTransList() || checkPlan.getTransInfo().getChildTransList().isEmpty())
		{
			nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_ENSURE_PAGE;
			if(needCreateNewAssets(assetsForm))
			{
				AssetsInfoBo newAssets = assetsService.getNewAssetsByAssetsID(null);
				newAssets.getAssets().setAssetsName(assetsForm.getAssetsInfo().getAssets().getAssetsName());
				newAssets.getAssets().setManufacture(assetsForm.getAssetsInfo().getAssets().getManufacture());
				newAssets.getAssets().setSpec(assetsForm.getAssetsInfo().getAssets().getSpec());
				newAssets.getExtAttr().setCheckState(AssetsConst.CHECK_STATUS_MORE);
				checkPlan.getPlanInfo().getAssetsPage().getAssetsList().add(newAssets);
				planService.updatePlan(checkPlan);
			}
		}
		else
		{
			nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_PAGE;
		}
		
		checkPlan.getPlanInfo().getAssetsPage().setShowNote(true);
		checkPlan.getPlanInfo().getAssetsPage().setShowCheckState(true);
        request.setAttribute(RspBoNameConst.CHECK_PLAN_DATA, checkPlan);
        request.getSession().setAttribute(RspBoNameConst.CHECK_TRANS_ID, checkPlan.getTransInfo().getTransInfo().getTransID());
		log.info(LogKeyConst.NEXT_PAGE + nextPage);
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

}
