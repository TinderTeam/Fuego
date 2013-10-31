/**   
 * @Title: TransExtAttrDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 上午11:53:43 
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

import cn.tinder.fuego.dao.TransExtAttrDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.TransExtAttr;

/**
 * @ClassName: TransExtAttrDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 上午11:53:43
 * 
 */
public class TransExtAttrDaoImpl implements TransExtAttrDao
{
	Log log = LogFactory.getLog(TransExtAttrDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransExtAttrDao#create(cn.tinder.fuego.domain.po. TransExtAttr)
	 */
	@Override
	public void create(TransExtAttr transattr)
	{
		// TODO Auto-generated method stub

		log.debug("[DAO] Create a TransExtAttr:" + transattr.toString());
		try
		{
			HibernateUtil.add(transattr);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a TransExtAttr:" + transattr.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransExtAttrDao#delete(cn.tinder.fuego.domain.po. TransExtAttr)
	 */
	@Override
	public void delete(TransExtAttr transattr)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the TransExtAttr:" + transattr.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from TransExtAttr where trans_id=? and attr_name=? ";
			Query query = session.createQuery(hql);
			query.setString(0, transattr.getTransID());
			query.setString(1, transattr.getAttrName());

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

		log.debug("[DAO] Success!Delete the TransExtAttr:" + transattr.toString());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransExtAttrDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<TransExtAttr> getByTransID(String transid)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the TransExtAttr by ID:" + transid);
		List<TransExtAttr> transList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(TransExtAttr.class);
			c.add(Restrictions.eq("transID", transid));

			transList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get TransExtAttr id by transid failed." + transid, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return transList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransExtAttrDao#saveOrUpdate(cn.tinder.fuego.domain .po.TransExtAttr)
	 */
	@Override
	public void saveOrUpdate(TransExtAttr transattr)
	{
		// TODO Auto-generated method stub
		try
		{
			delete(transattr);
			create(transattr);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

}
