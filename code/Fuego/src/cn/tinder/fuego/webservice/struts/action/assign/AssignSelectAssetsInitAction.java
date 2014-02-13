/**   
 * @Title: AssignSelectAssetsInitAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-1 下午07:05:14 
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

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * @ClassName: AssignSelectAssetsInitAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-1 下午07:05:14
 * 
 */

public class AssignSelectAssetsInitAction extends Action
{
	private static final Log log = LogFactory.getLog(AssignCreateInitAction.class);

	private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String nextPage = PageNameConst.ASSIGN_SELECT_ASSETS_PAGE;

		AssignPlanBo plan = (AssignPlanBo) request.getSession().getAttribute(RspBoNameConst.ASSIGN_PLAN_DATA);
		if (null == plan)
		{
			nextPage = PageNameConst.SYSTEM_ERROR_PAGE;
		} else
		{
			List<AssetsInfoBo> assetsList = assetsService.getAssetsByDutyDept(plan.getTransInfo().getOutDept());
			AssetsPageBo selectAssetsPage = new AssetsPageBo();
			selectAssetsPage.getPage().setAllPageData(assetsList);
			selectAssetsPage.setAssetsList(selectAssetsPage.getPage().getCurrentPageData());
			
			selectAssetsPage.setShowCheckBox(true);
			request.setAttribute(RspBoNameConst.ASSETS_PAGE_DATA, selectAssetsPage);
		}

		log.info(LogKeyConst.NEXT_PAGE + nextPage);
		return mapping.findForward(nextPage);

	}

}
