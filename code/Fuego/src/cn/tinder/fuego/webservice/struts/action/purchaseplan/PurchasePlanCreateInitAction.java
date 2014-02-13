package cn.tinder.fuego.webservice.struts.action.purchaseplan;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: PurchasePlanCreateInitAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-10-1 下午04:42:37 
*
 */
public class PurchasePlanCreateInitAction extends Action
{
    private static final Log log = LogFactory.getLog(PurchasePlanCreateInitAction.class);
    
    //Service
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();

    
    
    @Override

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION);
    	//Page
    	String pageName = PageNameConst.PURCHASE_PLAN_CREATE_PAGE;
    	// Rquest in
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(
				RspBoNameConst.SYSTEM_USER);

		if (null == user) {
			// Empty test
			log.error("the user is null");
			pageName = PageNameConst.LOGIN_PAGE;
			return mapping.findForward(pageName);	
		}
		
		
    	//RequestOut
    	
    	// SessionBo
		PurchasePlanSessionBo purchasePlanSessionBo = (PurchasePlanSessionBo) request
				.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);
		if (null == purchasePlanSessionBo) {
			// SessionBo
			purchasePlanSessionBo = new PurchasePlanSessionBo();
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA, purchasePlanSessionBo);

		}else{
			log.info(LogKeyConst.SESSION_BO+purchasePlanSessionBo.toString());
		}
        
		request.setAttribute(RspBoNameConst.TYPE_LIST,loadService.loadAssetsTypeList(user.getUserID()));//TypeList
    	request.setAttribute(RspBoNameConst.DEPT_INFO_LIST,loadService.loadDeptInfoByUser(user.getUserID(),false));//DeptList
        request.setAttribute(RspBoNameConst.REF_LIST,purchasePlanSessionBo.getPurchasePageBo().getAssetsList());
        pageName = PageNameConst.PURCHASE_PLAN_CREATE_PAGE;

        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	

    }
 

}
