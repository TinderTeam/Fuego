package cn.tinder.fuego.service.impl;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsCreateBoTest;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.util.MyArrayList;

public class PurchasePlanServiceImplTest
{

	public static PurchasePlanSessionBo getRefBo()
	{
		PurchasePlanSessionBo ob = new PurchasePlanSessionBo();

		List<PurchasePlanBo> list = new MyArrayList(PurchasePlanBo.class);

		PurchasePlanBo a = AssetsCreateBoTest.getTestCase();
		a.setIndex(1);
		list.add(a);
		PurchasePlanBo b = AssetsCreateBoTest.getTestCase();
		b.setIndex(2);
		list.add(b);
		PurchasePlanBo c = AssetsCreateBoTest.getTestCase();
		c.setIndex(3);
		list.add(c);
		//ob.setAssetsList(list);

		return ob;
	}

	@Test
	public void test()
	{
		System.out.println(getRefBo());
	}

}
