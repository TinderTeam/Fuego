/**   
 * @Title: TransactionDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-28 上午12:56:03 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.CheckPlan;
import cn.tinder.fuego.domain.po.DiscardPlan;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.constant.TransactionConst;

/**
 * @ClassName: TransactionDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-28 上午12:56:03
 * 
 */
public class TransEventDaoImpl implements TransEventDao
{
	Log log = LogFactory.getLog(TransEventDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransactionDao#create(cn.tinder.fuego.domain.po. Transaction)
	 */
	@Override
	public void create(TransEvent trans)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a TransEvent:" + trans.toString());
		try
		{
			HibernateUtil.add(trans);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a TransEvent:" + trans.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransactionDao#delete(cn.tinder.fuego.domain.po. Transaction)
	 */
	@Override
	public void delete(TransEvent trans)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the TransEvent:" + trans.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(TransEvent.class, trans.getTransID());

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
		log.debug("[DAO] Success!Delete the TransEvent:" + trans.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransactionDao#getByTransID(java.lang.String)
	 */
	@Override
	public TransEvent getByTransID(String transid)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the TransEvent by ID:" + transid);
		Session s = null;

		TransEvent trans = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(TransEvent.class);
			c.add(Restrictions.eq("transID", transid));//
			trans = (TransEvent) c.uniqueResult();
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
		if (trans != null)
		{
			log.debug("[DAO] Success!  Get the TransEvent:" + trans.toString());
		}
		return trans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.TransactionDao#saveOrUpdate(cn.tinder.fuego.domain .po.Transaction)
	 */
	@Override
	public void saveOrUpdate(TransEvent trans)
	{
		// TODO Auto-generated method stub
		try
		{
			HibernateUtil.update(trans);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransEventDao#getByHandlerUser(java.lang.String)
	 */
	@Override
	public List<TransEvent> getTransByUser(List<String> userIDList)
	{
		log.debug("Get the TransEvent by User" + userIDList);
		Session s = null;

		List<TransEvent> transList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(TransEvent.class);

			c.add(Restrictions.or(Restrictions.and(Restrictions.isNull("parentTransID"),Restrictions.in("createUser", userIDList)), Restrictions.in("handleUser", userIDList)));
			

			transList =   c.list();
		} catch (RuntimeException e)
		{
			log.error("get trans envent faile",e);
			throw e;
		} finally
		{
			// HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
 
		return transList;
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransEventDao#getByHandlerUser(java.lang.String)
	 */
	@Override
	public List<TransEvent> getTodoTransByHandlerUser(String userID)
	{
		log.debug("Get the TransEvent by User" + userID);
		Session s = null;

		List<TransEvent> transList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(TransEvent.class);
			c.add(Restrictions.eq("handleUser", userID));//
			c.add(Restrictions.ne("currentStep", TransactionConst.END_STEP_FLAG));//

			transList =   c.list();
		} catch (RuntimeException e)
		{
			log.error("get trans envent faile",e);
			throw e;
		} finally
		{
			// HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
 
		return transList;
	}
	
	public List<TransEvent> getTransByParentID(String parentTransID)
	{
		log.debug("Get the TransEvent by User" + parentTransID);
		Session s = null;

		List<TransEvent> transList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(TransEvent.class);
			c.add(Restrictions.eq("parentTransID", parentTransID));//
 
			transList =   c.list();
		} catch (RuntimeException e)
		{
			log.error("get trans envent faile",e);
			throw e;
		} finally
		{
			// HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
 
		return transList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransEventDao#create(java.util.List)
	 */
	@Override
	public void create(List<TransEvent> transList)
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			for(TransEvent tevent : transList)
			{
				session.save(tevent);
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
	 * @see cn.tinder.fuego.dao.TransEventDao#getTransByFilter(cn.tinder.fuego.domain.po.TransEvent, cn.tinder.fuego.domain.po.TransEvent)
	 */
	@Override
	public List<TransEvent> getTransByFilter(TransEvent filter1, TransEvent filter2)
	{
		     Session s = null;

			List<TransEvent> transList = null;
			try
			{
				s = HibernateUtil.getSession();
				Criteria c  = s.createCriteria(TransEvent.class);
				if(null != filter1)
				{
					if(null != filter1.getCreateUser())
					{
						c.add(Restrictions.eq("createUser", filter1.getCreateUser()));
					}
					if(null != filter1.getTransName())
					{
						c.add(Restrictions.eq("transName", filter1.getTransName()));
					}
					if(null != filter1.getStatus())
					{
						c.add(Restrictions.eq("status", filter1.getStatus()));
					}
					if(null != filter1.getEndTime())
					{
						c.add(Restrictions.ge("endTime", filter1.getEndTime()));
					}
				}
				if(null != filter2)
				{ 
					if(null != filter2.getEndTime())
					{
						c.add(Restrictions.le("endTime", filter2.getEndTime()));
					}
				}

	 
				transList =   c.list();
			} catch (RuntimeException e)
			{
				log.error("get trans envent faile",e);
				throw e;
			} finally
			{
				// HibernateUtil.closeSession();
				if (s != null)
				{
					s.close();
				}
			}
	 
			return transList;
	}
	



}
