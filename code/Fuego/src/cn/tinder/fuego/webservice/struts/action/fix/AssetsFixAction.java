/**   
* @Title: BasicDataInitAction.java 
* @Package cn.tinder.fuego.webservice.struts.action.assets 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-19 下午06:37:27 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.action.fix;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

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
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.model.convert.ConvertModel;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.ConfigInformation;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.util.engine.computer.ComputeService;
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
		createAssetsFixFile();
		
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


private File createAssetsFixFile() {

	File file;
	File modFile;

	/*
	 * 获取输出文件路径
	 */
	file = new File(OutputFileConst.ASSETS_FIX_FILE);
	if(file.exists()){
		/*
		 * 删除原有文件，重新构造
		 */
		file.delete();
		file = new File(OutputFileConst.ASSETS_FIX_FILE);
	}
		
	modFile = new File(OutputFileConst.ASSETS_FIX_FILE_MODEL);
	if(!modFile.exists()){
		log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.ASSETS_FIX_FILE_MODEL);
		throw new ServiceException(ExceptionMsg.MODFILE_NOT_EXIST);
	}
		
		// 获取JXL模版
        Workbook modWorkBook;
		try {
			modWorkBook = Workbook.getWorkbook( modFile );
			WritableWorkbook workbook = Workbook.createWorkbook(file, modWorkBook);
			
			
			
			/*
			 * 获取校验
			 */
			if(modWorkBook==null){
				log.error("Mod 文件无法加载 为Excel文件");
			}
			if(workbook==null){
				log.error("导出文件无法加载 为Excel文件");
			}
			
			
			// 获取一个Sheet 进行读取操作
			WritableSheet sheet = null;
            if (workbook.getNumberOfSheets() > 0){
                   sheet = workbook.getSheet(0); // 获取第一个sheet
            }else{
                   sheet=workbook.createSheet(OutputFileConst.ASSETS_FIX_FILE, 0);
            }
            
            List<AssetsFix> fixList=dao.getAllAssetsFix();
            if(null!=fixList&&fixList.size()>0){
            	for(int i = 0 ;i<fixList.size();i++){
            		excelIOService.writeLabel(sheet,i+3,1,fixList.get(i).getIndexNumber());
            		excelIOService.writeLabel(sheet,i+3,2,fixList.get(i).getContext());
            		excelIOService.writeLabel(sheet,i+3,3,fixList.get(i).getGasStation());
            		excelIOService.writeLabel(sheet,i+3,4,fixList.get(i).getDept());
            		excelIOService.writeLabel(sheet,i+3,5,fixList.get(i).getHandleUser());
            		excelIOService.writeLabel(sheet,i+3,6,fixList.get(i).getBudget());
            		excelIOService.writeLabel(sheet,i+3,7,fixList.get(i).getSendTime());
            		excelIOService.writeLabel(sheet,i+3,8,fixList.get(i).getStartTime());
            		excelIOService.writeLabel(sheet,i+3,9,fixList.get(i).getFinishTime());
            		excelIOService.writeLabel(sheet,i+3,10,fixList.get(i).getPayMoney());
            		excelIOService.writeLabel(sheet,i+3,11,fixList.get(i).getPayTime());
            		excelIOService.writeLabel(sheet,i+3,12,fixList.get(i).getDiff());
            		excelIOService.writeLabel(sheet,i+3,13,fixList.get(i).getAlreadyPay());
            		excelIOService.writeLabel(sheet,i+3,14,fixList.get(i).getUnPay());
            		
            		excelIOService.writeLabel(sheet,i+3,15,fixList.get(i).getNote());
            	}
              
            }

            workbook.write();// 将修改保存到workbook --》一定要保存
            workbook.close();// 关闭workbook，释放内存 ---》一定要释放内存
            modWorkBook.close();

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			log.error(ExceptionMsg.FILE_ERR,e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(ExceptionMsg.FILE_ERR,e);
			e.printStackTrace();
		} catch (WriteException e) {
			log.error(ExceptionMsg.FILE_ERR,e);
			e.printStackTrace();
		}
		return file;
}

}
