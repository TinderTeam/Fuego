/**   
 * @Title: AssetsManageServiceImpl.java 
 * @Package cn.tinder.fuego.service.impl 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-25 上午01:12:23 
 * @version V1.0   
 */
package cn.tinder.fuego.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.oscache.base.Cache;

import cn.tinder.fuego.dao.AssetsPriceDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.AssetsPrice;
import cn.tinder.fuego.domain.po.AssetsQuota;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.cache.CacheContext;
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.model.PurchaseSumModel;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanInfoBo;
import cn.tinder.fuego.webservice.struts.bo.excelimport.ImportBasicDataExcelFile;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchaseAssetsSelectForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

/**
 * @ClassName: AssetsManageServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-25 上午01:12:23
 * 
 */

public class AssetsManageServiceImpl implements AssetsManageService
{
	private static final Log log = LogFactory.getLog(AssetsManageServiceImpl.class);

	private AssetsPriceDao assetsPriceDao = DaoContext.getInstance().getAssetsPriceDao();

	private PhysicalAssetsStatusDao assetsDao = DaoContext.getInstance().getPhysicalAssetsStatusDao();
	private SystemUserDao userDao = DaoContext.getInstance().getSystemUserDao();


	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.AssetsManageService#getAssetsByDept(java.lang .String)
	 */
	@Override
	public List<AssetsInfoBo> getAssetsByDept(String dept)
	{
		// Beans
		List<PhysicalAssetsStatus> assetsList = null;

		assetsList = assetsDao.getAssetsByDept(dept);
		if (null == assetsList)
		{
			return null;
		}

		return ConvertAssetsModel.convertAssetsList(assetsList);
	}
	
	@Override
	public List<AssetsInfoBo> getAssetsByDutyDept(String dutyDept)
	{
		// Beans
		List<PhysicalAssetsStatus> assetsList = null;

		assetsList = assetsDao.getAssetsByDuty(dutyDept);
		if(null == assetsList)
		{
			return null;
		}

		return ConvertAssetsModel.convertAssetsList(assetsList);
	}

	@Override
	public AssetsPageBo getAssetsByFilter(AssetsFilterForm filter,boolean isAll)
	{
		PhysicalAssetsStatus assetsFilter = new PhysicalAssetsStatus();
		PhysicalAssetsStatus assetsFilterDate = new PhysicalAssetsStatus();
		if((null != filter.getAssetsID()) && (filter.getAssetsID().trim().isEmpty()))
		{
			assetsFilter.setAssetsID(null);
		
		}else{
			assetsFilter.setAssetsID(filter.getAssetsID());	
		}
		if((null != filter.getAssetsType()) && (filter.getAssetsType().trim().isEmpty()||AssetsConst.ASSETS_FITER_ALL.equals(filter.getAssetsType())))
		{	
			assetsFilter.setAssetsType(null);
			
		}else{
			assetsFilter.setAssetsType(filter.getAssetsType());
		}
		if((null != filter.getTechState()) && (filter.getTechState().trim().isEmpty()||AssetsConst.ASSETS_FITER_ALL.equals(filter.getTechState())))
		{	
			assetsFilter.setTechState(null);
		}else{
			assetsFilter.setTechState(filter.getTechState());
		}
		if((null != filter.getAssetsName()) && (filter.getAssetsName().trim().isEmpty()))
		{	
			assetsFilter.setAssetsName(null);
		}else{
			assetsFilter.setAssetsName(filter.getAssetsName());
		}
		if((null != filter.getDuty()) && (filter.getDuty().trim().isEmpty()||AssetsConst.ASSETS_FITER_ALL.equals(filter.getDuty())))
		{	
			assetsFilter.setDuty(null);
		}else{
			assetsFilter.setDuty(filter.getDuty());
		}
	
		if((null != filter.getLocation()) && (filter.getLocation().trim().isEmpty()))
		{	
			assetsFilter.setLocation(null);
		}else{
			assetsFilter.setLocation(filter.getLocation());
		}
		
		if((null != filter.getManageName()) && (filter.getManageName().trim().isEmpty()||AssetsConst.ASSETS_FITER_ALL.equals(filter.getManageName())))
		{	
			assetsFilter.setManageName(null);
		}else{
			assetsFilter.setManageName(filter.getManageName());
		}
		
		assetsFilter.setPurchaseDate(DateService.stringToDate(filter.getStartPurchaseDate()));
		assetsFilter.setDueDate(DateService.stringToDate(filter.getStartDueDate()));		
		assetsFilterDate.setPurchaseDate(DateService.stringToDate(filter.getEndPurchaseDate()));
		assetsFilterDate.setDueDate(DateService.stringToDate(filter.getEndDueDate()));

	    AssetsPageBo assetsPage = new AssetsPageBo();

		int count = assetsDao.getAssetsListByFilterCount(assetsFilter, assetsFilterDate);
		assetsPage.getPage().setCount(count);
		assetsPage.getPage().setCurrentPage(filter.getPageNum());
		List<PhysicalAssetsStatus> assetsList;
		if(isAll){
			assetsList = assetsDao.getAssetsListByFilter(assetsFilter, assetsFilterDate,0,count);								
		}else{
			assetsList = assetsDao.getAssetsListByFilter(assetsFilter, assetsFilterDate,assetsPage.getPage().getStartNum(),assetsPage.getPage().getPageSize());				
		}
	   assetsPage.setAssetsList(ConvertAssetsModel.convertAssetsList(assetsList));
	    return assetsPage;
	}

	public List<PurchasePlanBo> getPurchaseSumAssetsList(PurchaseAssetsSelectForm form)
	{
		if(null == form || null == form.getAssetsIDList() || 0 == form.getAssetsIDList().length)
		{
			log.warn("the form is null");
			return null;
		}
 
		List<PhysicalAssetsStatus> assetsList =  assetsDao.getAssetsListByAssetsIDList(Arrays.asList(form.getAssetsIDList()));

		return convertAndSumAssets(assetsList);
	}


	public List<PurchasePlanBo> getPurchaseSumAssetsList(PurchasePlanForm form)
	{
		
		Date dueDate = DateService.stringToDate(form.getDate());

		List<String> assetsTypeList = null;
		if(null == form.getTypeList() || form.getTypeList().length == 0)
		{
			log.warn("the type list is empty");
			assetsTypeList = null;
		}
		else
		{
			assetsTypeList = Arrays.asList(form.getTypeList());
		}
		if(assetsTypeList.contains(AssetsConst.ASSETS_FITER_ALL))
		{
			assetsTypeList = null;
		}
		
		List<String> techList = new ArrayList<String>();
		techList.add(AssetsConst.ASSETS_STATUS_BAD);
		techList.add(AssetsConst.ASSETS_STATUS_DISCARD);
		
		String duty = null;
		String manageName = null;
		if(form.getDuty().equals(AssetsConst.ASSETS_FITER_ALL))
		{
			duty = null;
		}
		else
		{
			duty = form.getDuty();
		}
		if(form.getManageName().equals(AssetsConst.ASSETS_FITER_ALL))
		{
			manageName = null;
		}
		else
		{
			manageName = form.getManageName();
		}
		
		List<PhysicalAssetsStatus> assetsList = assetsDao.getAssetsListByDateOrStatuListAndTypeList(dueDate, techList, assetsTypeList,duty,manageName);
		List<PurchasePlanBo> planList =  convertAndSumAssets(assetsList);

		//get the assets need to purchase by assets quota
		//1:get all the quata by
		List<PhysicalAssetsStatus> allAssetsList;
		List<AssetsQuota> quataList;
		if(null == duty)
		{	
			quataList = CacheContext.getInstance().getQuotaCache().getAllQuota();
			allAssetsList = assetsDao.getAssetsListByFilter(null, null, null, null);
		}
		else
		{
			quataList = CacheContext.getInstance().getQuotaCache().getQuataByDept(duty);
			allAssetsList = assetsDao.getAssetsByDept(duty);
		}
		
		
		List<PurchasePlanBo> quotaPlanList = null;
		
		
		for(PhysicalAssetsStatus physicalAssets : allAssetsList)
		{   
			PurchaseSumModel sumModel = new PurchaseSumModel();
			sumModel.setAssetsName(physicalAssets.getAssetsName());
			sumModel.setManufacture(physicalAssets.getManufacture());
			sumModel.setSpec(physicalAssets.getSpec());
			sumModel.setGasName(physicalAssets.getDept());
			PurchasePlanBo purchasePlan = getPurchaseFromList(quotaPlanList,sumModel);
			if(null != purchasePlan)
			{
				int cnt = purchasePlan.getAssetsBo().getQuantity();
				cnt -= physicalAssets.getQuantity();
				if(cnt<0)
				{
					cnt = 0;
				}
				purchasePlan.getAssetsBo().setQuantity(cnt); 
					
			}
		}
		for(PurchasePlanBo plan: quotaPlanList)
		{
			if(0 != plan.getAssetsBo().getQuantity())
			{
				PurchasePlanBo purchasePlan = getPurchaseFromList(planList,plan.getPurchaseSumModel());
				if(null == purchasePlan)
				{
					planList.add(plan);
				}
				else
				{
					purchasePlan.getAssetsBo().setQuantity(purchasePlan.getAssetsBo().getQuantity()+plan.getAssetsBo().getQuantity());
				}
			}
 
		}
		
		
  		return convertAndSumAssets(assetsList);
		 
	}
	
	public PurchasePlanBo getPurchaseFromList(List<PurchasePlanBo> planList,PurchaseSumModel sumModel)
	{
		for(PurchasePlanBo plan : planList)
		{
			if(plan.equals(sumModel))
			{
				return plan;
			}
		}
		return null;
	}
	
 
	
	public List<CheckPlanInfoBo> getCheckSumAssetsList(String dept)
	{
//		List<PhysicalAssetsStatus> assetsList = assetsDao.getAssetsByDuty(dept);
//		
//		CheckPlanPage checkPlanPage = new CheckPlanPage();
//		
//		for(PhysicalAssetsStatus assets :assetsList)
//		{
//			CheckPlanInfoBo checkInfo = new CheckPlanInfoBo();
//			checkInfo.setAssets(ConvertAssetsModel.convertAssets(assets));
//			checkInfo.setCheckCnt(checkInfo.getAssets().getQuantity());
//			checkInfo.setCheckState(AssetsConst.CHECK_STATUS_TODO);
//
//			CheckPlanInfoBo existCheck =  checkPlanPage.find(checkInfo);
//			if(null == existCheck)
//			{
//				checkPlanPage.getPlanList().add(checkInfo);
//			}
//			else
//			{
//				existCheck.getAssets().setQuantity(existCheck.getAssets().getQuantity()+1);
//				existCheck.setCheckCnt(existCheck.getAssets().getQuantity());
//				existCheck.setCheckState(AssetsConst.CHECK_STATUS_TODO);
//			}
//		}
//			
//		return checkPlanPage.getPlanList();
		return null;

	}


	private List<PurchasePlanBo> convertAndSumAssets(List<PhysicalAssetsStatus> assetsList)
	{
		//对资产进行汇总
		List<PurchasePlanBo> purchasePlanList;
		Map<PurchaseSumModel,PurchasePlanBo> purchasePlanMap = new HashMap<PurchaseSumModel,PurchasePlanBo>();
		
		int index = 1;
		for(PhysicalAssetsStatus assets : assetsList)
		{
			PurchaseSumModel purchaseSumModel = new PurchaseSumModel();
			purchaseSumModel.setAssetsName(assets.getAssetsName());
			purchaseSumModel.setManufacture(assets.getManufacture());
			purchaseSumModel.setSpec(assets.getSpec());
			purchaseSumModel.setGasName(assets.getDuty());
			PurchasePlanBo purchasePlan;
			if(null != purchasePlanMap.get(purchaseSumModel))
			{
			    purchasePlan = purchasePlanMap.get(purchaseSumModel);
				purchasePlan.getAssetsBo().setQuantity(purchasePlan.getAssetsBo().getQuantity()+1);
				purchasePlan.countMoney();
			}
			else
			{
				purchasePlan = new PurchasePlanBo();
				purchasePlan.setAssetsBo(ConvertAssetsModel.convertAssets(assets));
				AssetsPrice assetPrice = null; //assetsPriceDao.getBySpec(""); todo
				if(null == assetPrice)
				{	
					log.warn("the price is null for the stuff" + purchaseSumModel);
					purchasePlan.setPrice(String.valueOf(assets.getOriginalValue()));
					purchasePlan.countMoney();
				}
				else
				{
					float price = assetPrice.getPrice();
					purchasePlan.setPrice(String.valueOf(price));
					purchasePlan.countMoney();
				}
				//set index for page display
				purchasePlan.setIndex(index);
 				index++;
				purchasePlanMap.put(purchaseSumModel, purchasePlan);
			}
			if(AssetsConst.ASSETS_STATUS_DISCARD.equals(purchasePlan.getAssetsBo().getTechState()))
			{
				purchasePlan.setDiscardCnt(purchasePlan.getDiscardCnt()+1);
			}
			else if(AssetsConst.ASSETS_STATUS_BAD.equals(purchasePlan.getAssetsBo().getTechState()))
			{
				purchasePlan.setBadCnt(purchasePlan.getBadCnt()+1);
			}
			else
			{
				purchasePlan.setDueCnt(purchasePlan.getDueCnt()+1);
			}
		}
		purchasePlanList=new ArrayList(purchasePlanMap.values());
		return purchasePlanList;
	}

 
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#updateAssetsStatus(cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo)
	 */
	@Override
	public void updateAssets(AssetsInfoBo assetsInfo)
	{
		PhysicalAssetsStatus assetsUpdate = assetsDao.getByAssetsID(assetsInfo.getAssets().getAssetsID());
 
		if(null == assetsUpdate)
		{   			
			 this.createAssetsList(Arrays.asList(assetsInfo));
		}
		else
		{	
			PhysicalAssetsStatus assets = ConvertAssetsModel.convertAssetsBo(assetsInfo);

			assetsUpdate.setAssetsName(assets.getAssetsName());
			assetsUpdate.setAssetsSRC(assets.getAssetsSRC());
			assetsUpdate.setAssetsType(assets.getAssetsType());
			assetsUpdate.setTechState(assets.getTechState());
			assetsUpdate.setCheckDate(assets.getCheckDate());
			assetsUpdate.setDept(assets.getDept());
			assetsUpdate.setDueDate(assets.getDueDate());
			assetsUpdate.setDuty(assets.getDuty());
			assetsUpdate.setExpectYear(assets.getExpectYear());
			assetsUpdate.setLocation(assets.getLocation());
			assetsUpdate.setManufacture(assets.getManufacture());
			assetsUpdate.setNote(assets.getNote());
			assetsUpdate.setOriginalValue(assets.getOriginalValue());
			assetsUpdate.setPurchaseDate(assets.getPurchaseDate());
			assetsUpdate.setQuantity(assets.getQuantity());
			assetsUpdate.setSpec(assets.getSpec());
			assetsUpdate.setTechState(assets.getTechState());
			assetsUpdate.setUnit(assets.getUnit());
			assetsUpdate.setCheckDate(assets.getCheckDate());
			assetsDao.saveOrUpdate(assetsUpdate);
		}
		
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#getAssetsByAssetsIDList(java.util.List)
	 */
	@Override
	public List<AssetsInfoBo> getAssetsByAssetsIDList(String[] assetsIDList)
	{
		if((null == assetsIDList) || (assetsIDList.length == 0))
		{
			log.warn("the assest id list is empty");
			return null;
		}
		List<PhysicalAssetsStatus> assetsList =  assetsDao.getAssetsListByAssetsIDList(Arrays.asList(assetsIDList));

		return ConvertAssetsModel.convertAssetsList(assetsList);
	}
 

	@Override
	public List<AssetsInfoBo> getDiscardAssetsListBo(String dueDate, List<String> assetsTypeList,List<String> statusList)
	{
		return getAssetsListByFilterList(DateService.stringToDate(dueDate),assetsTypeList,null,statusList);
	}

	@Override
	public List<AssetsInfoBo> getRecaptureAssetsListBo(List<String> assetsDutyList, List<String> assetsTypeList)
	{
		
  		return 	getAssetsListByFilterList(null,assetsTypeList,assetsDutyList,null);

	}
	
	private List<AssetsInfoBo> getAssetsListByFilterList(Date dueDate, List<String> assetsTypeList,List<String> dutyList,List<String> statusList)
	{
		if(null != assetsTypeList && assetsTypeList.contains(AssetsConst.ASSETS_FITER_ALL))
		{
			assetsTypeList = null;
		}
		if(null != dutyList && dutyList.contains(AssetsConst.ASSETS_FITER_ALL))
		{	
			dutyList = null;
		}
		if(null != statusList && statusList.contains(AssetsConst.ASSETS_FITER_ALL))
		{
			statusList = null;
		}
		List<PhysicalAssetsStatus> assetsList = assetsDao.getAssetsListByFilter(dueDate,assetsTypeList,dutyList,statusList);
		return ConvertAssetsModel.convertAssetsList(assetsList);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#createAssetsList(java.util.List)
	 */
	@Override
	public void createAssetsList(List<AssetsInfoBo> assetsList)
	{
		
		 List<PhysicalAssetsStatus> physicalAssetsList = new ArrayList<PhysicalAssetsStatus>(); 
		 for(AssetsInfoBo assets : assetsList)
		 {
			 PhysicalAssetsStatus physicalAssets = ConvertAssetsModel.convertAssetsBo(assets);
			 
			 String manage = CacheContext.getInstance().getUserCache().getManageByUser(physicalAssets.getDuty());
			 
			 assets.getAssets().setManageName(manage);
			 physicalAssets.setManageName(manage);
 		 
			 physicalAssetsList.add(physicalAssets);
		 }
		 assetsDao.create(physicalAssetsList);
 
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#getAssetsByAssetID(java.lang.String)
	 */
	@Override
	public AssetsInfoBo getAssetsByAssetID(String assetsID)
	{
		PhysicalAssetsStatus assets =assetsDao.getByAssetsID(assetsID);
		AssetsInfoBo assetsInfo = new AssetsInfoBo();
		if(null != assets)
		{
			assetsInfo.setAssets(ConvertAssetsModel.convertAssets(assets));
		}
		else
		{
			log.warn("can not find the assets by assets id" + assetsID);
			return null;
		}
		return assetsInfo;
	}
	
	public AssetsInfoBo getAseestByAssetsIDFromAssetsLIst(List<PhysicalAssetsStatus> assetsStatusList,String assetsID)
	{
		AssetsInfoBo assets = null;
		for(PhysicalAssetsStatus physicalAssets : assetsStatusList)
		{
			if(physicalAssets.getAssetsID().equals(assetsID))
			{
				assets = new AssetsInfoBo();
				assets.setAssets(ConvertAssetsModel.convertAssets(physicalAssets));
				break;
			}
		}
 		return assets;
	}
	
	public void deleteAssets(AssetsInfoBo assetsInfo)
	{
		if(null == assetsInfo)
		{
			log.warn("the asset info is null");
		}
		PhysicalAssetsStatus assets = assetsDao.getByAssetsID(assetsInfo.getAssets().getAssetsID());
		assetsDao.delete(assets);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#importBasicAssest(java.io.File)
	 */
	@Override
	public void importBasicAssest(File file)
	{
		List<PhysicalAssetsStatus> assetsList = ImportBasicDataExcelFile.load(file);
		
		initManageName(assetsList);
		
		try
		{
			assetsDao.create(assetsList);
		}
		catch(Exception e)
		{
			log.error("import assets failed.",e);
			log.error(assetsList);
			String errMsg =e.getCause().getMessage();
			String arrStr[] =errMsg.split("'");
			String errID=null;
			if(arrStr.length>3){
				errID=arrStr[1];
			}
			
			throw new ServiceException(ExceptionMsg.ASSETS_NAME_ISEXIST+"("+errID+")");
		}
	}

	private void initManageName(List<PhysicalAssetsStatus> assetsList) {
		
		for(PhysicalAssetsStatus ast:assetsList){
			ast.setManageName(CacheContext.getInstance().getUserCache().getManageByUser(ast.getDuty()));
		}			
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#getUserListByAssestList(java.util.List)
	 */
	@Override
	public List<String> getUserListByAssestList(List<AssetsInfoBo> assetsList)
	{
		Set<String> userSet = new HashSet<String>(); 
		for(AssetsInfoBo assets : assetsList)
		{
			//这里应该取部门对应的用户名,当前部门名称就是用户名， 
			userSet.add(assets.getAssets().getDuty());
		 
		}
 
		return  new ArrayList(userSet);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.AssetsManageService#getNewAssetsByAssetsID(java.lang.String)
	 */
	@Override
	public AssetsInfoBo getNewAssetsByAssetsID(String assetsID)
	{
	   AssetsInfoBo assets = new AssetsInfoBo();

	   if(null == assetsID)
	   {	   
		 List<String> idList =  ServiceContext.getInstance().getAssetsIDCreateService().createIDList(AssetsConst.ASSETS_DZYH_TYPE, 1);
		 if(null != idList && !idList.isEmpty())
		 {
			 assets.getAssets().setAssetsID(idList.get(0));
		 }
		 return assets;
	   }
	   if(null == assetsDao.getByAssetsID(assetsID))
	   {
		   assets.getAssets().setAssetsID(assetsID);
		   return assets;
	   }
	   else
	   {
		   log.warn("the assets id is existed " + assetsID);
		   throw new ServiceException(ExceptionMsg.ASSETS_ID_ISEXIST);
	   }
		
	}


}
