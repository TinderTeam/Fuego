/**   
 * @Title: CheckPlanDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 下午05:46:16 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.CheckPlanDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.CheckPlan;

/**
 * @ClassName: CheckPlanDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 下午05:46:16
 * 
 */
public class CheckPlanDaoImplTest
{
	CheckPlan cp = new CheckPlan();
	private CheckPlanDao cpdi = DaoContext.getInstance().getCheckPlanDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.CheckPlanDaoImpl#create(cn.tinder.fuego.domain.po.CheckPlan)}.
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		cp.setDeptID("03");
		cp.setTransID("10021759");
		cp.setCheckState("OK");
		cpdi.create(cp);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.CheckPlanDaoImpl#delete(cn.tinder.fuego.domain.po.CheckPlan)}.
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		cp.setTransID("10021759");
		cp.setDeptID("02");
		cp.setCheckState("OK");
		cpdi.delete(cp);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.CheckPlanDaoImpl#getByTransID(java.lang.String)}.
	 */
	@Test
	public final void testGetByTransID()
	{
		// fail("Not yet implemented"); // TODO
		// cp.setDeptID("02");
		// cp.setTransID("10021759");
		// cp.setCheckState("OK");

		List<CheckPlan> checkList = cpdi.getByTransID("10021759");
		System.out.println(checkList);
		assert (true);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.CheckPlanDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.CheckPlan)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		cp.setDeptID("03");
		cp.setTransID("10021759");
		cp.setCheckState("NOK");
		cpdi.saveOrUpdate(cp);
	}

}
