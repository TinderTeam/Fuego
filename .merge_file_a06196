package cn.tinder.fuego.webservice.struts.action.purchaseplan;

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
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
 * @ClassName: RefPlanAction
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-28 上午12:26:28
 * 
 */
public class PurchasePlanEnsureInitAction extends Action
{
	private static final Log log = LogFactory.getLog(PurchasePlanEnsureInitAction.class);
	// Service
	private TransPlanService planService = ServiceContext.getInstance().getPurchasePlanService();

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

		String nextPage = PageNameConst.PURCHASE_PLAN_ENSURE_PAGE;
 
 
		// RequestOut
 
 		PurchasePlanSessionBo purchasePlanSessionBo = null;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
		if(null != transID)
		{
			purchasePlanSessionBo = (PurchasePlanSessionBo) planService.getPlanByTransID(transID);
  		}
		else
		{
			log.info("can not get check  by transaction id." + transID);

		}
 
		if (null == purchasePlanSessionBo)
		{
			purchasePlanSessionBo = (PurchasePlanSessionBo) request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);
		}
		else
		{
 		}
 
		request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA, purchasePlanSessionBo);// "assetsList"
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
		else if(TransactionConst.PURCHASE_MAX_STEP.equals(step))
		{
			nextPage = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
		}
		else if(TransactionConst.PURCHASE_APPROVAL_STEP.equals(step))
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
