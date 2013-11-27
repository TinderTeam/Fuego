/**   
 * @Title: PurchasePlanDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-28 上午12:37:35 
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

import cn.tinder.fuego.dao.PurchasePlanDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.PurchasePlan;

/**
 * @ClassName: PurchasePlanDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-28 上午12:37:35
 * 
 */
public class PurchasePlanDaoImpl implements PurchasePlanDao
{
	Log log = LogFactory.getLog(PurchasePlanDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PurchasePlanDao#create(cn.tinder.fuego.domain.po. PurchasePlan)
	 */
	@Override
	public void create(PurchasePlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a PurchasePlan:" + plan.toString());
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
		log.debug("[DAO] Success! -Create a PurchasePlan:" + plan.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PurchasePlanDao#delete(cn.tinder.fuego.domain.po. PurchasePlan)
	 */
	@Override
	public void delete(PurchasePlan plan)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the PurchasePlan:" + plan.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from PurchasePlan where trans_id=? and assets_name = ?";
			Query query = session.createQuery(hql);
			query.setString(0, plan.getTransID());
			query.setString(1, plan.getAssetsName());

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

		log.debug("[DAO] Success!Delete the PurchasePlan:" + plan.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PurchasePlanDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<PurchasePlan> getByTransID(String transid)
	{
		// TODO Auto-generated method stub
		List<PurchasePlan> purchaseList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(PurchasePlan.class);
			c.add(Restrictions.eq("transID", transid));

			purchaseList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get purchaseplan id by transid failed." + transid, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return purchaseList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PurchasePlanDao#saveOrUpdate(cn.tinder.fuego.domain .po.PurchasePlan)
	 */
	@Override
	public void saveOrUpdate(PurchasePlan plan)
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

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.PurchasePlanDao#deleteByTransID(java.lang.String)
	 */
	@Override
	public void deleteByTransID(String transID)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the PurchasePlan:" + transID);

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from PurchasePlan where trans_id=?";
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

		log.debug("[DAO] Success!Delete the PurchasePlan:" + transID);

		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.PurchasePlanDao#getByTransID(java.util.List)
	 */
	@Override
	public List<PurchasePlan> getByTransID(List<String> transIDList)
	{
		List<PurchasePlan> purchaseList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(PurchasePlan.class);
			c.add(Restrictions.in("transID", transIDList));

			purchaseList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get purchaseplan id by transid failed." + transIDList, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return purchaseList;
	}

}
