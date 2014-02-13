package cn.tinder.fuego.webservice.struts.bo.download;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

public class AssignFile {
	
	
	private static final Log log = LogFactory.getLog(AssignFile.class);
	
	


	private File file;
	private File modFile;
	   	    
	public AssignFile(AssignPlanBo assignPlanBo) {
		
		log.info("进入创建流程");
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
		
		/*
		 * 获取输出文件路径
		 */
		file = new File(OutputFileConst.ASSIGN_FILE_PATH);
		if(file.exists()){
			/*
			 * 删除原有文件，重新构造
			 */
			file.delete();
			file = new File(OutputFileConst.ASSIGN_FILE_PATH);
		}
		
		modFile = new File(OutputFileConst.ASSIGN_FILE_MODEL_PATH);
		if(!modFile.exists()){
			log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.ASSIGN_FILE_MODEL_PATH);
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
                   sheet=workbook.createSheet(OutputFileConst.ASSETS_STATUES_FILE_PATH, 0);
            }
            
            //文件内容
            
			Date date = new Date(System.currentTimeMillis());
			
            excelIOimpl.writeLabel(sheet,2,3, DateService.DateToLongString(date));
            
   
			excelIOimpl.writeLabel(sheet, 6, 13, assignPlanBo.getTransInfo().getOutDept());
			excelIOimpl.writeLabel(sheet, 6,10, assignPlanBo.getTransInfo().getInDept());
			excelIOimpl.writeLabel(sheet, 2,13, assignPlanBo.getTransInfo().getTransInfo().getCreateUser());
			
			
			
			List<AssetsInfoBo> boList=assignPlanBo.getAssetsPage().getAssetsList();
			

			int i=boList.size();
			for (AssetsInfoBo bo : boList)
			{

				
				sheet.insertRow(4);
				excelIOimpl.writeLabel(sheet, 5, 1,String.valueOf(i));
				i--;
				excelIOimpl.writeLabel(sheet, 5, 2, bo.getAssets().getAssetsID());
				excelIOimpl.writeLabel(sheet, 5, 3, bo.getAssets().getAssetsName());
				excelIOimpl.writeLabel(sheet, 5, 4, bo.getAssets().getAssetsSRC());
				excelIOimpl.writeLabel(sheet, 5, 5, bo.getAssets().getAssetsType());
				excelIOimpl.writeLabel(sheet, 5, 6, bo.getAssets().getManufacture());
				excelIOimpl.writeLabel(sheet, 5, 7, bo.getAssets().getSpec());
				excelIOimpl.writeLabel(sheet, 5, 8, bo.getAssets().getUnit());
				excelIOimpl.writeLabel(sheet, 5, 9, String.valueOf(bo.getAssets().getQuantity()));
				excelIOimpl.writeLabel(sheet, 5, 10, DateService.DateToString(DateService.stringToDate(bo.getAssets().getPurchaseDate())));
				excelIOimpl.writeLabel(sheet, 5, 11, String.valueOf(bo.getAssets().getOriginalValue()));				
				excelIOimpl.writeLabel(sheet, 5, 12,  String.valueOf(bo.getAssets().getExpectYear()));
				excelIOimpl.writeLabel(sheet, 5, 13, bo.getAssets().getTechState());
				excelIOimpl.writeLabel(sheet, 5, 14, bo.getAssets().getNote());
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
      
  
	}

	
	
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the modFile
	 */
	public File getModFile() {
		return modFile;
	}

	/**
	 * @param modFile the modFile to set
	 */
	public void setModFile(File modFile) {
		this.modFile = modFile;
	}
	
	
	
	
	
}
