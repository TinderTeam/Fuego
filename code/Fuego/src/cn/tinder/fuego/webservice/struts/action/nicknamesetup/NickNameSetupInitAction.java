package cn.tinder.fuego.webservice.struts.action.nicknamesetup;

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
import cn.tinder.fuego.webservice.struts.bo.sys.NickNameSetupPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

public class NickNameSetupInitAction extends Action
{
	private static final Log log = LogFactory.getLog(NickNameSetupInitAction.class);
    
	   private ConstService constService = ServiceContext.getInstance().getConstService();

		@Override
	    public ActionForward execute(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception
	    {
	    
	    	log.info(LogKeyConst.INPUT_ACTION+"NickNameSetupInitAction");
	        
	    	String nextPage =  	PageNameConst.NICKNAME_SETUP_PAGE;
	  
	    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
	        
	    	//PageBo
	    	NickNameSetupPageBo nickNameBo= new NickNameSetupPageBo();
	    	

	    	if(null == user)
	    	{
	    		log.error("the user is null");
	    		nextPage = PageNameConst.LOGIN_PAGE;
	    	}
	    	
	    	//Build Bo
	    	nickNameBo.setUserList(constService.getAllUserList());
	       	
	    	request.setAttribute(RspBoNameConst.USER_LIST,nickNameBo.getUserList());

	        log.info(LogKeyConst.NEXT_PAGE+nextPage);
	        return mapping.findForward(nextPage);	

	    }
	 

}
