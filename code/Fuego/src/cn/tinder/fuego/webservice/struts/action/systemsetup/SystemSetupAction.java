package cn.tinder.fuego.webservice.struts.action.systemsetup;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * 
* @ClassName: SystemSetupAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:14:26 
*
 */
public class SystemSetupAction extends Action
{
    private static final Log log = LogFactory.getLog(SystemSetupAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info("[Action]Get into IndexInit.do");
        
        
        
    	//Mapping
    	String pageName ="Index";
        
 
     
        log.info("[Jump]:NextPage:"+pageName);
        return mapping.findForward(pageName);	

    }

}
