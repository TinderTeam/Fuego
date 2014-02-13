package cn.tinder.fuego.stub.domain.po;

import java.sql.Date;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;

public class PhysicalAssetsStatusStub {
	public static PhysicalAssetsStatus getBasicAsset(String id){
		PhysicalAssetsStatus assets = new PhysicalAssetsStatus();
		assets.setAssetsID(id);
		assets.setAssetsName(id);
		assets.setAssetsSRC("测试来源");
		assets.setAssetsType("固定资产");
		assets.setAttrType("生产设施");
		assets.setCheckDate(new Date(System.currentTimeMillis()));
		assets.setDept("测试部门");
		assets.setDueDate(new Date(System.currentTimeMillis()));
		assets.setDuty("测试油站");
		assets.setExpectYear(10);
		assets.setLocation("地址");
		assets.setManageName("经管部");
		assets.setManufacture("测试生产厂家");
		assets.setNote("测试备注");
		assets.setOriginalValue(10000);
		assets.setPurchaseDate(new Date(System.currentTimeMillis()));
		assets.setQuantity(1);
		assets.setSpec("技术规格");
		assets.setTechState("正常");
		assets.setUnit("个");
		return assets;		
	}
	
	
	public  static PhysicalAssetsStatus getBasicAssetWithName(String id,String name){
		PhysicalAssetsStatus asset =getBasicAsset(id);
		asset.setAssetsName(name);
		return asset;	
	}


	public static PhysicalAssetsStatus getBasicAssetWithNameAndStatu(
			String id,String name, String status) {
		PhysicalAssetsStatus asset =getBasicAsset(id);
		asset.setAssetsName(name);
		asset.setTechState(status);
		return asset;
	}
	

}
