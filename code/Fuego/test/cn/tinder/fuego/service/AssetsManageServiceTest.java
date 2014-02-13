package cn.tinder.fuego.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.tinder.fuego.business.bo.Exceltest;
import cn.tinder.fuego.dao.AssetsQuotaDao;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl;
import cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl;
import cn.tinder.fuego.dao.impl.SystemUserDaoImpl;
import cn.tinder.fuego.domain.po.AssetsQuota;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.impl.AssetsManageServiceImpl;
import cn.tinder.fuego.stub.domain.po.AssetsQuotaStub;
import cn.tinder.fuego.stub.domain.po.PhysicalAssetsStatusStub;
import cn.tinder.fuego.stub.domain.po.SystemUserStub;
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
	static SystemUserDao systemUserDao = new SystemUserDaoImpl();
	
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
		String userName = SystemUserBoStub.getTestUserName();
		PurchasePlanForm  form = PurchasePlanFormStub.getRefCreateFrom();
		
		List<PurchasePlanBo> resultList = s.getRefPurchaseList(userName, form);
		
		
		//断言
		Map<String,Integer> resultMap = new HashMap();
		
		for(PurchasePlanBo bo: resultList){
			resultMap.put(bo.getAssetsBo().getAssetsName(), bo.getAssetsBo().getQuantity());			
		}
		
		assertEquals((Integer)3,resultMap.get("配置表存在，实际不存在资产")); 
		assertEquals((Integer)1,resultMap.get("故障的资产")); 
		assertEquals((Integer)1,resultMap.get("配置表存在的资产")); 
		
	}

	
	@BeforeClass
	public static void beforeTestGetRefPurchaseList() {
		/*
		 * 在数据库、配置表中增加测试用资产
		 * 测试覆盖：
		 * 1.资产列表的缺资产，配置表存在
		 * 2.资产列表损坏的资产，配置表不存在
		 * 
		 */
		
		//配置表存在3个，故应该生成该计划3个
		quotaList.add(AssetsQuotaStub.getAssetsQuota("配置表存在，实际不存在资产", "测试生产厂家", "测试技术规格", 3, "测试加油站"));
		
		//配置表不存在，已有1个损坏,应该生成1个
		assetsList.add(PhysicalAssetsStatusStub.getBasicAssetWithNameAndStatu("test1", "故障的资产","故障"));
		
		//配置表存在3个实际存在2个,应该生成1个
		assetsList.add(PhysicalAssetsStatusStub.getBasicAssetWithNameAndStatu("test2", "配置表存在的资产","正常"));
		assetsList.add(PhysicalAssetsStatusStub.getBasicAssetWithNameAndStatu("test3", "配置表存在的资产","正常"));
		quotaList.add(AssetsQuotaStub.getAssetsQuota("配置表存在的资产", "测试生产厂家", "测试技术规格", 3, "测试加油站"));	
		
		

		
		/*
		 *写入数据库 
		 */
		systemUserDao.create(SystemUserStub.getGasstationUser());
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
		systemUserDao.delete(SystemUserStub.getGasstationUser());
	}
	
}
