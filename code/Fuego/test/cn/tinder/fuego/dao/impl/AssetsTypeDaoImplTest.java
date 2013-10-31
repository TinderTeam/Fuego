/**   
 * @Title: AssetsTypeDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 下午10:03:45 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.tinder.fuego.dao.AssetsTypeDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.AssetsType;

/**
 * @ClassName: AssetsTypeDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 下午10:03:45
 * 
 */
public class AssetsTypeDaoImplTest
{
	AssetsType at = new AssetsType();
	private AssetsTypeDao atdi = DaoContext.getInstance().getAssetsTypeDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsTypeDaoImpl#create(cn.tinder.fuego.domain.po.AssetsType)}.
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		at.setAttrID("20131003");
		at.setAttrName("打印机");
		at.setDeptID("01");
		at.setTypeID("0001");
		at.setTypeName("办公用品");
		atdi.create(at);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsTypeDaoImpl#delete(cn.tinder.fuego.domain.po.AssetsType)}.
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		at.setAttrID("20131003");
		at.setAttrName("打印机");
		at.setDeptID("01");
		at.setTypeID("0001");
		at.setTypeName("办公用品");
		atdi.delete(at);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsTypeDaoImpl#getByTypeName(java.lang.String)}.
	 */
	@Test
	public final void testGetByTypeID()
	{
		// fail("Not yet implemented"); // TODO
		at.setTypeID("0001");
		System.out.println(atdi.getByTypeName("0001"));
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsTypeDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.AssetsType)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		at.setAttrID("20131003");
		at.setAttrName("打印机");
		at.setDeptID("02");
		at.setTypeID("0001");
		at.setTypeName("办公用品");
		atdi.saveOrUpdate(at);
	}

}
