/**   
 * @Title: OperateRecordDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-27 下午11:40:47 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.OperateRecordDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.OperateRecord;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.model.DomainFilterModel;

/**
 * @ClassName: OperateRecordDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-27 下午11:40:47
 * 
 */
public class OperateRecordDaoImpl implements OperateRecordDao
{
	Log log = LogFactory.getLog(OperateRecordDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.OperateRecordDao#create(cn.tinder.fuego.domain.po .OperateRecord)
	 */
	@Override
	public void create(OperateRecord record)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a OperateRecord:" + record.toString());
		try
		{
			HibernateUtil.add(record);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a OperateRecord:" + record.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.OperateRecordDao#delete(cn.tinder.fuego.domain.po .OperateRecord)
	 */
	@Override
	public void delete(OperateRecord record)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the OperateRecord:" + record.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(OperateRecord.class, record.getAssets().getAssetsID());

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
		log.debug("[DAO] Success!Delete the OperateRecord:" + record.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.OperateRecordDao#getByAssetsID(java.lang.String)
	 */
	@Override
	public OperateRecord getByAssetsID(String assetsid)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the OperateRecord by ID:" + assetsid);
		Session s = null;

		OperateRecord record = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(OperateRecord.class);
			c.add(Restrictions.eq("assets.assetsID", assetsid));//
			record = (OperateRecord) c.uniqueResult();
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
		if (record != null)
		{
			log.debug("[DAO] Success!  Get the OperateRecord:" + record.toString());
		}
		return record;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.OperateRecordDao#saveOrUpdate(cn.tinder.fuego.domain .po.OperateRecord)
	 */
	@Override
	public void saveOrUpdate(OperateRecord record)
	{
		// TODO Auto-generated method stub
		try
		{
			HibernateUtil.update(record);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.OperateRecordDao#create(java.util.List)
	 */
	@Override
	public void create(List<OperateRecord> recordList)
	{

		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			for(OperateRecord record : recordList)
			{
				session.save(record);
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
	 * @see cn.tinder.fuego.dao.OperateRecordDao#getAssetsOperateLogByFilterCount(cn.tinder.fuego.domain.po.OperateRecord, cn.tinder.fuego.domain.po.OperateRecord)
	 */
	@Override
	public int getAssetsOperateLogByFilterCount(OperateRecord filter, OperateRecord filterDate)
	{
		Session s = null;

		int count = 0;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = getCriteriaByFilter(filter, filterDate,s);
			count = (Integer)c.setProjection(Projections.rowCount()).uniqueResult(); 		
		}
		catch (RuntimeException re)
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
 
		return count;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.OperateRecordDao#getAssetsOperateLogByFilter(cn.tinder.fuego.domain.po.OperateRecord, cn.tinder.fuego.domain.po.OperateRecord)
	 */
	@Override
	public List<OperateRecord> getAssetsOperateLogByFilter(OperateRecord filter, OperateRecord filterDate,int startNum,int pageSize)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the PhysicalAssetsStatus by ID:" + filter);
 
		
		Session s = null;

		List<OperateRecord> assetsList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = getCriteriaByFilter(filter, filterDate,s);


			c.setFirstResult(startNum);  
	        c.setMaxResults(pageSize); 

			assetsList = c.list();
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
 
		return assetsList;
	}
	private Criteria getCriteriaByFilter(OperateRecord filter, OperateRecord filterDate,Session s)
	{
		Criteria c  = s.createCriteria(OperateRecord.class);
		if(null != filter)
		{
			if(null != filter.getUserName())
			{
				c.add(Restrictions.eq("userName", filter.getUserName()));

			}
			if(null != filter.getOperate())
			{
				c.add(Restrictions.eq("operate", filter.getOperate()));
			}
			if(null != filterDate.getTime())
			{
				c.add(Restrictions.gt("time", filter.getTime()));
			}
			if(null != filter.getTransID())
			{
				c.add(Restrictions.eq("transID", filter.getTransID()));
			}
			
			if(null != filter.getAssets().getAssetsID())
			{
				c.add(Restrictions.eq("assets.assetsID", filter.getAssets().getAssetsID()));
			}
			if(null != filter.getAssets().getAssetsName())
			{
				c.add(Restrictions.like("assets.assetsName", "%"+filter.getAssets().getAssetsName()+"%"));
			}
 

		}
		if(null != filterDate)
		{
			if(null != filterDate.getTime())
			{
				c.add(Restrictions.le("time", filterDate.getTime()));
			}
		}
		
 		return c;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.OperateRecordDao#getByTransID(java.lang.String)
	 */
	@Override
	public List<OperateRecord> getByTransID(String transID)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the OperateRecord by ID:" + transID);
		Session s = null;

		List<OperateRecord> recordList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(OperateRecord.class);
			c.add(Restrictions.eq("transID", transID));//
			recordList =  c.list();
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
 
		return recordList;
	}
	
	@Override
	public List<OperateRecord> getByTransID(List<String> transIDList)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the OperateRecord by ID:" + transIDList);
		Session s = null;

		List<OperateRecord> recordList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(OperateRecord.class);
			c.add(Restrictions.in("transID", transIDList));//
			recordList =  c.list();
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
 
		return recordList;
	}

	 
}
