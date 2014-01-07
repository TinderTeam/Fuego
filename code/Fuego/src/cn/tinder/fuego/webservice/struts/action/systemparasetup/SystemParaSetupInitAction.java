/**   
* @Title: SystemParaSetupInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.systemparasetup 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-4 下午10:32:43 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.systemparasetup;

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
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.sys.SystemParaSetupBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/** 
 * @ClassName: SystemParaSetupInitAction 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-4 下午10:32:43 
 *  
 */
public class SystemParaSetupInitAction extends Action
{
	private static final Log log = LogFactory.getLog(SystemParaSetupInitAction.class);
	private LoadService loadService = ServiceContext.getInstance().getLoadService();

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION + "systemParaSetupInit");

		String nextPage = PageNameConst.SYSTEM_PARA_SETUP_PAGE;
	
		// Bo test
		
		SystemParaSetupBo setupBo=new SystemParaSetupBo();
		
		
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

		
		setupBo.setDeptList1(loadService.loadManageDeptList(user.getUserID(),false));
        setupBo.setDeptList2(loadService.loadManageDeptList(user.getUserID(),false));
        setupBo.setGasList(loadService.loadGasNameList());
        

        
		SystemParaSetupBo modifyBo= (SystemParaSetupBo) request.getAttribute("modifyBo");

        if(modifyBo!=null)
        {
        	setupBo.setOrignDept(modifyBo.getOrignDept());
        	setupBo.setCurrentGas(modifyBo.getCurrentGas());
        	log.info("modiffBo:"+modifyBo);
         }
        
        
		request.setAttribute(RspBoNameConst.SETUP_BO, setupBo);
		
		
		log.info("setupBo"+setupBo);
		
		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

	}

}
