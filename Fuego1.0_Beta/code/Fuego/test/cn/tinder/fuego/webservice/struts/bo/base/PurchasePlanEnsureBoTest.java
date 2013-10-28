package cn.tinder.fuego.webservice.struts.bo.base;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanEnsurePageBo;

public class PurchasePlanEnsureBoTest
{

	public static PurchasePlanEnsureBo getTestCase()
	{
		PurchasePlanEnsureBo ob = new PurchasePlanEnsureBo();

		ob.setAssetsID("ID" + System.currentTimeMillis());
		ob.setAssetsName("testName" + System.currentTimeMillis());
		ob.setManufacture("testManu");
		ob.setNote("testNote");
		ob.setPrice("20.00");
		ob.setQuantity("10");
		ob.setMoney("200");
		ob.setUnit("个");
		ob.setUseDepartment("test");

		return ob;

	}

}
