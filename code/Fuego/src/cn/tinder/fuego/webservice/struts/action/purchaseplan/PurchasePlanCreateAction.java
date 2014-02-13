package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.sys.FileLoadService;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.bo.excelimport.ImportPruchaseExcelFile;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePageBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanCreateForm;

/**
 * 
 * @ClassName: PurchasePlanAction
 * @Description: PurchasePlanAction
 * @author Nan Bowen
 * @date 2013-9-24 下午08:34:01
 * 
 */
public class PurchasePlanCreateAction extends Action {
	private static final Log log = LogFactory
			.getLog(PurchasePlanCreateAction.class);
	// Service
	ExcelIOService excelIOService = new ExcelIOServiceImpl();
	FileLoadService fileLoadService = ServiceContext.getInstance()
			.getFileLoadService();


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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

	private String handle(ActionForm form, HttpServletRequest request) {

		String pageName = null;
		// RequestIn
		SystemUserBo user = (SystemUserBo) request.getSession().getAttribute(
				RspBoNameConst.SYSTEM_USER);
 
		if (null == user)
		{
			log.error("the user is null");
			pageName = PageNameConst.LOGIN_PAGE;
			return pageName;
		}
		
		
		
		// Form
		PurchasePlanCreateForm purchasePlanCreateForm = (PurchasePlanCreateForm) form;
		// Form Empty test
		if (purchasePlanCreateForm == null)
		{

			log.error("cant find form!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else
		{
			log.info(LogKeyConst.PAGE_FORM + purchasePlanCreateForm.toString());
		}
		

		// Para
		String submitPara = request
				.getParameter(ParameterConst.SUBMIT_PARA_NAME);
		if (null == submitPara || submitPara.isEmpty())
		{
			log.error("submit is null!!");
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
			return pageName;
		} else
		{
			log.info(LogKeyConst.SUBMIT_VALUE + submitPara);
		}
	
		
		
		
		// RequestIn Session
		// SessionBo
		PurchasePlanSessionBo purchasePlanSessionBo = (PurchasePlanSessionBo) request
				.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA);
  

		if (submitPara.equals(ParameterConst.ADD_NEW_PARA_NAME)) {
			// submit the plan
			/*
			 * submit the plan for ensure!
			 */
			PurchasePlanBo newAssets=new PurchasePlanBo();
			newAssets.getAssetsBo().setAssetsName(purchasePlanCreateForm.getNewAssetsName());
			newAssets.getAssetsBo().setDuty(user.getDeptName());
			newAssets.getAssetsBo().setAssetsType(purchasePlanCreateForm.getTypeList());
			newAssets.getAssetsBo().setQuantity(1);
			newAssets.setPrice("0.00");
			newAssets.setIndex(purchasePlanSessionBo.getPurchasePageBo().getAssetsList().size()+1);
			purchasePlanSessionBo.getPurchasePageBo().getAssetsList().add(newAssets);
			log.info(LogKeyConst.SERVICE
					+ "submit the plan, submit the plan for ensure!");
			
			/*
			 * 将人工修改信息更新到Session
			 */
			purchasePlanSessionBo.getPurchasePageBo().updateByForm(purchasePlanCreateForm);
			
			
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,purchasePlanSessionBo);
			
			pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
		} else if (submitPara.equals(ParameterConst.SUBMIT_PARA_NAME)) {
			// submit the plan
			/*
			 * submit the plan for ensure!
			 */
			log.info(LogKeyConst.SERVICE
					+ "submit the plan, submit the plan for ensure!");
			
			/*
			 * 将人工修改信息更新到Session
			 */
			purchasePlanSessionBo.getPurchasePageBo().updateByForm(purchasePlanCreateForm);
			
			
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,purchasePlanSessionBo);
			
			pageName = PageNameConst.PURCHASE_PLAN_ENSURE_ACTION;
		} else if (submitPara.equals(ParameterConst.REDO_PARA_NAME)) {
			// ReDo this Page
			/*
			 * trans a empty bo;
			 */
			log.info(LogKeyConst.SERVICE + "Redo the Page");
			purchasePlanSessionBo.setPurchasePageBo(new PurchasePageBo());
			
			pageName = PageNameConst.PURCHASE_PLAN_CREATE_ACTION;
		} else if (submitPara.equals(ParameterConst.DOWNLOAD_PARA_NAME)) {
 
			request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE,OutputFileConst.PURCHASE_PLAN_MODE); 
		
			pageName = PageNameConst.DOWNLOAD_ACTION;
		} else if (submitPara.equals(ParameterConst.UPLOAD_PARA_NAME)) {
			// UpLoad the Excel File!
			/*
			 * 1.upload file 2.jump to ensure Page.
			 * 
			 * 上载文件
			 */			
 			
			purchasePlanSessionBo.getPurchasePageBo().setAssetsList(
					ImportPruchaseExcelFile.load(
							excelIOService.uploadFile(purchasePlanCreateForm.getMyFile())
					)
			);
			
			
			request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,purchasePlanSessionBo);
			pageName = PageNameConst.PURCHASE_PLAN_ENSURE_ACTION;
		} else if (submitPara.equals(ParameterConst.BACK_PARA_NAME)) {
			pageName = PageNameConst.PURCHASE_PLAN_PAGE_ACTION;
		}else{
			pageName = PageNameConst.SYSTEM_ERROR_PAGE;
		}

		log.info(LogKeyConst.NEXT_PAGE + pageName);

		return pageName;

	}

}
