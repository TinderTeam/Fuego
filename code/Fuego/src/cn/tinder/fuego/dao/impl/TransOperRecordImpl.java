/**   
* @Title: TransOperRecordImpl.java 
* @Package cn.tinder.fuego.dao.impl 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-12-26 上午01:10:35 
* @version V1.0   
*/ 
package cn.tinder.fuego.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.TransOperRecordDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.TransOperRecord;

/** 
 * @ClassName: TransOperRecordImpl 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-12-26 上午01:10:35 
 *  
 */
public class TransOperRecordImpl implements TransOperRecordDao
{
	Log log = LogFactory.getLog(OperateRecordDaoImpl.class);
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransOperRecordDao#create(cn.tinder.fuego.domain.po.TransOperRecord)
	 */
	@Override
	public void create(TransOperRecord record)
	{
		log.debug("[DAO] Create a TransOperRecord:" + record.toString());
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
		log.debug("[DAO] Success! -Create a TransOperRecord:" + record.toString());

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransOperRecordDao#delete(cn.tinder.fuego.domain.po.TransOperRecord)
	 */
	@Override
	public void delete(TransOperRecord record)
	{
		log.debug("[DAO] Delete the TransOperRecord:" + record.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(TransOperRecord.class, record.getTransID());

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
		log.debug("[DAO] Success!Delete the TransOperRecord:" + record.toString());

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransOperRecordDao#getByTransID(java.lang.String)
	 */
	@Override
	public TransOperRecord getByTransID(String transid)
	{
		log.debug("[DAO] Get the TransOperRecord by transid:" + transid);
		Session s = null;

		TransOperRecord record = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(TransOperRecord.class);
			c.add(Restrictions.eq("transID", transid));//
			record = (TransOperRecord) c.uniqueResult();
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

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.TransOperRecordDao#saveOrUpdate(cn.tinder.fuego.domain.po.TransOperRecord)
	 */
	@Override
	public void saveOrUpdate(TransOperRecord record)
	{
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

}
