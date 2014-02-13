package cn.tinder.fuego.webservice.struts.action.search;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.download.AssetsStatuesFile;
import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusSearchInitPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;




/**
 * 
* @ClassName: AssetsStatusSearchAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-10-2 下午01:06:40 
*
 */
public class AssetsStatusSearchAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsStatusSearchAction.class);
    
    /**
     *     SearchStatusSearchService
     */
     private AssetsManageService  assetsManageService = ServiceContext.getInstance().getAssetsManageService();
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
	private String handle(ActionForm form, HttpServletRequest request)
	{
		//PageName
     	String PageName = null;
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);   	

 
     	AssetsFilterForm  searchForm = (AssetsFilterForm) form;
 
    	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);   
 
     	//RequestOut
     	AssetsStatusSearchInitPageBo pageBo = new AssetsStatusSearchInitPageBo(assetsManageService.getAssetsByFilter(user.getUserID(),searchForm,false));
 
     	/*
     	 * Get PageBo by DB.
     	 */
     	if(submitPara.equals(ParameterConst.SUBMIT_PARA_NAME)||submitPara.equals(ParameterConst.PAGECHANGE_PARA_NAME)){
     		log.info("Para is " + submitPara);
     		  
     		log.info("Bo is " + pageBo);
         	request.setAttribute(RspBoNameConst.ASSETS_STATUS_SEARCH_PAGE_BO,pageBo);  
         	
         	String modify = request.getParameter(ParameterConst.SHOW_MODIFY_BTN);
     		if("true".equals(modify))
    		{
             	PageName = PageNameConst.ASSETS_STATUS_SEARCH_INIT_ENABLE_BTN_ACTION;
    		}
     		else
     		{
             	PageName = PageNameConst.ASSETS_STATUS_SEARCH_INIT_DISABLE_BTN_ACTION;
     		}
         	
     		request.getSession().setAttribute(RspBoNameConst.SEARCH_FORM,searchForm);
     		
		}else if(submitPara.equals(ParameterConst.DOWNLOAD_PARA_NAME)){
			/*
			 * download	
			 */
			log.info("Para is " + submitPara);
			
			
			searchForm = (AssetsFilterForm) form;
			

			AssetsPageBo  assetsPageBo= assetsManageService.getAssetsByFilter(user.getUserID(),searchForm,true);
			
			AssetsStatuesFile downfile= new AssetsStatuesFile(assetsPageBo);
			
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE,downfile.getFile().getAbsolutePath()); 
			
			PageName = PageNameConst.DOWNLOAD_ACTION;
		  
		}
     	//pageBo=assetsStatusSearchService.getPageBoByForm(searchForm);     	
     	
     	request.setAttribute(RspBoNameConst.ASSETS_STATUS_SEARCH_PAGE_BO,pageBo);
		return PageName;
	}

}
