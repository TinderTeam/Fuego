package cn.tinder.fuego.webservice.struts.action.purchaseWarehousing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.WareHousingEnsureBo;

public class WareHousingEnsureTest {

	public static List<WareHousingEnsureBo> wareHousingEnsureBo() {
		// TODO Auto-generated method stub
		List<WareHousingEnsureBo> l = new ArrayList<WareHousingEnsureBo>();
		WareHousingEnsureBo a = new WareHousingEnsureBo();
		
		AssetsBo bo = new AssetsBo();
		
		bo.setAssetsID("2328979237");
		bo.setAssetsName("Car");
		bo.setAssetsSRC("Deploy");
		bo.setManufacture("Benz");
		bo.setSpec("uioa");
		bo.setUnit("ge");
		bo.setQuantity(3);
		bo.setPurchaseDate(DateService.DateToString(new Date(System.currentTimeMillis())));
		bo.setOriginalValue(320000);
		bo.setExpectYear(10);
		bo.setDueDate(DateService.DateToString(new Date(System.currentTimeMillis())));
		bo.setDept("Finance");
		bo.setDuty("nan");
		bo.setAssetsType("dizhiyihao");
		bo.setLocation("Shanghai");
		bo.setTechState("Good");
		
		a.setBo(bo);
		a.setIndex("1");
		
		l.add(a);

		return l;
	}

}
