package cn.tinder.fuego.webservice.struts.action.userpasswordsetup;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.LoginService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.LoginForm;
import cn.tinder.fuego.webservice.struts.form.UserPasswordSetupForm;



/**
 * 
* @ClassName: UserPasswordSetupAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:15:46 
*
 */
public class UserPasswordSetupAction extends Action
{
	
	
    private static final Log log = LogFactory.getLog(UserPasswordSetupAction.class);
    private SystemParaService systemParaService = ServiceContext.getInstance().getSystemParaService();
   
       
      @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"UserPasswordSetupAction");
    	//获取系统用户信息
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		// Page
		String nextpage = null;
		// ActionForm
		UserPasswordSetupForm passwordForm = (UserPasswordSetupForm) form;
		//Para
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);

		log.info("[Info]loginForm:" + passwordForm+"[Info]submit"+submitPara);
		// Submit is null Error
		if(null==submitPara)
         {
        	 log.error("submit is null");
        	 return mapping.findForward(PageNameConst.SYSTEM_ERROR_PAGE);
         }
		
         else
         {
        	 if(submitPara.equals(ParameterConst.SUBMIT_PARA_NAME)
        			 || submitPara.equals(ParameterConst.OPERATE_ENTER))
        	 {
        		 try{
        			 	systemParaService.setPassword(passwordForm, user);
        			 	user.setPassword(passwordForm.getNewpassword2());
        			 	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,user);
        			 	log.info("setup successful");
        			 	request.setAttribute(RspBoNameConst.LOGIN_EXCEPTION,"密码修改成功，请重新登录！");
        			 	nextpage= PageNameConst.LOGIN_PAGE ;
        			 	return mapping.findForward(nextpage);
        		 }catch(ServiceException e){
        			 log.warn("PasswordSetup failed.",e);        
        			 
					request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
        			
        			 nextpage=PageNameConst.ERROR_PAGE;
        			return mapping.findForward(nextpage);
        		}
        	 }
         }
 
     
        log.info("[Jump]:NextPage:"+nextpage);
        return mapping.findForward(nextpage);	

    }

}
