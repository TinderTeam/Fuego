package cn.tinder.fuego.dao.impl;

import cn.tinder.fuego.dao.AssetsPriceDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.AssetsPrice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class AssetsPriceDaoImpl implements AssetsPriceDao
{
	Log log = LogFactory.getLog(AssetsPriceDaoImpl.class);

	@Override
	public void create(AssetsPrice price)
	{

		// TODO Auto-generated method stub
		log.debug("[DAO] Create a AssetsPrice:" + price.toString());
		try
		{
			HibernateUtil.add(price);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a AssetsPrice:" + price.toString());

	}

	@Override
	public void delete(AssetsPrice price)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the AssetsPrice:" + price.toString());
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(AssetsPrice.class, price.getSpec());

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
		log.debug("[DAO] Success!Delete the AssetsPrice:" + price.toString());
	}

	@Override
	public AssetsPrice getBySpec(String spec)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] get the AssetsPrice by SPEC:" + spec);
		Session s = null;
		AssetsPrice price = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsPrice.class);
			c.add(Restrictions.eq("spec", spec));//
			price = (AssetsPrice) c.uniqueResult();
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
		if (price != null)
		{
			log.debug("[DAO] Success!  get the AssetsPrice:" + price.toString());
		}
		return price;
	}

	@Override
	public void saveOrUpdate(AssetsPrice price)
	{
		// TODO Auto-generated method stub
		try
		{
			HibernateUtil.update(price);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public AssetsPrice getByAssetsPrice(AssetsPrice assetsPrice) {
		// TODO Auto-generated method stub
		log.debug("[DAO] get the AssetsPrice by assetsPrice:" + assetsPrice);
		Session s = null;
		AssetsPrice price = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(AssetsPrice.class);
			c.add(Restrictions.eq("spec", assetsPrice.getSpec()));//
			c.add(Restrictions.eq("assetsName", assetsPrice.getAssetsName()));//
			c.add(Restrictions.eq("manufacture", assetsPrice.getManufacture()));//
			price = (AssetsPrice) c.uniqueResult();
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
		if (price != null)
		{
			log.debug("[DAO] Success!  get the AssetsPrice:" + price.toString());
		}
		return price;
	}

	@Override
	public List<AssetsPrice> getAll() {
		List<AssetsPrice> price;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(AssetsPrice.class);			
			price = c.list();

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

		return price;
	}

	@Override
	public void createByList(List<AssetsPrice> list) {
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a AssetsPrice:" + list.toString());
		Session s = null;
		Transaction tx = null;
		try
		{
			
				
				try
				{
					s = HibernateUtil.getSession();
					tx = s.beginTransaction();
					for(AssetsPrice p:list){
						s.save(list);
					
						tx.commit();
					}
				} finally
				{
					if (s != null)
						s.close();
				}
		
			
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	
	}

}
