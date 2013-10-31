/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.recapture;

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
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.action.index.IndexAction;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.recapture.RecapturePlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsRecaptureCreateForm;
import cn.tinder.fuego.webservice.struts.form.RecaptureForm;
import cn.tinder.fuego.webservice.struts.form.SelectAssetsForm;

/**
 * 
* @ClassName: AssetsRecaptureCreateAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:23:29 
*
 */

public class AssetsRecaptureCreateAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureCreateAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
    private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();
    private TransPlanService planService = ServiceContext.getInstance().getRecapturePlanService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
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
		log.info(LogKeyConst.INPUT_ACTION);
    	String nextPage = PageNameConst.ASSETS_RECAPTURE_ENSURE_INIT_PAGE;
    	
        String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
        
        RecapturePlanBo plan = null;
        if(submitPara.equals(ParameterConst.SUBMIT_PARA_NAME))
        {
        	SelectAssetsForm recaptureSelectForm = (SelectAssetsForm)form;
        	log.info("selected assests" + recaptureSelectForm);

        	List<AssetsInfoBo> assetsList = assetsService.getAssetsByAssetsIDList(recaptureSelectForm.getAssetsIDList());
    		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
    		plan = (RecapturePlanBo) request.getSession().getAttribute(RspBoNameConst.RECAPTURE_PLAN);
    		if(null == plan)
    		{
    			plan = (RecapturePlanBo) planService.createPlan(user.getUserID());

    		}
         	plan.getAssetsPage().setAssetsList(assetsList);
        	planService.validate(plan);
            
        	nextPage = PageNameConst.ASSETS_RECAPTURE_ENSURE_INIT_PAGE;
        }
        else
        {
        	nextPage = PageNameConst.ASSETS_RECAPTURE_INIT_PAGE;
        }
        
        request.getSession().setAttribute(RspBoNameConst.RECAPTURE_PLAN, plan);
         
		return nextPage;
	}

}
