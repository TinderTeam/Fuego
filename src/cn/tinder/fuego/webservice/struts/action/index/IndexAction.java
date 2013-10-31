package cn.tinder.fuego.webservice.struts.action.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Nan Bowen
 * @function:LoginAction
 */
public class IndexAction extends Action
{
	private static final Log log = LogFactory.getLog(IndexAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info("[Action]Get into IndexInit.do");

		// Mapping
		String pageName = "Index";

		log.info("[Jump]:NextPage:" + pageName);
		return mapping.findForward(pageName);

	}

}
