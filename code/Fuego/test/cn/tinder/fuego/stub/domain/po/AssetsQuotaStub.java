package cn.tinder.fuego.stub.domain.po;

import cn.tinder.fuego.domain.po.AssetsQuota;

public class AssetsQuotaStub {
	public static AssetsQuota getAssetsQuota(String assetsName,String manufacture,String spec,int quantity,String duty){		
		AssetsQuota quota = new AssetsQuota();
		quota.setAssetsName(assetsName);
		quota.setManufacture(manufacture);
		quota.setSpec(spec);
		quota.setQuantity(quantity);
		quota.setDuty(duty);		
		return quota;		
	}
}
