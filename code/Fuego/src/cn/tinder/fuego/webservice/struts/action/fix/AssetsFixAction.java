/**   
* @Title: BasicDataInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.assets 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-19 下午06:37:27 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.fix;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.tinder.fuego.dao.AssetsFixDao;
import cn.tinder.fuego.domain.po.AssetsFix;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.model.convert.ConvertModel;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.ConfigInformation;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.engine.jxl.ExcelReader;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.excelimport.ImportAssetsExcelFile;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.AssetsFixForm;
import cn.tinder.fuego.webservice.struts.form.ImportAssetsForm;

/** 
 * 
* @ClassName: AssetsFixAction 
* @Description: TODO
* @author Nan Bowen
* @date 2014-1-11 下午08:53:58 
*
 */
public class AssetsFixAction extends Action
{   private static final Log log = LogFactory.getLog(AssetsFixAction.class);
	ExcelIOService excelIOService = new ExcelIOServiceImpl();
	ApplicationContext ctx = new FileSystemXmlApplicationContext( ConfigInformation.getResourcePath()+"/resource/assetsFixBean.xml");
	AssetsFixDao dao =  (AssetsFixDao) ctx.getBean("assetsFixDao");

public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception
{
	log.info(LogKeyConst.INPUT_ACTION+"BasicDataInitAction");

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
	String nextPage = PageNameConst.DOWNLOAD_ACTION;
	AssetsFixForm 	assetsFixForm = (AssetsFixForm) form;
	String submitPara = request.getParameter(ParameterConst.SUBMIT_PARA_NAME);
	
	log.info("SubmitPara is :" +submitPara);
	

	if(ParameterConst.DOWNLOAD_PARA_NAME.equals(submitPara)){
		/*
		 * 导出资产进行下载
		 */
		request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, OutputFileConst.ASSETS_FIX_FILE);
		nextPage = PageNameConst.DOWNLOAD_ACTION;
	}
	else if(ParameterConst.DELETE_DOWNLOAD_PARA_NAME.equals(submitPara))			
	{		
		/*
		 * 下载删除导入模版
		 */
		request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, OutputFileConst.ASSETS_FIX_FILE_DELETE_MODEL);
		nextPage = PageNameConst.DOWNLOAD_ACTION;
	}
	else if(ParameterConst.ADD_DOWNLOAD_PARA_NAME.equals(submitPara)||ParameterConst.UPDATE_DOWNLOAD_PARA_NAME.equals(submitPara))			
	{
		/*
		 * 卸载其他模版
		 */
		request.setAttribute(RspBoNameConst.DOWN_LOAD_FILE, OutputFileConst.ASSETS_FIX_FILE_MODEL);
		nextPage = PageNameConst.DOWNLOAD_ACTION;
	}
	/*
	 * 维修表导入
	 */
	else if(ParameterConst.ADD_UPLOAD_PARA_NAME.equals(submitPara)){
		//追加表
		ExcelReader excelReader= new ExcelReader(excelIOService.uploadFile(assetsFixForm.getAddAssetsFixFile()),2);
		for(int i=0;i<excelReader.getRows()+1;i++){
			AssetsFix fix= ConvertModel.convertyToAssetsFix(excelReader.getItem(i));
			dao.create(fix);			
		}		
		nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
	}
	else if(ParameterConst.DELETE_UPLOAD_PARA_NAME.equals(submitPara)){
		/**
		 * 删除
		 */
		
		ExcelReader excelReader= new ExcelReader(excelIOService.uploadFile(assetsFixForm.getDeleteAssetsFixFile()),2);
		for(int i=0;i<excelReader.getRows();i++){
			AssetsFix fix= ConvertModel.convertyToAssetsFixDelete(excelReader.getItem(i));
			dao.delete(fix);		
		}		
		nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
	}
	else if(ParameterConst.UPDATE_UPLOAD_PARA_NAME.equals(submitPara)){
		/**
		 * 更新
		 */
		ExcelReader excelReader= new ExcelReader(excelIOService.uploadFile(assetsFixForm.getUpdateAssetsFixFile()),2);
		for(int i=0;i<excelReader.getRows()+1;i++){
			AssetsFix fix= ConvertModel.convertyToAssetsFix(excelReader.getItem(i));
			dao.saveOrUpdate(fix);			
		}		
		nextPage = PageNameConst.SYSTEM_SUCCESS_PAGE;
	}
	return nextPage;
}

}
