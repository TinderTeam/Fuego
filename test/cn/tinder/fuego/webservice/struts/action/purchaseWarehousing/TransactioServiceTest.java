package cn.tinder.fuego.webservice.struts.action.purchaseWarehousing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsPuchaseDeployBo;

public class TransactioServiceTest {

	public static List<AssetsPuchaseDeployBo> getAssetsPDBo() {
		// TODO Auto-generated method stub
		
		List<AssetsPuchaseDeployBo> l = new ArrayList<AssetsPuchaseDeployBo>();
		
		AssetsPuchaseDeployBo a = new AssetsPuchaseDeployBo();
		
		a.setIndex("1");
		a.setPurchasePlanName("A457898765");
		a.setCreateTime("23432");
		a.setCreateUser("Nan");
		a.setHandleUser("Zhang");
		
		AssetsPuchaseDeployBo b = new AssetsPuchaseDeployBo();
		
		b.setIndex("1");
		b.setPurchasePlanName("B457898765");
		b.setCreateTime("882636");
		b.setCreateUser("Tang");
		b.setHandleUser("Cao");
		
		l.add(a);
		l.add(b);
		
		return l;
	}

}
