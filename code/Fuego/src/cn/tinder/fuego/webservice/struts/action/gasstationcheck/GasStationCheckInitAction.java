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
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;



/**
 * 
* @ClassName: GasStationCheckInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-4 下午07:44:50 
*
 */
public class GasStationCheckInitAction extends Action
{
    private static final Log log = LogFactory.getLog(GasStationCheckInitAction.class);
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"GasStationCheckInitAction");

    	String nextPage =PageNameConst.GAS_STATION_CHECK_PAGE;      	
 
		return mapping.findForward(nextPage);

		
		//log.info("[Info]loginForm:" + gasStationCheckStatusForm+"[Info]submit"+submitPara);
		
    	//Mapping
	

    }

}
