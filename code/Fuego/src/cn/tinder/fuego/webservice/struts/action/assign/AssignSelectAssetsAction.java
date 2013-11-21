/**   
 * @Title: AssignSelectAssetsAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-1 下午07:04:41 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assign;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.SelectAssetsForm;

/**
 * @ClassName: AssignSelectAssetsAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-1 下午07:04:41
 * 
 */

public class AssignSelectAssetsAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignCreateInitAction.class);
	private TransPlanService planService = ServiceContext.getInstance().getAssignPlanService();

	private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String nextPage = PageNameConst.ASSIGN_CREATE_INIT_ACTION;
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		SelectAssetsForm selectAssetsForm = (SelectAssetsForm)form;

		AssignPlanBo plan = (AssignPlanBo) request.getSession().getAttribute(RspBoNameConst.ASSIGN_PLAN_DATA);

		if (null == plan || null == selectAssetsForm)
		{
 			log.error("parameter is null."+plan + selectAssetsForm + submitPara);
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE;
		} else
		{
			
			if (ParameterConst.CONFIRM_PARA_NAME.equals(submitPara))
			{
				if(null==selectAssetsForm.getAssetsIDList() || selectAssetsForm.getAssetsIDList().length == 0)
				{
					log.warn("Select Assets is Empty!");
					
				}else
				{
					List<AssetsInfoBo> assetsList = assetsService.getAssetsByAssetsIDList(selectAssetsForm.getAssetsIDList());
					plan.getAssetsPage().getAssetsList().addAll(assetsList);
				}
			}
			nextPage = PageNameConst.ASSIGN_CREATE_INIT_ACTION;
			log.info(selectAssetsForm);
 			
 		}

		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

	}
}
