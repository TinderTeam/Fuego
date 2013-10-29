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
import cn.tinder.fuego.service.util.ConstService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.sys.PasswordResetupInitPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: PasswordResetupInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:19:20 
*
 */
public class PasswordResetupInitAction extends Action
{
    
	private static final Log log = LogFactory.getLog(PasswordResetupInitAction.class);
    
   private ConstService constService = ServiceContext.getInstance().getConstService();

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    
    	log.info(LogKeyConst.INPUT_ACTION+"PasswordResetupInitAction");
        
    	String nextPage =  	PageNameConst.PASSWORD_RESETUP;
  
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
        
    	//PageBo
    	PasswordResetupInitPageBo pageBo = new PasswordResetupInitPageBo();
    	

    	if(null == user)
    	{
    		log.error("the user is null");
    		nextPage = PageNameConst.LOGIN_PAGE;
    	}
    	
    	//Build Bo
    	
    	pageBo.setUserList(constService.getAllUserList());
    	
    	
    	request.setAttribute(RspBoNameConst.USER_LIST,pageBo.getUserList());

        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
