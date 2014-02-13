/**   
 * @Title: AssetsTypeDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: 资产类型Dao实现
 * @author Zhu Liucao   
 * @date 2013-9-26 上午01:42:29 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.AssetsTypeDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.AssetsType;

public class AssetsTypeDaoImpl implements AssetsTypeDao
{
	Log log = LogFactory.getLog(AssetsTypeDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsTypeDao#create(cn.tinder.fuego.domain.po.AssetsType )
	 */
	@Override
	public void create(AssetsType type)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a AssetsType:" + type.toString());
		try
		{
			HibernateUtil.add(type);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a AssetsType:" + type.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsTypeDao#delete(cn.tinder.fuego.domain.po.AssetsType )
	 */
	@Override
	public void delete(AssetsType type)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the AssetsType:" + type.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(AssetsType.class, type.getTypeID());

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

		log.debug("[DAO] Success!Delete the AssetsType:" + type.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsTypeDao#getByTypeID(java.lang.String)
	 */
	@Override
	public AssetsType getByTypeName(String typeName)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the AssetsType by ID:" + typeName);
		Session s = null;

		AssetsType type = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsType.class);
			c.add(Restrictions.eq("typeName", typeName));//
			type = (AssetsType) c.uniqueResult();
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
 
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.AssetsTypeDao#saveOrUpdate(cn.tinder.fuego.domain .po.AssetsType)
	 */
	@Override
	public void saveOrUpdate(AssetsType type)
	{
		// TODO Auto-generated method stub
		try
		{
			HibernateUtil.update(type);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

}
