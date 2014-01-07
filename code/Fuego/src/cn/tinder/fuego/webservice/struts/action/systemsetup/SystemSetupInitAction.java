package cn.tinder.fuego.webservice.struts.action.systemsetup;



import java.util.List;

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
import cn.tinder.fuego.webservice.struts.action.index.IndexInitActionTest;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: SystemSetupInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:14:35 
*
 */
public class SystemSetupInitAction extends Action
{
    private static final Log log = LogFactory.getLog(SystemSetupInitAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.debug(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = PageNameConst.INDEX_PAGE;
  
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
        
    	

    
        return mapping.findForward(nextPage);	

    }
 

}
