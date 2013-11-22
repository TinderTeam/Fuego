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
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.download.PurchasePlanFile;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

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
	private TransPlanService purchasePlanService = ServiceContext.getInstance().getPurchasePlanService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION + "PurchasePlanEnsureAction");
		// Page
		String nextPage = null;

		// RequestIn
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

		if (null == user)
		{
 			log.error("the user is null");
			nextPage = PageNameConst.LOGIN_PAGE;
			return mapping.findForward(nextPage);
		}

		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		log.info(LogKeyConst.SUBMIT_VALUE + submitPara);

		if (null == submitPara || submitPara.isEmpty())
		{
			log.error("can't find submitPara!");
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE;
			return mapping.findForward(nextPage);
		}
		
		PurchasePlanSessionBo purchasePlan  = (PurchasePlanSessionBo) request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);

		if (submitPara.equals(ParameterConst.SUBMIT_PARA_NAME))
		{ // ="submit"

 
			purchasePlanService.updatePlan(purchasePlan);
			purchasePlanService.forwardNext(purchasePlan.getPurchaseTransBo().getTransInfo().getTransID());

			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;// "PurchasePlanCreateInit"
		}
		else if (submitPara.equals(ParameterConst.BACK_PARA_NAME))
		{ 
			
			nextPage = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
		} 
		else if(ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara))
		{
					
			purchasePlan.getPurchaseTransBo().getTransInfo().setCreateUser(user.getUserID());
			purchasePlan.getPurchaseTransBo().getTransInfo().setHandleUser(user.getDeptName());
			PurchasePlanFile file = new PurchasePlanFile(purchasePlan);
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, file.getFile().getAbsolutePath());
			nextPage = PageNameConst.DOWNLOAD_ACTION;
		}
		else if(ParameterConst.AGREE_PARA_NAME.equals(submitPara))
		{
			purchasePlanService.forwardNext(purchasePlan.getPurchaseTransBo().getTransInfo().getTransID());
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;

		}
		else if(ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
		{
			purchasePlanService.forwardNext(purchasePlan.getPurchaseTransBo().getTransInfo().getTransID());
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;

		}else if(ParameterConst.REFUSE_PARA_NAME.equals(submitPara))
		{
			purchasePlanService.backward(purchasePlan.getPurchaseTransBo().getTransInfo().getTransID());
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

		return mapping.findForward(nextPage);

	}

}
