package cn.tinder.fuego.webservice.struts.action.gasstationcheck;

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
import cn.tinder.fuego.service.GasStationCheckService;
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.action.index.IndexInitActionTest;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.base.UserNoticeBo;
import cn.tinder.fuego.webservice.struts.bo.check.GasStationCheckStatusBo;
import cn.tinder.fuego.webservice.struts.bo.check.GasStationCheckStatusInitBo;

import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: GasStationCheckStatusInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-4 下午07:43:53 
*
 */
public class GasStationCheckStatusInitAction extends Action
{
	private static final Log log = LogFactory.getLog(GasStationCheckStatusInitAction.class);

	private GasStationCheckService gasStationCheckStatusService = ServiceContext.getInstance().getGasStationCheckService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception
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
    	String nextPage = PageNameConst.GAS_STATION_CHECK_STATUS_PAGE;

		return nextPage;
	}

	

}
