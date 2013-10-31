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
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.recapture.*;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

/**
 * 
* @ClassName: AssetsRecaptureApprovalInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-3 上午01:15:07 
*
 */
public class AssetsRecaptureApprovalInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureApprovalInitAction.class);
    
    //private AssetsRecoverService  assetsRecoverService = ServiceContext.getInstance().getAssetsRecoverService();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	log.info(LogKeyConst.INPUT_ACTION+"AssetsRecaptureApprovalInitAction");
    	
    	
    	String pageName = PageNameConst.ASSETS_RECAPTURE_APPROVAL_PAGE;
    	
    	//get department information list
      
    	AssetsPageBo assetsRecoverPage = new  AssetsPageBo();
    	assetsRecoverPage=null;
        
        // request.setAttribute(RspBoNameConst.ASSETS_INFO_LIST, selectAssetsPage);
 
        log.info(LogKeyConst.NEXT_PAGE+pageName);
        return mapping.findForward(pageName);	

    }

}
