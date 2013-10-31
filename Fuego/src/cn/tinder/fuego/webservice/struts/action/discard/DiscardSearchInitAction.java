package cn.tinder.fuego.webservice.struts.action.discard;



import java.util.Arrays;
import java.util.Date;
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
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSearchBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.DiscardSearchForm;



/**
 * 
* @ClassName: DiscardInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:14:55 
*
 */
public class DiscardSearchInitAction extends Action
{
    private static final Log log = LogFactory.getLog(DiscardSearchInitAction.class);
    
	private TransPlanService planService = ServiceContext.getInstance().getDiscardPlanService();
	private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();

	private LoadService loadService = ServiceContext.getInstance().getLoadService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"DiscardInitAction");
    
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		log.info(user);
    	String nextPage = PageNameConst.DISCARD_SEARCH;
    	
     	DiscardSearchBo discardSearchBo = new DiscardSearchBo();
    	List<String> assetsTypeList = loadService.loadAssetsTypeList();
    	List<String> assetsStatusList = loadService.loadAssetsTechList();

    	discardSearchBo.setDate(DateService.DateToString(new Date(System.currentTimeMillis())));
    	discardSearchBo.setAssetsTypeList(assetsTypeList);
    	discardSearchBo.setTechStatusList(assetsStatusList);
    	request.setAttribute(RspBoNameConst.DISCARD_SEARCH_BO,discardSearchBo);
    	    	
    	//discardSearchBo = DiscardSearchTest.discardSearch();
    	
    	//For displaying
    				
    	DiscardSearchForm searchForm = (DiscardSearchForm) request.getAttribute(RspBoNameConst.DISCARD_SEARCH_FORM);
    	List<AssetsInfoBo> assetsList = null;
    	if(null == searchForm )
    	{
    		//assetsList = assetsService.getDiscardAssetsListBo(discardSearchBo.getDate(), null, null);
    	}
    	else
    	{
    		assetsList = assetsService.getDiscardAssetsListBo(searchForm.getDate(), Arrays.asList(searchForm.getAssetsTypeList()), Arrays.asList(searchForm.getTechStatusList())); 
        }
		AssetsPageBo selectAssetsPage = new AssetsPageBo();

		selectAssetsPage.setAssetsList(assetsList);
		selectAssetsPage.setShowCheckBox(true);
	

		request.setAttribute(RspBoNameConst.ASSETS_PAGE_DATA,selectAssetsPage);
        
    	log.info(LogKeyConst.NEXT_PAGE+nextPage);
        return mapping.findForward(nextPage);	

    }
 

}
