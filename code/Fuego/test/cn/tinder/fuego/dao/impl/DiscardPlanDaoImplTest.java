/**   
 * @Title: DiscardPlanDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 下午07:23:03 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.DiscardPlanDao;
import cn.tinder.fuego.domain.po.DiscardPlan;

/**
 * @ClassName: DiscardPlanDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 下午07:23:03
 * 
 */
public class DiscardPlanDaoImplTest
{
	DiscardPlan dp = new DiscardPlan();
	private DiscardPlanDao dpdi = DaoContext.getInstance().getDiscardPlanDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.DiscardPlanDaoImpl#create(cn.tinder.fuego.domain.po.DiscardPlan)}.
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		dp.setAssetsID("100202");
		dp.setNote("使用中");
		dp.setTransID("01");
		dpdi.create(dp);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.DiscardPlanDaoImpl#delete(cn.tinder.fuego.domain.po.DiscardPlan)}.
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		dp.setAssetsID("100201");
		dp.setNote("待检");
		dp.setTransID("01");
		dpdi.delete(dp);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.DiscardPlanDaoImpl#getByTransID(java.lang.String)}.
	 */
	@Test
	public final void testGetByTransID()
	{
		// fail("Not yet implemented"); // TODO
		List<DiscardPlan> discardList = dpdi.getByTransID("01");
		System.out.println(discardList);
		assert (true);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.DiscardPlanDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.DiscardPlan)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		dp.setAssetsID("100202");
		dp.setNote("end");
		dp.setTransID("01");
		dpdi.saveOrUpdate(dp);
	}

}
