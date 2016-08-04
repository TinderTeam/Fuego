package cn.tinder.fuego.webservice.struts.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanPageBo;

public class PurchasePlanRefSelectBoTest
{
	public static PurchasePlanPageBo getTestCase()
	{
		PurchasePlanPageBo ob = new PurchasePlanPageBo();

		List<String> typeList = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
		{
			typeList.add("TestTpye_" + i);
		}
		ob.setAssetsTpyeList(typeList);
		ob.setDepartmentName("TestDptName");
		return ob;
	}

	@Test
	public void boTest()
	{
		System.out.println(this.getTestCase().toString());

	}
}
