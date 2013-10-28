package cn.tinder.fuego.webservice.struts.action.gasstationcheck;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
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

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION + "GasStationCheckStatusAction");

		// Mapping
		
			
			String nextPage = null;

 
			
			String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
			
			log.info( "[Info]submit" + submitPara);
           if(submitPara.equals(ParameterConst.SUBMIT_1))
        	{
        	   nextPage=PageNameConst.GAS_STATION_CHECK_INIT_PAGE;
        	   
        	}
           if(submitPara.equals(ParameterConst.SUBMIT_2))
           {
        	   nextPage=PageNameConst.SYSTEM_SUCCESS_PAGE;
           }
			//request.setAttribute(RspBoNameConst.PASSWORD_SETUP_EXCEPTION, exceptionStr);
			log.info(LogKeyConst.NEXT_PAGE + nextPage);
			// nextPage=PageNameConst.GAS_STATION_CHECK_STATUS_ENSURE_PAGE;		   
	
	return mapping.findForward(nextPage);
	}
	
}
