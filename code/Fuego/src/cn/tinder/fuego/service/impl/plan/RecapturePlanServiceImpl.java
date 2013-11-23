package cn.tinder.fuego.service.impl.plan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.DiscardPlanDao;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.RecapturePlanDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.TransExtAttrDao;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.RecapturePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.download.AssetsStatuesFile;
import cn.tinder.fuego.webservice.struts.bo.download.AssignFile;
import cn.tinder.fuego.webservice.struts.bo.recapture.RecapturePlanBo;
import cn.tinder.fuego.webservice.struts.bo.recapture.RecaptureTransBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

public class RecapturePlanServiceImpl <E> extends TransactionServiceImpl implements TransPlanService<E>
{
	RecapturePlanDao recapturePlanDao = DaoContext.getInstance().getRecapturePlanDao();
	TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();
	TransExtAttrDao transExtAtrrDao = DaoContext.getInstance().getTransExtAttrDao();
	SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	PhysicalAssetsStatusDao physicalAssetsStatusDao = DaoContext.getInstance().getPhysicalAssetsStatusDao();
	
	private static final Log log = LogFactory.getLog(RecapturePlanServiceImpl.class);
	
	public E createPlan(String user)
	{
		RecapturePlanBo plan = new RecapturePlanBo();
		TransactionBaseInfoBo baseTrans = super.createTransByUserAndType(user,user,TransactionConst.RECAPTURE_PLAN_TYPE , null);
		RecaptureTransBo recapturePlan = new RecaptureTransBo();
		recapturePlan.setTransInfo(baseTrans);
		plan.setTransInfo(recapturePlan);
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
		recapturePlanDao.deleteByTransID(transID);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#updatePlan(java.lang.Object)
	 */
	@Override
	public void updatePlan(E plan)
	{
		//step1: force transform 
		RecapturePlanBo planInfo = new RecapturePlanBo();
		planInfo = (RecapturePlanBo) plan;
		
		
		//step2: save basic transaction information
		String transID = planInfo.getTransInfo().getTransInfo().getTransID();
		String handleUser = planInfo.getTransInfo().getTransInfo().getHandleUser();
		super.updateTrans(transID,handleUser);

		//step3: save assign plan list
		recapturePlanDao.deleteByTransID(transID);
		List<AssetsInfoBo> recaptureAssetsList = planInfo.getAssetsPage().getAssetsList();
		for (AssetsInfoBo assets: recaptureAssetsList)
		{
			RecapturePlan recapturePlan = new RecapturePlan();
			recapturePlan.setTransID(transID);
			recapturePlan.setAssetsID(assets.getAssets().getAssetsID());
			recapturePlan.setNote(assets.getExtAttr().getNote());
			recapturePlanDao.saveOrUpdate(recapturePlan);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);
		
		String handleUser;
        SystemUser systemUser = systemUserDao.find(transEvent.getCreateUser());
		String department = systemUser.getDepartment();
        switch(transEvent.getCurrentStep())
		{
		case 3 :
			 handleUser = super.getLeader(transEvent.getCreateUser());
			break;	
		case 2 :
			handleUser = transEvent.getCreateUser();
			break;
		case 1 :
			handleUser = transEvent.getCreateUser();
			RecaptureAssets(transID,department);
		    break;
		default :
			handleUser = transEvent.getCreateUser();
		}
        
		super.forwardNext(transID,handleUser);

	}

	private void RecaptureAssets(String transID , String department)
	{
		List<RecapturePlan> recapturePlanList =  recapturePlanDao.getByTransID(transID);
		AssetsBo assets = new AssetsBo();
		for(RecapturePlan assignPlan : recapturePlanList)
		{
			String assetsID = assignPlan.getAssetsID();
			PhysicalAssetsStatus assetsStatus = physicalAssetsStatusDao.getByAssetsID(assetsID);
			// update Duty department
			assetsStatus.setDuty(department);
			// update Location
			assetsStatus.setLocation(assets.getLocation());
			// set AssetsStatus to DB
			physicalAssetsStatusDao.saveOrUpdate(assetsStatus);
		    
		}

	}
	/*
	 * (non-Javadoc)
s	 * 
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
		RecapturePlanBo recapturePlan = new RecapturePlanBo();
		
		// get assetsStatusList by TransID
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(getAssetsIDListByTransID(transID));
		// get assetsListBo
		List<AssetsInfoBo> assetsListBo = null;
		if(null != assetsStatusList)
		{
			assetsListBo = ConvertAssetsModel.convertAssetsList(assetsStatusList);
		}
		//get AssetsPageBo and save to recapturePlan
 		recapturePlan.getAssetsPage().setAssetsList(assetsListBo);
		
		 //get recaptureTransBo by recapturePlan 
		RecaptureTransBo recaptureTrans = new RecaptureTransBo();
		recaptureTrans.setTransInfo(baseTrans);
		recapturePlan.setTransInfo(recaptureTrans);

		 //init the all page data
		recapturePlan.getAssetsPage().getPage().setAllPageData(recapturePlan.getAssetsPage().getAssetsList());

		return (E) recapturePlan;
	}
	private List<String> getAssetsIDListByTransID(String transID)
	{
		List<RecapturePlan> recapturePlanList = recapturePlanDao.getByTransID(transID);
		List<String> assetsIDList = new ArrayList<String>();
        String assetsID = null;
		for(RecapturePlan recaptureAssetsPlan : recapturePlanList)
		{
			 assetsID = recaptureAssetsPlan.getAssetsID();
			 assetsIDList.add(assetsID);
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
		RecapturePlanBo planInfo = new RecapturePlanBo();
		planInfo = (RecapturePlanBo) plan;
		
		
		
		return  getdownloadFile(planInfo);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID)
	{
		/**
		 * Issue #36
		 * need backward
		 */
		super.backward(transID);
	}
	
	
	
	private File getdownloadFile(RecapturePlanBo planInfo){
		File file;
		File modFile;
		   	    
		log.info("���봴��Excel�ļ�����");
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
			
			/*
			 * ��ȡ����ļ�·��
			 */
			file = new File(OutputFileConst.RECAPTUREPLAN_FILE_PATH);
			if(file.exists()){
				/*
				 * ɾ��ԭ���ļ������¹���
				 */
				file.delete();
				file = new File(OutputFileConst.RECAPTUREPLAN_FILE_PATH);
			}
			
			modFile = new File(OutputFileConst.RECAPTUREPLAN_FILE_MODEL_PATH);
			//modFile = new File("F:\\WorkPlace\\Fuego\\WebRoot\\files\\model\\RecapturePlanFileModel.xls");
			if(!modFile.exists()){
				//log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.RECAPTUREPLAN_FILE_MODEL_PATH);
				
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
		RecapturePlanBo planInfo = (RecapturePlanBo)plan;
 		
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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanAssetsSumValue(java.util.List)
	 */
	@Override
	public float getPlanAssetsSumValue(List<String> transIDList)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
