package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.PurchasePlanService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanRefBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanRefListBoTest;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
 * @ClassName: RefPlanInitAction
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-28 上午12:26:37
 * 
 */
public class RefPlanInitAction extends Action {
	private static final Log log = LogFactory.getLog(RefPlanInitAction.class);

	// Service
	private LoadService loadService = ServiceContext.getInstance()
			.getLoadService();


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info(LogKeyConst.INPUT_ACTION + "RefPlanInitAction");
		String pageName = handle(form, request);
		log.info(LogKeyConst.NEXT_PAGE + pageName); // ->>"/jsp/purchasePlanCreateRef.jsp"
		return mapping.findForward(pageName);

	}

	private String handle(ActionForm form, HttpServletRequest request) {
		// Page
		String pageName = PageNameConst.PURCHASE_REF_PLAN_CREATE_PAGE; //
																		// "RefPlanCreate"
																		// ->>"/jsp/purchasePlanCreateRef.jsp"
		//User check
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(
				RspBoNameConst.SYSTEM_USER);
		if (null == user) {
			log.error("the user is null");
			pageName = PageNameConst.LOGIN_PAGE;
			return pageName;
		}
		
		//SessionBo
		PurchasePlanSessionBo purchasePlanSessionBo = (PurchasePlanSessionBo) request
				.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);
			// Empty Test
			if (purchasePlanSessionBo == null) {
				log.error("SessionBo is Null");
				pageName = PageNameConst.LOGIN_PAGE;
				return pageName;
			} else {
				log.info(LogKeyConst.SESSION_BO + purchasePlanSessionBo);
			}

		/*
		 * For showing: The Auto Assets list for select,then submit to next
		 * page(add and search assets).
		 */
			
		//Bo for show			
		request.setAttribute(RspBoNameConst.REF_LIST, purchasePlanSessionBo.getPurchasePageBo().getAssetsList());	
		
		return pageName;

	}

}
