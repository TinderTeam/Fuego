/**   
 * @Title: AllocationInitAction.java 
 * @Package cn.tinder.fuego.webservice.struts.action.allocation 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-24 下午11:55:04 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.action.assets;

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
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * @ClassName: AllocationInitAction
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 下午11:55:04
 * 
 */

public class SelectAssetsInitAction extends Action
{
	private static final Log log = LogFactory.getLog(SelectAssetsInitAction.class);

	private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		log.info(LogKeyConst.INPUT_ACTION);
		String pageName = PageNameConst.SELECT_ASSETS_PAGE;

		// get department information list
 		AssetsPageBo selectAssetsPage = new AssetsPageBo();
 

		log.info(LogKeyConst.NEXT_PAGE + pageName);
		return mapping.findForward(pageName);

	}

}
