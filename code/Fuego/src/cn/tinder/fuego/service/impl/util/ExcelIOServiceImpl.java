package cn.tinder.fuego.service.impl.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.write.Border;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;

import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.model.AssignSumModel;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.engine.jxl.ExcelReader;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

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

	@Override
	public Map<AssignSumModel, List<AssetsInfoBo>> loadDataToPlanList(ExcelReader er) {
		PhysicalAssetsStatusDao assetsDao = new PhysicalAssetsStatusDaoImpl();
		ConvertAssetsModel conMod= new ConvertAssetsModel();
		Map<AssignSumModel,List<AssetsInfoBo>> map = new HashMap<AssignSumModel,List<AssetsInfoBo>>();
		/*
		 *此处源代码为
		 * 	for(int i = 0 ;i<er.getRows()+1;i++){
		 * Debug 时认为 此处+1 存在问题，故修改为正常
		 */
		for(int i = 0 ;i<er.getRows();i++){
			AssignSumModel model = new AssignSumModel();
			try{
				
				String id = er.getItem(i).get(0);
				String inGas=er.getItem(i).get(1);
				String dept=er.getItem(i).get(3);
								
		
				model.setAssetsID(id);
				model.setAssetsInGas(inGas);
				
				model.setDept(dept);
				AssetsInfoBo bo = new AssetsInfoBo();
				AssetsBo assets = new AssetsBo();
				
				assets.setAssetsID(id);
				assets =ConvertAssetsModel.convertAssets(assetsDao.getByAssetsID(id));
				bo.setAssets(assets);
				if(null==map.get(model)){
					List<AssetsInfoBo>list = new ArrayList<AssetsInfoBo>();		
					list.add(bo);					
					model.setAssetsOutGas(assets.getDept());
					map.put(model, list);
					
				}else{
					map.get(model).add(bo);
				}
			}catch(Exception ex){
				throw new ServiceException("第"+i+"行资产错误，请检查数据有效性。");
				
			}	
		}
		

		
		return map;
	}

	
}
