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
import org.jgroups.util.Rsp;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.sys.FileLoadService;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.excelimport.ImportAssetsExcelFile;
import cn.tinder.fuego.webservice.struts.bo.excelimport.ImportPruchaseExcelFile;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.ImportAssetsForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanCreateForm;



/**
 * 
* @ClassName: PurchaseWarehousingAction 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-1 下午05:07:26 
*
 */

public class ImportAssetsAction extends Action
{
    private static final Log log = LogFactory.getLog(ImportAssetsAction.class);
    private AssetsManageService  assetsManageService = ServiceContext.getInstance().getAssetsManageService();
	ExcelIOService excelIOService = new ExcelIOServiceImpl();
	FileLoadService fileLoadService = ServiceContext.getInstance()
			.getFileLoadService();
	
	
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
		String nextPage = PageNameConst.DOWNLOAD_ACTION;
		ImportAssetsForm 	importAssetsForm = (ImportAssetsForm) form;
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		if(ParameterConst.UPLOAD_PARA_NAME.equals(submitPara))
		{
			// UpLoad the Excel File!
			/*
			 * 1.upload file 2.jump to ensure Page.
			 * 
			 * 上载文件
			 */			
			AssetsPageBo assetsPage = new AssetsPageBo();
			
			List<AssetsInfoBo> assetsList =ImportAssetsExcelFile.load(excelIOService.uploadFile(importAssetsForm.getAssetsFile()));
			
			assetsPage.getPage().setAllPageData(assetsList);
 
			assetsPage.setAssetsList(assetsPage.getPage().getAllPageData().subList(assetsPage.getPage().getStartNum(), assetsPage.getPage().getEndNum()));
			request.getSession().setAttribute(RspBoNameConst.ASSETS_PAGE_DATA,assetsPage);
  			
			nextPage = PageNameConst.IMPORT_ASSETS_SUBMIT_INIT_ACTION;
			
		}
		else if(ParameterConst.DB_UPLOAD_PARA_NAME.equals(submitPara))
		{
			assetsManageService.importBasicAssest(excelIOService.uploadFile(importAssetsForm.getAssetsFile()));
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
		}
		else			
		{
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, OutputFileConst.ASSETS_STATUES_FILE_MODEL_PATH);
		}
 

		return nextPage;
	}

	

}
