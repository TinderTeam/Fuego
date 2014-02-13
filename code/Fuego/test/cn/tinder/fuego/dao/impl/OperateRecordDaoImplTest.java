/**   
 * @Title: OperateRecordDaoImplTest.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-3 上午12:59:43 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.OperateRecordDao;
import cn.tinder.fuego.domain.po.OperateRecord;

/**
 * @ClassName: OperateRecordDaoImplTest
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-3 上午12:59:43
 * 
 */
public class OperateRecordDaoImplTest
{
	OperateRecord ord = new OperateRecord();
	private OperateRecordDao ordi = DaoContext.getInstance().getOperateRecordDao();

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.OperateRecordDaoImpl#create(cn.tinder.fuego.domain.po.OperateRecord)}.
	 */
	@Test
	public final void testCreate()
	{
		// fail("Not yet implemented"); // TODO
 
		ordi.create(ord);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.OperateRecordDaoImpl#delete(cn.tinder.fuego.domain.po.OperateRecord)}.
	 */
	@Test
	public final void testDelete()
	{
		// fail("Not yet implemented"); // TODO
 		ordi.delete(ord);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.OperateRecordDaoImpl#getByAssetsID(java.lang.String)}.
	 */
	@Test
	public final void testGetByAssetsID()
	{
		// fail("Not yet implemented"); // TODO
 		System.out.println(ordi.getByAssetsID("001"));
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.OperateRecordDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.OperateRecord)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		// fail("Not yet implemented"); // TODO
 
		ordi.saveOrUpdate(ord);
	}

}
