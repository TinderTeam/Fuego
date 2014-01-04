/**   
 * @Title: DiscardPlanServiceImpl.java 
 * @Package cn.tinder.fuego.service.impl.plan 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-1 下午11:20:31 
 * @version V1.0   
 */
package cn.tinder.fuego.service.impl.plan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.DiscardPlanDao;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.TransExtAttrDao;
import cn.tinder.fuego.domain.po.DiscardPlan;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.PurchasePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.cache.AssetsTypeParaCache;

import cn.tinder.fuego.service.cache.UserCache;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserNameConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardTransBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

/**
 * @ClassName: DiscardPlanServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-1 下午11:20:31
 * 
 */

public class DiscardPlanServiceImpl<E>extends TransactionServiceImpl implements TransPlanService<E>
{
	private static final Log log = LogFactory.getLog(CheckPlanServiceImpl.class);

	DiscardPlanDao discardPlanDao = DaoContext.getInstance().getDiscardPlanDao();
	TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();
	TransExtAttrDao transExtAtrrDao = DaoContext.getInstance().getTransExtAttrDao();
	SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	PhysicalAssetsStatusDao physicalAssetsStatusDao = DaoContext.getInstance().getPhysicalAssetsStatusDao();
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user)
	{
		DiscardPlanBo plan = new DiscardPlanBo();
		TransactionBaseInfoBo baseTrans = super.createTransByUserAndType(user,user, TransactionConst.DISCARD_PLAN_TYPE , null);
		DiscardTransBo discardPlan = new DiscardTransBo();
		discardPlan.setTransInfo(baseTrans);
		plan.setTransInfo(discardPlan);
		return (E) plan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#deletePlan(java.lang.String)
	 */
	@Override
	public void deletePlan(String transID)
	{
		super.deleteTransByID(transID);
		discardPlanDao.deleteByTransID(transID);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#updatePlan(java.lang.Object)
	 */
	@Override
	public void updatePlan(E plan)
	{
		if(null != plan)
		{
			log.warn("the plan is null");
		}
		//step1: force transform 
 		DiscardPlanBo planInfo = new DiscardPlanBo();
		planInfo = (DiscardPlanBo) plan;
		
		
		//step2: save basic transaction information
		String transID = planInfo.getTransInfo().getTransInfo().getTransID();
		String handleUser = planInfo.getTransInfo().getTransInfo().getHandleUser();
		super.updateTrans(transID,handleUser);

		//step3: save assign plan list
		discardPlanDao.deleteByTransID(transID);
		List<AssetsInfoBo> discardAssetsList = planInfo.getAssetsPage().getAssetsList();
		for (AssetsInfoBo assets: discardAssetsList)
		{
			DiscardPlan discardPlan = new DiscardPlan();
			discardPlan.setTransID(transID);
			discardPlan.setAssetsID(assets.getAssets().getAssetsID());
			discardPlan.setNote(assets.getExtAttr().getNote());
			discardPlanDao.saveOrUpdate(discardPlan);
		}

	}
	
	public void forwardNextBySystem(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);

		super.forwardNext(transID,transEvent.getHandleUser(),null);

	}
	@Override
	public void forwardNext(String transID)
	{
		forwardNext(transID,"");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID,String transInfo)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);
		List<DiscardPlan> planList = discardPlanDao.getByTransID(transID);
		  
		String type = "";
		if(null != planList && !planList.isEmpty())
		{	
			type = physicalAssetsStatusDao.getByAssetsID(planList.get(0).getAssetsID()).getAssetsType();
		}
		
		String handleUser;
 
		if(transEvent.getCurrentStep() == getMaxStep(transID))
		{
        	transInfo = TransactionConst.TRANS_OPERATE_SUBMIT;
		}
        switch(transEvent.getCurrentStep())
		{
        case 5 :
        	handleUser = AssetsTypeParaCache.getInstance().getDeptByType(type);
        	if(null == UserCache.getInstance().getUserByName(handleUser))
        	{
        		 log.warn("can not get the user by name." + handleUser);
        		 throw new ServiceException(ExceptionMsg.ASSETS_TYPE_WRONG);
        	}
        	if(UserNameConst.CWZCB.equals(handleUser))
        	{
        		super.forwardNext(transID,handleUser,transInfo);
            	transInfo = null;
        	}
        	break;
        case 4 :
        	handleUser = UserNameConst.CWZCB;
        	break;
		case 3 :
			 handleUser = super.getLeader(UserNameConst.CWZCB);
			break;	
		case 2 :
			handleUser = transEvent.getCreateUser();
			break;
		case 1 :
			handleUser = transEvent.getCreateUser();
			discardAssets(transID);
        	transInfo = TransactionConst.TRANS_OPERATE_FINISH;

		    break;
		default :
			handleUser = transEvent.getCreateUser();
		}
        
		super.forwardNext(transID,handleUser,transInfo);

	}

	private void discardAssets(String transID)
	{
		List<DiscardPlan> discardPlanList =  discardPlanDao.getByTransID(transID);
		List<String> assetsIDList = new ArrayList<String>();
		for(DiscardPlan assignPlan : discardPlanList)
		{
			String assetsID = assignPlan.getAssetsID();
		    assetsIDList.add(assetsID);
		    
		}
		physicalAssetsStatusDao.deleteAssetListsByAssetsIDList(assetsIDList);

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanByTransID(java.lang.String )
	 */
	@Override
	public E getPlanByTransID(String transID)
	{
		TransactionBaseInfoBo baseTrans = super.getTransByID(transID);
		if(null == baseTrans)
		{
			return null;
		}
		//new a DiscardPlan
		DiscardPlanBo discardPlan = new DiscardPlanBo();
		
		// get assetsStatusList by TransID
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(getAssetsIDListByTransID(transID));
		// get assetsListBo
		List<AssetsInfoBo> assetsListBo = null;
		if(null != assetsStatusList)
		{
			assetsListBo = ConvertAssetsModel.convertAssetsList(assetsStatusList);
		}
			 
 
		discardPlan.getAssetsPage().setAssetsList(assetsListBo);
		
		 //get discardTransBo by discardPlan 
		DiscardTransBo discardTrans = new DiscardTransBo();
		discardTrans.setTransInfo(baseTrans);
		discardPlan.setTransInfo(discardTrans);

		 //init the all page data
		 discardPlan.getAssetsPage().getPage().setAllPageData(discardPlan.getAssetsPage().getAssetsList());

		return (E) discardPlan;
	}
	private List<String> getAssetsIDListByTransID(String transID)
	{
		List<DiscardPlan> discardPlanList = discardPlanDao.getByTransID(transID);
		List<String> assetsIDList = new ArrayList<String>();
 		for(DiscardPlan discardAssetsPlan : discardPlanList)
		{
			 assetsIDList.add(discardAssetsPlan.getAssetsID());
		}
		return assetsIDList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getExportFile(java.lang.Object)
	 */
	@Override
	public File getExportFile(E plan)
	{
		if(null == plan)
		{
		
			return null;
		}
		//step1: force transform 
		DiscardPlanBo planInfo = new DiscardPlanBo();
		planInfo = (DiscardPlanBo) plan;
		
		
		
		return  getdownloadFile(planInfo);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID,String transInfo)
	{
		super.backward(transID,transInfo);
		
	}
	private File getdownloadFile(DiscardPlanBo planInfo){
		File file;
		File modFile;
		   	    
		log.info("���봴��Excel�ļ�����");
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
			
			/*
			 * ��ȡ����ļ�·��
			 */
			file = new File(OutputFileConst.DISCARD_PLAN_FILE_PATH);
			if(file.exists()){
				/*
				 * ɾ��ԭ���ļ������¹���
				 */
				file.delete();
				file = new File(OutputFileConst.DISCARD_PLAN_FILE_PATH);
			}
			
			modFile = new File(OutputFileConst.DISCARD_PLAN_FILE_MODEL_PATH);
		
			if(!modFile.exists()){
				log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.RECAPTUREPLAN_FILE_MODEL_PATH);
				
				throw new ServiceException(ExceptionMsg.MODFILE_NOT_EXIST);
			}
			
			// ��ȡJXLģ��
	        Workbook modWorkBook;
			try {
				modWorkBook = Workbook.getWorkbook( modFile );
				WritableWorkbook workbook = Workbook.createWorkbook(file, modWorkBook);
				
				
				
				/*
				 * ��ȡУ��
				 */
				if(modWorkBook==null){
					log.error("Mod �ļ��޷����� ΪExcel�ļ�");
				}
				if(workbook==null){
					log.error("�����ļ��޷����� ΪExcel�ļ�");
				}
				
				
				// ��ȡһ��Sheet ���ж�ȡ����
				WritableSheet sheet = null;
	            if (workbook.getNumberOfSheets() > 0){
	                   sheet = workbook.getSheet(0); // ��ȡ��һ��sheet
	            }else{
	                   sheet=workbook.createSheet(OutputFileConst.RECAPTUREPLAN_FILE_PATH, 0);
	            }
	            
	            //Bo
	        
	            
	            
	        	Date date = new Date(System.currentTimeMillis());
				
	            excelIOimpl.writeLabel(sheet,2,3, DateService.DateToLongString(date));
	            excelIOimpl.writeLabel(sheet,2,11,planInfo.getTransInfo().getTransInfo().getCreateUser());
	            /*
	   
				excelIOimpl.writeLabel(sheet, 6, 13, assignPlanBo.getTransInfo().getOutDept());
				excelIOimpl.writeLabel(sheet, 6,10, assignPlanBo.getTransInfo().getInDept());
				excelIOimpl.writeLabel(sheet, 2,13, assignPlanBo.getTransInfo().getTransInfo().getCreateUser());
				*/
				
				
				List<AssetsInfoBo> boList=planInfo.getAssetsPage().getAssetsList();
			
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
					excelIOimpl.writeLabel(sheet, 5, 9, String.valueOf(bo.getAssets().getOriginalValue()));				
					excelIOimpl.writeLabel(sheet, 5, 10, String.valueOf(bo.getAssets().getExpectYear()));
					excelIOimpl.writeLabel(sheet, 5, 11, DateService.DateToString(DateService.stringToDate(bo.getAssets().getPurchaseDate())));					
					excelIOimpl.writeLabel(sheet, 5, 12, DateService.DateToString(DateService.stringToDate(bo.getAssets().getDueDate())));
					excelIOimpl.writeLabel(sheet, 5, 13, bo.getAssets().getTechState());
					excelIOimpl.writeLabel(sheet, 5, 14, bo.getAssets().getNote());
				}

	            workbook.write();// ���޸ı��浽workbook --��һ��Ҫ����
	            workbook.close();// �ر�workbook���ͷ��ڴ� ---��һ��Ҫ�ͷ��ڴ�
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

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#validate(java.lang.Object)
	 */
	@Override
	public void validate(E plan)
	{
		DiscardPlanBo planInfo = (DiscardPlanBo)plan;
 		
 		List<AssetsInfoBo> assetsList = planInfo.getAssetsPage().getAssetsList();
 		if(null == assetsList || assetsList.isEmpty())
 		{
 			throw new ServiceException(ExceptionMsg.ASSETS_LIST_EMPTY);
 		}
 
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String, java.util.List)
	 */
	@Override
	public E createPlan(String user, List<String> childUserList)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanCount(java.util.List)
	 */
	@Override
	public int getPlanCount(List<String> transIDList)
	{
		List<DiscardPlan> planList = this.discardPlanDao.getByTransID(transIDList);
		if(null == planList)
		{
			return 0;
		}
		return planList.size();
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanAssetsSumValue(java.util.List)
	 */
	@Override
	public float getPlanAssetsSumValue(List<String> transIDList)
	{
		float sumValue = 0;

		List<DiscardPlan> planList = discardPlanDao.getByTransID(transIDList);
		List<String> assetsIDList = new ArrayList<String>();
 		for(DiscardPlan plan : planList)
		{
			 assetsIDList.add(plan.getAssetsID());
		}
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(assetsIDList);
		for(PhysicalAssetsStatus assets : assetsStatusList)
		{
			sumValue += assets.getOriginalValue()* assets.getQuantity();
		}
		return sumValue;
	}

	@Override
	public List<AssetsInfoBo> importByFile(File uploadFile) {
		//通过Excel文件导入
		
		List<AssetsBo> assetslist = new ArrayList<AssetsBo>();
		// TODO Auto-generated method stub
		log.info(uploadFile.getAbsolutePath());
		
 		List<String> assetsIDList = new ArrayList<String>();	
	    if (!uploadFile.getName().endsWith(".xls")){
	    	throw new ServiceException(ExceptionMsg.EXCEL_FORMART_WRONG+uploadFile.getName());
	    }
	     
	        // 2.判断文件是否存在
	    File excelFile = uploadFile;
	    if (!excelFile.exists()){
	    	throw new ServiceException(ExceptionMsg.FILEPATH_NOT_EXIST+ uploadFile.getAbsolutePath());
	    }
	    	
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
					try{
						cell = sheet.getCell(0,i);
						log.info(cell.getContents());
						assetsIDList.add(cell.getContents());			    	
					}catch(ServiceException ex){
						throw new ServiceException(ex.getMessage()+ExceptionMsg.ERR_ROW+String.valueOf(i),ex);
					}
				}   	   
 			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		/*
		 * 導入后查詢系統
		 */
			
		
			return  ConvertAssetsModel.convertAssetsList(physicalAssetsStatusDao.getAssetsListByAssetsIDList(assetsIDList));
		
		
			
			
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#isMaxStep(int)
	 */
	@Override
	public int getMaxStep(String transID)
	{
		TransactionBaseInfoBo baseTrans = super.getTransByID(transID);

		SystemUser user = UserCache.getInstance().getUserByName(baseTrans.getCreateUser());
		if(null == user)
		{
			log.error("can not find the user by user name " + baseTrans.getCreateUser());
		}
		else
		{	
			if(user.getRole().equals(UserRoleConst.GASSTATION))
			{
				return Integer.valueOf(TransactionConst.DISCARD_GAS_MAX_STEP);
			}
			else if(user.getRole().equals(UserRoleConst.DEPT))
			{
				return Integer.valueOf(TransactionConst.DISCARD_MAX_STEP);

			}
			else
			{
				return Integer.valueOf(TransactionConst.DISCARD_MAX_STEP);

			}
				
			
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getAprovalStep(java.lang.String)
	 */
	@Override
	public boolean isApporalStep(int step)
	{
		if(4 == step || 3 == step || 2 == step)
		{
			return true;
		}
		return false;
	}

	@Override
	public String getSumInfo(List<String> transIDList) {
		return "事务数量："+transIDList.size()+";涉及资产："+getPlanCount(transIDList)+";涉及金额："+getPlanAssetsSumValue(transIDList);
	}

}
