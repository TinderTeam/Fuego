/**   
* @Title: SystemMaintanceServiceImpl.java 
* @Package cn.tinder.fuego.service.impl 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-5 下午11:02:02 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.AssetsPriceDao;
import cn.tinder.fuego.dao.AssetsQuotaDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl;
import cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl;
import cn.tinder.fuego.domain.po.AssetsPrice;
import cn.tinder.fuego.domain.po.AssetsQuota;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.SystemMaintanceService;
import cn.tinder.fuego.service.cache.CacheContext;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.ValidatorUtil;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

/** 
 * @ClassName: SystemMaintanceServiceImpl 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-5 下午11:02:02 
 *  
 */
public class SystemMaintanceServiceImpl implements SystemMaintanceService
{
	private static final Log log = LogFactory.getLog(SystemMaintanceServiceImpl.class);
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.SystemMaintanceService#addGasStation(java.lang.String, java.lang.String)
	 */
	@Override
	public void addGasStation(String userName, String userID,String manageName)
	{
		SystemUser user = new SystemUser();
		if(null != systemUserDao.find(userName))
		{
			throw new ServiceException(ExceptionMsg.USER_ALREADY_EXISTED);
		}
		
//		if(null == userName || userName.trim().isEmpty()||null==userID||userID.trim().isEmpty()||null == manageName||manageName.trim().isEmpty())
		if(ValidatorUtil.isEmpty(userName)||ValidatorUtil.isEmpty(userID)||ValidatorUtil.isEmpty(manageName))
		{
			throw new ServiceException(ExceptionMsg.INPUT_EMPUTY);

		}
		 
		user.setUserName(userName);  //加油站名
		user.setDepartment(userID);  //用户名
		user.setManageName(manageName); //经管部
		user.setDepartmentID("new");
		user.setPassword(UserRoleConst.DEFUALT_PASSWORD); 
		user.setRole(UserRoleConst.GASSTATION);
		
		systemUserDao.create(user);

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.SystemMaintanceService#modifyUserInfo(java.lang.String, java.lang.String)
	 */
	//nickname service
	public String searchUserNickName(String userName)
	{
		String nickname=null;
		nickname=systemUserDao.find(userName).getNickName();
		SystemUser user = systemUserDao.find(userName);
		log.info(user);
		return nickname;
	}
    public SystemUser deleteUserNickName(String userName)
    {
    	SystemUser user=new SystemUser();
    	user=systemUserDao.find(userName);
    	user.setNickName(null);
    	
    	systemUserDao.saveOrUpdate(user);
    	    	
    	return user;
    	
    }	
    public SystemUser  saveUserNickName(String userName,String nickName)
    {
    	SystemUser user=new SystemUser();
    	user=systemUserDao.find(userName);
    	user.setNickName(nickName);
    	systemUserDao.saveOrUpdate(user);
    	return user;
    }
	public  String searchUserInfo(String userName)
	{  
		
		
		String managename=null;
		if(ValidatorUtil.isEmpty(userName))
		{
			throw new ServiceException(ExceptionMsg.INPUT_EMPUTY);

		}
			managename=systemUserDao.find(userName).getManageName();

          return managename;
	}
    public SystemUser deleteUserInfo(String userName)
    {
    	SystemUser gas=new SystemUser();
    	gas=systemUserDao.find(userName);
    	systemUserDao.delete(gas);
    	
    	return gas;
    	
    }
    public SystemUser  saveUserInfo(String userName,String deptName)
    {
    	SystemUser gas=new SystemUser();
    	gas.setDepartment(userName);
    	gas.setDepartmentID("New");
    	gas.setManageName(deptName);
    	gas.setPassword(UserRoleConst.DEFUALT_PASSWORD);
    	gas.setRole(UserRoleConst.GASSTATION);
    	gas.setUserName(userName);
    	
    	systemUserDao.saveOrUpdate(gas);
    	return gas;
    }
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.SystemMaintanceService#modifyUserInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyUserInfo(String userName, String mannageName)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importPriceAssest(File uploadFile) {
		AssetsPriceDao assetsPriceDao = new AssetsPriceDaoImpl();
		List<AssetsPrice> lsit=readToList(uploadFile);
		assetsPriceDao.deletAll();
		for(AssetsPrice a:lsit){
			assetsPriceDao.create(a);
		}
		return ;
	}

	private List<AssetsPrice> readToList(File uploadFile) {
		log.info(uploadFile.getAbsolutePath());
		
 		List<AssetsPrice> priceAssest = new ArrayList<AssetsPrice>();
	
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
			       
			       /*
			        * Debug
			        * 受到Excel可能有空行的影响，这里加入对实际行数的检验
			        * 2014-06-13
			        * 
			        */
			       
			       int contentRow=0;
			       for(int j =2;j<row;j++){
			    	   
			    	   cell = sheet.getCell(1,j);
			    	   if(!cell.getContents().isEmpty()){
			    		   contentRow=j+1;
			    	   }
			       }
			       
			       
			       for(int i=2;i<contentRow;i++){
			    	
				    	  /*
				    	   * 1.获取一条价格信息
				    	   */ 			    	
			    		    
			    		   AssetsPrice assetsPrice = new  AssetsPrice();
			    		   
			    			/*
			    			 * 2.导入信息
			    			 */
			    		
			    			NumberCell numCell;
			    			

			    	 	   
			    	 	 cell = sheet.getCell(0,i);
			    	 	 assetsPrice.setAssetsName(cell.getContents());
			    	 	 cell = sheet.getCell(1,i);
			    	 	 assetsPrice.setManufacture(cell.getContents());
			    	 	 cell = sheet.getCell(2,i);
			    	 	 assetsPrice.setSpec(cell.getContents());			    	 	 
			    	 	 cell = sheet.getCell(3,i);
			    	 	 assetsPrice.setPrice(Float.valueOf(cell.getContents()));
			    	 	 priceAssest.add(assetsPrice);
			    	 	 
			    	 	log.info("导入入第:"+i+"行"+assetsPrice.getAssetsName());
			    		  }	
			       
			}catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("Result:"+priceAssest);
			return priceAssest;
	}

	

	@Override
	public File getPriceFile() {
		AssetsPriceDao assetsPriceDao = new AssetsPriceDaoImpl();
		File file = null;
		
		
		List<AssetsPrice> list =assetsPriceDao.getAll();
		
		if(null==list||list.isEmpty()){
			//No Excist PriceInfo
			file = new File(OutputFileConst.PRICEFILE_MODEL);
		}else{
			file = output(list);
		}
		return file;
	}

	private File output(List<AssetsPrice> list) {

		File file;
		File modFile;
		   	    
			
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
			
			/*
			 * 获取输出文件路径
			 */
			file = new File(OutputFileConst.PRICEFILE);
			if(file.exists()){
				/*
				 * 删除原有文件，重新构造
				 */
				file.delete();
				file = new File(OutputFileConst.PRICEFILE);
			}
			
			modFile = new File(OutputFileConst.PRICEFILE_MODEL);
			if(!modFile.exists()){
				log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.PRICEFILE_MODEL);
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
	                   sheet=workbook.createSheet(OutputFileConst.PRICEFILE, 0);
	            }
	            
	          
				
	        

	            for(int i=0; i<list.size();i++){
	            	excelIOimpl.writeLabel(sheet,i+3,1,list.get(i).getAssetsName());
	            	excelIOimpl.writeLabel(sheet,i+3,2,list.get(i).getManufacture());
	            	excelIOimpl.writeLabel(sheet,i+3,3,list.get(i).getSpec());
	            	excelIOimpl.writeLabel(sheet,i+3,4,String.valueOf(list.get(i).getPrice()));
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

	@Override
	public File getQuotaFile() {
		
		File file = null;
		
		
		List<AssetsQuota> list =CacheContext.getInstance().getQuotaCache().getAllQuota();
		
		
		if(null==list||list.isEmpty()){
			//No Excist PriceInfo
			file = new File(OutputFileConst.QUOTAFILE_MODEL);
		}else{
			file = outputQuota(list);
		}
		return file;
	}

	private File outputQuota(List<AssetsQuota> list) {

		File file;
		File modFile;
		   	    
			
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
			
			/*
			 * 获取输出文件路径
			 */
			file = new File(OutputFileConst.QUOTAFILE);
			if(file.exists()){
				/*
				 * 删除原有文件，重新构造
				 */
				file.delete();
				file = new File(OutputFileConst.QUOTAFILE);
			}
			
			modFile = new File(OutputFileConst.QUOTAFILE_MODEL);
			if(!modFile.exists()){
				log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.QUOTAFILE_MODEL);
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
	                   sheet=workbook.createSheet(OutputFileConst.QUOTAFILE, 0);
	            }
	            
	          
				
	        

	            for(int i=0; i<list.size();i++){
	            	excelIOimpl.writeLabel(sheet,i+3,1,list.get(i).getAssetsName());
	            	excelIOimpl.writeLabel(sheet,i+3,2,list.get(i).getManufacture());
	            	excelIOimpl.writeLabel(sheet,i+3,3,list.get(i).getSpec());
	            	excelIOimpl.writeLabel(sheet,i+3,4,String.valueOf(list.get(i).getQuantity()));
	            	excelIOimpl.writeLabel(sheet,i+3,5,list.get(i).getDuty());
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

	@Override
	public void importQuotaAssest(File uploadFile) {

		AssetsQuotaDao assetsQuotaDao = new AssetsQuotaDaoImpl();
		List<AssetsQuota> lsit=readToQuotaList(uploadFile);
		for(AssetsQuota a:lsit){

			List<AssetsQuota> quota = assetsQuotaDao.getByFilter(a);

			if(ValidatorUtil.isEmpty(quota) && quota.contains(a))
			{
				assetsQuotaDao.saveOrUpdate(a);	
				
			}
			else
			{
				assetsQuotaDao.create(a);	
			}
				
		}
		return ;
	}

	private List<AssetsQuota> readToQuotaList(File uploadFile) {
		log.info(uploadFile.getAbsolutePath());
		
		List<AssetsQuota> assetsQuotaList = new ArrayList<AssetsQuota>();
	
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
			    	
				    	  /*
				    	   * 1.获取一条配置信息
				    	   */ 			    	
			    		    
			    	   		AssetsQuota assetsQuota = new  AssetsQuota();
			    		   
			    			/*
			    			 * 2.导入信息
			    			 */
			    		
			    			NumberCell numCell;
			    			

			    	 	   
			    	 	 cell = sheet.getCell(0,i);
			    	 	assetsQuota.setAssetsName(cell.getContents());
			    	 	 cell = sheet.getCell(1,i);
			    	 	assetsQuota.setManufacture(cell.getContents());
			    	 	 cell = sheet.getCell(2,i);
			    	 	assetsQuota.setSpec(cell.getContents());			    	 	 
			    	 	 cell = sheet.getCell(3,i);
			    	 	 numCell=(NumberCell)cell;
			    	 	assetsQuota.setQuantity((int)numCell.getValue());
			    	 	cell = sheet.getCell(4,i);
			    	 	assetsQuota.setDuty(cell.getContents());		
			    	 	
			    	 	assetsQuotaList.add(assetsQuota);
			    	 	 
			    	 	log.info("导入入第:"+i+"行"+assetsQuota.getAssetsName());
			    		  }	
			       
			}catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("Result:"+assetsQuotaList);
			return assetsQuotaList;
	}

}
