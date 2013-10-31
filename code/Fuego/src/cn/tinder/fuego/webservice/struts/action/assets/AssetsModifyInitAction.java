package cn.tinder.fuego.webservice.struts.action.assets;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: AssetsStatusModifyInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午07:12:53 
*
 */
public class AssetsModifyInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsModifyInitAction.class);
    private AssetsManageService  assetsManageService = ServiceContext.getInstance().getAssetsManageService();

	private LoadService loadService = ServiceContext.getInstance().getLoadService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        log.info(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = null;
    	try
    	{
    		nextPage = handle(form,request);
		} 
    	catch(ServiceException e)
    	{
    		log.warn("opration failed",e);
			nextPage = PageNameConst.ERROR_PAGE; 
			request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
    	}
    	catch (Exception e)
		{
			log.error("system error",e);
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE; 
		}
     
 
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	



    }
	private String handle(ActionForm form,HttpServletRequest request)
	{
 
    	String nextPage = PageNameConst.ASSETS_STATUS_MODIFY_PAGE;
    	String assetsID = request.getParameter("assetsID");   
    	 
    	AssetsInfoBo assets = assetsManageService.getAssetsByAssetID(assetsID);
    	
    	List<String> assetsTypeList = loadService.loadAssetsTypeList();
    	List<String> assetsStatusList = loadService.loadAssetsTechList();
    	List<String> assetsSrcList = loadService.loadAssetsSrcList();
    	
    	request.setAttribute(RspBoNameConst.ASSETS_INFO_DATA, assets);
    	request.setAttribute(RspBoNameConst.TYPE_LIST, assetsTypeList);
    	request.setAttribute(RspBoNameConst.TECH_LIST, assetsStatusList);
    	request.setAttribute(RspBoNameConst.ASSETS_SRC_LIST, assetsSrcList);


    	 
		return nextPage;
	}
 

}
