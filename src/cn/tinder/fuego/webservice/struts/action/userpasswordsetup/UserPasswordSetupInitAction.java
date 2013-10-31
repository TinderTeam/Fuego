package cn.tinder.fuego.webservice.struts.action.userpasswordsetup;



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
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: UserPasswordSetupInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:15:56 
*
 */
public class UserPasswordSetupInitAction extends Action
{
    private static final Log log = LogFactory.getLog(UserPasswordSetupInitAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"PasswordResetupInitAction");
        
    	String nextPage = PageNameConst.USER_PASSWORD_SETUP_PAGE;
  
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
        String exceptionStr = (String)request.getAttribute(RspBoNameConst.PASSWORD_SETUP_EXCEPTION);
    	
        log.info(exceptionStr);
        //RequestOut
    	//Bo

    	if(null == user)
    	{
    		log.error("the user is null");
    		nextPage = PageNameConst.LOGIN_PAGE;
    	}
    	
    	request.setAttribute(RspBoNameConst.PASSWORD_SETUP_EXCEPTION,exceptionStr);
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
