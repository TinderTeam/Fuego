/**   
 * @Title: PhysicalAssetsStatusDaoImpl.java 
 * @Package cn.tinder.fuego.dao.impl 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-9-28 上午12:02:35 
 * @version V1.0   
 */
package cn.tinder.fuego.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.model.DomainFilterModel;
import cn.tinder.fuego.util.ValidatorUtil;

/**
 * @ClassName: PhysicalAssetsStatusDaoImpl
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-9-28 上午12:02:35
 * 
 */
public class PhysicalAssetsStatusDaoImpl implements PhysicalAssetsStatusDao
{
	Log log = LogFactory.getLog(PhysicalAssetsStatusDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PhysicalAssetsStatusDao#create(cn.tinder.fuego.domain .po.PhysicalAssetsStatus)
	 */
	@Override
	public void create(PhysicalAssetsStatus status)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Create a PhysicalAssetsStatus:" + status.toString());
		try
		{
			HibernateUtil.add(status);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
		log.debug("[DAO] Success! -Create a PhysicalAssetsStatus:" + status.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PhysicalAssetsStatusDao#delete(cn.tinder.fuego.domain .po.PhysicalAssetsStatus)
	 */
	@Override
	public void delete(PhysicalAssetsStatus status)
	{
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();

			Object classObj = session.load(PhysicalAssetsStatus.class, status.getAssetsID());

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
		log.debug("[DAO] Success!Delete the PhysicalAssetsStatus:" + status.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PhysicalAssetsStatusDao#getByAssetsID(java.lang.String )
	 */
	@Override
	public PhysicalAssetsStatus getByAssetsID(String assetsid)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the PhysicalAssetsStatus by ID:" + assetsid);
		Session s = null;

		PhysicalAssetsStatus status = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(PhysicalAssetsStatus.class);
			c.add(Restrictions.eq("assetsID", assetsid));//
			status = (PhysicalAssetsStatus) c.uniqueResult();
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
		if (status != null)
		{
			log.debug("[DAO] Success!  Get the PhysicalAssetsStatus:" + status.toString());
		}
		return status;
	}
	
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.dao.PhysicalAssetsStatusDao#saveOrUpdate(cn.tinder.fuego .domain.po.PhysicalAssetsStatus)
	 */
	@Override
	public void saveOrUpdate(PhysicalAssetsStatus status)
	{
		// TODO Auto-generated method stub
		try
		{
			HibernateUtil.update(status);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

 
	@Override
	public List<PhysicalAssetsStatus> getAssetsByDuty(String dutyDept)
	{
		// TODO Auto-generated method stub

		List<PhysicalAssetsStatus> status;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(PhysicalAssetsStatus.class);
			c.add(Restrictions.eq("duty", dutyDept));
			status = c.list();

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

		return status;
	}
 

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.dao.PhysicalAssetsStatusDao#getAssetsListByFilterCount(cn.tinder.fuego.domain.po.PhysicalAssetsStatus, cn.tinder.fuego.domain.po.PhysicalAssetsStatus)
	 */
	@Override
	public int getAssetsListByFilterCount(PhysicalAssetsStatus filter, PhysicalAssetsStatus filterDate,DomainFilterModel domainFilter)
	{
		
		Session s = null;

		int count = 0;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = getCriteriaByFilter(filter, filterDate,domainFilter,s);
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

 	public List<PhysicalAssetsStatus> getAssetsListByFilter(PhysicalAssetsStatus filter,PhysicalAssetsStatus filterDate,DomainFilterModel domainFilter,int startNum,int pageSize)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the PhysicalAssetsStatus by ID:" + filter);
 
		
		Session s = null;

		List<PhysicalAssetsStatus> assetsList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = getCriteriaByFilter(filter, filterDate,domainFilter,s);


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

	private Criteria getCriteriaByFilter(PhysicalAssetsStatus filter, PhysicalAssetsStatus filterDate, DomainFilterModel domainFilter,Session s)
	{
		Criteria c  = s.createCriteria(PhysicalAssetsStatus.class);
		if(null != filter)
		{
			if(null != filter.getAssetsID())
			{
				c.add(Restrictions.eq("assetsID", filter.getAssetsID()));
			}
			if(null != filter.getAssetsName())
			{
				c.add(Restrictions.like("assetsName", "%"+filter.getAssetsName()+"%"));
			}
			if(null != filter.getAssetsType())
			{
				c.add(Restrictions.eq("assetsType", filter.getAssetsType()));
			}
			if(null != filter.getLocation())
			{
				c.add(Restrictions.like("location", "%"+filter.getLocation()+"%"));
			}
			if(null != filter.getTechState())
			{
				c.add(Restrictions.eq("techState", filter.getTechState()));
			}
			if(null != filter.getManageName())
			{
				c.add(Restrictions.eq("manageName", filter.getManageName()));
			}
			if(null != filter.getDuty())
			{
				c.add(Restrictions.like("duty", filter.getDuty()));
			}
			if(null != filter.getPurchaseDate())
			{
				c.add(Restrictions.ge("purchaseDate", filter.getPurchaseDate()));
			}
			if(null != filter.getDueDate())
			{
				c.add(Restrictions.ge("dueDate", filter.getDueDate()));
			}

		}
		if(null != filterDate)
		{
			if(null != filterDate.getPurchaseDate())
			{
				c.add(Restrictions.le("purchaseDate", filterDate.getPurchaseDate()));
			}
			if(null != filterDate.getDueDate())
			{
				c.add(Restrictions.le("dueDate", filterDate.getDueDate()));
			}
		}
		
		addDomainFilter(domainFilter, c);
		return c;
	}

	private void addDomainFilter(DomainFilterModel domainFilter, Criteria c)
	{
		if(null != domainFilter)
		{
			if(!ValidatorUtil.isEmpty(domainFilter.getAssetsTypeList()))
			{
				c.add(Restrictions.in("assetsType", domainFilter.getAssetsTypeList()));
			}
			if(!ValidatorUtil.isEmpty(domainFilter.getDutyList()))
			{
				c.add(Restrictions.in("duty", domainFilter.getDutyList()));
			}
			if(!ValidatorUtil.isEmpty(domainFilter.getManageList()))
			{
				c.add(Restrictions.in("manageName", domainFilter.getManageList()));
			}

		}
	}
 	
 
 	
 	public List<PhysicalAssetsStatus> getAssetsListByDateOrStatuListAndTypeList(Date dueDate,List<String> techStatusList,List<String> assetsTypeList,String duty,String manageName,String assetsName,DomainFilterModel domainFilter)
	{
 
		Session s = null;

		List<PhysicalAssetsStatus> assetsList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(PhysicalAssetsStatus.class);
 
			if((null != dueDate)&&(null != techStatusList && !techStatusList.isEmpty()))
			{
				c.add(Restrictions.or(Restrictions.le("dueDate", dueDate), Restrictions.in("techState", techStatusList)));
			}
			else if(null == dueDate)
			{
				c.add(Restrictions.in("techState", techStatusList));

			}
			else
			{	
				c.add(Restrictions.le("dueDate", dueDate));
			}	
			if(null != assetsTypeList)
			{
				c.add(Restrictions.in("assetsType", assetsTypeList));
			}
			if(null != assetsName)
			{
				c.add(Restrictions.like("assetsName", "%"+assetsName+"%"));		
			}
			if(null != duty)
			{
				c.add(Restrictions.eq("duty", duty));
			}
			
			if(null != manageName)
			{
				c.add(Restrictions.eq("manageName", manageName));
			}
			addDomainFilter(domainFilter, c);

 
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
		if (assetsList != null)
		{
			log.debug("[DAO] Success!  Get the PhysicalAssetsStatus:" + assetsList.toString());
		}
		return assetsList;
	}
	
	public List<PhysicalAssetsStatus> getAssetsListByAssetsIDList(List<String> assetsIDList)
	{
		// TODO Auto-generated method stub
		log.debug("[DAO] Get the PhysicalAssetsStatus by ID List:" + assetsIDList);
		
		if(null == assetsIDList || assetsIDList.isEmpty())
		{
			log.info("the assets id list is empty");
			return null;
		}
 
		
		Session s = null;

		List<PhysicalAssetsStatus> assetsList = null;
		try
		{
			s = HibernateUtil.getSession();
			Criteria c = s.createCriteria(PhysicalAssetsStatus.class);
				if(null != assetsIDList && !assetsIDList.isEmpty())
				{
					c.add(Restrictions.in("assetsID", assetsIDList));
				}
				

			assetsList = c.list();
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			//  HibernateUtil.closeSession();
			if (s != null)
			{
				s.close();
			}
		}
 
		return assetsList;
	}
 	public void deleteAssetListsByAssetsIDList(List<String> assetsIDList)
 	{

		// TODO Auto-generated method stub
		log.debug("[DAO] Delete the PhysicalAssetsStatus by ID List:" + assetsIDList);
 
		
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = (Transaction) session.beginTransaction();
			for(String assetsID : assetsIDList)
			{
				Object classObj = session.load(PhysicalAssetsStatus.class, assetsID);
				session.delete(classObj);
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
	 * @see cn.tinder.fuego.dao.PhysicalAssetsStatusDao#create(java.util.List)
	 */
	@Override
	public void create(List<PhysicalAssetsStatus> assetsList)
	{
 
		Session session = null;
		Transaction tx = null;
		try
		{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			for(PhysicalAssetsStatus assets : assetsList)
			{
				session.save(assets);
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

}
