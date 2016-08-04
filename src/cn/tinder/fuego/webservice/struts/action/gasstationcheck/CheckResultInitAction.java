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
import cn.tinder.fuego.webservice.struts.bo.check.CheckResultInitBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;



/**
 * 
* @ClassName: CheckResultInitAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-5 上午01:45:12 
*
 */
public class CheckResultInitAction extends Action
{
    private static final Log log = LogFactory.getLog(CheckResultInitAction.class);
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"CheckResultInitAction");

    	String nextPage =PageNameConst.CHECK_RESULT_PAGE;      	
    	
    	//CheckResultForm checkResultForm = (CheckResultForm) form;
    	
 
    	// Bo
		/**
		 * 通过getcase()方法获得内容给总BO                                               
		 */
    	CheckResultInitBo checkResultBo=CheckResultInitBoTest.getCase();
    	
    	log.warn(checkResultBo);
    	
    	//log.info(LogKeyConst.INPUT_ACTION+checkBo.getGasStation()+checkBo.getDeptName()+checkBo.getAssetsType());
    	
    	
    	request.setAttribute(RspBoNameConst.CHECK_RESULT_BO, checkResultBo);
    	/**
    	
    	
    	monthList = ConstServiceTest.getMonthList();
    	checkInitBo.setMonthList(monthList) ;
    	checkInitBo.setTransID("201309");
    	
    	AssetsCheckBo bo= new AssetsCheckBo();
    	      
        bo.setDept("Dept");
        bo.setGasName("gasName");

        checkInitBo.getCheckList().add(bo);
        request.setAttribute(RspBoNameConst.CHECK_INIT_BO, checkInitBo);
        */
        
		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

		
		//log.info("[Info]loginForm:" + gasStationCheckStatusForm+"[Info]submit"+submitPara);
		
    	//Mapping
	

    }

}
