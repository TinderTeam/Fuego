package cn.tinder.fuego.webservice.struts.action.pricedata;



import java.io.File;
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
import cn.tinder.fuego.service.SystemMaintanceService;
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
import cn.tinder.fuego.webservice.struts.form.ImportPriceForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanCreateForm;



/**
 * 
* @ClassName: ImportPriceDataAction 
* @Description: TODO
* @author Nan Bowen
* @date 2013-11-22 上午12:55:12 
*/

public class ImportPriceDataAction extends Action
{
    private static final Log log = LogFactory.getLog(ImportPriceDataAction.class);
    private SystemMaintanceService  systemMaintanceService = ServiceContext.getInstance().getSystemMaintanceService();
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
		ImportPriceForm 	importPriceForm = (ImportPriceForm) form;
		String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		
		
	    if(ParameterConst.PRICE_UPLOAD_PARA_NAME.equals(submitPara)){
	    	systemMaintanceService.importPriceAssest(excelIOService.uploadFile(importPriceForm.getPriceFile()));
			nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
		}
		else			
		{
			/*DownLoad the Pricefile
			 * 
			 */
			File priceFile = systemMaintanceService.getPriceFile();
			log.info("DownLoad File is : "+priceFile.getAbsolutePath());
			
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, priceFile.getAbsolutePath());
		}
 

		return nextPage;
	}

	

}
