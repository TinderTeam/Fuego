/**   
 * @Title: AssignPlanDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-26 下午10:16:42 
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

import cn.tinder.fuego.dao.AssignPlanDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.AssignPlan;

/**
 * @ClassName: AssignPlanDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-26 下午10:16:42
 * 
 */
public class AssignPlanDaoImpl implements AssignPlanDao
{
	Log log = LogFactory.getLog(AssignPlanDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssignPlanDao#create(cn.tinder.fuego.domain.po.AssignPlan )
	 */
	@Override
	public void create(AssignPlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a AssignPlan:" + plan.toString());
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
		log.debug("[DAO] Success! -Create a AssignPlan:" + plan.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssignPlanDao#delete(cn.tinder.fuego.domain.po.AssignPlan )
	 */
	@Override
	public void delete(AssignPlan plan)
	{
		log.debug("[DAO] Delete the AssignPlan:" + plan.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from AssignPlan where trans_id=? and assets_id = ?";
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

		log.debug("[DAO] Success!Delete the AssignPlan:" + plan.toString());
	}

	@Override
	// public List<Integer> getMenuIDByRole(String role)
	public AssignPlan getByPriKey(String transID, String assetsID)
	{
		// TODO Auto-generated method stub

		AssignPlan assign;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(AssignPlan.class);
			c.add(Restrictions.eq("transID", transID));
			c.add(Restrictions.eq("assetsID", assetsID));

			assign = (AssignPlan) c.uniqueResult();

		} catch (RuntimeException e)
		{
			log.error("get assignplan id by transid failed.transID is :" + transID + "  sassetsID is:" + assetsID, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return assign;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssignPlanDao#getByTransID(java.lang.String)
	 */
	@Override
	// public List<Integer> getMenuIDByRole(String role)
	public List<AssignPlan> getByTransID(String transID)
	{
		// TODO Auto-generated method stub

		List<AssignPlan> assignList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(AssignPlan.class);
			c.add(Restrictions.eq("transID", transID));

			assignList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get assignplan id by transid failed." + transID, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return assignList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssignPlanDao#saveOrUpdate(cn.tinder.fuego.domain .po.AssignPlan)
	 */
	@Override
	public void saveOrUpdate(AssignPlan plan)
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
		log.debug("[DAO] Delete the AssignPlan:" + transID.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from AssignPlan where trans_id=?";
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

		log.debug("[DAO] Success!Delete the AssignPlan:" + transID.toString());
		
	}

	/**
	 * @return
	 */

}
