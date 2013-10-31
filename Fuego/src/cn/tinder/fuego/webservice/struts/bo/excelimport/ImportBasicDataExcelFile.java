package cn.tinder.fuego.webservice.struts.bo.excelimport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.util.engine.jxl.JXLService;

public class ImportBasicDataExcelFile {
	 private static final Log log = LogFactory.getLog(ImportBasicDataExcelFile.class);
	    
	public static List<PhysicalAssetsStatus> load(File uploadFile) {

		
		
		List<PhysicalAssetsStatus> assetsList = new ArrayList<PhysicalAssetsStatus>();
	    File excelFile = uploadFile;
	    
	    log.info("UploadFile is :"+uploadFile.getAbsolutePath());
	    
		/*
	     * 1.File Valdate
	     */
	    excelFileValidate(uploadFile, excelFile);
	
	    /*
	     *  2.Excel Operate Object , workbook
	     */
	    Workbook book;
		try {
			book = Workbook.getWorkbook(excelFile);
				
			if (book == null) {
				log.warn("Can not open the file as a ExcelFile!");
				throw new ServiceException(ExceptionMsg.EXCEL_READ_ERR);
			}
			
			Sheet sheet = book.getSheet(0);
			int column=sheet.getColumns();
			int row = sheet.getRows();
			log.info("Excel Load Info: row="  +row + "; column=" + column + ";"); 

			for(int i=3;i<row-1;i++){
				/*
				 *  4. Read a item of data
				 */
				try{
					readLine(assetsList, sheet, row, i);
				}catch(ServiceException ex){
					/*
					 * Exception catch add the row info.
					 */
					log.warn("Read Excel Line Err："+ExceptionMsg.EXCEPTION_ROW+i+":"+ex.getMessage());
					throw new ServiceException(ExceptionMsg.EXCEPTION_ROW+i+":"+ex.getMessage());					
				}
			}    
			log.debug(assetsList);			    
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		return assetsList;
	}

	private static void readLine(List<PhysicalAssetsStatus> assetsList,
			Sheet sheet, int row, int i) {
		
		Cell cell;
		NumberCell numCell;
		log.info("i="+i+";rowMax="+(row-3)); 
		PhysicalAssetsStatus assets = new PhysicalAssetsStatus();
		
		cell = sheet.getCell(0,i);			
		assets.setAssetsID(cell.getContents());
		
		cell = sheet.getCell(1,i);			
		assets.setAssetsName(cell.getContents());
      
		
		//AsdSRC
		cell = sheet.getCell(2,i);
		assets.setAssetsSRC(cell.getContents());	
		//Manufacture
		cell = sheet.getCell(3,i);
		assets.setManufacture(cell.getContents());	
		
		//spc
		cell = sheet.getCell(4,i);
		assets.setSpec(cell.getContents());
 
		//Unit
		cell = sheet.getCell(5,i);
		assets.setUnit(cell.getContents());
		
		//Quantity 	
		cell = sheet.getCell(6,i);
		numCell=(NumberCell) cell;
		assets.setQuantity(numCell.getColumn());
		
		//PurchaseDate
		assets.setPurchaseDate(JXLService.getData(sheet, 7,i));
		
		//O.V.
		assets.setOriginalValue(JXLService.getFloat(sheet, 8, i));
     
 
		
		//存放位置
		cell = sheet.getCell(10,i);
		assets.setLocation(cell.getContents());
      
      
		//年限
		cell = sheet.getCell(11,i);		
		
		assets.setExpectYear(Integer.valueOf(cell.getContents()));
		
		//DueDate
        
		assets.setDueDate(JXLService.getData(sheet,12,i));
		//DEPT
		
		cell = sheet.getCell(13,i);
		assets.setDept(cell.getContents());
		
		//TYPE	    
		cell = sheet.getCell(14,i);
		assets.setAssetsType(cell.getContents());
		//DUTY	    
		cell = sheet.getCell(15,i);
		assets.setDuty(cell.getContents());		
		//备注		  
		cell = sheet.getCell(16,i);
		assets.setNote(cell.getContents());
		//技术状态
		cell = sheet.getCell(17,i);
		assets.setTechState(cell.getContents());
		assetsList.add(assets);
	}

	private static void excelFileValidate(File uploadFile, File excelFile) {
		if (uploadFile.getName().indexOf(".xls") <= 0){
	    	log.warn("File formart is wrong: "+uploadFile.getName());
	        throw new ServiceException(ExceptionMsg.EXCEL_FORMART_WRONG);
	    }	   
	    if (!excelFile.exists()){
	     	log.warn("File is not exist: "+uploadFile.getAbsolutePath());
	    	throw new ServiceException(ExceptionMsg.FILEPATH_NOT_EXIST+ uploadFile.getAbsolutePath());
	    }
	}
	
}
