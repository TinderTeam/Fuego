/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.apply;

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
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.GasAssetsApplyForm;

/**
 * 
* @ClassName: GasAssetsApplyAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:41:39 
*
 */
public class GasAssetsApplyAction extends Action
{
    private static final Log log = LogFactory.getLog(GasAssetsApplyAction.class);
    

    private AssetsManageService  assetsService = ServiceContext.getInstance().getAssetsManageService();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"GasAssetsApplyAction");
    	String pageName = null;
    	
    	//RequestIn
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);    
    	if(null == user)
    	{
    		log.error("the user is null");
    		pageName = PageNameConst.LOGIN_PAGE;
    		 return mapping.findForward(pageName);			
    	}

    	//Form
    	GasAssetsApplyForm gasAssetsApplyForm = (GasAssetsApplyForm)form;
    	//Form Empty test
    	if(gasAssetsApplyForm==null){
    		log.error("cant find form!!");   
    		pageName = PageNameConst.SYSTEM_ERROR_PAGE;
    	}else{
    		log.info(LogKeyConst.PAGE_FORM+gasAssetsApplyForm.toString());    		
    	}
    	
    	//Para
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);    	
    	if(null==submitPara){
    		log.error("submit is miss!");
    		pageName = PageNameConst.SYSTEM_ERROR_PAGE;
    	}else{
    		log.info(LogKeyConst.SUBMIT_VALUE+submitPara); 
    	}

    	// Banch select
    	if(submitPara.equals(ParameterConst.SUBMIT_1)){
    		/*
    		 * Submit1 means Change the status, we should change the assets-status, and create a new Notice to the DepartmentManager
    		 */
    		assetsService.sendAssetsStatusChangeApply(gasAssetsApplyForm);	
    		pageName=PageNameConst.SYSTEM_SUCCESS_PAGE;
    	}else if(submitPara.equals(ParameterConst.BACK_PARA_NAME)){
    		/*
    		 *Submit is Back, we'd better, return Index page without send any bo. and Have a nice night!! Thankyou!!
    		 *
    		 */

    		pageName=PageNameConst.INDEX_INIT_ACTION;
    	}else if(submitPara.equals(ParameterConst.SUBMIT_2)){
    		/*
    		 * The submit2 is to create a assets-apply notice to department-manager and without doing anyother thing.
    		 */
    		
    		assetsService.sendAssetsApply(gasAssetsApplyForm);
    		pageName=PageNameConst.SYSTEM_SUCCESS_PAGE;
    	}
    	    	
    	
        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	

    }

}
