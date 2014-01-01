/**   
 * @Title: AssetManageService.java 
 * @Package cn.tinder.fuego.service 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-25 上午01:08:58 
 * @version V1.0   
 */
package cn.tinder.fuego.service;

import java.io.File;
import java.util.List;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanInfoBo;
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;
import cn.tinder.fuego.webservice.struts.form.GasAssetsApplyForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchaseAssetsSelectForm;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

/**
 * @ClassName: AssetManageService
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-25 上午01:08:58
 * 
 */

public interface AssetsManageService
{
	public void createAssetsList(List<AssetsInfoBo> assetsList);
	
	public AssetsInfoBo getAssetsByAssetID(String assetsID);
	public AssetsInfoBo getAseestByAssetsIDFromAssetsLIst(List<PhysicalAssetsStatus> assetsStatusList,String assetsID);

	public AssetsInfoBo getNewAssetsByAssetsID(String assetsID);

	public List<AssetsInfoBo> getAssetsByDept(String deptName);
	
	public void importBasicAssest(File file);

	public AssetsPageBo getAssetsByFilter(AssetsFilterForm filter,boolean isAll);
	public List<AssetsInfoBo> getAssetsByDutyDept(String dutyDept);
	
	public List<AssetsInfoBo> getAssetsByAssetsIDList(String[] assestsList);
	
	public List<PurchasePlanBo> getPurchaseSumAssetsList(PurchaseAssetsSelectForm filter);

	public List<CheckPlanInfoBo> getCheckSumAssetsList(String dept);
	
  	public void updateAssets (AssetsInfoBo assetsInfo);
	public void deleteAssets(AssetsInfoBo assetsInfo);

 	public List<AssetsInfoBo> getDiscardAssetsListBo(String dueDate, List<String> assetsTypeList,List<String> statusList);
	public List<AssetsInfoBo> getRecaptureAssetsListBo(List<String> assetsDutyList, List<String> assetsTypeList);
	
	public List<String> getUserListByAssestList(List<AssetsInfoBo> assetsList);

	/*
	 * TASK #16 Story93_1: 实现资产的批量增加与修改
	 */
	//追加资产
	public void addBasicAssets(File uploadFile);
	//删除资产
	public void deleteBasicAssets(File uploadFile);
	//更新资产
	public void updateBasicAssets(File uploadFile);

	public List<PhysicalAssetsStatus> initAssetsID(List<PhysicalAssetsStatus> list);

	public List<PurchasePlanBo> getRefPurchaseList(PurchasePlanForm purchasePlanForm);
	
}
