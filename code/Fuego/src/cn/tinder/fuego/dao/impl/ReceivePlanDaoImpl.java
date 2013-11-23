/**   
* @Title: ReceivePlanDaoImpl.java 
* @Package cn.tinder.fuego.dao.impl 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-22 上午12:45:42 
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

import cn.tinder.fuego.dao.ReceivePlanDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.ReceivePlan;

/** 
 * @ClassName: ReceivePlanDaoImpl 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-22 上午12:45:42 
 *  
 */
public class ReceivePlanDaoImpl implements ReceivePlanDao
{
	Log log = LogFactory.getLog(ReceivePlanDaoImpl.class);
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#create(cn.tinder.fuego.domain.po.ReceivePlan)
	 */
	@Override
	public void create(ReceivePlan plan)
	{
		log.debug("[DAO] Create a ReceivePlan:" + plan.toString());
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
		log.debug("[DAO] Success! -Create a ReceivePlan:" + plan.toString());

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#delete(cn.tinder.fuego.domain.po.ReceivePlan)
	 */
	@Override
	public void delete(ReceivePlan plan)
	{
		log.debug("[DAO] Delete the ReceivePlan:" + plan.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from ReceivePlan where trans_id=? and assets_id=? ";
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

		log.debug("[DAO] Success!Delete the ReceivePlan:" + plan.toString());

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#deleteByTransID(java.lang.String)
	 */
	@Override
	public void deleteByTransID(String transID)
	{
		log.debug("[DAO] Delete the ReceivePlan:" + transID.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from ReceivePlan where trans_id=?";
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

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<ReceivePlan> getByTransID(String transid)
	{
		log.debug("[DAO] get the TransID by ID:" + transid);
		List<ReceivePlan> receiveList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(ReceivePlan.class);
			c.add(Restrictions.eq("transID", transid));

			receiveList = c.list();

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

		return receiveList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#saveOrUpdate(cn.tinder.fuego.domain.po.ReceivePlan)
	 */
	@Override
	public void saveOrUpdate(ReceivePlan plan)
	{
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
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#create(java.util.List)
	 */
	@Override
	public void create(List<ReceivePlan> planlist)
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			for(ReceivePlan rplan : planlist)
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

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.ReceivePlanDao#getByTransID(java.util.List)
	 */
	@Override
	public List<ReceivePlan> getByTransID(List<String> transIDList)
	{
		log.debug("[DAO] get the TransID by ID:" + transIDList);
		List<ReceivePlan> receiveList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(ReceivePlan.class);
			c.add(Restrictions.in("transID", transIDList));

			receiveList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get ReceivePlan id by transaction id list failed." + transIDList, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return receiveList;
	}

}
