package cn.tinder.fuego.service.model.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.tinder.fuego.domain.po.AssetsQuota;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/**
 * 
 * @ClassName: ConvertAssetsModel
 * @Description: TODO
 * @author Li yong lei
 * @date 2013-10-1 下午09:07:56
 * 
 */

public class ConvertAssetsModel
{
	public static List<AssetsInfoBo> convertAssetsList(List<PhysicalAssetsStatus> assetsList)
	{
		List<AssetsInfoBo> assetsBoList = new ArrayList<AssetsInfoBo>();

		for (PhysicalAssetsStatus assets : assetsList)
		{
			AssetsBo assetsBo = convertAssets(assets);

			AssetsInfoBo assetsInfoBo = new AssetsInfoBo();
			assetsInfoBo.setAssets(assetsBo);

			assetsBoList.add(assetsInfoBo);

		}

		return assetsBoList;
	}

	public static AssetsBo convertAssets(PhysicalAssetsStatus assets)
	{
		AssetsBo assetsBo = new AssetsBo();
		assetsBo.setAssetsID(assets.getAssetsID());
		assetsBo.setAssetsName(assets.getAssetsName());
		assetsBo.setAssetsSRC(assets.getAssetsSRC());
		assetsBo.setAssetsType(assets.getAssetsType());
		assetsBo.setCheckDate(DateService.DateToString(assets.getCheckDate()));
		assetsBo.setDept(assets.getDept());
		assetsBo.setDueDate(DateService.DateToString(assets.getDueDate()));
		assetsBo.setDuty(assets.getDuty());
		assetsBo.setExpectYear(assets.getExpectYear());
		assetsBo.setLocation(assets.getLocation());
		assetsBo.setManageName(assets.getManageName());
		assetsBo.setManufacture(assets.getManufacture());
		assetsBo.setNote(assets.getNote());
		assetsBo.setOriginalValue(assets.getOriginalValue());
		assetsBo.setPurchaseDate(DateService.DateToString((assets.getPurchaseDate())));
		assetsBo.setQuantity(assets.getQuantity());
		assetsBo.setSpec(assets.getSpec());
		assetsBo.setTechState(assets.getTechState());
		assetsBo.setUnit(assets.getUnit());
		assetsBo.setAttrType((assets.getAttrType()));
		
		AssetsInfoBo assetsInfoBo = new AssetsInfoBo();
		assetsInfoBo.setAssets(assetsBo);

		return assetsBo;
	}
	
	public static List<PhysicalAssetsStatus> convertAssetsBoList(List<AssetsInfoBo> assetsList)
	{
		List<PhysicalAssetsStatus> physicalAssetsList = new ArrayList<PhysicalAssetsStatus>();

		for (AssetsInfoBo assets : assetsList)
		{
			PhysicalAssetsStatus physicalAssets = convertAssetsBo(assets);
 
			physicalAssetsList.add(physicalAssets);

		}

		return physicalAssetsList;
	}

	public static PhysicalAssetsStatus convertAssetsBo(AssetsInfoBo assetsInfoBo)
	{
		PhysicalAssetsStatus assets = new PhysicalAssetsStatus();
		AssetsBo assetsBo = assetsInfoBo.getAssets();
		assets.setAssetsID(assetsBo.getAssetsID());
		assets.setAssetsName(assetsBo.getAssetsName());
		assets.setAssetsSRC(assetsBo.getAssetsSRC());
		assets.setAssetsType(assetsBo.getAssetsType());
		assets.setCheckDate(DateService.stringToDate(assetsBo.getCheckDate()));
		assets.setDept(assetsBo.getDept());
		assets.setDueDate(DateService.addYear(assetsBo.getPurchaseDate(), assetsBo.getExpectYear()));
		assets.setDuty(assetsBo.getDuty());
		assets.setManageName(assets.getManageName());
		assets.setExpectYear(assetsBo.getExpectYear());
		assets.setLocation(assetsBo.getLocation());
		assets.setManufacture(assetsBo.getManufacture());
		assets.setNote(assetsBo.getNote());
		assets.setOriginalValue(assetsBo.getOriginalValue());
		assets.setPurchaseDate(DateService.stringToDate(assetsBo.getPurchaseDate()));
		assets.setQuantity(assetsBo.getQuantity());
		assets.setSpec(assetsBo.getSpec());
		assets.setTechState(assetsBo.getTechState());
		assets.setUnit(assetsBo.getUnit());
		return assets;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Map<String,List<AssetsInfoBo>> convertAssestsListBoToDeptMap(List<AssetsInfoBo> assetsList)
	{
		Map<String,List<AssetsInfoBo>> deptMapAssest = new HashMap<String,List<AssetsInfoBo>>();
		for(AssetsInfoBo assets :assetsList)
		{
			List<AssetsInfoBo> deptAssetsList = deptMapAssest.get(assets.getAssets().getDuty());
			if(null == deptAssetsList)
			{	
				deptAssetsList = new ArrayList<AssetsInfoBo>();
				deptAssetsList.add(assets);
				deptMapAssest.put(assets.getAssets().getDuty(), deptAssetsList);
			}
			else
			{
				deptAssetsList.add(assets);
			}
		}
		return deptMapAssest;
	}

	public static AssetsBo convertAssets(AssetsQuota quota) {
		AssetsBo assetsBo = new AssetsBo();
		assetsBo.setAssetsName(quota.getAssetsName());
		assetsBo.setDuty(quota.getDuty());		
		assetsBo.setManufacture(quota.getManufacture());
		assetsBo.setQuantity(quota.getQuantity());
		assetsBo.setSpec(quota.getSpec());
		return assetsBo;
	}
	

}
