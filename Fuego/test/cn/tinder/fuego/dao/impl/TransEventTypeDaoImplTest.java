/**   
 * @Title: TransEventTypeDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-1 下午11:39:59 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.TransEventTypeDao;
import cn.tinder.fuego.domain.po.TransEventType;

/**
 * @ClassName: TransEventTypeDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-1 下午11:39:59
 * 
 */
public class TransEventTypeDaoImplTest
{
	TransEventType tet = new TransEventType();
	private TransEventTypeDao tetdi = DaoContext.getInstance().getTransEventTypeDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransEventTypeDaoImpl#create(cn.tinder.fuego.domain.po.TransEventType)} .
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		tet.setStep(10);
		tet.setType("D");
		tet.setCurrentID(10);
		tetdi.create(tet);
		System.out.println(tet);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransEventTypeDaoImpl#delete(cn.tinder.fuego.domain.po.TransEventType)} .
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		tet.setStep(2);
		tet.setType("B");
		tetdi.delete(tet);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransEventTypeDaoImpl#getByType(java.lang.String)} .
	 */
	@Test
	public final void testGetByType()
	{
		// fail("Not yet implemented"); // TODO
		tet.setType("A");
		System.out.println(tetdi.getByType("A"));
		System.out.println(tet);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransEventTypeDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.TransEventType)} .
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		tet.setStep(3);
		tet.setType("C");
		tetdi.saveOrUpdate(tet);
		System.out.println(tet);
	}

}
