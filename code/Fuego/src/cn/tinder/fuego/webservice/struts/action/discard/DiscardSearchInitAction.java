package cn.tinder.fuego.webservice.struts.action.discard;



import java.util.ArrayList;
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
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSearchBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;



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
        log.info(LogKeyConst.INPUT_ACTION);
        
    	String nextPage = null;
    	try
    	{
    		nextPage = handle(form,request);
		} 
    	catch(ServiceException e)
    	{
    		log.warn("opration failed",e);
			request.setAttribute(RspBoNameConst.OPERATE_EXCEPION, e.getMessage());
			nextPage = PageNameConst.ERROR_PAGE; 
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
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		log.info(user);
    	String nextPage = PageNameConst.DISCARD_SEARCH;
    	
     	DiscardSearchBo discardSearchBo = new DiscardSearchBo();
    	List<String> assetsTypeList = loadService.loadAssetsTypeList(user.getUserID());
    	List<String> assetsStatusList = new ArrayList<String>();
    	
    	assetsStatusList.add(AssetsConst.ASSETS_FITER_ALL);
    	assetsStatusList.addAll(loadService.loadAssetsTechList());

    	discardSearchBo.setDate(DateService.DateToString(new Date(System.currentTimeMillis())));
    	discardSearchBo.setAssetsTypeList(assetsTypeList);
    	discardSearchBo.setTechStatusList(assetsStatusList);
    	request.setAttribute(RspBoNameConst.DISCARD_SEARCH_BO,discardSearchBo);
    	
    	
		List<String> deptList = loadService.loadDeptInfoByUser(user.getUserID(),true);

    	List<String> manageList = loadService.loadManageDeptList(user.getUserID(),true);

      	
    	
    	request.setAttribute(RspBoNameConst.DEPT_INFO_LIST,deptList);//DeptList
    	request.setAttribute(RspBoNameConst.MANAGE_DEPT_LIST,manageList);//manage dept list

    	AssetsFilterForm filterForm = (AssetsFilterForm) request.getAttribute(RspBoNameConst.DISCARD_SEARCH_FORM);
    	
    	request.setAttribute(RspBoNameConst.SEARCH_FORM, filterForm);    	

		AssetsPageBo selectAssetsPage = new AssetsPageBo();
    	if(null == filterForm )
    	{
    		filterForm = new AssetsFilterForm();
    	}
    	else
    	{
    		selectAssetsPage = assetsService.getAssetsByFilter(user.getUserID(),filterForm, false);
        }
 		selectAssetsPage.setShowCheckBox(true);

		request.setAttribute(RspBoNameConst.ASSETS_PAGE_DATA,selectAssetsPage);
        
		return nextPage;
	}


}
