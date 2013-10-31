package cn.tinder.fuego.webservice.struts.action.discard;



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
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSearchBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.DiscardSearchForm;
import cn.tinder.fuego.webservice.struts.form.DiscardSearchSelectForm;
import cn.tinder.fuego.webservice.struts.form.SelectAssetsForm;



/**
 * 
* @ClassName: DiscardAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:14:32 
*
 */
public class DiscardSearchResultAction extends Action
{
    private static final Log log = LogFactory.getLog(DiscardSearchResultAction.class);
    private TransPlanService planService = ServiceContext.getInstance().getDiscardPlanService();
    private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();
	
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
		String nextPage = null;
    	SelectAssetsForm discardSearchSelectForm = (SelectAssetsForm)form;	
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME); 
    	log.info(LogKeyConst.SUBMIT_VALUE+submitPara);
 

    	DiscardPlanBo plan = null;
    	if(submitPara.equals(ParameterConst.SUBMIT_2))
    	{
    		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
    		plan = (DiscardPlanBo) request.getSession().getAttribute(RspBoNameConst.DISCARD_PLAN_INFO);
    		if(null == plan)
    		{
    			plan = (DiscardPlanBo) planService.createPlan(user.getUserID());

    		}
    		
        	List<AssetsInfoBo> assetsList = assetsService.getAssetsByAssetsIDList(discardSearchSelectForm.getAssetsIDList());
        	plan.getAssetsPage().setAssetsList(assetsList);
        	planService.validate(plan);

			nextPage = PageNameConst.DISCARD_SURE_INIT;
		}
    	

    	
    	if(submitPara.equals(ParameterConst.BACK_PARA_NAME))
    	{
			
			nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
 
		request.getSession().setAttribute(RspBoNameConst.DISCARD_PLAN_INFO, plan);
		return nextPage;
	}

}
