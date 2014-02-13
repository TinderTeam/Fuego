/**   
 * @Title: AllocationInitAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.allocation 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-24 下午11:55:04 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assets;

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
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;

/**
 * @ClassName: AllocationInitAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 下午11:55:04
 * 
 */

public class SelectAssetsAction extends Action
{
	private static final Log log = LogFactory.getLog(SelectAssetsAction.class);

	private LoadService loadService = ServiceContext.getInstance().getLoadService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String pageName = PageNameConst.ASSIGN_PAGE;

 

		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}

}
