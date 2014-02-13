package cn.tinder.fuego.webservice.struts.bo.excelimport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.service.cache.CacheContext;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;

public class ImportPruchaseExcelFile {
	 private static final Log log = LogFactory.getLog(ImportPruchaseExcelFile.class);
	    
	public static List<PurchasePlanBo> load(File uploadFile) {
		// TODO Auto-generated method stub
		log.info(uploadFile.getAbsolutePath());
		
		
		List<PurchasePlanBo> list= new ArrayList<PurchasePlanBo>();
		
	    
	    
	     if (!uploadFile.getName().endsWith(".xls")){
	            throw new ServiceException(ExceptionMsg.EXCEL_FORMART_WRONG+uploadFile.getName());
	     }
	        // 2.判断文件是否存在
	        File excelFile = uploadFile;
	        if (!excelFile.exists())
	        	 throw new ServiceException(ExceptionMsg.FILEPATH_NOT_EXIST+ uploadFile.getAbsolutePath());
	        // 3.定义Excel对象,即workbook
	        Workbook book;
			try {
				book = Workbook.getWorkbook(excelFile);
				
				 if (book == null) {
			            throw new ServiceException(ExceptionMsg.EXCEL_READ_ERR);
			        }
			        // 3. 获取所有workSheets
			        Sheet sheet = book.getSheet(0);
			        int column=sheet.getColumns();
			        int row = sheet.getRows();
			        log.info("Excel Load Info: row="  +row + "; column=" + column + ";"); 
			        
			        Cell cell;
			        for(int i=2;i<row;i++){
			        	PurchasePlanBo planBo = new PurchasePlanBo();
			        	cell = sheet.getCell(0,i);		
			        	if(cell.getContents()==null||cell.getContents().isEmpty()){
			        		;
			        	}else{
			        		planBo.setIndex(Integer.valueOf(cell.getContents()));
			        	}
			        			        		
			        	/*Edit By Bowen
			        	 * for  Issue #58
			        	 * 2013-11-24 21:07
			        	 */
			        	
			        	cell = sheet.getCell(1,i);
			        	AssetsBo assBo=new AssetsBo();
			        	assBo.setAssetsID(cell.getContents());
			        	cell = sheet.getCell(2,i);
			        	if(cell.getContents().isEmpty()){
			        		continue;
			        	}
			        	assBo.setAssetsName(cell.getContents());
			        	cell = sheet.getCell(3,i);
			        	assBo.setAssetsType(cell.getContents());
			        	cell = sheet.getCell(4,i);
			        	assBo.setManufacture(cell.getContents());
			        	cell = sheet.getCell(5,i);
			        	assBo.setSpec(cell.getContents());
			        	cell = sheet.getCell(6,i);
			        	assBo.setUnit(cell.getContents());
			        	cell = sheet.getCell(7,i);			  
			        	assBo.setQuantity(Integer.valueOf(cell.getContents()));
			        	cell = sheet.getCell(8,i);
			        	planBo.setPrice(cell.getContents());
			        	cell = sheet.getCell(9,i);
			        	assBo.setNote(cell.getContents());
			        	
			        	
			        	/*
			        	 * Edit By Bowen
			        	 * Added set dept
			        	*/
			        	cell = sheet.getCell(10,i);
			        	assBo.setDuty(cell.getContents());
			        	
			        	assBo.setManageName(CacheContext.getInstance().getUserCache().getManageByUser(assBo.getDuty()));
			        	
			        	/*-------------*/
			        	float price = Float.valueOf(planBo.getPrice())*(Float.valueOf((String.valueOf(assBo.getQuantity()))));
			        	planBo.setMoney(String.valueOf(price));
			        	planBo.setAssetsBo(assBo);
			        	list.add(planBo);
			        }
			     
			        
			        log.info(list);
			    
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		return list;
	}

}
