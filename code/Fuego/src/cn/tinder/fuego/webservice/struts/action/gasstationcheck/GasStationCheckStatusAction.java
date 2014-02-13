package cn.tinder.fuego.webservice.struts.action.gasstationcheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
 * @ClassName: GasStationCheckStatusAction
 * @Description: TODO
 * @author Kong Jingliang
 * @date 2013-10-1 下午07:07:51
 * 
 */
public class GasStationCheckStatusAction extends Action
{
	private static final Log log = LogFactory.getLog(GasStationCheckStatusAction.class);
	private TransPlanService planService = ServiceContext.getInstance().getCheckPlanService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
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
		String nextPage = PageNameConst.INDEX_INIT_ACTION;
		
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);

		CheckPlanBo plan;
		String transID = (String) request.getSession().getAttribute(RspBoNameConst.CHECK_TRANS_ID);
		
		if(submitPara.equals(ParameterConst.DOWNLOAD_PARA_NAME))
        {
     		/*
     		 * 2.Download File
     		 */
	        plan = (CheckPlanBo) planService.getPlanByTransID(transID);

     		request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE,planService.getExportFile(plan).getAbsolutePath()); 
			
     		nextPage = PageNameConst.DOWNLOAD_ACTION;
         }
		else if(submitPara.equals(ParameterConst.FINISH_PARA_NAME))
		{
			planService.forwardNext(transID);
		}
		 
		return nextPage;
	}
	
}
