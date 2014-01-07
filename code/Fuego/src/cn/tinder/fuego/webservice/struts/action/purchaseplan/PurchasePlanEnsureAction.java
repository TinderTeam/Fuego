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
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.download.PurchasePlanFile;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.TransOperateInfoForm;

/**
 * 
 * @ClassName: PurchasePlanEnsureAction
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-10-1 下午11:38:00
 * 
 */
public class PurchasePlanEnsureAction extends Action
{
	private static final Log log = LogFactory.getLog(PurchasePlanEnsureAction.class);
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

	private String handle(ActionForm form,HttpServletRequest request)
	{
		log.info(LogKeyConst.INPUT_ACTION + "PurchasePlanEnsureAction");
		// Page
		String nextPage = null;

		// RequestIn
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

 

		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		log.info(LogKeyConst.SUBMIT_VALUE + submitPara);

 
		
		PurchasePlanSessionBo plan  = (PurchasePlanSessionBo) request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);
		TransOperateInfoForm operateInfo = (TransOperateInfoForm)form;

		if (submitPara.equals(ParameterConst.SUBMIT_PARA_NAME))
		{ // ="submit"

			plan.getPurchaseTransBo().getTransInfo().setHandleUser(operateInfo.getHandleUser());
			plan.getPurchaseTransBo().getTransInfo().setExecuteName(operateInfo.getExecuteName());
			
			planService.updatePlan(plan);
			if(user.getRole().equals(UserRoleConst.SUPER_DEPT))
			{
 
				planService.forwardNextBySystem(plan.getPurchaseTransBo().getTransInfo().getTransID());
				planService.forwardNextBySystem(plan.getPurchaseTransBo().getTransInfo().getTransID());
            }
			else if(user.getRole().equals(UserRoleConst.DEPT))
			{	
				planService.forwardNextBySystem(plan.getPurchaseTransBo().getTransInfo().getTransID());

			}
			planService.forwardNext(plan.getPurchaseTransBo().getTransInfo().getTransID());

			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;// "PurchasePlanCreateInit"
		}
		else if (submitPara.equals(ParameterConst.CANCEL_PARA_NAME))
		{ 
			planService.deletePlan(plan.getPurchaseTransBo().getTransInfo().getTransID());
			nextPage = PageNameConst.INDEX_INIT_ACTION;
		} 
		else if(ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara))
		{
					
			plan.getPurchaseTransBo().getTransInfo().setCreateUser(user.getUserID());
			plan.getPurchaseTransBo().getTransInfo().setHandleUser(user.getDeptName());
			PurchasePlanFile file = new PurchasePlanFile(plan);
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, file.getFile().getAbsolutePath());
			nextPage = PageNameConst.DOWNLOAD_ACTION;
		}
		else if(ParameterConst.AGREE_PARA_NAME.equals(submitPara))
		{
			planService.forwardNext(plan.getPurchaseTransBo().getTransInfo().getTransID(),operateInfo.getOperateInfo());
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;

		}
		else if(ParameterConst.FINISH_PARA_NAME.equals(submitPara))
		{
			planService.forwardNext(plan.getPurchaseTransBo().getTransInfo().getTransID());
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;

		}else if(ParameterConst.REFUSE_PARA_NAME.equals(submitPara))
		{
			planService.backward(plan.getPurchaseTransBo().getTransInfo().getTransID(),operateInfo.getOperateInfo());
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;

		}
		else if(ParameterConst.VIEW_PARA_NAME.equals(submitPara))
		{
		 	nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
		else
		{
			log.error("can't match the submit Para!");
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE;
		}

		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		
		return nextPage;

 

	}

		

}
