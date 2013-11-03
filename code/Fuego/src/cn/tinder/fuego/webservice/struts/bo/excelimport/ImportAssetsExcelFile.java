package cn.tinder.fuego.webservice.struts.bo.excelimport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class ImportAssetsExcelFile {
	 private static final Log log = LogFactory.getLog(ImportAssetsExcelFile.class);
	    
	public static AssetsPageBo load(File uploadFile) {
		// TODO Auto-generated method stub
		log.info(uploadFile.getAbsolutePath());
		
		AssetsPageBo bo =new AssetsPageBo();
		List<AssetsInfoBo> assetsList = new ArrayList<AssetsInfoBo>();
	
	    
	    
	     if (uploadFile.getName().indexOf(".xls") <= 0){
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
			       
			       for(int i=3;i<row-1;i++){
			    	   try{
				    	  /*
				    	   * 1.获取一条资产信息
				    	   */
				    	   
				    		AssetsInfoBo infoBo = getAssetInfo(sheet,i);
				    		
				    		infoBo.inportTest();
				    		
				    		/*
				    		 * 2.配置拆分参数
				    		 */
				    		
				    		int q=infoBo.getAssets().getQuantity();	//循环次数
				    	  	if(q==0){
				    	  		q=1;
				    	  	}
	
				    	   /*
				    	    * 3.拆分处理
				    	    */
					    	   for(int j=0;j<q;j++){
					    		   /*
					    		    * 拆分出一个信息
					    		    */
					    		   AssetsInfoBo ibo = splitInfoBo(infoBo);
					    		   assetsList.add(ibo);			    		 
						        
					    	   }
				    	   }catch(ServiceException ex){

									throw new ServiceException(ex.getMessage()+String.valueOf(i),ex);


				    	   }
			    	   }
			        bo.setAssetsList(assetsList);
			        
			        log.info(assetsList);
			    
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		return bo;
	}

	private static AssetsInfoBo splitInfoBo(AssetsInfoBo infoBo) {
		/*
		 * 1.初始化
		 */
		AssetsInfoBo ibo = new AssetsInfoBo();
		ibo.setAssets(new AssetsBo());
		   
		/*
		 *   2,重置重复信息  
		 */
		
		// 1.ID
		if (AssetsConst.ASSETS_GDZC_TYPE.equals(infoBo.getAssets().getAssetsType())) {
			if(infoBo.getAssets().getQuantity()!=1){
				throw new ServiceException(ExceptionMsg.GDZC_QUANTITY_ERR);
			}
			ibo.getAssets().setAssetsID(
					"G" + infoBo.getAssets().getAssetsType()
			);
		} else {
			ibo.getAssets().setAssetsID(
					ServiceContext.getInstance().getAssetsIDCreateService()
						.getCurrentID(infoBo.getAssets().getAssetsType()));
		}
		 
		   //AsdName	        
		   ibo.getAssets().setAssetsName(infoBo.getAssets().getAssetsName());
		   //AsdSRC
		   ibo.getAssets().setAssetsSRC(infoBo.getAssets().getAssetsSRC());	
		   //Manufacture
		   ibo.getAssets().setManufacture(infoBo.getAssets().getManufacture());	
		   //spc
		   ibo.getAssets().setSpec(infoBo.getAssets().getSpec());
		   //PurchaseDate
		   ibo.getAssets().setPurchaseDate(infoBo.getAssets().getPurchaseDate());
		   //O.V.
		   ibo.getAssets().setOriginalValue(infoBo.getAssets().getOriginalValue());
		   ibo.getAssets().setUnit(infoBo.getAssets().getUnit());
		   ibo.getAssets().setQuantity(1);
		 
		  //存放位置
		   if(infoBo.getAssets().getLocation().isEmpty()||(null==infoBo.getAssets().getLocation())){
			    ibo.getAssets().setLocation(infoBo.getAssets().getDuty());
		   }else{
			   ibo.getAssets().setLocation(infoBo.getAssets().getLocation());
		   }
   
		  //年限
		   ibo.getAssets().setExpectYear(infoBo.getAssets().getExpectYear());
		   //DueDate
		 
		   ibo.getAssets().setDueDate(
				   DateService.DateToString(
					   DateService.addYear(
							   DateService.stringToDate(infoBo.getAssets().getPurchaseDate()),
							   infoBo.getAssets().getExpectYear()
					   )				   
				   )
		   );
		    	
			//资产分类
		  
		   ibo.getAssets().setAssetsType(infoBo.getAssets().getAssetsType());
		   	
			//资产用途
		  
		   ibo.getAssets().setAttrType( infoBo.getAssets().getAttrType());
		   	
  
		
		   	//责任部门				        
		   ibo.getAssets().setDept( infoBo.getAssets().getDept());
		  //责任人
		   ibo.getAssets().setDuty(infoBo.getAssets().getDuty());
		  //备注		  
		   ibo.getAssets().setNote(infoBo.getAssets().getNote());
		    		
		   
		 
		return ibo;
	}

	private static AssetsInfoBo getAssetInfo(Sheet sheet, int i) {
		/*
		 * 1.Bo初始化
		 */
		AssetsInfoBo infoBo = new AssetsInfoBo();
		infoBo.setAssets(new AssetsBo());
   	   
		/*
		 * 2.导入信息
		 */
		Cell cell;
		NumberCell numCell;
		

 	   //1.AstID
 	   cell = sheet.getCell(0,i);
 	   infoBo.getAssets().setAssetsID(cell.getContents());
 	   
 	   //2.AsdName
    	cell = sheet.getCell(1,i);			        
    	infoBo.getAssets().setAssetsName(cell.getContents());
    	//3.AsdSRC
    	cell = sheet.getCell(2,i);
    	infoBo.getAssets().setAssetsSRC(cell.getContents());	
    	//4.Manufacture
    	cell = sheet.getCell(3,i);
    	infoBo.getAssets().setManufacture(cell.getContents());	   	
    	//5.spc
    	cell = sheet.getCell(4,i);
    	infoBo.getAssets().setSpec(cell.getContents());
    	//5.unit
    	cell = sheet.getCell(5,i);
    	infoBo.getAssets().setUnit(cell.getContents());
    	//6.AstQuantity 
    	cell = sheet.getCell(6,i);	
    	
    	if(cell.getType()==CellType.NUMBER){
    		numCell= (NumberCell) cell;  
    		infoBo.getAssets().setQuantity((int)numCell.getValue());
    	}else{
    		
    	}
    	
    
    	
    	//7.PurchaseDate
    	cell = sheet.getCell(7,i);
    	try{
    	infoBo.getAssets().setPurchaseDate(DateService.DateToString((DateService.stringToDate(cell.getContents().replace("/", "-")))));
		}catch(Exception ex){
			log.warn("Date Foamt Err",ex);
		}
		
		
		
	 	//8.O.V.
	 	cell = sheet.getCell(8,i);
	 	
	 	if(cell.getType()==CellType.NUMBER){
	 		numCell= (NumberCell) cell;   	
		 	infoBo.getAssets().setOriginalValue(((Double)numCell.getValue()).floatValue());
	 	}else{
	 		
	 	}
	 	
 		
 	
	 	//9.净值
	 	cell = sheet.getCell(9,i);
	 	//infoBo.getAssets().setNote(cell.getContents());
	 	
	 	//10.存放位置
	 	cell = sheet.getCell(10,i);
	 	infoBo.getAssets().setLocation(cell.getContents());
	 	
	 	//11.年限
	 	cell = sheet.getCell(11,i);	
	 	if(cell.getType()==CellType.NUMBER){
	 		numCell= (NumberCell) cell;   	
	 		infoBo.getAssets().setExpectYear((int)numCell.getValue());
	 	}else{
	 		
	 	}
	 
 	
	 	//12.DueDate
    	cell = sheet.getCell(12,i);
    	try{
    		infoBo.getAssets().setDueDate(DateService.DateToString(DateService.stringToDate(cell.getContents().replace("/", "-"))));
    	}catch(Exception ex){
    		log.warn("Date Foamt Err",ex);
    	}
    	//13.资产分类
    	cell = sheet.getCell(13,i);
    	infoBo.getAssets().setAssetsType(cell.getContents());
    	
    	//14.资产用途
    	cell = sheet.getCell(14,i);
    	infoBo.getAssets().setAttrType(cell.getContents());
   
    	//15.责任部门
    	cell = sheet.getCell(15,i);
    	
    	infoBo.getAssets().setDept(cell.getContents());
    	
    	
    	//16.技术状态
    	cell = sheet.getCell(16,i);
        infoBo.getAssets().setTechState(cell.getContents());
        
        //17.责任人
        cell = sheet.getCell(17,i);
        infoBo.getAssets().setDuty(cell.getContents());
        
        //18.备注		  
        cell = sheet.getCell(19,i);
        infoBo.getAssets().setNote(cell.getContents());

		return infoBo;
	}

	
	
	
}


