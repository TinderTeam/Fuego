package cn.tinder.fuego.webservice.struts.action.purchaseplan;

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
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

/**
 * 
 * @ClassName: PurchasePlanAction
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-10-1 下午04:39:00
 * 
 */
public class PurchasePlanAction extends Action {
	private static final Log log = LogFactory.getLog(PurchasePlanAction.class);
	   
	// Service
	AssetsManageService assetsManageService = ServiceContext.getInstance().getAssetsManageService();
	private TransPlanService purchasePlanService = ServiceContext.getInstance().getPurchasePlanService();

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info(LogKeyConst.INPUT_ACTION + "PurchasePlanAction");
		// Page
		String pageName = handle(form, request);
		
		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}

	private String handle(ActionForm form, HttpServletRequest request) {

		String pageName = null;
		
		// RequestIn
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(
				RspBoNameConst.SYSTEM_USER);

		// Form
		PurchasePlanForm purchasePlanForm = (PurchasePlanForm) form;
		
		
		if (null == user) {
			log.error("the user is null");
			pageName = PageNameConst.LOGIN_PAGE;
			return pageName;
		}
		
		// Form Empty test
		if (purchasePlanForm == null) {

			log.error("cant find form!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else {
			log.info(LogKeyConst.PAGE_FORM + purchasePlanForm.toString());
		}

		// Para
		String submitPara = request
				.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		if (null == submitPara || submitPara.isEmpty()) {
			log.error("submit is null!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else {
			log.info(LogKeyConst.SUBMIT_VALUE + submitPara);
		}

		// RequestOut
		PurchasePlanSessionBo purchasePlanSessionBo = new PurchasePlanSessionBo();


		if (submitPara.equals(ParameterConst.SUBMIT_1)) {
			/*
			 * 1.Create Refferance
			 */
			purchasePlanSessionBo.getPurchasePageBo().getPage().setAllPageData(assetsManageService.getRefPurchaseList(user.getUserID(),purchasePlanForm));
			purchasePlanSessionBo.getPurchasePageBo().setAssetsList(purchasePlanSessionBo.getPurchasePageBo().getPage().getCurrentPageData());
 
			log.info(LogKeyConst.SESSION_BO
					+ "output BO is refBO " + purchasePlanSessionBo);

			pageName = PageNameConst.PURCHASE_REF_PLAN_CREATE_ACTION; // "RefPlanCreateInit"
			/*
			 * path="/RefPlanCreateInit.do" ->> RefPlanInitAction
			 */

		} else if (submitPara.equals(ParameterConst.SUBMIT_2)) {
			/*
			 * 2.Create Plan
			 */
			pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
		} else {
			log.error("can't match the submit Para!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
		
		}
		
		request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,purchasePlanSessionBo);

		return pageName;
	}

}
