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
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.check.AssetsCheckBo;
import cn.tinder.fuego.webservice.struts.bo.check.GasStationCheckInitBo;
import cn.tinder.fuego.webservice.struts.bo.check.GasStationCheckStatusBo;
import cn.tinder.fuego.webservice.struts.bo.check.GasStationCheckStatusInitBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.GasStationCheckStatusForm;
import cn.tinder.fuego.webservice.struts.form.UserPasswordSetupForm;



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
    	
    	GasStationCheckStatusForm gasStationCheckStatusForm = (GasStationCheckStatusForm) form;
    	
// Bo
		
    	GasStationCheckInitBo checkInitBo=new GasStationCheckInitBo();
    	
    	List<String> monthList =null ;
    	
    	monthList = ConstServiceTest.getMonthList();
    	checkInitBo.setMonthList(monthList) ;
    	checkInitBo.setTransID("201309");
    	
    	AssetsCheckBo bo= new AssetsCheckBo();
    	      
        bo.setDept("Dept");
        bo.setGasName("gasName");

        checkInitBo.getCheckList().add(bo);
        request.setAttribute(RspBoNameConst.CHECK_INIT_BO, checkInitBo);
		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

		
		//log.info("[Info]loginForm:" + gasStationCheckStatusForm+"[Info]submit"+submitPara);
		
    	//Mapping
	

    }

}
