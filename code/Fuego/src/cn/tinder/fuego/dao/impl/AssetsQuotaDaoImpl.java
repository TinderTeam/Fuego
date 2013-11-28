/**   
 * @Title: AssetsQuotaDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-26 上午01:14:56 
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

import cn.tinder.fuego.dao.AssetsQuotaDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.AssetsQuota;

/**
 * @ClassName: AssetsQuotaDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-26 上午01:14:56
 * 
 */
public class AssetsQuotaDaoImpl implements AssetsQuotaDao
{
	Log log = LogFactory.getLog(AssetsQuotaDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsQuotaDao#create(cn.tinder.fuego.domain.po. AssetsQuota)
	 */
	@Override
	public void create(AssetsQuota quota)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a AssetsQuota:" + quota.toString());
		try
		{
			HibernateUtil.add(quota);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a AssetsQuota:" + quota.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsQuotaDao#delete(cn.tinder.fuego.domain.po. AssetsQuota)
	 */
	@Override
	public void delete(AssetsQuota quota)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the DiscardPlan:" + quota.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from DiscardPlan where assets_name =? and  duty = ?";
			Query query = session.createQuery(hql);
			query.setString(0, quota.getAssetsName());
			query.setString(1, quota.getDuty());

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

		log.debug("[DAO] Success!Delete the DiscardPlan:" + quota.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsQuotaDao#getByAssetsName(java.lang.String)
	 */
	@Override
	public AssetsQuota getByAssetsName(String assetsname)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the AssetsQuota by Name:" + assetsname);
		Session s = null;
		// Transaction tx = null;
		// String hql = null;
		AssetsQuota quota = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsQuota.class);
			c.add(Restrictions.eq("assetsName", assetsname));//
			quota = (AssetsQuota) c.uniqueResult();
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			// HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
		if (quota != null)
		{
			log.debug("[DAO] Success!  get the AssetsName by Name:" + quota.toString());
		}
		return quota;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsQuotaDao#saveOrUpdate(cn.tinder.fuego.domain .po.AssetsQuota)
	 */
	@Override
	public void saveOrUpdate(AssetsQuota quota)
	{
		// TODO Auto-generated method stub
		try
		{
			this.delete(quota);
			this.create(quota);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.AssetsQuotaDao#getByDuty(java.lang.String)
	 */
	@Override
	public List<AssetsQuota> getByDuty(String duty)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the AssetsQuota by Name:" + duty);
		Session s = null;
		// Transaction tx = null;
		// String hql = null;
		List<AssetsQuota> quotaList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsQuota.class);
			c.add(Restrictions.eq("duty", duty));//
			quotaList = c.list();
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			// HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
		if (quotaList != null)
		{
			log.debug("[DAO] Success!  get the AssetsName by Name:" + quotaList.toString());
		}
		return quotaList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.AssetsQuotaDao#getAllAssetsQuota()
	 */
	@Override
	public List<AssetsQuota> getAllAssetsQuota()
	{
	 
		Session s = null;
		// Transaction tx = null;
		// String hql = null;
		List<AssetsQuota> quotaList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsQuota.class);
 			quotaList = c.list();
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			// HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
		if (quotaList != null)
		{
			log.debug("[DAO] Success!  get the AssetsName by Name:" + quotaList.toString());
		}
		return quotaList;
	}

}
