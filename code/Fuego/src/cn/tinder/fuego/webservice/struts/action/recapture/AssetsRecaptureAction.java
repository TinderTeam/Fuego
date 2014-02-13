/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.recapture;

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
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.RecaptureForm;

/**
 * 
* @ClassName: AssetsRecaptureAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:23:29 
*
 */

public class AssetsRecaptureAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureAction.class);
    
   //  private ConstService  loadService = ServiceContext.getInstance().get;
    
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
    	
    	RecaptureForm recaptureForm = (RecaptureForm) form;   	
    	request.getSession().setAttribute(RspBoNameConst.RECAPTURE_FORM, recaptureForm);

 		log.info("[Info]AssetsRecoverForm:" + recaptureForm);
		
		nextPage = PageNameConst.ASSETS_RECAPTURE_CREATE_INIT_PAGE;
		return nextPage;
	}

}
