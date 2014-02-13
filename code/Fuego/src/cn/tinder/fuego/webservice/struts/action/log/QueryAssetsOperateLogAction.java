package cn.tinder.fuego.webservice.struts.action.log;



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
import cn.tinder.fuego.service.OperateLogService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.engine.computer.ComputeService;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.log.AssetsOperateLogBo;
import cn.tinder.fuego.webservice.struts.bo.page.PageModelBo;
import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusSearchInitPageBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;
import cn.tinder.fuego.webservice.struts.form.log.OperateLogFilterForm;



/**
 * 
* @ClassName: AssetsStatusSearchInitAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-10-2 上午10:50:24 
*
 */
public class QueryAssetsOperateLogAction extends Action
{
    private static final Log log = LogFactory.getLog(QueryAssetsOperateLogAction.class);
    
    OperateLogService logService = ServiceContext.getInstance().getOperateLogService();
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
    	String PageName = PageNameConst.ASSETS_OPERATE_LOG_PAGE;
    
  
     	OperateLogFilterForm filter = (OperateLogFilterForm)form;
 
     	if(null == filter)
     	{
     		filter = new OperateLogFilterForm();
     	}
		 
     	PageModelBo<AssetsOperateLogBo> operateLogPage = logService.getAssetsOperateLog(filter);
	 
		List<String> operateNameList = logService.getAllOperateName();
		operateNameList.add(0, AssetsConst.ASSETS_FITER_ALL);
		request.setAttribute(RspBoNameConst.OPERATE_NAME_LIST,operateNameList);//PageList

		request.setAttribute(RspBoNameConst.SEARCH_FORM,filter);//PageList
		
		request.setAttribute(RspBoNameConst.OPERATE_LOG_PAGE_DATA, operateLogPage);
		
		return PageName;
	}
 

}
