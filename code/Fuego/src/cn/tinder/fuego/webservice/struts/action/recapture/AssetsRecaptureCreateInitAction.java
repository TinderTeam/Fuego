/**   
* @Title: AllocationInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.allocation 
* @Description: TODO
* @author Tang Jun   
* @date 2013-9-24 下午11:55:04 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.recapture;

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
import cn.tinder.fuego.service.ConstServiceTest;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.recapture.*;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.RecaptureForm;

/**
 * 
* @ClassName: AssetsRecaptureCreateInitAction 
* @Description: TODO
* @author Kaiqiang Zhang
* @date 2013-10-3 上午01:07:48 
*
 */

public class AssetsRecaptureCreateInitAction extends Action
{
    private static final Log log = LogFactory.getLog(AssetsRecaptureCreateInitAction.class);
	private AssetsManageService assetsService = ServiceContext.getInstance().getAssetsManageService();


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
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
		String nextPage = PageNameConst.ASSETS_RECAPTURE_CREATE_PAGE;
    	RecaptureForm reForm = (RecaptureForm) request.getSession().getAttribute(RspBoNameConst.RECAPTURE_FORM);
    	List<AssetsInfoBo> assetsList;
    	if(null == reForm)
    	{
    		assetsList = assetsService.getRecaptureAssetsListBo(null,null); 
    	}
    	else
    	{
    		assetsList = assetsService.getRecaptureAssetsListBo(Arrays.asList(reForm.getGasName()), Arrays.asList(reForm.getAssetsType())); 
    	}
    	log.info(assetsList);
     	
		AssetsPageBo selectAssetsPage = new AssetsPageBo();
		selectAssetsPage.setAssetsList(assetsList);
		selectAssetsPage.setShowCheckBox(true);
		request.setAttribute(RspBoNameConst.ASSETS_PAGE_DATA, selectAssetsPage);
		return nextPage;
	}

}
