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
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import cn.tinder.fuego.dao.ReceivePlanDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.domain.po.AssignPlan;
import cn.tinder.fuego.domain.po.DiscardPlan;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
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
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceivePlanBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceivePlanInfoBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceiveTransBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.constant.OutputFileConst;

/** 
 * @ClassName: CheckPlanServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午03:23:11 
 *  
 */

public class ReceivePlanServiceImpl<E> extends TransactionServiceImpl implements TransPlanService<E>
{
	private static final Log log = LogFactory.getLog(ReceivePlanServiceImpl.class);

	private TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();

    private ReceivePlanDao receivePlanDao = DaoContext.getInstance().getReceivePlanDao();
    private PhysicalAssetsStatusDao physicalAssetsStatusDao = DaoContext.getInstance().getPhysicalAssetsStatusDao();

	private AssetsManageService assetsManageService = ServiceContext.getInstance().getAssetsManageService();

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user)
	{
		ReceivePlanBo plan = new ReceivePlanBo();
		
		TransactionBaseInfoBo trans = super.createTransByUserAndType(user,user, TransactionConst.RECEIVE_PLAN_TYPE,null);

		plan.getTransInfo().setTransInfo(trans);
		return (E) plan;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user,List<String> childUserList)
	{
		
		ReceivePlanBo plan = (ReceivePlanBo) this.createPlan(user);
 
		List<ReceiveTransBo> childTransList = new ArrayList<ReceiveTransBo>();
		for(String childUser : childUserList)
		{
			TransactionBaseInfoBo baseTrans = super.createTransByUserAndType(user,childUser, TransactionConst.RECEIVE_PLAN_TYPE,plan.getTransInfo().getTransInfo().getTransID());
			ReceiveTransBo receiveTrans  = new ReceiveTransBo();
			receiveTrans.setTransInfo(baseTrans);
			childTransList.add(receiveTrans);
		}
		
		plan.getTransInfo().setChildTransList(childTransList);
  		
 
		return (E)plan;
	}



	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#deletePlan(java.lang.String)
	 */
	@Override
	public void deletePlan(String transID)
	{
		super.deleteTransByID(transID);
		receivePlanDao.deleteByTransID(transID);
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#updatePlan(java.lang.Object)
	 */
	@Override
	public void updatePlan(E plan)
	{
		ReceivePlanBo receivePlan = (ReceivePlanBo)plan;
		
		List<ReceivePlan> planList = new ArrayList<ReceivePlan>();

		List<ReceiveTransBo>  childList = receivePlan.getTransInfo().getChildTransList();
		if(null == childList)
		{
			log.warn("the child is null,is not a parent transaction");
			receivePlanDao.deleteByTransID(receivePlan.getTransInfo().getTransInfo().getTransID());
			planList = convertReceivePlan(receivePlan.getPlanInfo().getAssetsPage().getAssetsList(),receivePlan.getTransInfo().getTransInfo().getTransID());
//			if(receivePlan.getPlanInfo().getAssetsPage().isReceiveFinished())
//			{
//				this.forwardNext(receivePlan.getTransInfo().getTransInfo().getTransID());
//			}
		}
		else
		{
			Map<String,List<AssetsInfoBo>> deptMapAssestList = ConvertAssetsModel.convertAssestsListBoToDeptMap(receivePlan.getPlanInfo().getAssetsPage().getAssetsList());

			assetsManageService.createAssetsList(receivePlan.getPlanInfo().getAssetsPage().getPage().getAllPageData());

			
 			//get all the plan for every child transaction
			for(ReceiveTransBo childTrans : childList)
			{
				planList.addAll(convertReceivePlan(deptMapAssestList.get(childTrans.getTransInfo().getHandleUser()), childTrans.getTransInfo().getTransID()));
 			}
			//add the receive information in database.
			
		}
		receivePlanDao.create(planList);
	}

	private List<ReceivePlan>  convertReceivePlan(List<AssetsInfoBo> assetsList, String transID)
	{ 
		List<ReceivePlan> planList = new ArrayList<ReceivePlan>();
		for(AssetsInfoBo assets : assetsList)
		{	
			ReceivePlan planDB = new ReceivePlan();
			planDB.setTransID(transID);
			planDB.setAssetsID(assets.getAssets().getAssetsID());
			planDB.setReceiveState(assets.getExtAttr().getReceiveState());
			planDB.setNote(assets.getExtAttr().getNote());
			planList.add(planDB);
		}
		return planList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);

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
		super.forwardNext(transID, handleUser);
		
		//update  parent transaction status.
		if(!super.hasChildTrans(transID))
		{
			if(super.isParentTransFinish(transEvent.getParentTransID()))
			{
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
		ReceivePlanBo plan = new ReceivePlanBo();
 
		plan.getTransInfo().setTransInfo(baseTrans);

		List<TransEvent> childEventList = transEventDao.getTransByParentID(transID);

		 for(TransEvent event : childEventList)
		 {
			 if(null == plan.getTransInfo().getChildTransList())
			 {
				 plan.getTransInfo().setChildTransList(new ArrayList<ReceiveTransBo>());
			 }
			ReceiveTransBo child = new ReceiveTransBo();
			child.setTransInfo(super.getTransByID(event.getTransID()));
			plan.getTransInfo().getChildTransList().add(child);
		 } 
		
		 List<AssetsInfoBo> assetsList = getAssetsListByTransID(Arrays.asList(transID));
		 plan.getPlanInfo().getAssetsPage().setAssetsList(assetsList);
		 //init the all page data
		 plan.getPlanInfo().getAssetsPage().getPage().setAllPageData(plan.getPlanInfo().getAssetsPage().getAssetsList());

	 
		return (E) plan;
	}

	private List<AssetsInfoBo> getAssetsListByTransID(List<String> transIDList)
	{
		
		List<ReceivePlan> receivePlanList = getPlanListByTransIDList(transIDList);
		 
 		List<String> assetsIDList = new ArrayList<String>();
 		for(ReceivePlan plan : receivePlanList)
		{
			 assetsIDList.add(plan.getAssetsID());
		}
 		
 		//for performance use batch query method 
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(assetsIDList);
		
		
		
		List<AssetsInfoBo> assetsList = new ArrayList<AssetsInfoBo>();
		for(ReceivePlan receivePlan : receivePlanList)
		{
			AssetsInfoBo assets = assetsManageService.getAseestByAssetsIDFromAssetsLIst(assetsStatusList, receivePlan.getAssetsID());
			if(assets == null)
			{
				assets = new AssetsInfoBo();
			}
 
 			assets.getExtAttr().setNote(receivePlan.getNote());
			assets.getExtAttr().setReceiveState(receivePlan.getReceiveState());
			assetsList.add(assets);
		}
		return  assetsList;
	}

	private List<ReceivePlan> getPlanListByTransIDList(List<String> transIDList)
	{
		List<String> allTransIDList = super.getChildTransIDByIDList(transIDList);

		List<ReceivePlan> receivePlanList = receivePlanDao.getByTransID(allTransIDList);
		return receivePlanList;
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
 		ReceivePlanBo planBo = new ReceivePlanBo();
 		planBo = (ReceivePlanBo) plan;
 	
		
		return getDownfile(planBo); 	
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#validate(java.lang.Object)
	 */
	@Override
	public void validate(E plan)
	{
		// TODO Auto-generated method stub
		
	}

	
	private File getDownfile(ReceivePlanBo planBo) {
		log.info("进入创建流程");
		File file;
		File modeFile;
		
		
		ExcelIOService excelIOimpl=new ExcelIOServiceImpl();
		
		/*
		 * 获取输出文件路径
		 */
		file = new File(OutputFileConst.RECIEVE_FILE_PATH);
		if(file.exists()){
			/*
			 * 删除原有文件，重新构造
			 */
			file.delete();
			file = new File(OutputFileConst.RECIEVE_FILE_PATH);
		}
		
		modeFile = new File(OutputFileConst.RECIEVE_FILE_MODEL_PATH);
		if(!modeFile.exists()){
			log.error(ExceptionMsg.MODFILE_NOT_EXIST+" FilePath="+OutputFileConst.RECIEVE_FILE_MODEL_PATH);
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
                   sheet=workbook.createSheet(OutputFileConst.RECIEVE_FILE_PATH, 0);
            }
            
            //文件内容
            
			Date date = new Date(System.currentTimeMillis());
			
			
			
			//导出日期
            excelIOimpl.writeLabel(sheet,2,1, DateService.DateToLongString(date));
            
            //导出油站名称
            excelIOimpl.writeLabel(sheet,2,1, DateService.DateToLongString(date));
            
			
			List<AssetsInfoBo> boList=planBo.getPlanInfo().getAssetsPage().getAssetsList();
			
			

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
				excelIOimpl.writeLabel(sheet, 5, 9, String.valueOf(bo.getAssets().getQuantity()));
				excelIOimpl.writeLabel(sheet, 5, 10, DateService.DateToString(DateService.stringToDate(bo.getAssets().getPurchaseDate())));
				excelIOimpl.writeLabel(sheet, 5, 11, String.valueOf(bo.getAssets().getOriginalValue()));	
				excelIOimpl.writeLabel(sheet, 5, 12, bo.getAssets().getLocation());
				excelIOimpl.writeLabel(sheet, 5, 13, String.valueOf(bo.getAssets().getExpectYear()));
				excelIOimpl.writeLabel(sheet, 5, 14, bo.getAssets().getDuty());
				excelIOimpl.writeLabel(sheet, 5, 15, bo.getExtAttr().getReceiveState());

				excelIOimpl.writeLabel(sheet, 5, 16, bo.getExtAttr().getNote());
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
	public int getPlanCount(List<String> transIDList)
	{
		int cnt = super.getAssetsCount(getAssestByTransIDList(transIDList));
	 
		
		return cnt;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanAssetsSumValue(java.util.List)
	 */
	@Override
	public float getPlanAssetsSumValue(List<String> transIDList)
	{
		float sumValue = super.getAssetsSumValue(getAssestByTransIDList(transIDList));
		return sumValue;
	}
	
	private List<PhysicalAssetsStatus> getAssestByTransIDList(List<String> transIDList)
	{
		List<ReceivePlan> planList = receivePlanDao.getByTransID(transIDList);
		List<String> assetsIDList = new ArrayList<String>();
 		for(ReceivePlan plan : planList)
		{
			 assetsIDList.add(plan.getAssetsID());
		}
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(assetsIDList);
		return assetsStatusList;
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
		return 0;
	}
}
