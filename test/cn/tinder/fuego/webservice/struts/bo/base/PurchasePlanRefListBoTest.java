package cn.tinder.fuego.webservice.struts.bo.base;

import java.util.ArrayList;
import java.util.List;

public class PurchasePlanRefListBoTest
{
	public static List<PurchasePlanRefBo> getCase()
	{
		List<PurchasePlanRefBo> list = new ArrayList<PurchasePlanRefBo>();
		PurchasePlanRefBo ob = new PurchasePlanRefBo();
		ob.setIndex("1");
		ob.setAssetsID("1");
		ob.setAssetsName("AssetsName1");
		ob.setMoney("300");
		ob.setNote("Note1");
		list.add(ob);
		PurchasePlanRefBo ob2 = new PurchasePlanRefBo();
		ob2.setIndex("2");
		ob2.setAssetsID("2");
		ob2.setAssetsName("AssetsName2");
		ob2.setMoney("303");
		ob2.setNote("Note2");
		list.add(ob2);
		ob2 = new PurchasePlanRefBo();
		ob2.setAssetsID("3");
		ob2.setIndex("3");
		ob2.setAssetsName("AssetsName3");
		ob2.setMoney("3034");
		ob2.setNote("Note4");
		list.add(ob2);

		return list;

	}
}
