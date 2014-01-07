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
public class AssetsStatusDealHistoryInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsStatusDealHistoryInitAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = PageNameConst.AssetsStatusDealHistory_PAGE;
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);   	

    	List<String> userNameList=null ;
    	List<String> operationTypeList=null ;
    	List<String> assetsTypeList=null;
    	List<String> techStateList=null;
    	
    	//这里需要在LoadService 里加入 两个方法，加载所有管理员账户、加载所有操作类型列表
    	
    	userNameList = loadService.loadGasNameList();	//Fix it
    	
    	operationTypeList = loadService.loadAssetsTechList(); //Fix it
    	assetsTypeList = loadService.loadAssetsTypeList(user.getUserID());
    	techStateList =loadService.loadAssetsTechList();
 
    	request.setAttribute(RspBoNameConst.USER_NAME_LIST,userNameList);
    	request.setAttribute(RspBoNameConst.OPERATION_TYPE_LIST,operationTypeList);
    	request.setAttribute(RspBoNameConst.ASSETS_TYPE_LIST,assetsTypeList);
    	request.setAttribute(RspBoNameConst.TECH_STATE_LIST,techStateList);
    	
    	//Bo
    	List<AssetsStatusDealHistoryInitActionBo> assetsStatusDealHistoryInitAction = null;

    	assetsStatusDealHistoryInitAction = AssetsStatusDealHistoryTest.assetsStatusDealHistory();
    	
    	log.info(assetsStatusDealHistoryInitAction);
    	
    	request.setAttribute(RspBoNameConst.HISTORY_LIST, assetsStatusDealHistoryInitAction);
    	
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
