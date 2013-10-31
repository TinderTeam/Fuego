/**   
 * @Title: TransEventTypeDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-28 上午01:53:42 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.TransEventTypeDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.TransEventType;

/**
 * @ClassName: TransEventTypeDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-28 上午01:53:42
 * 
 */
public class TransEventTypeDaoImpl implements TransEventTypeDao
{
	Log log = LogFactory.getLog(TransEventTypeDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransEventTypeDao#create(cn.tinder.fuego.domain.po .TransEventType)
	 */
	@Override
	public void create(TransEventType transtype)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a TransEventType:" + transtype.toString());
		try
		{
			HibernateUtil.add(transtype);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a TransEventType:" + transtype.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransEventTypeDao#delete(cn.tinder.fuego.domain.po .TransEventType)
	 */
	@Override
	public void delete(TransEventType transtype)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the TransEventType:" + transtype.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(TransEventType.class, transtype.getType());

			session.delete(classObj);

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
		log.debug("[DAO] Success!Delete the TransEventType:" + transtype.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransEventTypeDao#getByType(java.lang.String)
	 */
	@Override
	public TransEventType getByType(String type)
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the TransEventType by type:" + type);
		Session s = null;

		TransEventType transtype = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(TransEventType.class);
			c.add(Restrictions.eq("type", type));//
			transtype = (TransEventType) c.uniqueResult();
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
		if (transtype != null)
		{
			log.debug("[DAO] Success!  Get the TransEventType:" + transtype.toString());
		}
		return transtype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransEventTypeDao#saveOrUpdate(cn.tinder.fuego.domain .po.TransEventType)
	 */
	@Override
	public void saveOrUpdate(TransEventType transtype)
	{
		// TODO Auto-generated method stub
		try
		{
			HibernateUtil.update(transtype);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

}
