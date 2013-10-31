package cn.tinder.fuego.webservice.struts.action.assets;



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
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsModifyForm;



/**
 * 
* @ClassName: AssetsStatusModifyAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:12:43 
*
 */
public class AssetsModifyAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsModifyAction.class);
    private AssetsManageService  assetsManageService = ServiceContext.getInstance().getAssetsManageService();

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
			nextPage = PageNameConst.ERROR_PAGE; 
			request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
    	}
    	catch (Exception e)
		{
			log.error("system error",e);
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE; 
		}
     
 
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	



    }
	private String handle(ActionForm form,HttpServletRequest request)
	{
 
    	String nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE; 
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		AssetsModifyForm assetsForm = (AssetsModifyForm)form;
		if(ParameterConst.SUBMIT_PARA_NAME.equals(submitPara))
		{
	    	assetsManageService.updateAssetsStatus(assetsForm.getAssetsInfo());
		}
		else if(ParameterConst.DELETE_PARA_NAME.equals(submitPara))
		{
			assetsManageService.deleteAssets(assetsForm.getAssetsInfo());
		}
		else if(ParameterConst.BACK_PARA_NAME.equals(submitPara))
		{
			nextPage = PageNameConst.ASSETS_MODIFY_PAGE;
		}
		else if(ParameterConst.CANCEL_PARA_NAME.equals(submitPara))
		{
			nextPage = PageNameConst.INDEX_INIT_ACTION;
		}
     	 
		return nextPage;
	}
 

}
