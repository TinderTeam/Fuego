/**   
* @Title: SystemParaSetupAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.systemparasetup 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-4 下午10:33:36 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.systemparasetup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.impl.SystemMaintanceServiceImpl;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.sys.SystemParaSetupBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.SystemParaSetupForm;

/** 
 * @ClassName: SystemParaSetupAction 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-4 下午10:33:36 
 *  
 */
public class SystemParaSetupAction extends Action
{
	/*
	 * By Bowen Nan
	 * 
	 */
    private static final Log log = LogFactory.getLog(SystemParaSetupAction.class);
    //SystemMaintanceService service = ServiceContext.getInstance().getSystemMaintanceService();
    SystemMaintanceServiceImpl serviceImpl=ServiceContext.getInstance().getSystemMaintanceServiceImpl();

     //service待修改 
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"SystemParaSetupAction");
        
		// Page
		String nextPage = null;
		//SystemUserBo gasUser=new SystemUserBo();
		// ActionForm
		SystemParaSetupForm setForm = (SystemParaSetupForm) form;
		//Para
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		SystemParaSetupBo modifyBo=new SystemParaSetupBo();
		log.info("[Info]loginForm:" + setForm+"[Info]submit"+submitPara);
		//新增用户
		
		//修改账户
	    serviceImpl.modifyUserInfo(setForm.getGasaccount(), setForm.getDept2());
		 if(submitPara.equals(ParameterConst.SUBMIT_1))
		 {
		 
       		 try{
       			 
       			/*
       			 * Edit By Bowen Nan
       			 * Issue #50
       			 * 16:17 2013/11/23
       			 * Edit From:
       				serviceImpl.addGasStation(setForm.getGasname(), setForm.getUsername(), setForm.getDept1());
 	 			*/
       			 
       			serviceImpl.addGasStation(setForm.getGasname(), setForm.getGasname(), setForm.getDept1());
       			
       			
  			 	log.info("setup successful");
 	
 			 	nextPage=PageNameConst.SYSTEM_SUCCESS_PAGE ;
 			 	return mapping.findForward(nextPage);
 		 }catch(ServiceException e){
 			    log.warn("PasswordSetup failed.",e);        
	 
				request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
			
				nextPage=PageNameConst.ERROR_PAGE;
 			return mapping.findForward(nextPage);
 		}
		 }
		     

        if(submitPara.equals(ParameterConst.SUBMIT_2))
        {
        	try{
        	String searchDept = serviceImpl.searchUserInfo(setForm.getGasaccount());
        	modifyBo.setOrignDept(searchDept);
        	modifyBo.setCurrentGas(setForm.getGasaccount());
        	
        	nextPage=PageNameConst.SYSTEM_PARA_SETUP_INIT_PAGE ;
        	}catch(ServiceException e){
 			    log.warn("systemParaSearch failed.",e);        
	 
				request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
			
				nextPage=PageNameConst.ERROR_PAGE;
 			return mapping.findForward(nextPage);
 		}
    		log.info("[Input]:buttn:查询");
        }
        if(submitPara.equals(ParameterConst.SUBMIT_3))
        {
        	serviceImpl.deleteUserInfo(setForm.getGasaccount());
        	modifyBo.setCurrentGas(null);
        	modifyBo.setOrignDept(null);
        	nextPage=PageNameConst.SYSTEM_SUCCESS_PAGE ;
        	
    		log.info("[Input]:buttn:删除");
        }
        if(submitPara.equals(ParameterConst.SUBMIT_4))
        {
        	serviceImpl.saveUserInfo(setForm.getGasaccount(), setForm.getDept2());
        	
        	nextPage=PageNameConst.SYSTEM_SUCCESS_PAGE ;
        	
    		log.info("[Input]:buttn:保存");
        }
        
        request.setAttribute("modifyBo",modifyBo); 
        log.info("[Jump]:NextPage:"+nextPage);
        return mapping.findForward(nextPage);	

    }

}
