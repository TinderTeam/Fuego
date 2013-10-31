package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanEnsureBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanEnsureBoTest;
import cn.tinder.fuego.webservice.util.MyArrayList;

public class PurchasePlanEnsurePageBoTest
{

	public static PurchasePlanEnsurePageBo getTestCase()
	{
		PurchasePlanEnsurePageBo ob = new PurchasePlanEnsurePageBo();
		List<PurchasePlanEnsureBo> list = new MyArrayList(PurchasePlanEnsureBo.class);

		PurchasePlanEnsureBo a = PurchasePlanEnsureBoTest.getTestCase();
		a.setIndex("0");
		list.add(a);
		PurchasePlanEnsureBo b = PurchasePlanEnsureBoTest.getTestCase();
		b.setIndex("1");
		list.add(b);
		PurchasePlanEnsureBo c = PurchasePlanEnsureBoTest.getTestCase();
		c.setIndex("2");
		list.add(c);
		// ob.setAssetsList(assetsList);
		return ob;

	}
}
