package cn.tinder.fuego.webservice.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.LoginService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.LoginForm;

/**
 * @author Nan Bowen
 * @function:LoginAction
 */
public class LoginAction extends Action
{
	private static final Log log = LogFactory.getLog(LoginAction.class);

	LoginService loginService = ServiceContext.getInstance().getLoginService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.debug(LogKeyConst.INPUT_ACTION);

		// Page
		String pageName = PageNameConst.INDEX_INIT_ACTION;
		// ActionForm
		LoginForm loginForm = (LoginForm) form;
		// Para
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);

		log.info("[Info]Login.do Information:+loginForm.toString():submit=" + submitPara);
		// Beans
		SystemUserBo systemUser = null;

		// Submit is null Error
		if (submitPara == null)
		{
			log.error("submit is null");
			return mapping.findForward(PageNameConst.SYSTEM_ERROR_PAGE);
		}

		// Select Submit
		if (submitPara.equals(ParameterConst.BUTTON_LOGIN_LOGIN) || submitPara.equals(ParameterConst.OPERATE_ENTER))
		{
			try
			{
				systemUser = loginService.login(loginForm);

				// Request : User
				request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, systemUser);

				pageName = PageNameConst.INDEX_INIT_ACTION;

			} catch (ServiceException e)
			{
				// Login Fault
				log.warn("login failed.", e);
				request.setAttribute(RspBoNameConst.LOGIN_EXCEPTION, e.toString());
				pageName = PageNameConst.LOGIN_PAGE;
			}
		}
		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}

}
