/**   
 * @Title: TransExtAttrDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 下午12:06:20 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.TransExtAttrDao;
import cn.tinder.fuego.domain.po.TransExtAttr;

/**
 * @ClassName: TransExtAttrDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 下午12:06:20
 * 
 */
public class TransExtAttrDaoImplTest
{
	TransExtAttr tea = new TransExtAttr();
	private TransExtAttrDao teadi = DaoContext.getInstance().getTransExtAttrDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransExtAttrDaoImpl#create(cn.tinder.fuego.domain.po.TransExtAttr)} .
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
		tea.setAttrName("饮水机");
		tea.setAttrValue("一台");
		tea.setTransID("004");
		teadi.create(tea);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransExtAttrDaoImpl#delete(cn.tinder.fuego.domain.po.TransExtAttr)} .
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		tea.setAttrName("饮水机");
		tea.setAttrValue("一台");
		tea.setTransID("001");
		teadi.delete(tea);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransExtAttrDaoImpl#getByTransID(java.lang.String)} .
	 */
	@Test
	public final void testGetByTransID()
	{
		// fail("Not yet implemented"); // TODO
		List<TransExtAttr> transList = teadi.getByTransID("001");
		System.out.println(transList);
		assert (true);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.TransExtAttrDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.TransExtAttr)} .
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		tea.setAttrName("饮水机");
		tea.setAttrValue("三台");
		tea.setTransID("001");
		teadi.saveOrUpdate(tea);
	}

}
