/**   
 * @Title: PurchasePlanDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-3 上午12:34:41 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PurchasePlanDao;
import cn.tinder.fuego.domain.po.AssignPlan;
import cn.tinder.fuego.domain.po.PurchasePlan;

/**
 * @ClassName: PurchasePlanDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-3 上午12:34:41
 * 
 */
public class PurchasePlanDaoImplTest
{
	PurchasePlan pp = new PurchasePlan();
	private PurchasePlanDao ppdi = DaoContext.getInstance().getPurchasePlanDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PurchasePlanDaoImpl#create(cn.tinder.fuego.domain.po.PurchasePlan)}.
	 */
	@Test
	public final void testCreate()
	{
		
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PurchasePlanDaoImpl#delete(cn.tinder.fuego.domain.po.PurchasePlan)}.
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		pp.setTransID("01");
		pp.setAssetsName("手推车");
		ppdi.delete(pp);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PurchasePlanDaoImpl#getByTransID(java.lang.String)}.
	 */
	@Test
	public final void testGetByTransID()
	{
		// fail("Not yet implemented"); // TODO
		List<PurchasePlan> purchaseList = ppdi.getByTransID("02");
		System.out.println(purchaseList);
		assert (true);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PurchasePlanDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.PurchasePlan)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		pp.setAssetsName("手推车");

		pp.setNote("使用中");

		pp.setQuantity(7);

		pp.setTransID("02");

		ppdi.saveOrUpdate(pp);
		System.out.println(pp);
	}

}
