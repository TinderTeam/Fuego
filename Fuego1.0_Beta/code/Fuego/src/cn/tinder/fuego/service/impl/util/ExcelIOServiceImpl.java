package cn.tinder.fuego.service.impl.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.webservice.struts.action.upLoad.UpLoadAction;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.write.Border;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelIOServiceImpl implements ExcelIOService{
	 private static final Log log = LogFactory.getLog(ExcelIOServiceImpl.class);
 	
	/**
	 * 将字符串写入Excel表格
	 * @param sheet
	 * @param x
	 * @param y
	 * @param str
	 */
	public void writeLabel(WritableSheet sheet, int x, int y, String str){

	        WritableCell cell = sheet.getWritableCell(y - 1, x - 1);// 获取第一个单元格\
	        jxl.format.CellFormat cf = cell.getCellFormat();// 获取第一个单元格的格式
	        WritableCellFormat wc = new WritableCellFormat(); 
		    
	        // 设置边框线 
	        try {
				wc.setBorder(Border.ALL, BorderLineStyle.THIN);
				// 设置居中 
		        wc.setAlignment(Alignment.CENTRE); 
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
	        jxl.write.Label lbl = new jxl.write.Label(y - 1, x - 1, str,wc);// 将第一个单元格的值改
	   
	    
	        if(null != cf)
	        {
	        	
	            lbl.setCellFormat(cf);// 将修改后的单元格的格式设定成跟原来一样
	        }
	        try
	        {
	            sheet.addCell(lbl);
	        } catch (RowsExceededException e)
	        {
	            throw new ServiceException(e.getMessage());
	        } catch (WriteException e)
	        {
	            throw new ServiceException(e.getMessage());
	        }
	    }
	
	public void simpleWriteLabel(WritableSheet sheet, int x, int y, String str){

	  
        jxl.write.Label lbl = new jxl.write.Label(y - 1, x - 1, str);// 将第一个单元格的值改
       
        try
        {
            sheet.addCell(lbl);
        } catch (RowsExceededException e)
        {
            throw new ServiceException(e.getMessage());
        } catch (WriteException e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

	@Override
	public File uploadFile(FormFile file) {
		String path = null;
		// TODO Auto-generated method stub
		FileOutputStream fileOutput;
        try {
            log.info("file is："+file);
            path=OutputFileConst.UPLOAD_FILE_PATH +System.currentTimeMillis() +file.getFileName();
            fileOutput = new FileOutputStream(path);
            log.info("Output="+fileOutput.getFD().toString());
            fileOutput.write(file.getFileData());
            fileOutput.flush();
            fileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File result =new File(path);
        log.info("File path = "+result.isFile()+"  path=" + result.getAbsolutePath());
        return result ;

	}
}
