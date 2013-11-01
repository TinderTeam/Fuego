package cn.tinder.fuego.util.engine.jxl;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;

public class JXLService {

	 public static Date getData(Sheet sheet,int cell, int row) {
		  Date date=null;
		  Cell rs = sheet.getCell(cell,row);
		
		     if(rs.getType()==CellType.DATE){
		      
		          DateCell dc = (DateCell)rs;
		         date = dc.getDate();		  
	
		     } else{
		    	 throw new ServiceException(ExceptionMsg.DATE_FOMATE+"["+rs.getContents()+"]");
		 
		     }
			return date;
		    	 
		 
		 }
	 
	 public static String getString(Sheet sheet,int cell, int row) {
		  Cell rs = sheet.getCell(cell, row);
		     if(rs.getType()==CellType.STRING_FORMULA){
		    	 return rs.getContents();   
		     }
		     throw new ServiceException(ExceptionMsg.CELL_WRONG);
		 }
	 
	 public static Float getFloat(Sheet sheet,int cell, int row) {
		  Cell rs = sheet.getCell(cell, row);
		     if(rs.getType()==CellType.NUMBER){
		      if(rs.getContents()!=null){		      
		          NumberCell dc = (NumberCell) rs;
		          
		        return (float)dc.getValue();
		      }
		     } else{
		    	 throw new ServiceException(ExceptionMsg.CELL_WRONG);
		 
		     }
			return null;
		    	 
		 
		 }
}
