package cn.tinder.fuego.service.model.convert;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.domain.po.OperateRecord;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;

public class ConvertOperateRecordToAssetModel
{
	static public List<PhysicalAssetsStatus> convertOperateRecordToAssetModel(List<OperateRecord> assetsStatusList){
		List<PhysicalAssetsStatus> list= new ArrayList<PhysicalAssetsStatus>();
		
		for(OperateRecord or:assetsStatusList){
			list.add(or.getAssets());
		}
		return list;
	}
}
