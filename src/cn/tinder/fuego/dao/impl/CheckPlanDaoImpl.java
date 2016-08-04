/**   
 * @Title: CheckPlanDao.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-26 下午11:41:32 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.CheckPlanDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.CheckPlan;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.TransEvent;

/**
 * @ClassName: CheckPlanDao
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-26 下午11:41:32
 * 
 */
public class CheckPlanDaoImpl implements CheckPlanDao
{
	Log log = LogFactory.getLog(CheckPlanDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.CheckPlanDao#create(cn.tinder.fuego.domain.po.CheckPlan )
	 */
	@Override
	public void create(CheckPlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a CheckPlan:" + plan.toString());
		try
		{
			HibernateUtil.add(plan);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a CheckPlan:" + plan.toString());
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#create(java.util.List)
	 */
	@Override
	public void create(List<CheckPlan> planlist)
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			for(CheckPlan rplan : planlist)
			{
				session.save(rplan);
			}
 			tx.commit();
			
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			//  HibernateUtil.closeSession();
			if (session != null)
			{
				session.close();
			}
		}
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.CheckPlanDao#delete(cn.tinder.fuego.domain.po.CheckPlan )
	 */
	@Override
	public void delete(CheckPlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the CheckPlan:" + plan.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from CheckPlan where trans_id=? and dept_id=? ";
			Query query = session.createQuery(hql);
			query.setString(0, plan.getTransID());
			query.setString(1, plan.getDeptID());

			query.executeUpdate();

			tx.commit();
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			if(null != session)
			{
				session.close();
			}
		}

		log.debug("[DAO] Success!Delete the CheckPlan:" + plan.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.CheckPlanDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<CheckPlan> getByTransID(String transid)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the TransID by ID:" + transid);
		List<CheckPlan> checkList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(CheckPlan.class);
			c.add(Restrictions.eq("transID", transid));

			checkList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get checkplan id by transid failed." + transid, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return checkList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.CheckPlanDao#saveOrUpdate(cn.tinder.fuego.domain. po.CheckPlan)
	 */
	@Override
	public void saveOrUpdate(CheckPlan plan)
	{
		// TODO Auto-generated method stub
		try
		{
			delete(plan);
			create(plan);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}
	
	public List<CheckPlan> getByTransIDList(List<String> transIDList)
	{// TODO Auto-generated method stub
		log.debug(" get the plan  by ID list:" + transIDList);
		List<CheckPlan> checkList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(CheckPlan.class);
			c.add(Restrictions.in("transID", transIDList));

			checkList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get checkplan id by transid failed." + transIDList, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return checkList;
	}


}
