/**   
 * @Title: AssignSelectAssetsInitAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-1 下午07:05:14 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import java.util.ArrayList;

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
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * @ClassName: AssignSelectAssetsInitAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-1 下午07:05:14
 * 
 */

public class PurchaseAssetsSelectInitAction extends Action {
	private static final Log log = LogFactory
			.getLog(PurchaseAssetsSelectInitAction.class);

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


		//Page
		String pageName = PageNameConst.PURCHASE_ASSETS_SELECT_PAGE; //PurchaseAssetsSelect


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
			log.warn("cant find purchasePlanSessionBo!!");
			purchasePlanSessionBo =new PurchasePlanSessionBo();
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,purchasePlanSessionBo);
		} else {
			log.info(LogKeyConst.PAGE_FORM + purchasePlanSessionBo.toString());					
		}
			
		
		//SearchList Init
			/*
			 * Search for the List
			 */
		/*
		 * 显示AddList的内容
		 */
			if(purchasePlanSessionBo.getPurchaseAddPageBo().getAssetsList()==null){
				purchasePlanSessionBo.getPurchaseAddPageBo().setAssetsList(new ArrayList<PurchasePlanBo>());
				request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,purchasePlanSessionBo);
			}			
			request.setAttribute(RspBoNameConst.PURCHASE_LIST,purchasePlanSessionBo.getPurchaseAddPageBo().getAssetsList());
			return pageName;
		}
}
