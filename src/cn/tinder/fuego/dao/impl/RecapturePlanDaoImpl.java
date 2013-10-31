package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.RecapturePlanDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.RecapturePlan;


/**
 * 
* @ClassName: DiscardPlanDaoImpl 
* @Description: TODO
* @author Li yong lei
* @date 2013-10-5 下午11:46:04 
*
 */
public class RecapturePlanDaoImpl implements RecapturePlanDao
{
	Log log = LogFactory.getLog(RecapturePlanDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#create(cn.tinder.fuego.domain.po. DiscardPlan)
	 */
	@Override
	public void create(RecapturePlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a RecapturePlan:" + plan.toString());
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
		log.debug("[DAO] Success! -Create a RecapturePlan:" + plan.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#delete(cn.tinder.fuego.domain.po. DiscardPlan)
	 */
	@Override
	public void delete(RecapturePlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the RecapturePlan:" + plan.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from RecapturePlan where trans_id=? and assets_id=? ";
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
			if (session != null)
			{
				session.close();
			}
		}

		log.debug("[DAO] Success!Delete the RecapturePlan:" + plan.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<RecapturePlan> getByTransID(String transID)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the TransID by ID:" + transID);
		List<RecapturePlan> recaptureList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(RecapturePlan.class);
			c.add(Restrictions.eq("transID", transID));

			recaptureList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get discardplan id by transid failed." + transID, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return recaptureList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.DiscardPlanDao#saveOrUpdate(cn.tinder.fuego.domain .po.DiscardPlan)
	 */
	@Override
	public void saveOrUpdate(RecapturePlan plan)
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
		log.debug("[DAO] Delete the RecapturePlan:" + transID.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from RecapturePlan where trans_id=?";
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
