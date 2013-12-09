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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.AssetsPriceDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.AssetsPrice;
import cn.tinder.fuego.domain.po.AssetsQuota;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.IDCreateService;
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.cache.CacheContext;
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.id.AssetsIDCreateServiceImpl;
import cn.tinder.fuego.service.model.DomainFilterModel;
import cn.tinder.fuego.service.model.PurchaseSumModel;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
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
	public AssetsPageBo getAssetsByFilter(String userName,AssetsFilterForm filter,boolean isAll)
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

		int count = assetsDao.getAssetsListByFilterCount(assetsFilter, assetsFilterDate,getDomainFilterByUser(userName));
		assetsPage.getPage().setCount(count);
		assetsPage.getPage().setCurrentPage(filter.getPageNum());
		List<PhysicalAssetsStatus> assetsList;
		if(isAll){
			assetsList = assetsDao.getAssetsListByFilter(assetsFilter, assetsFilterDate,getDomainFilterByUser(userName),0,count);								
		}else{
			assetsList = assetsDao.getAssetsListByFilter(assetsFilter, assetsFilterDate,getDomainFilterByUser(userName),assetsPage.getPage().getStartNum(),assetsPage.getPage().getPageSize());				
		}
	   assetsPage.setAssetsList(ConvertAssetsModel.convertAssetsList(assetsList));
	   return assetsPage;
	}
	
	private DomainFilterModel getDomainFilterByUser(String userName)
	{
		LoadService service = ServiceContext.getInstance().getLoadService();
		DomainFilterModel domainFilter = new DomainFilterModel();
		domainFilter.setDutyList(service.loadDeptInfoByUser(userName,false));
		domainFilter.setAssetsTypeList(service.loadAssetsTypeList(userName));
		domainFilter.setManageList(service.loadManageDeptList(userName, false));
		return domainFilter;
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


	
	
	
	public PurchasePlanBo getPurchaseFromList(List<PurchasePlanBo> planList,PurchasePlanBo sumModel)
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
		
		createAssetsByList(assetsList);
	}

	private void createAssetsByList(List<PhysicalAssetsStatus> assetsList) {
		try
		{
			assetsDao.create(assetsList);
		}
		catch(Exception e)
		{
			log.error("import assets failed.",e);
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

	/*
	 * TASK #16 Story93_1: 实现资产的批量增加与修改
	 * @see cn.tinder.fuego.service.SystemMaintanceService#addBasicAssets(java.io.File)
	 */
	@Override
	public void addBasicAssets(File uploadFile) {
		List<PhysicalAssetsStatus> assetsList = ImportBasicDataExcelFile.load(uploadFile);		
		initManageName(assetsList);
		//处理导入资产的资产ID
		List<PhysicalAssetsStatus> AssetsCreatedIDList = initAssetsID(assetsList); 
		//将资产导入系统
		createAssetsByList(AssetsCreatedIDList);
	}
/**
 * 将列表内的资产按照新增的方式初始化资产ID，如果固定资产已经有ID则不用处理直接保存。
 * @param assetsList
 * @return
 */
	public  List<PhysicalAssetsStatus> initAssetsID(
			List<PhysicalAssetsStatus> assetsList) {
		// TODO Auto-generated method stub
		//实例化编号服务
		IDCreateService idCreateService = new AssetsIDCreateServiceImpl();
		/*
		 * 统计类别资产出现的数量
		 */
		Map<String,Integer> styleNumMap = new HashMap<String,Integer>();
		
		for(PhysicalAssetsStatus asset:assetsList){
			if(
					//通过逻辑计算得出，ID空||不是固定资产
					(asset.getAssetsID()==null||asset.getAssetsID().isEmpty())
					||
					!asset.getAssetsType().equals(AssetsConst.ASSETS_GDZC_TYPE)	
			){
				//排出固定资产已有编号的情况
				if(styleNumMap.containsKey(asset.getAssetsType())){
					//已有类型
					//数量加1
					styleNumMap.put(asset.getAssetsType(), styleNumMap.get(asset.getAssetsType())+1);
					
				}else{
					//未出现类型
					styleNumMap.put(asset.getAssetsType(), 1);
				}
			}else{
				;
			}
		}	
		
		//根据数量生成编号组
		
		Map<String,Iterator<String> > IDMap = new HashMap<String,Iterator<String>>();
		Set set = styleNumMap.entrySet() ;
		java.util.Iterator it = styleNumMap.entrySet().iterator();
		while(it.hasNext()){
			java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
			IDMap.put(
					(String)entry.getKey(),
					idCreateService.createIDList((String)entry.getKey(),(Integer)entry.getValue()).iterator());
		} 
		
		
		//遍历资产更改ID
		Iterator assetIlerator=assetsList.iterator();
		while(assetIlerator.hasNext()){
			PhysicalAssetsStatus ast=(PhysicalAssetsStatus) assetIlerator.next();
			if(
				//通过逻辑计算得出，ID空||不是固定资产
				(ast.getAssetsID()==null||ast.getAssetsID().isEmpty())
				||
				!ast.getAssetsType().equals(AssetsConst.ASSETS_GDZC_TYPE)	
			){
				//获得新ID
				ast.setAssetsID( 
						IDMap.get(ast.getAssetsType()).next()
						);				
			}
		}
			
		return assetsList;
	}
	


	@Override
	public void deleteBasicAssets(File uploadFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBasicAssets(File uploadFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * TASK #7 #80 资产采购增加配置数量描述信息
	 */
	public List<PurchasePlanBo> getRefPurchaseList(String userName,PurchasePlanForm form) {
		
		/*
		 * 1.准备Form中的筛选条件
		 */

		Date 			dueDate;			//截止日期
		List<String> 	assetsTypeList;		//资产类型列表
		List<String>	techList;			//资产状态筛选列表 
		String 			duty;				//责任部门条件
		String 			manageName;			//经管部条件
		
		 
		dueDate = DateService.stringToDate(form.getDate());

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
		
		techList = new ArrayList<String>();
		techList.add(AssetsConst.ASSETS_STATUS_BAD);
		techList.add(AssetsConst.ASSETS_STATUS_DISCARD);
		
	
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
		
		
		/*
		 * 获取部门、经管部、资产类型筛选范围内的所有资产
		 * 相关表申明	
		 */
		
		List<PhysicalAssetsStatus> currentAssetsList; //现有资产总列表（注意：此处不包含对超期日期、资产状态的筛选）
		List<AssetsQuota> assetsQuotaList;		//现有资产总配置表（注意：此处不包含对超期日期、资产状态的筛选）
		
		List<PurchasePlanBo> purchasePlanList;	//需采购资产表

		//总表获取
		DomainFilterModel domainFilter = getDomainFilterByUser(userName);
		currentAssetsList = assetsDao.getAssetsListByDateOrStatuListAndTypeList(DateService.stringToDate(AssetsConst.ASSETS_LARGE_DATE), null, assetsTypeList,duty,manageName,domainFilter);
		assetsQuotaList = getQuotaListByDutyAndManageName(duty,manageName);
		
		/*
		 * 2.通过现有资产匹配出需采购的资产名称的PlanBo。其中包含生成 CQ 、 DQ 的数量
		 */
		purchasePlanList=getCurrentList(currentAssetsList,dueDate,assetsTypeList,techList);//获取统计范围内所有资产的数量信息
		
		/*
		 * 3.根据配置表生成采购清单
		 */
		purchasePlanList = getPurchasePlanListByCurrentAndQuota(assetsQuotaList,purchasePlanList);

		
  		return purchasePlanList;
		
	}
	
	/**
	 * 匹配建立采购表
	 * @param assetsQuotaList
	 * @param purchasePlanList
	 * @return
	 */
	private List<PurchasePlanBo> getPurchasePlanListByCurrentAndQuota(
			List<AssetsQuota> assetsQuotaList,
			List<PurchasePlanBo> currentPlanList) {
		// TODO Auto-generated method stub
		
		List<PurchasePlanBo> purchasePlanBoList = new ArrayList<PurchasePlanBo>();
		
		//生成采购匹配图
		Map<PurchaseSumModel,PurchasePlanBo> purchasePlanMap = new HashMap<PurchaseSumModel,PurchasePlanBo>();
		
	
		
		/*
		 * 将待匹配数据装入Map
		 */
		for(PurchasePlanBo crtPlanBo : currentPlanList){		
			PurchaseSumModel purchaseSumModel = new PurchaseSumModel();
			purchaseSumModel.setAssetsName(crtPlanBo.getAssetsBo().getAssetsName());
			purchaseSumModel.setManufacture(crtPlanBo.getAssetsBo().getManufacture());
			purchaseSumModel.setSpec(crtPlanBo.getAssetsBo().getSpec());
			purchaseSumModel.setGasName(crtPlanBo.getAssetsBo().getDuty());
			purchasePlanMap.put(purchaseSumModel, crtPlanBo);
		}
		
		
		/*
		 * 遍历配置表进行匹配
		 */
		
		
		for(AssetsQuota quota:assetsQuotaList){
			
			//准备匹配模式
			PurchaseSumModel quotaModel = new PurchaseSumModel();
			quotaModel.setAssetsName(quota.getAssetsName());
			quotaModel.setManufacture(quota.getManufacture());
			quotaModel.setSpec(quota.getSpec());
			quotaModel.setGasName(quota.getDuty());
			
			PurchasePlanBo planBo=purchasePlanMap.get(quotaModel);//进行匹配
			
			if(null==planBo){
				//采购表中无配置表内容（此项配置无实物，需采购）
				planBo = new PurchasePlanBo();	//新建一个PlanBo
				planBo.setAssetsBo(ConvertAssetsModel.convertAssets(quota));	//将这个Asset 转换为 Bo	
				
				assetsPriceInit(quota,  planBo);	//获取价格（价格表/原价格）
				/*
				 * 配置统计计数信息
				 */
				computeAssetsQuantityInfo(quota, planBo);	
				planBo.setQuotaQuantity(quota.getQuantity());
				int PQ=planBo.getQuotaQuantity()-(planBo.getCurrentQuantity()-planBo.getDisableQuantity());
				if(PQ>=0){
					planBo.getAssetsBo().setQuantity(
							//利用 QQ-(CQ-DQ)计算需采购的数量
							
							planBo.getQuotaQuantity()-(planBo.getCurrentQuantity()-planBo.getDisableQuantity())
					);
				}else{
					planBo.getAssetsBo().setQuantity(0);
				}
				
				planBo.countMoney();//计算总金额
				purchasePlanMap.put(quotaModel, planBo);
				
			}else{
				planBo.setQuotaQuantity(quota.getQuantity());
				int PQ=planBo.getQuotaQuantity()-(planBo.getCurrentQuantity()-planBo.getDisableQuantity());
				if(PQ>0){
					planBo.getAssetsBo().setQuantity(
							//利用 QQ-(CQ-DQ)计算需采购的数量
							
							planBo.getQuotaQuantity()-(planBo.getCurrentQuantity()-planBo.getDisableQuantity())
					);
				}else{
					planBo.getAssetsBo().setQuantity(0);
				}
				
				planBo.countMoney();//计算总金额
			}
		}
		
		List<PurchasePlanBo> mapList=new ArrayList<PurchasePlanBo>(purchasePlanMap.values());
		for( PurchasePlanBo bo:mapList){//将Map结果转入List
			if(bo.getAssetsBo().getQuantity()>0){
				purchasePlanBoList.add(bo);
			}
		}

		return purchasePlanBoList;
		
		
	}

	



	private void computeAssetsQuantityInfo(AssetsQuota quota,
			PurchasePlanBo planBo) {
		planBo.setQuotaQuantity(quota.getQuantity());
		planBo.setCurrentQuantity(0);
		planBo.setDisableQuantity(0);
	}

	private void assetsPriceInit(AssetsQuota quota, PurchasePlanBo planBo) {
		AssetsPrice assetPriceModel = setAssetPriceModel(quota.getAssetsName(),quota.getManufacture(),quota.getSpec());    //创建一个价格查询匹配模版			
		AssetsPrice assetPrice = assetsPriceDao.getByAssetsPrice(assetPriceModel); //通过匹配模版查询资产价格
		
		if(null == assetPrice){		//资产无价格的情况用原值进行计算
			log.warn("the price is null for the stuff" + quota.getAssetsName()+"-"+quota.getManufacture()+"-"+quota.getSpec());
			planBo.setPrice("0.0");
			//purchasePlan.countMoney(); 价格需要在汇总了配置表的情况下计算
		}
		else{	//有价格则取价格计算
			planBo.setPrice(String.valueOf(assetPrice.getPrice()));
			//purchasePlan.countMoney();  
		}
	}

	private List<AssetsQuota> getQuotaListByDutyAndManageName(String duty,
			String manageName) {
		
		//TODO 增加对经管部的处理
		
		List<AssetsQuota> quotaList=null;
		if(null == duty)	//根据责任部门获取配置表
		{	
			quotaList = CacheContext.getInstance().getQuotaCache().getAllQuota();			
		}
		else
		{
			quotaList = CacheContext.getInstance().getQuotaCache().getQuataByDept(duty);			
		}
		
		return quotaList;
	}



	/**
	 * 汇总并获取需采购列表的中间态(未和配置表做比较)
	 * @param currentAssetsList
	 * @param dueDate
	 * @param assetsTypeList
	 * @param techList
	 * @return
	 */
	private List<PurchasePlanBo> getCurrentList(List<PhysicalAssetsStatus> currentAssetsList,
			Date dueDate, List<String> assetsTypeList, List<String> techList) {
		
		List<PurchasePlanBo> purchasePlanBoList = new ArrayList<PurchasePlanBo>();
		
		//匹配模式图
		Map<PurchaseSumModel,PurchasePlanBo> purchasePlanMap = new HashMap<PurchaseSumModel,PurchasePlanBo>();
		
		
		/*
		 * 遍历现有表
		 */
		for(PhysicalAssetsStatus asset : currentAssetsList){
			
		
			//对符合汇总条件的进行
			
			PurchaseSumModel purchaseSumModel = new PurchaseSumModel();
			purchaseSumModel.setAssetsName(asset.getAssetsName());
			purchaseSumModel.setManufacture(asset.getManufacture());
			purchaseSumModel.setSpec(asset.getSpec());
			purchaseSumModel.setGasName(asset.getDuty());
			PurchasePlanBo purchasePlan;
			
			if(null != purchasePlanMap.get(purchaseSumModel))//汇总表中已有
			{
			    purchasePlan = purchasePlanMap.get(purchaseSumModel);
				/*
				 * 配置统计计数信息
				 */
			    computeAssetsQuantityInfo(asset, purchasePlan,dueDate);		
			    purchasePlan.getAssetsBo().setQuantity(purchasePlan.getDisableQuantity());
			    purchasePlan.countMoney();
		
			}else{	//汇总表中未出现
				
				purchasePlan = new PurchasePlanBo();	//新建一个PlanBo
				purchasePlan.setAssetsBo(ConvertAssetsModel.convertAssets(asset));	//将这个Asset 转换为 Bo	
				
				assetsPriceInit(asset,  purchasePlan);	//获取价格（价格表/原价格）
				/*
				 * 配置统计计数信息
				 */
				computeAssetsQuantityInfo(asset, purchasePlan,dueDate);	
				purchasePlan.getAssetsBo().setQuantity(purchasePlan.getDisableQuantity());
				purchasePlan.countMoney();
				purchasePlanMap.put(purchaseSumModel, purchasePlan);
			}
		}
		
		purchasePlanBoList = new ArrayList<PurchasePlanBo>(purchasePlanMap.values());	//将Map结果转入List
		
		
		
		return purchasePlanBoList;
		
	}

	private void computeAssetsQuantityInfo(PhysicalAssetsStatus asset,
			PurchasePlanBo purchasePlan,Date dueDate) {
		//已有数量增加
		purchasePlan.setCurrentQuantity(purchasePlan.getCurrentQuantity()+asset.getQuantity());
		//检查资产是否可以正常使用
		if(!checkFilter(asset,dueDate)){
			//不可正常使用 计入损坏/待报废/超期资产
			purchasePlan.setDisableQuantity(purchasePlan.getDisableQuantity()+asset.getQuantity());
		}
	}

	private void assetsPriceInit(PhysicalAssetsStatus asset,
			 PurchasePlanBo purchasePlan) {
		AssetsPrice assetPriceModel = setAssetPriceModel(asset.getAssetsName(),asset.getManufacture(),asset.getSpec());    //创建一个价格查询匹配模版			
		AssetsPrice assetPrice = assetsPriceDao.getByAssetsPrice(assetPriceModel); //通过匹配模版查询资产价格
		
		if(null == assetPrice){		//资产无价格的情况用原值进行计算
			log.warn("the price is null for the stuff" + asset.getAssetsName()+"-"+asset.getManufacture()+"-"+asset.getSpec());
			purchasePlan.setPrice(String.valueOf(asset.getOriginalValue()));
			//purchasePlan.countMoney(); 价格需要在汇总了配置表的情况下计算
		}
		else{	//有价格则取价格计算
			purchasePlan.setPrice(String.valueOf(assetPrice.getPrice()));
			//purchasePlan.countMoney();  
		}
	}
	/**
	 * 通过价格查询模版查询到资产价格
	 * TODO： 可拓展性，可以通过配置文件获取调节到匹配程度（名称、厂家、型号）
	 * @param asset
	 * @return
	 */
	private AssetsPrice setAssetPriceModel(String name,String manufacture,String spec) {
		
		AssetsPrice assetsPrice= new AssetsPrice();
		assetsPrice.setAssetsName(name);
		assetsPrice.setManufacture(manufacture);
		assetsPrice.setSpec(spec);
		
		return assetsPrice;
	}

	private boolean checkFilter(PhysicalAssetsStatus asset,Date dueDate) {
		if(AssetsConst.ASSETS_STATUS_BAD.equals(asset.getTechState())||AssetsConst.ASSETS_STATUS_DISCARD.equals(asset.getTechState())){
			return false;
		}else if(dueDate.after(asset.getDueDate())){
			return false;
		}
		return true;
	}



}
