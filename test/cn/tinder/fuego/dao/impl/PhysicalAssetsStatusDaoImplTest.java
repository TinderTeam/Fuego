/**   
 * @Title: PhysicalAssetsStatusDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-1 下午04:27:33 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import org.junit.Test;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;

/**
 * @ClassName: PhysicalAssetsStatusDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-1 下午04:27:33
 * 
 */
public class PhysicalAssetsStatusDaoImplTest
{
	PhysicalAssetsStatus pas = new PhysicalAssetsStatus();
	PhysicalAssetsStatusDaoImpl pasdi = new PhysicalAssetsStatusDaoImpl();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl#create(cn.tinder.fuego.domain.po.PhysicalAssetsStatus)} .
	 */
	@Test
	public void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		pas.setAssetsID("20122002");
		pas.setAssetsName("电脑");
		pas.setAssetsSRC("外购");
		pas.setAssetsType("IT");
		pas.setDept("dianzi");
		pas.setQuantity(2);

		pasdi.create(pas);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl#delete(cn.tinder.fuego.domain.po.PhysicalAssetsStatus)} .
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		pas.setAssetsID("20131001");
		pas.setAssetsName("电脑");
		pasdi.delete(pas);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl#getByAssetsID(java.lang.String)} .
	 */
	@Test
	public final void testGetByAssetsID()
	{
		// fail("Not yet implemented"); // TODO
		pas.setAssetsID("20131001");
		System.out.println(pasdi.getByAssetsID("20131001"));

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.PhysicalAssetsStatus)} .
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		pas.setAssetsID("20131001");
		pas.setAssetsName("电脑");
		pas.setAssetsSRC("外购");

		pasdi.saveOrUpdate(pas);

	}

	@Test
	public void testGetAssetsByDept()
	{
		System.out.println(pasdi.getAssetsByDept("tts"));

	}

}
