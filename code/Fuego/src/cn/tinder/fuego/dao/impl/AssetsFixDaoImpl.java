package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.tinder.fuego.dao.AssetsFixDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.AssetsFix;


@Component ("assetsFixDao")
public class AssetsFixDaoImpl implements AssetsFixDao {
	Log log = LogFactory.getLog(AssetsFixDaoImpl.class);

	@Override
	public void create(AssetsFix fix) {
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a AssetsFix:" + fix.toString());
		try
		{
			HibernateUtil.add(fix);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a AssetsFix:" + fix.toString());
	}

	@Override
	public void saveOrUpdate(AssetsFix fix) {
		// TODO Auto-generated method stub
		try
		{
			this.delete(fix);
			this.create(fix);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void delete(AssetsFix fix) {
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the AssetsFix:" + fix.toString());

		Session session = null;
		Transaction tx = null;
		String hql = null;
		// SystemUser user = null;
		try
		{
			session = HibernateUtil.getSession();

			tx = session.beginTransaction();

			hql = "delete from AssetsFix where INDEX_NUMBER =? ";
			Query query = session.createQuery(hql);
			query.setString(0, fix.getIndexNumber());
		
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

		log.debug("[DAO] Success!Delete the AssetsFix:" + fix.toString());
	}

	@Override
	public List<AssetsFix> getAllAssetsFix() {

		Session s = null;
		// Transaction tx = null;
		// String hql = null;
		List<AssetsFix> fixList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsFix.class);
 			fixList = c.list();
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
		if (fixList != null)
		{
			log.debug("[DAO] Success!  get the AssetsName by Name:" + fixList.toString());
		}
		return fixList;
	}
	

}
