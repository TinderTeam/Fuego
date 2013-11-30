package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.purchase.RefPlanCreateForm;

/**
 * 
 * @ClassName: RefPlanAction
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-28 上午12:26:28
 * 
 */
public class RefPlanAction extends Action {
	private static final Log log = LogFactory.getLog(RefPlanAction.class);
	// Service


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

		// Rquest in
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(
				RspBoNameConst.SYSTEM_USER);

		if (null == user) {
			// Empty test
			log.error("the user is null");
			pageName = PageNameConst.LOGIN_PAGE;
			return pageName;
		}

		// SessionBo
		PurchasePlanSessionBo purchasePlanSessionBo = (PurchasePlanSessionBo) request
				.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);
		if(null==purchasePlanSessionBo){
			log.error("cant find purchasePlanSessionBo!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else {
			log.info(LogKeyConst.PAGE_FORM + purchasePlanSessionBo.toString());
		}
		
		// Form
		RefPlanCreateForm refPlanCreateForm = (RefPlanCreateForm) form;

		// Form Empty test
		if (refPlanCreateForm == null) {
			log.error("cant find form!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else {
			log.info(LogKeyConst.PAGE_FORM + refPlanCreateForm.toString());
		}

		// Para
		String submitPara = request
				.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		log.info(LogKeyConst.SUBMIT_VALUE + submitPara);

		if (null == submitPara || submitPara.isEmpty()) {
			log.error("can't find submitPara!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		}

		/*
		 * Main Logic
		 */

		// Submit Value Select

		if (submitPara.equals(ParameterConst.SUBMIT_PARA_NAME)) { // ="submit"
			
			/*
			 *  Handel the List by the selected boxes
			 */
			 purchasePlanSessionBo.getPurchasePageBo().selectItemsByStringArray(refPlanCreateForm.getBoxes());			
			log.info("purchasePlanSessionBo is：" + purchasePlanSessionBo.toString());
			
			
			pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;// "PurchasePlanCreateInit"
		} else if (submitPara.equals(ParameterConst.BACK_PARA_NAME)) { // ="back"
			/*
			 * return nothing to page : P.P.Init;
			 * and clean the session
			 */
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,
					null);
			pageName = PageNameConst.PURCHASE_PLAN_PAGE_ACTION; // =
																// "PurchasePlanInit"->>
																// path="/PurchasePlanInit.do"
		} else {
			log.error("can't match the submit Para!");
		}

		log.info(LogKeyConst.NEXT_PAGE + pageName);

		return pageName;

	}

}
