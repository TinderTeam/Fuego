package cn.tinder.fuego.webservice.struts.action.index;

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
import cn.tinder.fuego.service.TransactionService;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * @author Nan Bowen
 * @function:LoginAction
 */
public class IndexInitAction extends Action
{
	private static final Log log = LogFactory.getLog(IndexInitAction.class);

	private LoadService loadService = ServiceContext.getInstance().getLoadService();
	private TransactionService transactionService =ServiceContext.getInstance().getTransactionService();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
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
		//clear session
		clearSession(request);

		String nextPage = PageNameConst.INDEX_PAGE;

		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

		// RequestOut
		// Bo
		List<MenuTreeBo> menuTreeList = null;
		if (null == user)
		{
			log.error("the user is null");
			nextPage = PageNameConst.LOGIN_PAGE;
		} else
		{
			menuTreeList = loadService.loadMenuTreeByUserID(user.getUserID());
 
			request.getSession().setAttribute(RspBoNameConst.MENU_TREE_LIST, menuTreeList);
			// Init Notice
			List<TransactionBaseInfoBo> infoBoList = null;

			infoBoList = transactionService.getTransListByUser(user.getUserID());
 
			request.setAttribute(RspBoNameConst.TRANS_INFO_LIST, infoBoList);
		}
		return nextPage;
	}
	private void clearSession(HttpServletRequest request)
	{
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);
		SystemUserBo sessionUser = new SystemUserBo(user);
	 
		request.getSession().invalidate();
		
		request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, sessionUser);

		 
	}
	

}
