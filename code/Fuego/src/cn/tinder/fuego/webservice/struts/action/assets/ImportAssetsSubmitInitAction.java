package cn.tinder.fuego.webservice.struts.action.assets;



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
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceivePlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: PurchaseWarehousingEnsuringInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:51:54 
*
 */
public class ImportAssetsSubmitInitAction extends Action
{
    private static final Log log = LogFactory.getLog(ImportAssetsSubmitInitAction.class);
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
         
    	String nextPage = PageNameConst.IMPORT_ASSETS_SUBMIT_PAGE;// "PurchaseWarehousingEnsure"->"/jsp/purchaseWarehousingEnsure.jsp"
 

		ReceivePlanBo plan;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
		plan = (ReceivePlanBo) planService.getPlanByTransID(transID);
		
		if(null ==plan )
		{
	        log.info("can not get plan by transaction id." + transID);
	        nextPage = PageNameConst.IMPORT_ASSETS_SUBMIT_PAGE;
 		}
		else
		{
			//the receive transaction is child, so jump to assets receive status
			if(null == plan.getTransInfo().getChildTransList() || plan.getTransInfo().getChildTransList().isEmpty())
			{
				nextPage = PageNameConst.ASSETS_RECEIVE_PAGE;
				plan.getPlanInfo().getAssetsPage().setShowReceiveState(true);
				plan.getPlanInfo().getAssetsPage().setShowNote(true);
			}
			else
			{
				nextPage = PageNameConst.RECEIVE_STATUS_PAGE;
	 		}
	        request.setAttribute(RspBoNameConst.RECEIVE_PLAN_DATA, plan);
	    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

			nextPage = controlPageBtnDis(plan.getTransInfo().getTransInfo().canOperate(user),nextPage,request);
	 
		}


		return nextPage;

    }
	private String controlPageBtnDis(boolean canOperate,String nextPage,HttpServletRequest request)
	{
		//control page button display by the step
		String pageCtr = RspBoNameConst.PAGE_CREATE;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
		String step = request.getParameter(ParameterConst.PLAN_STEP);

		if(!canOperate)
		{
			pageCtr = RspBoNameConst.PAGE_VIEW;
		}
		else
		{
			if(null == transID)
			{
				pageCtr = RspBoNameConst.PAGE_CREATE;
			}
			else 
			{
				if(null == step)
				{
					pageCtr = RspBoNameConst.PAGE_CREATE;
				}
				else if(TransactionConst.RECEIVE_MAX_STEP.equals(step))
				{
					pageCtr = RspBoNameConst.PAGE_CREATE;
				}
				else if(TransactionConst.TRANS_FINISH_STEP.equals(step))
				{
					pageCtr = RspBoNameConst.PAGE_FINISH;
				}
				else
				{
					pageCtr = RspBoNameConst.PAGE_FINISH;
				}
 			}
		}
 
		request.setAttribute(RspBoNameConst.PAGE_DIS_CTL, pageCtr);
		return nextPage;
	}

}
