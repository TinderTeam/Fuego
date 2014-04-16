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
import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;
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
 

	public AssetsPageBo getAssetsByFilter(String userName,AssetsFilterForm filter,boolean isAll);
	public List<AssetsInfoBo> getAssetsByDutyDept(String dutyDept);
	
	public List<AssetsInfoBo> getAssetsByAssetsIDList(String[] assestsList);
	
 
	
  	public void updateAssets (AssetsInfoBo assetsInfo);
	public void deleteAssets(AssetsInfoBo assetsInfo);

 	
	public List<String> getUserListByAssestList(List<AssetsInfoBo> assetsList);

	
	public void importBasicAssest(File file);

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

	/**
	 * 
	 * @param userName
	 * 
	 * @param purchasePlanForm
	 * @return 参考计划的采购Bo列表
	 * @See AssetsManageService
	 * @描述  传入登陆用户用户名作为分权分域参数
	 * 传入Form作为筛选条件直接生产采购参考表。生成按照计算规则统计的采购计划
	 * 
	 */
	public List<PurchasePlanBo> getRefPurchaseList(String userName,PurchasePlanForm purchasePlanForm);
	
}
