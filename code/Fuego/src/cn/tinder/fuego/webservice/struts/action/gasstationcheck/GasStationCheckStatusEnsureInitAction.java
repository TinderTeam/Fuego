package cn.tinder.fuego.webservice.struts.action.gasstationcheck;

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
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

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
		
		if(null ==checkPlan )
		{
	        log.info("can not get plan by transaction id." + transID);
			checkPlan = (CheckPlanBo) request.getAttribute(RspBoNameConst.CHECK_PLAN_DATA);
		}
 
		//the check transaction is child, so jump to assets check status
		if(null == checkPlan.getTransInfo().getChildTransList() || checkPlan.getTransInfo().getChildTransList().isEmpty())
		{
			nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_ENSURE_PAGE;
		}
		else
		{
			nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_PAGE;
			
		}
		
		checkPlan.getPlanInfo().getAssetsPage().setShowNote(true);
		checkPlan.getPlanInfo().getAssetsPage().setShowCheckState(true);
        request.setAttribute(RspBoNameConst.CHECK_PLAN_DATA, checkPlan);
 
		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return nextPage;
	}

}
