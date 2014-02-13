package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.exception.ServiceException;
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
public class PurchaseSelectAction extends Action
{
	private static final Log log = LogFactory.getLog(PurchaseSelectAction.class);

	// Service

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

	private String handle(ActionForm form, HttpServletRequest request)
	{

		String pageName = PageNameConst.PURCHASE_REF_PLAN_CREATE_ACTION;;

 
		PurchasePlanSessionBo purchasePlanSessionBo = (PurchasePlanSessionBo) request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);

		// Form
		RefPlanCreateForm refPlanCreateForm = (RefPlanCreateForm) form;

 
		// Para
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		log.info(LogKeyConst.SUBMIT_VALUE + submitPara);
 
		if(ParameterConst.SELECT_MARK.equals(submitPara))
		{
			purchasePlanSessionBo.getPurchasePageBo().addData(purchasePlanSessionBo.getSelectPageBo().getPlanListByIndex(refPlanCreateForm.getBoxes()));

		}
		else if(ParameterConst.SELECT_PAGE.equals(submitPara))
		{
			purchasePlanSessionBo.getPurchasePageBo().addData(purchasePlanSessionBo.getSelectPageBo().getPage().getCurrentPageData());
 		}		
		else if(ParameterConst.SELECT_ALL.equals(submitPara))
		{
			purchasePlanSessionBo.getPurchasePageBo().addData(purchasePlanSessionBo.getSelectPageBo().getPage().getAllPageData());;
		}
        else if (submitPara.equals(ParameterConst.PAGECHANGE_PARA_NAME))
		{
			purchasePlanSessionBo.getSelectPageBo().getPage().setCurrentPage(refPlanCreateForm.getPageNum());
 		}

		log.info(LogKeyConst.NEXT_PAGE + pageName);

		return pageName;

	}

}
