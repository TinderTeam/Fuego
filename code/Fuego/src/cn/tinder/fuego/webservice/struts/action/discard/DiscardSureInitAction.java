package cn.tinder.fuego.webservice.struts.action.discard;



import java.util.ArrayList;
import java.util.Date;
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
import cn.tinder.fuego.service.ConstServiceTest;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.AssetsDiscardInfoBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSearchBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.DiscardSearchSelectForm;



/**
 * 
* @ClassName: DiscardInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:14:55 
*
 */
public class DiscardSureInitAction extends Action
{
    private static final Log log = LogFactory.getLog(DiscardSureInitAction.class);
    private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();
	
    private TransPlanService  planService = ServiceContext.getInstance().getDiscardPlanService();
    
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

	private String handle(ActionForm form, HttpServletRequest request)
	{
		String nextPage = PageNameConst.DISCARD_SURE;
		String transID = request.getParameter(ParameterConst.PLAN_TRANS_ID);
		DiscardPlanBo plan = (DiscardPlanBo) planService.getPlanByTransID(transID);
		if (null == plan)
		{
			log.info("can not get the plan by transaction id" + transID);

			plan = (DiscardPlanBo) request.getSession().getAttribute(RspBoNameConst.DISCARD_PLAN_INFO);
	    	planService.updatePlan(plan);

		} 
		request.getSession().setAttribute(RspBoNameConst.DISCARD_PLAN_INFO, plan);

    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

    	   
		nextPage = controlPageBtnDis(plan.getTransInfo().getTransInfo().canOperate(user),plan.getTransInfo().getTransInfo().getTransID(),nextPage,request);


		return nextPage;
	}
	private String controlPageBtnDis(boolean canOperate,String transID,String nextPage,HttpServletRequest request)
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
			else if(Integer.valueOf(step)>= planService.getMaxStep(transID))
			{
				nextPage = PageNameConst.DISCARD_SEARCH_INIT;
			}
			else if(planService.isApporalStep(Integer.valueOf(step)))
			{
				pageCtr = RspBoNameConst.PAGE_APPROVAL;
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
				pageCtr = RspBoNameConst.PAGE_APPROVAL;
	 		}
		}

		request.setAttribute(RspBoNameConst.PAGE_DIS_CTL, pageCtr);
		return nextPage;
	}


}
