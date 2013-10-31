/**   
 * @Title: AssignPlanDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 下午12:41:53 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.AssignPlanDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.AssignPlan;

/**
 * @ClassName: AssignPlanDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 下午12:41:53
 * 
 */
public class AssignPlanDaoImplTest
{
	AssignPlan ap = new AssignPlan();
	private AssignPlanDao apdi = DaoContext.getInstance().getAssignPlanDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssignPlanDaoImpl#create(cn.tinder.fuego.domain.po.AssignPlan)} .
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		ap.setAssetsID("201310021312");
		ap.setInDeptID("002");
		ap.setNote("已使用三年");
		ap.setTransID("001310");
		apdi.create(ap);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssignPlanDaoImpl#delete(cn.tinder.fuego.domain.po.AssignPlan)} .
	 */
	@Test
	public final void testdelete()
	{
		// fail("Not yet implemented"); // TODO
		ap.setAssetsID("201310021309");
		ap.setInDeptID("001");
		ap.setNote("已使用三年");
		ap.setTransID("001308");
		apdi.delete(ap);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssignPlanDaoImpl#getByTransID(java.lang.String)} .
	 */
	@Test
	public final void testGetByTransID()
	{

		// fail("Not yet implemented"); // TODO
		ap.setAssetsID("201310021310");
		// ap.setInDeptID("001");
		// ap.setNote("已使用三年");
		ap.setTransID("001310");
		// System.out.println(apdi.getByTransID("001310"));
		List<AssignPlan> assignList = apdi.getByTransID("001310");
		System.out.println(assignList);
		assert (true);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssignPlanDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.AssignPlan)} .
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		// AssignPlan ap = null;

		// ap = apdi.getByPriKey("001310", "201310021312");
		ap.setAssetsID("201310021309");
		ap.setInDeptID("001");
		ap.setNote("已使用四年");
		ap.setTransID("001308");
		// apdi.delete(ap);

		apdi.saveOrUpdate(ap);
		System.out.println(ap);
	}
}
