/**   
 * @Title: AssignSelectAssetsAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-1 下午07:04:41 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import java.util.Arrays;
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
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.SelectAssetsForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchaseAssetsSelectForm;
import cn.tinder.fuego.webservice.struts.form.purchase.RefPlanCreateForm;

/**
 * @ClassName: AssignSelectAssetsAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-1 下午07:04:41
 * 
 */

public class PurchaseAssetsSelectAction extends Action {
	private static final Log log = LogFactory
			.getLog(PurchaseAssetsSelectAction.class);
	private TransPlanService planService = ServiceContext.getInstance()
			.getAssignPlanService(TransactionConst.ASSIGN_PLAN_TYPE);

	private AssetsManageService assetsService = ServiceContext.getInstance()
			.getAssetsManageService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(LogKeyConst.INPUT_ACTION + "PurchaseAssetsSelectInitAction");
		// Page
		String pageName = handle(form, request);
		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);
		}

		

	private String handle(ActionForm form, HttpServletRequest request) {
		
		String pageName = PageNameConst.PURCHASE_ASSETS_SELECT_INIT;
		

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
		if (null == purchasePlanSessionBo) {
			log.error("cant find purchasePlanSessionBo!!");
			purchasePlanSessionBo = new PurchasePlanSessionBo();
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,
					purchasePlanSessionBo);
		} else {
			log.info(LogKeyConst.PAGE_FORM + purchasePlanSessionBo.toString());
		}

		// Form
		PurchaseAssetsSelectForm purchaseAssetsSelectForm = (PurchaseAssetsSelectForm) form;
		// Form Empty test
		if (purchaseAssetsSelectForm == null) {
			log.error("cant find form!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else {
			log.info(LogKeyConst.PAGE_FORM + purchaseAssetsSelectForm.toString());
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

		


			if (ParameterConst.CONFIRM_PARA_NAME.equals(submitPara)) {
				/*
				 * Confirm the Select
				 */
				if (null == purchaseAssetsSelectForm.getAssetsIDList()) {
					// if nothing selected , jump to last page
					log.warn("Select Assets is Empty!");
					pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
				} else {

					/*
					 * 1.将选中的属性增加至 采购表中
					 */
					purchasePlanSessionBo.getPurchaseAddPageBo().selectItemsByStringArray(purchaseAssetsSelectForm.getAssetsIDList());//这里的参数实际上是Index数组
					
					
					/*
					 * 2.将增加内容更新至采购表显示
					 */
					purchasePlanSessionBo.updateAdd();
			
					request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA, purchasePlanSessionBo);
					pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
				}
			}else if (ParameterConst.CANCEL_PARA_NAME.equals(submitPara)) {
				/*
				 * Cancel Branch
				 */
				pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
			}else if(ParameterConst.SEARCH_PARA_NAME.equals(submitPara)){
				/*
				 * Search Branch
				 */
				purchasePlanSessionBo.getPurchaseAddPageBo().setAssetsList(assetsService.getPurchaseSumAssetsList(purchaseAssetsSelectForm));
				
				pageName = PageNameConst.PURCHASE_ASSETS_SELECT_INIT;
			}
		
		return pageName;

	}
}
