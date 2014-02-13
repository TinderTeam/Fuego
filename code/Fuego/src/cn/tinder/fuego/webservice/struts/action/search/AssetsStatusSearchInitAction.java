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

import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.engine.computer.ComputeService;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusSearchInitPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;



/**
 * 
* @ClassName: AssetsStatusSearchInitAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-10-2 上午10:50:24 
*
 */
public class AssetsStatusSearchInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsStatusSearchInitAction.class);
    
   /**
    *     SearchStatusSearchService
    */
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


	private String handle(ActionForm form, HttpServletRequest request)
	{
		//Para
    	String submitPara = request.getParameter(ParameterConst.PAGE_PARA);   
 
        //PageName
    	String PageName = PageNameConst.ASSETS_STATUS_SEARCH_PAGE;
    
     	AssetsStatusSearchInitPageBo pageBo = (AssetsStatusSearchInitPageBo) request.getAttribute(RspBoNameConst.ASSETS_STATUS_SEARCH_PAGE_BO);
 
 
		String modify = request.getParameter(ParameterConst.SHOW_MODIFY_BTN);
		log.info("modify is"+modify);
    	
    	if(pageBo==null){
    		pageBo=new AssetsStatusSearchInitPageBo();
    		request.getSession().setAttribute(RspBoNameConst.ASSETS_PAGE_DATA,pageBo.getPageBo());//TechList
        	
    	}else{
    		
    		if("true".equals(modify))
    		{
            	pageBo.getPageBo().setShowModifyBtn(true);
    		}
    		
    		pageBo.setPageBo(ComputeService.cptShowValueAsList(pageBo.getPageBo()));
    		pageBo.getPageBo().setShowCurrentValue(true);
    		/*
    			 * Edit By Bowen Nan
    			 * Issue #42
    			 * 17:02 2013/11/23
    			 * 
    		*/
    		request.getSession().setAttribute(RspBoNameConst.ASSETS_PAGE_DATA,pageBo.getPageBo());//TechList
    	}
    	
    	log.info("PageBo="+pageBo.getPageBo().getPage());

    	//if the user is gas station,he can just get his own assets (Edit By Bowen 	17:02 2013/11/23)
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);   	
		List<String> deptList = loadService.loadDeptInfoByUser(user.getUserID(),true);
 
    	request.setAttribute(RspBoNameConst.DEPT_INFO_LIST,deptList);//DeptList
    	request.setAttribute(RspBoNameConst.TYPE_LIST,loadService.loadAssetsTypeList(user.getUserID()));//TypeList
    	request.setAttribute(RspBoNameConst.TECH_LIST,loadService.loadAssetsTechList());//TechList
    	List<String> manageList = loadService.loadManageDeptList(user.getUserID(),true);
 
     	request.setAttribute(RspBoNameConst.MANAGE_DEPT_LIST,manageList);//DeptList

		request.setAttribute(ParameterConst.SHOW_MODIFY_BTN,modify);
     	
		
		//回填
		AssetsFilterForm searchForm=(AssetsFilterForm) request.getSession().getAttribute(RspBoNameConst.SEARCH_FORM);
		if(searchForm==null)
		{
			searchForm = new AssetsFilterForm();
			searchForm.setDuty(deptList.get(0));
		}
		request.setAttribute(RspBoNameConst.SEARCH_FORM,searchForm);//PageList
		return PageName;
	}
 

}
