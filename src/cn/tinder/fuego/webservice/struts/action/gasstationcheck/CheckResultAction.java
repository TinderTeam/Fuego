package cn.tinder.fuego.webservice.struts.action.gasstationcheck;



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
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.CheckResultForm;
import cn.tinder.fuego.webservice.struts.form.GasStationCheckStatusForm;
import cn.tinder.fuego.webservice.struts.form.UserPasswordSetupForm;



/**
 * 
* @ClassName: CheckResultAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-5 上午03:18:32 
*
 */
public class CheckResultAction extends Action
{
    private static final Log log = LogFactory.getLog(CheckResultAction.class);
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"CheckResultAction");

    	String nextPage = null;      	
    	
    	CheckResultForm checkResultForm = (CheckResultForm) form;
    	
    	
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);

		//String s = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");		
		
		log.info("[Info]loginForm:" + checkResultForm+"[Info]submit"+submitPara); 

		//log.info( "[Info]submit" + submitPara);
		
        
       	 if(submitPara.equals(ParameterConst.SUBMIT_1))
         {
       		
       		nextPage= PageNameConst.CHECK_RESULT_INIT_PAGE ;
 
       	 }
       	 
       	 if(submitPara.equals(ParameterConst.SUBMIT_2))
         {
       		
       		nextPage= PageNameConst.SYSTEM_SUCCESS_PAGE ;
 
       	 }       
       	 
       	 if(submitPara.equals(ParameterConst.SUBMIT_3))
         {
       		
       		nextPage= PageNameConst.SYSTEM_SUCCESS_PAGE ;
 
       	 }   
       	 
       	 
       	 if(submitPara.equals(ParameterConst.SUBMIT_4))
         {
       		
       		nextPage= PageNameConst.CHECK_RESULT_INIT_PAGE ;
 
       	 }        	 
       	 
		//log.info("[Info]loginForm:" + gasStationCheckStatusForm+"[Info]submit"+submitPara);
		
    	//Mapping
        
        
 
     
        log.info("[Jump]:NextPage:"+nextPage);
        return mapping.findForward(nextPage);	

    }

}
