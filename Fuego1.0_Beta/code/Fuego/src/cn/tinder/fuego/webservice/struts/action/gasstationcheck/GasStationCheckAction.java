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
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.GasStationCheckStatusForm;



/**
 * 
* @ClassName: GasStationCheckAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-4 下午12:23:03 
*
 */
public class GasStationCheckAction extends Action
{
    private static final Log log = LogFactory.getLog(GasStationCheckAction.class);
    
	private TransPlanService planService = ServiceContext.getInstance().getCheckPlanService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"GasStationCheckAction");

    	String nextPage =null;      	
    	
    	GasStationCheckStatusForm gasStationCheckStatusForm = (GasStationCheckStatusForm) form;
    	
    	
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);

		if(null == submitPara)
		{
			log.error("the submit parameter is null");
		}
		
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
 

		
        
       	 if(submitPara.equals(ParameterConst.SUBMIT_1))
         {
       		
       		nextPage= PageNameConst.GAS_STATION_CHECK_STATUS_INIT_PAGE ;
 
       	 }
       	 
       	 if(submitPara.equals(ParameterConst.SUBMIT_2))
         {
            CheckPlanBo checkPlan = (CheckPlanBo) planService.createPlan(user.getUserID());
            request.setAttribute(RspBoNameConst.CHECK_PLAN_DATA, checkPlan);

       		nextPage= PageNameConst.GAS_STATION_CHECK_STATUS_ENSURE_INIT_PAGE ;
       	 }       	 

     
        log.info("[Jump]:NextPage:"+nextPage);
        return mapping.findForward(nextPage);	

    }

}
