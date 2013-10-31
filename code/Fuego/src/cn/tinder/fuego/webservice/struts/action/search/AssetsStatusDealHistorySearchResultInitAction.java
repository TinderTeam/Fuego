package cn.tinder.fuego.webservice.struts.action.search;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ConstServiceTest;
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.action.index.IndexInitActionTest;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.base.UserNoticeBo;
import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusDealHistoryInitActionBo;
import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusDealHistorySearchResultBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: AssetsStatusDealHistorySerarchResultInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:54:52 
*
 */
public class AssetsStatusDealHistorySearchResultInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsStatusDealHistorySearchResultInitAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = PageNameConst.AssetsStatusDealHistorySearchResult_PAGE;
    	
    	//Bo
    	List<AssetsStatusDealHistorySearchResultBo> adHistoryResult = null;

    	adHistoryResult = AssetsStatusDealHistorySearchResultTest.assetsStatusDealHistorySearchResult();
    	
    	//log.info(assetsStatusDealHistoryInitAction);
    	
    	request.setAttribute(RspBoNameConst.HISTORYRESULT_LIST, adHistoryResult);
    	
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
