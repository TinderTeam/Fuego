/**   
* @Title: CheckPlanServiceImpl.java 
* @Package cn.tinder.fuego.service.impl.plan 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 上午03:23:11 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl.plan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.CheckPlanDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.domain.po.CheckPlan;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.service.impl.util.ExcelIOServiceImpl;
import cn.tinder.fuego.service.util.ExcelIOService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckTransBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

/** 
 * @ClassName: CheckPlanServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午03:23:11 
 *  
 */

public class CheckPlanServiceImpl<E> extends TransactionServiceImpl implements TransPlanService<E>
{
	private static final Log log = LogFactory.getLog(CheckPlanServiceImpl.class);

	private TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();

	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	private CheckPlanDao checkPlanDao = DaoContext.getInstance().getCheckPlanDao();
	
	private PhysicalAssetsStatusDao assetsDao = DaoContext.getInstance().getPhysicalAssetsStatusDao();

	private AssetsManageService assetsManageService = ServiceContext.getInstance().getAssetsManageService();

	private ExcelIOService excelIOimpl= new ExcelIOServiceImpl();
	
 	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user)
	{
		CheckPlanBo plan = new CheckPlanBo();

		TransactionBaseInfoBo parentTrans = super.createTransByUserAndType(user,user, TransactionConst.CHECK_PLAN_TYPE,null);
		
		
		List<SystemUser> gasUserList = systemUserDao.getUserByRole(UserRoleConst.GASSTATION);
 		//create a transaction for every gas station
		
		boolean needCheck = false;
		
		 List<CheckPlan> checkPlanList = new ArrayList<CheckPlan>();
		for(SystemUser gasUser : gasUserList)
		{
			List<AssetsInfoBo> assestList = assetsManageService.getAssetsByDutyDept(gasUser.getDepartment());

 			if(null != assestList && !assestList.isEmpty())
 			{ 
 				TransactionBaseInfoBo trans = super.createTransByUserAndType(user,gasUser.getUserName(), TransactionConst.CHECK_PLAN_TYPE,parentTrans.getTransID());
 	 			needCheck = true;
 				checkPlanList.addAll(convertCheckPlanByBo(trans.getTransID(), assestList));
   			}
 			else
 			{
 				log.warn("the assests of " + gasUser + "is 0,no need to create a transaction.");
 			}
		}
		if(!needCheck)
		{	
			log.warn("there is no assets,no need to create a check plan");
			this.deletePlan(parentTrans.getTransID());
			throw new ServiceException(ExceptionMsg.NO_NEED_CHECK);
			
		}
		else
		{
            checkPlanDao.create(checkPlanList);

		}
	  	//checkPlanDao.create(checkPlanList);
  		plan.getTransInfo().setTransInfo(parentTrans);
		return (E)plan;
	}

	private List<CheckPlan> convertCheckPlanByBo(String transID, List<AssetsInfoBo> assestsInfoList)
	{
		List<CheckPlan> planList = new ArrayList<CheckPlan>();
		for(AssetsInfoBo assetsBo : assestsInfoList)
		{	
			CheckPlan checkPlan = new CheckPlan();
			checkPlan.setTransID(transID);
			checkPlan.setAssetsID(assetsBo.getAssets().getAssetsID());
			checkPlan.setDeptID(assetsBo.getAssets().getDuty());
			checkPlan.setAssetsName(assetsBo.getAssets().getAssetsName());
			checkPlan.setManufacture(assetsBo.getAssets().getManufacture());
			checkPlan.setSpec(assetsBo.getAssets().getSpec());
			checkPlan.setQuantity(assetsBo.getAssets().getQuantity());
			checkPlan.setCheckCnt(assetsBo.getExtAttr().getCheckCnt());
			checkPlan.setCheckState(assetsBo.getExtAttr().getCheckState());
			checkPlan.setNote(assetsBo.getExtAttr().getNote());
			planList.add(checkPlan);
		}

		return planList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#deletePlan(java.lang.String)
	 */
	@Override
	public void deletePlan(String transID)
	{
		super.deleteTransByID(transID);
		checkPlanDao.deleteByTransID(transID);
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#updatePlan(java.lang.Object)
	 */
	@Override
	public void updatePlan(E plan)
	{
		CheckPlanBo checkPlan = (CheckPlanBo)plan;
		
		List<CheckPlan> planList = new ArrayList<CheckPlan>();

		List<CheckTransBo>  childList = checkPlan.getTransInfo().getChildTransList();
		if(null == childList)
		{
			log.warn("the child is null,is not a parent transaction");
			checkPlanDao.deleteByTransID(checkPlan.getTransInfo().getTransInfo().getTransID());
			planList = convertCheckPlanByBo(checkPlan.getTransInfo().getTransInfo().getTransID(),checkPlan.getPlanInfo().getAssetsPage().getAssetsList());
//			if(checkPlan.getPlanInfo().getAssetsPage().isCheckFinished())
//			{
//				this.forwardNext(checkPlan.getTransInfo().getTransInfo().getTransID());
//			}
		}
//		else
//		{
//			Map<String,List<AssetsInfoBo>> deptMapAssestList = ConvertAssetsModel.convertAssestsListBoToDeptMap(checkPlan.getPlanInfo().getAssetsPage().getAssetsList());
//
// 
//			
// 			//get all the plan for every child transaction
//			for(CheckTransBo childTrans : childList)
//			{
//				planList.addAll(convertCheckPlanByBo(childTrans.getTransInfo().getTransID(),deptMapAssestList.get(childTrans.getTransInfo().getHandleUser())));
// 			}
//			//add the receive information in database.
//			
//		}
		checkPlanDao.create(planList);
		
	}
	@Override
	public void forwardNext(String transID)
	{
		forwardNext(transID,"");
	}
	
	public void forwardNextBySystem(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);

		super.forwardNext(transID,transEvent.getHandleUser(),null);

	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID,String transInfo)
	{
		
		TransEvent transEvent =transEventDao.getByTransID(transID);
		
		log.info("now the transaction need forward next."+transEvent);
		
		String handleUser;
		switch(transEvent.getCurrentStep())
		{
		case 2 :
		    handleUser = transEvent.getHandleUser();
		    break;
		case 1 :
		    handleUser = transEvent.getHandleUser();
		    break;
		default :
			handleUser = transEvent.getCreateUser();
			log.warn("the step i unexpected. step is" + transEvent.getCurrentStep());
			
		}
		super.forwardNext(transID,handleUser,transInfo);
		
		if(!super.hasChildTrans(transID))
		{
			log.info("the transation is child transaction,will update the parent transaction status" + transID);
			if(super.isParentTransFinish(transEvent.getParentTransID()))
			{
				log.info("the parent id is finish.the parent transaction id is " + transEvent.getParentTransID());
				forwardNext(transEvent.getParentTransID());
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanByTransID(java.lang.String)
	 */
	@Override
	public E getPlanByTransID(String transID)
	{
		TransactionBaseInfoBo baseTrans = super.getTransByID(transID);
		if(null == baseTrans)
		{
			return null;
		}
		CheckPlanBo plan = new CheckPlanBo();
		CheckTransBo checkTrans = new CheckTransBo();
		checkTrans.setTransInfo(baseTrans);
		plan.setTransInfo(checkTrans);

		List<TransEvent> childEventList = transEventDao.getTransByParentID(transID);

		if(null != childEventList)
		{
			 for(TransEvent event : childEventList)
			 {
				 if(null == checkTrans.getChildTransList())
				 {
					 checkTrans.setChildTransList(new ArrayList<CheckTransBo>());
				 }
				CheckTransBo child = new CheckTransBo();
				child.setTransInfo(super.getTransByID(event.getTransID()));
				checkTrans.getChildTransList().add(child);
			 } 
		}
 
		 List<CheckPlan> checkPlanList = getPlanListByTransIDList(Arrays.asList(transID));
  
		 plan.getPlanInfo().getAssetsPage().setAssetsList(convertCheckPlanList(checkPlanList));
		 //init the all page data
		 plan.getPlanInfo().getAssetsPage().getPage().setAllPageData(plan.getPlanInfo().getAssetsPage().getAssetsList());

	 	 return (E) plan;
	}
	private List<CheckPlan> getPlanListByTransIDList(List<String> transIDList)
	{
		//get the all the child list 
		List<String> allTransIDList = super.getChildTransIDByIDList(transIDList);
		List<CheckPlan> receivePlanList = checkPlanDao.getByTransID(allTransIDList);
		return receivePlanList;
	}

	private List<AssetsInfoBo> convertCheckPlanList(List<CheckPlan> checkPlanList)
	{
 		 
 		List<String> assetsIDList = new ArrayList<String>();
 		for(CheckPlan plan : checkPlanList)
		{
			 assetsIDList.add(plan.getAssetsID());
		}
 		
 		//for performance use batch query method 
		List<PhysicalAssetsStatus> assetsStatusList = assetsDao.getAssetsListByAssetsIDList(assetsIDList);
 
		List<AssetsInfoBo> assetsList = new ArrayList<AssetsInfoBo>();

		for(CheckPlan checkPlan : checkPlanList)
		{
			AssetsInfoBo assetsInfo = assetsManageService.getAseestByAssetsIDFromAssetsLIst(assetsStatusList, checkPlan.getAssetsID());

			if(null == assetsInfo)
			{
				assetsInfo = new AssetsInfoBo();
				assetsInfo.getAssets().setAssetsID(checkPlan.getAssetsID());
				assetsInfo.getAssets().setAssetsName(checkPlan.getAssetsName());
				assetsInfo.getAssets().setManufacture(checkPlan.getManufacture());
				assetsInfo.getAssets().setSpec(checkPlan.getSpec());
				assetsInfo.getAssets().setDuty(checkPlan.getDeptID());
			}
			assetsInfo.getExtAttr().setCheckState(checkPlan.getCheckState());
			assetsInfo.getExtAttr().setCheckCnt(checkPlan.getCheckCnt());
			assetsInfo.getExtAttr().setNote(checkPlan.getNote());
			assetsList.add(assetsInfo);
		}


		return assetsList;
	}
 

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getExportFile(java.lang.Object)
	 */
	@Override
	public File getExportFile(E plan)
	{
		
		if(null == plan)
		{
			log.warn("the plan is null");
			return null;
		}
		//step1: force transform 
		CheckPlanBo planBo = new CheckPlanBo();
 		planBo = (CheckPlanBo) plan;
		
		return getDownfile(planBo); 
		
	}

	private File getDownfile(CheckPlanBo planBo) {
		
		log.info("进入创建流程");
		File file;
		File modeFile;
		
		
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
		
		/*
		 * 获取输出文件路径
		 */
		file = new File(OutputFileConst.CHECK_FILE_PATH);
		if(file.exists()){
			/*
			 * 删除原有文件，重新构造
			 */
			file.delete();
			file = new File(OutputFileConst.CHECK_FILE_PATH);
		}
		
		modeFile = new File(OutputFileConst.CHECK_FILE_MODEL_PATH);
		if(!modeFile.exists()){
			log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.CHECK_FILE_MODEL_PATH);
			throw new ServiceException(ExceptionMsg.MODFILE_NOT_EXIST);
		}
		
		// 获取JXL模版
        Workbook modWorkBook;
		try {
			modWorkBook = Workbook.getWorkbook( modeFile );
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
                   sheet=workbook.createSheet(OutputFileConst.CHECK_FILE_PATH, 0);
            }
            
            
            
		/*
		 * 1.Get transID List
		 */
		List<CheckTransBo> checkTransBoList = planBo.getTransInfo().getChildTransList();
		
		/*
		 * 2.inport each CheckTransBo
		 */
		
		
		for(CheckTransBo checkTransBo:checkTransBoList){
			inportTransBo(sheet,checkTransBo);
		}
			
		
		   workbook.write();
           workbook.close();
           modWorkBook.close();
			
		
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				log.error(ExceptionMsg.FILE_ERR,e);
				e.printStackTrace();
			} catch (IOException e) {
				log.error(ExceptionMsg.FILE_ERR,e);
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return file;
	}

	private void inportTransBo(WritableSheet sheet, CheckTransBo checkTransBo) {
		/*
		 * import each transBo
		 */
		
		CheckPlanBo planBo =(CheckPlanBo) getPlanByTransID(checkTransBo.getTransInfo().getTransID());
		List<AssetsInfoBo> assetsList=planBo.getPlanInfo().getAssetsPage().getAssetsList();
		
		
		for(AssetsInfoBo assetsInfoBo:assetsList){
			inportALine(sheet,assetsInfoBo);
		}
		
		
	}

	private void inportALine(WritableSheet sheet, AssetsInfoBo assetsInfoBo) {
		/*
		 * inport a line 
		 */
		int newRow = 5;
		
		
		sheet.insertRow(newRow-1);
		excelIOimpl.writeLabel(sheet, newRow,1,assetsInfoBo.getAssets().getAssetsID());				
		excelIOimpl.writeLabel(sheet, newRow,2,assetsInfoBo.getAssets().getAssetsName());
		excelIOimpl.writeLabel(sheet, newRow,3,assetsInfoBo.getAssets().getAssetsSRC());
		excelIOimpl.writeLabel(sheet, newRow,4,assetsInfoBo.getAssets().getManufacture());
		excelIOimpl.writeLabel(sheet, newRow,5,assetsInfoBo.getAssets().getSpec());
		excelIOimpl.writeLabel(sheet, newRow,6,assetsInfoBo.getAssets().getUnit());
		
		excelIOimpl.writeLabel(sheet, newRow,7,String.valueOf(assetsInfoBo.getAssets().getQuantity()));
		excelIOimpl.writeLabel(sheet, newRow,8,assetsInfoBo.getAssets().getPurchaseDate());
		excelIOimpl.writeLabel(sheet, newRow,9,String.valueOf(assetsInfoBo.getAssets().getOriginalValue()));
		excelIOimpl.writeLabel(sheet, newRow,10,String.valueOf(assetsInfoBo.getAssets().getOriginalValue() ));
		excelIOimpl.writeLabel(sheet, newRow,11,assetsInfoBo.getAssets().getLocation());
		excelIOimpl.writeLabel(sheet, newRow,12,String.valueOf(assetsInfoBo.getAssets().getExpectYear()));
		excelIOimpl.writeLabel(sheet, newRow,13,assetsInfoBo.getAssets().getDueDate());
		excelIOimpl.writeLabel(sheet, newRow,14,assetsInfoBo.getAssets().getAssetsType());
	
		excelIOimpl.writeLabel(sheet, newRow,15,assetsInfoBo.getAssets().getAttrType());
		excelIOimpl.writeLabel(sheet, newRow,16,assetsInfoBo.getAssets().getDept());

		excelIOimpl.writeLabel(sheet, newRow,17,assetsInfoBo.getAssets().getTechState());
		excelIOimpl.writeLabel(sheet, newRow,18,assetsInfoBo.getAssets().getDuty());
		/*
		 * Get Date from title
		 */
		excelIOimpl.writeLabel(sheet, newRow,19,assetsInfoBo.getAssets().getCheckDate());
		
		excelIOimpl.writeLabel(sheet, newRow,20,assetsInfoBo.getExtAttr().getCheckState());
		
		excelIOimpl.writeLabel(sheet, newRow,21,String.valueOf(assetsInfoBo.getExtAttr().getCheckCnt()));
		
		excelIOimpl.writeLabel(sheet, newRow,22,assetsInfoBo.getExtAttr().getNote());


		
		
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID,String transInfo)
	{
		super.backward(transID,transInfo);
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#validate(java.lang.Object)
	 */
	@Override
	public void validate(E plan)
	{
		// TODO Auto-generated method stub
		
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

	@Override
	public List<AssetsInfoBo> importByFile(File file) {
		return null;
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#isMaxStep(int)
	 */
	@Override
	public int getMaxStep(String transID)
	{
		// TODO Auto-generated method stub
		return Integer.valueOf(TransactionConst.CHECK_MAX_STEP);
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getAprovalStep(java.lang.String)
	 */
	@Override
	public boolean isApporalStep(int step)
	{
		 
		return false;
	}

}
