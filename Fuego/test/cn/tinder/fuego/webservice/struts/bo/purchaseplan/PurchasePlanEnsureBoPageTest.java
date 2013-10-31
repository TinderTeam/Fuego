package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanEnsureBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanEnsureBoTest;

public class PurchasePlanEnsureBoPageTest
{

	public static PurchasePlanEnsurePageBo getTestCase()
	{
		// TODO Auto-generated method stub
		PurchasePlanEnsurePageBo bo = new PurchasePlanEnsurePageBo();
		List<PurchasePlanEnsureBo> assetsList = new ArrayList<PurchasePlanEnsureBo>();

		PurchasePlanEnsureBo a = PurchasePlanEnsureBoTest.getTestCase();

		assetsList.add(a);
		assetsList.add(a);
		assetsList.add(a);
		bo.setAssetsList(assetsList);
		return bo;
	}

}
