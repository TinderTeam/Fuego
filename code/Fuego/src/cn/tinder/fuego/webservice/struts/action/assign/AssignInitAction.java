/**   
 * @Title: AllocationInitAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.allocation 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-24 下午11:55:04 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assign;

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
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * @ClassName: AllocationInitAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 下午11:55:04
 * 
 */

public class AssignInitAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignInitAction.class);

	private LoadService loadService = ServiceContext.getInstance().getLoadService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String nextPage = PageNameConst.ASSIGN_PAGE;
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

 
		List<String> deptInfoList = loadService.loadDeptInfoByUser(user.getDeptName(),false);
		
		List<String> inDeptInfoList = loadService.loadDeptInfoByUser(null,false);

		 
		
		request.setAttribute(RspBoNameConst.DEPT_INFO_LIST, deptInfoList);
		request.setAttribute(RspBoNameConst.IN_DEPT_INFO_LIST, inDeptInfoList);

 
		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

	}

}
