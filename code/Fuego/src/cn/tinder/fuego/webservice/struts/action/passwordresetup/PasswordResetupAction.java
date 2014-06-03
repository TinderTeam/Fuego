package cn.tinder.fuego.webservice.struts.action.passwordresetup;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.cache.CacheContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.PasswordResetupForm;



/**
 * 
* @ClassName: PasswordResetupAction 
* @Description: TODO
* @author Zhu Liucao
* @date 2013-10-7 上午12:56:31 
*
 */
public class PasswordResetupAction extends Action
{
    private static final Log log = LogFactory.getLog(PasswordResetupAction.class);
    
    private SystemParaService systemParaService = ServiceContext.getInstance().getSystemParaService();
   
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"PasswordResetupAction");
    	
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
    	//Mapping
    	String nextpage =PageNameConst.SYSTEM_SUCCESS_PAGE;
    	//button catch
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
    	// ActionForm
    	PasswordResetupForm pdForm = (PasswordResetupForm)form;   
    	
		if(null==submitPara)
        {
       	 log.error("submit is null");
       	 return mapping.findForward(PageNameConst.SYSTEM_ERROR_PAGE);
        }
		else if(submitPara.equals(ParameterConst.SUBMIT_1))
		{
   		  try{
			 	systemParaService.resetPassword(pdForm, user);
			 	CacheContext.reCatchInstance();
			 	log.info("Resetup successful");
			 	//request.setAttribute(RspBoNameConst.LOGIN_EXCEPTION,"密码重置成功，请重新登录！");
			 	nextpage= PageNameConst.SYSTEM_SUCCESS_PAGE ;
			 	return mapping.findForward(nextpage);
		    }catch(ServiceException e){
			    log.warn("PasswordResetup failed.",e);        
			    request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
			
			     nextpage=PageNameConst.ERROR_PAGE;
			    return mapping.findForward(nextpage);
		   }
		}
		
        log.info("[Jump]:NextPage:"+ nextpage);
        return mapping.findForward(nextpage);	

    }

}
