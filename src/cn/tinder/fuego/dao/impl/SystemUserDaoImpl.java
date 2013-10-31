package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.SystemUser;

public class SystemUserDaoImpl implements SystemUserDao
{
	Log log = LogFactory.getLog(SystemUserDaoImpl.class);

	/**
	 * <h3>CopyRright (c) 2008-Tinder</h3> <h3>Interface Infornation</h3>
	 * <p>
	 * Project: Fuego <br>
	 * Module ID: <br>
	 * Comments: SystemUser Dao interface <br>
	 * JDK version used: JDK1.6 <br>
	 * Namespace: cn.tinder.feugo.dao; <br>
	 * Create Date: 2013-9-16
	 * 
	 * <h3>Modified Information</h3>
	 * <br>
	 * Modified By: Nan Bowen <br>
	 * Modified Date: 2013-9-16 <br>
	 * Why & What is modified:
	 * 
	 * 
	 * @author Nan Bowen
	 * @version 0.0.1.130916_Base
	 */

	/**
	 * create a record in DB.
	 */
	public void create(SystemUser user)
	{
		log.debug("[DAO] Create a SystemUser:" + user.toString());
		try
		{
			HibernateUtil.add(user);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a SystemUser:" + user.toString());
	}

	public void delete(SystemUser user)
	{
		log.debug("[DAO] Delete the SystemUser:" + user.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			Object classObj = session.load(SystemUser.class, user.getUserName());

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
		log.debug("[DAO] Success!Delete the SystemUser:" + user.toString());
	}

	public SystemUser find(String username)
	{
		log.debug("[DAO] Find the SystemUser by Name:" + username);
		Session s = null;
		// Transaction tx = null;
		// String hql = null;
		SystemUser user = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(SystemUser.class);
			c.add(Restrictions.eq("userName", username));//
			user = (SystemUser) c.uniqueResult();
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
		if (user != null)
		{
			log.debug("[DAO] Success!  Find the SystemUser:" + user.toString());
		}
		return user;
	}

	/**
	 * find a list of SystemUser.(Just when the data is small enough!!!)
	 */
	public List<SystemUser> find(String field, Object value)
	{

		List<SystemUser> users;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(SystemUser.class);
			c.add(Restrictions.eq(field, value));
			users = c.list();

		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return users;
	}

	public void saveOrUpdate(SystemUser user)
	{
		try
		{
			delete(user);
			create(user);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.SystemUserDao#getAllSystemUser()
	 */
	@Override
	public List<SystemUser> getAllSystemUser()
	{
		// TODO Auto-generated method stub
		List<SystemUser> users;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(SystemUser.class);

			users = c.list();

		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return users;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.SystemUserDao#getUserByRole(java.lang.String)
	 */
	@Override
	public List<SystemUser> getUserByRole(String role)
	{
		// TODO Auto-generated method stub
		
		List<SystemUser> userlist;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(SystemUser.class);
			c.add(Restrictions.eq("role", role));
			userlist = c.list();

		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return userlist;
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.SystemUserDao#getUserByRole(java.lang.String)
	 */
	@Override
	public List<SystemUser> getUserByDept(String dept)
	{
		// TODO Auto-generated method stub
		
		List<SystemUser> userlist;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(SystemUser.class);
			c.add(Restrictions.eq("department", dept));
			userlist = c.list();

		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return userlist;
	}
}