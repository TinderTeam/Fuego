package cn.tinder.fuego.webservice.struts.action.search;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.webservice.struts.constant.PageNameConst;



/**
 * 
* @ClassName: AssetsStatusDealHistorySerarchResultAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:55:03 
*
 */

public class AssetsStatusDealHistorySearchResultAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsStatusDealHistorySearchResultAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info("[Action]Get into IndexInit.do");
        
    	String nextPage = null;
    	
    	
        
    	
        
 
     
        log.info("[Jump]:NextPage:"+nextPage);
        return mapping.findForward(nextPage);	

    }

}
