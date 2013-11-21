/**   
 * @Title: DiscardPlanDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-27 上午12:11:44 
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

import cn.tinder.fuego.dao.DiscardPlanDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.DiscardPlan;

/**
 * @ClassName: DiscardPlanDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-27 上午12:11:44
 * 
 */
public class DiscardPlanDaoImpl implements DiscardPlanDao
{
	Log log = LogFactory.getLog(DiscardPlanDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#create(cn.tinder.fuego.domain.po. DiscardPlan)
	 */
	@Override
	public void create(DiscardPlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a DiscardPlan:" + plan.toString());
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
		log.debug("[DAO] Success! -Create a DiscardPlan:" + plan.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#delete(cn.tinder.fuego.domain.po. DiscardPlan)
	 */
	@Override
	public void delete(DiscardPlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the DiscardPlan:" + plan.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from DiscardPlan where trans_id=? and assets_id=? ";
			Query query = session.createQuery(hql);
			query.setString(0, plan.getTransID());
			query.setString(1, plan.getAssetsID());

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

		log.debug("[DAO] Success!Delete the DiscardPlan:" + plan.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<DiscardPlan> getByTransID(String transid)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the TransID by ID:" + transid);
		List<DiscardPlan> discardList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(DiscardPlan.class);
			c.add(Restrictions.eq("transID", transid));

			discardList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get discardplan id by transid failed." + transid, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return discardList;
	}
	@Override
	public List<DiscardPlan> getByTransID(List<String> transIDList)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the TransID by ID:" + transIDList);
		List<DiscardPlan> discardList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(DiscardPlan.class);
			c.add(Restrictions.in("transID", transIDList));

			discardList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get discardplan id by transid failed." + transIDList, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return discardList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#saveOrUpdate(cn.tinder.fuego.domain .po.DiscardPlan)
	 */
	@Override
	public void saveOrUpdate(DiscardPlan plan)
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

	@Override
	public void deleteByTransID(String transID)
	{
		log.debug("[DAO] Delete the DiscardPlan:" + transID.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from DiscardPlan where trans_id=?";
			Query query = session.createQuery(hql);
			query.setString(0, transID);

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
		
	}

}
