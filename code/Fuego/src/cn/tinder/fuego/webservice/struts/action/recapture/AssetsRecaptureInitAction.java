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

import cn.tinder.fuego.service.AssetsManageService;

import cn.tinder.fuego.service.ConstServiceTest;
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.recapture.*;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsSelectForm;

/**
 * 
* @ClassName: AssetsRecaptureInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-1 下午05:33:18 
*
 */

public class AssetsRecaptureInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureInitAction.class);
	private TransPlanService planService = ServiceContext.getInstance().getRecapturePlanService();
	private LoadService loadService = ServiceContext.getInstance().getLoadService();

   // private AssetsRecoverService  assetsRecoverService = ServiceContext.getInstance().getAssetsRecoverService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"AssetsRecaptureInitAction");
    	
    	
    	String pageName = PageNameConst.ASSETS_RECAPTURE_PAGE;
    	
    	SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(RspBoNameConst.SYSTEM_USER);

		RecaptureSearchBo recaptureSearch = new RecaptureSearchBo();
		
 		
		//get gasName information list
		List<String> gasNameList = loadService.loadGasNameList();
    	List<String> assetsTypeList = loadService.loadAssetsTypeList(user.getUserID());

    	recaptureSearch.setGasNameList(gasNameList);
    	recaptureSearch.setAssetsTypeList(assetsTypeList);
    	
    	request.setAttribute(RspBoNameConst.RECAPTURE_SEARCH, recaptureSearch);
    	
        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	

    }

}
