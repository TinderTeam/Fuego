/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.recapture;

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
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: AssetsRecaptureApprovalAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:23:29 
*
 */

public class AssetsRecaptureApprovalAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureApprovalAction.class);
    
    private LoadService  loadService = ServiceContext.getInstance().getLoadService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION);
    	String nextPage = null;
    	
    	//get department information list
        List<DeptInfoBo> deptInfoList = loadService.loadAllDeptInfo();
        
        request.setAttribute(RspBoNameConst.DEPT_INFO_LIST, deptInfoList);
 
        log.info(LogKeyConst.NEXT_PAGE+nextPage);
       
        return mapping.findForward(nextPage);	

    }

}
