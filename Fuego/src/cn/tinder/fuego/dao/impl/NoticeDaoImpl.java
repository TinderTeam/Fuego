package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.NoticeDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.Notice;
import cn.tinder.fuego.domain.po.SystemUser;

public class NoticeDaoImpl implements NoticeDao
{
	Log log = LogFactory.getLog(SystemUserDaoImpl.class);

	@Override
	public void create(Notice notice)
	{
		log.debug("[DAO] Create a UserNotice:" + notice.toString());
		try
		{
			HibernateUtil.add(notice);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}

	}

	@Override
	public void saveOrUpdate(Notice notice)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Notice notice)
	{
		log.debug("[DAO] Delete the UserNotice:" + notice.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();

			Object classObj = session.load(Notice.class, notice.getTransID());

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
		log.debug("[DAO] Success!Delete the SystemUser:" + notice.toString());
	}

	@Override
	public Notice find(String id)
	{
		log.debug("[DAO] Find the UserNotice by ID:" + id);
		Session s = null;
		Transaction tx = null;
		String hql = null;
		Notice notice = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(SystemUser.class);
			c.add(Restrictions.eq("transID", id));//
			notice = (Notice) c.uniqueResult();
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
		if (notice != null)
		{
			log.debug("[DAO] Success!  Find the UserNotice:" + notice.toString());
		}
		return notice;
	}

	@Override
	public List<Notice> findByUser(String username)
	{

		List<Notice> notices;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(Notice.class);
			c.add(Restrictions.eq("createUser", username));
			notices = c.list();

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

		return notices;
	}

}
