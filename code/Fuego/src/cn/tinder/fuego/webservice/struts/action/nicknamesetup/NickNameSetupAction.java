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
import cn.tinder.fuego.service.impl.SystemMaintanceServiceImpl;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.sys.NickNameSetupPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.form.NickNameSetupForm;

public class NickNameSetupAction extends Action
{
    private static final Log log = LogFactory.getLog(NickNameSetupAction.class);
    SystemMaintanceServiceImpl serviceImpl=ServiceContext.getInstance().getSystemMaintanceServiceImpl();
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"NickNameSetupAction");
        
		// Page
		String nextPage = null;

		// ActionForm
		NickNameSetupForm setForm = (NickNameSetupForm) form;
		//获取按键参数
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		NickNameSetupPageBo nickNameBo= new NickNameSetupPageBo();

		log.info("[Info]loginForm:" + setForm+"[Info]submit"+submitPara);
		
	    //serviceImpl.modifyUserInfo(setForm.getGasaccount(), setForm.getDept2());

		     
         //查询nickname
        if(submitPara.equals(ParameterConst.SUBMIT_2))
        {
        	
        	String oldNickName=serviceImpl.searchUserNickName(setForm.getUserName());

        	nickNameBo.setNickName(oldNickName);
        	nickNameBo.setUserName(setForm.getUserName());
        	log.info("oldNickName :" +oldNickName);
        	nextPage=PageNameConst.NICKNAME_SETUP_INIT_PAGE;
    		log.info("[Input]:buttn:查询");
        }
        if(submitPara.equals(ParameterConst.SUBMIT_3))
        {
        	serviceImpl.deleteUserNickName(setForm.getUserName());
        	//modifyBo.setCurrentGas(null);
        	//modifyBo.setOrignDept(null);
        	nextPage=PageNameConst.SYSTEM_SUCCESS_PAGE ;
        	
    		log.info("[Input]:buttn:删除");
        }
        if(submitPara.equals(ParameterConst.SUBMIT_4))
        {
        	//serviceImpl.saveUserInfo(setForm.getGasaccount(), setForm.getDept2());
        	serviceImpl.saveUserNickName(setForm.getUserName(), setForm.getNickName());
        	nextPage=PageNameConst.SYSTEM_SUCCESS_PAGE ;
        	
    		log.info("[Input]:buttn:保存");
        }
        
        request.setAttribute("nickNameBo",nickNameBo); 
        
        log.info("[Jump]:NextPage:"+nextPage);
        return mapping.findForward(nextPage);	

    }
    
}
