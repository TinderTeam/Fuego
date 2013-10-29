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
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

public class AssetsStatuesFile {
	
	
	private static final Log log = LogFactory.getLog(AssetsStatuesFile.class);
	
	


	private File file;
	private File modFile;
	   	    
	public AssetsStatuesFile(AssetsPageBo assetsPageBo) {
		
		log.info("进入创建流程");
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
		
		/*
		 * 获取输出文件路径
		 */
		file = new File(OutputFileConst.ASSETS_STATUES_FILE_PATH);
		if(file.exists()){
			/*
			 * 删除原有文件，重新构造
			 */
			file.delete();
			file = new File(OutputFileConst.ASSETS_STATUES_FILE_PATH);
		}
		
		modFile = new File(OutputFileConst.ASSETS_STATUES_FILE_MODEL_PATH);
		if(!modFile.exists()){
			log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.ASSETS_STATUES_FILE_MODEL_PATH);
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
            
            
			Date date = new Date(System.currentTimeMillis());
			
            excelIOimpl.writeLabel(sheet,2,2, DateService.DateToLongString(date));
            
            List<AssetsInfoBo> a =assetsPageBo.getAssetsList();
            log.info("List的数据："+a.size());
            for(int i=0; i<a.size();i++){
            	excelIOimpl.writeLabel(sheet,i+4,1, a.get(i).getAssets().getAssetsID());
            	excelIOimpl.writeLabel(sheet,i+4,2, a.get(i).getAssets().getAssetsName());
            	excelIOimpl.writeLabel(sheet,i+4,3, a.get(i).getAssets().getAssetsSRC());
            	excelIOimpl.writeLabel(sheet,i+4,4, a.get(i).getAssets().getManufacture());
            	excelIOimpl.writeLabel(sheet,i+4,5, a.get(i).getAssets().getSpec());
            	excelIOimpl.writeLabel(sheet,i+4,6, String.valueOf(a.get(i).getAssets().getQuantity()));
            	excelIOimpl.writeLabel(sheet,i+4,7,  DateService.DateToString(DateService.stringToDate(a.get(i).getAssets().getPurchaseDate())));
        
            	excelIOimpl.writeLabel(sheet,i+4,8,String.valueOf( a.get(i).getAssets().getOriginalValue()));
            	excelIOimpl.writeLabel(sheet,i+4,9, "");
            	excelIOimpl.writeLabel(sheet,i+4,10, a.get(i).getAssets().getLocation());
            	excelIOimpl.writeLabel(sheet,i+4,11, String.valueOf(a.get(i).getAssets().getExpectYear()));
            	excelIOimpl.writeLabel(sheet,i+4,12, DateService.DateToString(DateService.stringToDate(a.get(i).getAssets().getDueDate())));
            	excelIOimpl.writeLabel(sheet,i+4,13, a.get(i).getAssets().getAssetsType());
            	excelIOimpl.writeLabel(sheet,i+4,14, a.get(i).getAssets().getAttrType());
            	excelIOimpl.writeLabel(sheet,i+4,15, a.get(i).getAssets().getDept());
            	excelIOimpl.writeLabel(sheet,i+4,16, a.get(i).getAssets().getTechState());
            	excelIOimpl.writeLabel(sheet,i+4,17, a.get(i).getAssets().getDuty());
            	excelIOimpl.writeLabel(sheet,i+4,18, a.get(i).getExtAttr().getCheckState());
            	
            	excelIOimpl.writeLabel(sheet,i+4,19, a.get(i).getExtAttr().getNote());
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
