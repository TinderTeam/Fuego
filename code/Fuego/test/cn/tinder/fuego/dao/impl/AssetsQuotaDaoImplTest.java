/**   
 * @Title: AssetsQuotaDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 下午09:24:39 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import org.junit.Test;

import cn.tinder.fuego.dao.AssetsQuotaDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.domain.po.AssetsQuota;

/**
 * @ClassName: AssetsQuotaDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 下午09:24:39
 * 
 */
public class AssetsQuotaDaoImplTest
{
	AssetsQuota aq = new AssetsQuota();
	private AssetsQuotaDao aqdi = DaoContext.getInstance().getAssetsQuotaDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl#create(cn.tinder.fuego.domain.po.AssetsQuota)}.
	 */
	@Test
	public final void testCreate()
	{
 
		aq.setSpec("A");
		aqdi.create(aq);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl#delete(cn.tinder.fuego.domain.po.AssetsQuota)}.
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
 
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl#getByAssetsName(java.lang.String)}.
	 */
	@Test
	public final void testGetByAssetsName()
	{
		// fail("Not yet implemented"); // TODO
		aq.setAssetsName("油箱");
		System.out.println(aqdi.getByAssetsName("油箱"));

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.AssetsQuota)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
		aq.setAssetsName("油罐");
		aq.setQuantity(10);
		aqdi.saveOrUpdate(aq);

	}

}
