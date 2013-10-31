/**   
 * @Title: AssetsPriceDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-24 下午10:42:17 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import org.junit.Test;

import cn.tinder.fuego.domain.po.AssetsPrice;
import cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl;

/**
 * @ClassName: AssetsPriceDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-24 下午10:42:17
 * 
 */
public class AssetsPriceDaoImplTest
{
	AssetsPrice ap = new AssetsPrice();
	AssetsPriceDaoImpl apdimpl = new AssetsPriceDaoImpl();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl#create(cn.tinder.fuego.domain.po.AssetsPrice)} .
	 */
	@Test
	public void testCreate()
	{
		ap.setSpec("AAA");
		ap.setPrice(12.0f);

		apdimpl.create(ap);

	}

	// /**
	// // * Test method for {@link
	// cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl#delete(cn.tinder.fuego.domain.po.AssetsPrice)}.
	// // */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
		ap.setSpec("AAA");
		ap.setPrice(12.0f);

		apdimpl.delete(ap);

	}

	// ////
	// /**
	// * Test method for {@link
	// cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl#find(java.lang.String)}.
	// */
	@Test
	public void testGetBy()
	{
		// fail("Not yet implemented"); // TODO
		ap.setSpec("AAA");
		System.out.println(apdimpl.getBySpec("AAA"));

	}

	// /**
	// * Test method for {@link
	// cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.AssetsPrice)}.
	// */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		ap.setSpec("AAA");
		apdimpl.saveOrUpdate(ap);
	}
	//
}
