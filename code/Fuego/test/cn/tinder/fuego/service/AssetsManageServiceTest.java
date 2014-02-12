package cn.tinder.fuego.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.tinder.fuego.business.bo.Exceltest;
import cn.tinder.fuego.dao.AssetsQuotaDao;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl;
import cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl;
import cn.tinder.fuego.domain.po.AssetsQuota;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.impl.AssetsManageServiceImpl;
import cn.tinder.fuego.stub.domain.po.AssetsQuotaStub;
import cn.tinder.fuego.stub.domain.po.PhysicalAssetsStatusStub;
import cn.tinder.fuego.stub.strust.bo.base.SystemUserBoStub;
import cn.tinder.fuego.stub.strust.form.PurchasePlanFormStub;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

/**
 * 
* @ClassName: AssetsManageServiceTest 
* @Description:
* 1.生成采购计划测试
* @author Nan Bowen
* @date 2014-2-12 上午12:53:12 
*
 */
public class AssetsManageServiceTest {
	private static final Log log = LogFactory.getLog(AssetsManageServiceTest.class);
	static PhysicalAssetsStatusDao assetsDao = new PhysicalAssetsStatusDaoImpl();
	static AssetsQuotaDao assetsQuotaDao = new AssetsQuotaDaoImpl();
	
	AssetsManageService s = new AssetsManageServiceImpl();
	static List<AssetsQuota> quotaList = new ArrayList<AssetsQuota>();
	static List<PhysicalAssetsStatus> assetsList = new ArrayList<PhysicalAssetsStatus>();
	
	
	/* 
	 * 测试对象 
	 * public List<PurchasePlanBo> getRefPurchaseList(String userName,PurchasePlanForm purchasePlanForm);
	 * 根据测试Form里的条件生成采购计划
	 */


	
	@Test 
	public void testGetRefPurchaseList(){		
		
		List<PurchasePlanBo> resultList = s.getRefPurchaseList(SystemUserBoStub.getAdminUserName(), PurchasePlanFormStub.getRefCreateFrom());
		log.info(resultList);
	}

	
	@BeforeClass
	public static void beforeTestGetRefPurchaseList() {
		/*
		 * 在数据库、配置表中增加测试用资产
		 * 测试覆盖：
		 * 1.资产列表的缺资产，配置表存在
		 * 2.资产列表损坏的资产，配置表存在
		 * 
		 */
		
		quotaList.add(AssetsQuotaStub.getAssetsQuota("配置表存在资产", "", "", 3, "测试加油站"));
		
		assetsList.add(PhysicalAssetsStatusStub.getBasicAssetWithNameAndStatu("test1", "损坏的资产","损坏"));
		quotaList.add(AssetsQuotaStub.getAssetsQuota("损坏的资产", "", "", 3, "测试加油站"));	
		
		/*
		 *写入数据库 
		 */
		
		assetsDao.create(assetsList);
		assetsQuotaDao.create(quotaList);
		
	}
	
	
	@AfterClass
	public static void afterTestGetRefPurchaseList() {
		for(PhysicalAssetsStatus asset:assetsList){
			assetsDao.delete(asset);
		}
		for(AssetsQuota quota:quotaList){
			assetsQuotaDao.delete(quota);
		}
	}
	
}
