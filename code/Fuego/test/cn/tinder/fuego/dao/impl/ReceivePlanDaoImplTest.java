/**   
* @Title: ReceivePlanDaoImplTest.java 
* @Package cn.tinder.fuego.dao.impl 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-22 上午01:00:58 
* @version V1.0   
*/ 
package cn.tinder.fuego.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.DiscardPlanDao;
import cn.tinder.fuego.dao.ReceivePlanDao;
import cn.tinder.fuego.domain.po.DiscardPlan;
import cn.tinder.fuego.domain.po.ReceivePlan;

/** 
 * @ClassName: ReceivePlanDaoImplTest 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-22 上午01:00:58 
 *  
 */
public class ReceivePlanDaoImplTest
{
	ReceivePlan rp = new ReceivePlan();
	private ReceivePlanDao rpdi = DaoContext.getInstance().getReceivePlanDao();
	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl#create(cn.tinder.fuego.domain.po.ReceivePlan)}.
	 */
	@Test
	public final void testCreate()
	{
		rp.setAssetsID("e001");
		rp.setTransID("20131021");
		rp.setReceiveState("确认");
		rp.setNote("惠城加油站");
		rpdi.create(rp);
				
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl#delete(cn.tinder.fuego.domain.po.ReceivePlan)}.
	 */
	@Test
	public final void testDelete()
	{
		rp.setAssetsID("e001");
		rp.setTransID("20131021");
		rp.setReceiveState("确认");
		rp.setNote("惠城加油站");
		rpdi.delete(rp);
		
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl#deleteByTransID(java.lang.String)}.
	 */
	@Test
	public final void testDeleteByTransID()
	{
		rp.setAssetsID("e001");
		rp.setTransID("20131021");
		rp.setReceiveState("确认");
		rp.setNote("惠城加油站");
		rpdi.create(rp);
	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl#getByTransID(java.lang.String)}.
	 */
	@Test
	public final void testGetByTransID()
	{
		List<ReceivePlan> receiveList = rpdi.getByTransID("20131022");
		System.out.println(receiveList);
		assert (true);

	}

	/**
	 * Test method for {@link cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl#saveOrUpdate(cn.tinder.fuego.domain.po.ReceivePlan)}.
	 */
	@Test
	public final void testSaveOrUpdate()
	{
		rp.setAssetsID("e001");
		rp.setTransID("20131021");
		rp.setReceiveState("确认");
		rp.setNote("大亚湾加油站");
		rpdi.saveOrUpdate(rp);
	}

}
