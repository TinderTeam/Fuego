package cn.tinder.fuego.dao;

import cn.tinder.fuego.dao.impl.AssetsPriceDaoImpl;
import cn.tinder.fuego.dao.impl.AssetsQuotaDaoImpl;
import cn.tinder.fuego.dao.impl.AssetsTypeDaoImpl;
import cn.tinder.fuego.dao.impl.AssignPlanDaoImpl;
import cn.tinder.fuego.dao.impl.CheckPlanDaoImpl;
import cn.tinder.fuego.dao.impl.DiscardPlanDaoImpl;
import cn.tinder.fuego.dao.impl.MenuTreeDaoImpl;
import cn.tinder.fuego.dao.impl.OperateRecordDaoImpl;
import cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl;
import cn.tinder.fuego.dao.impl.PurchasePlanDaoImpl;
import cn.tinder.fuego.dao.impl.RecapturePlanDaoImpl;
import cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl;
import cn.tinder.fuego.dao.impl.RoleMapMenuDaoImpl;
import cn.tinder.fuego.dao.impl.SystemUserDaoImpl;
import cn.tinder.fuego.dao.impl.TransEventDaoImpl;
import cn.tinder.fuego.dao.impl.TransEventTypeDaoImpl;
import cn.tinder.fuego.dao.impl.TransExtAttrDaoImpl;
import cn.tinder.fuego.dao.impl.TransOperRecordDaoImpl;

public class DaoContext
{
	private static DaoContext instance;

	private MenuTreeDao menuTreeDao;
	private RoleMapMenuDao roleMapMenuDao;
	private SystemUserDao systemUserDao;
	private AssetsPriceDao assetsPriceDao;
	private AssetsQuotaDao assetsQuotaDao;
	private AssetsTypeDao assetsTypeDao;
	private AssignPlanDao assignPlanDao;
	private CheckPlanDao checkPlanDao;
	private DiscardPlanDao discardPlanDao;
	private RecapturePlanDao recapturePlanDao;
	private OperateRecordDao operateRecordDao;
	private PhysicalAssetsStatusDao physicalAssetsStatusDao;
	private PurchasePlanDao purchasePlanDao;
	private TransEventDao transEventDao;
	private TransEventTypeDao transEventTypeDao;
	private TransExtAttrDao transExtAttrDao;
    //zhuliucao 20131021 add
	private ReceivePlanDao receivePlanDao;
	
	private TransOperRecordDao transOperRecordDao;
	private DaoContext()
	{

	}

	public static synchronized DaoContext getInstance()
	{
		if (null == instance)
		{
			instance = new DaoContext();
		}
		return instance;
	}

	public synchronized MenuTreeDao getMenuTreeDao()
	{
		if (null == menuTreeDao)
		{
			menuTreeDao = new MenuTreeDaoImpl();

		}
		return menuTreeDao;
	}

	public synchronized RoleMapMenuDao getRoleMapMenuDao()
	{
		if (null == roleMapMenuDao)
		{
			roleMapMenuDao = new RoleMapMenuDaoImpl();
		}
		return roleMapMenuDao;
	}

	public synchronized SystemUserDao getSystemUserDao()
	{
		if (null == systemUserDao)
		{
			systemUserDao = new SystemUserDaoImpl();
		}
		return systemUserDao;
	}

	public synchronized AssetsPriceDao getAssetsPriceDao()
	{
		if (null == assetsPriceDao)
		{
			assetsPriceDao = new AssetsPriceDaoImpl();
		}
		return assetsPriceDao;
	}

	public synchronized AssetsQuotaDao getAssetsQuotaDao()
	{
		if (null == assetsQuotaDao)
		{
			assetsQuotaDao = new AssetsQuotaDaoImpl();
		}
		return assetsQuotaDao;
	}

	public synchronized AssetsTypeDao getAssetsTypeDao()
	{
		if (null == assetsTypeDao)
		{
			assetsTypeDao = new AssetsTypeDaoImpl();
		}
		return assetsTypeDao;
	}

	public synchronized TransEventDao getTransEventDao()
	{
		if (null == transEventDao)
		{
			transEventDao = new TransEventDaoImpl();
		}
		return transEventDao;
	}

	public synchronized TransEventTypeDao getTransEventTypeDao()
	{
		if (null == transEventTypeDao)
		{
			transEventTypeDao = new TransEventTypeDaoImpl();
		}
		return transEventTypeDao;
	}

	public synchronized PhysicalAssetsStatusDao getPhysicalAssetsStatusDao()
	{
		if (null == physicalAssetsStatusDao)
		{
			physicalAssetsStatusDao = new PhysicalAssetsStatusDaoImpl();
		}
		return physicalAssetsStatusDao;
	}

	public synchronized AssignPlanDao getAssignPlanDao()
	{
		if (null == assignPlanDao)
		{
			assignPlanDao = new AssignPlanDaoImpl();
		}
		return assignPlanDao;
	}

	public synchronized CheckPlanDao getCheckPlanDao()
	{
		if (null == checkPlanDao)
		{
			checkPlanDao = new CheckPlanDaoImpl();
		}
		return checkPlanDao;
	}

	public synchronized DiscardPlanDao getDiscardPlanDao()
	{
		if (null == discardPlanDao)
		{
			discardPlanDao = new DiscardPlanDaoImpl();
		}
		return discardPlanDao;
	}

	public RecapturePlanDao getRecapturePlanDao()
	{
		if (null == recapturePlanDao)
		{
			recapturePlanDao = new RecapturePlanDaoImpl();
		}
		return recapturePlanDao;
 	}

 
	public synchronized PurchasePlanDao getPurchasePlanDao()
	{
		if (null == purchasePlanDao)
		{
			purchasePlanDao = new PurchasePlanDaoImpl();
		}
		return purchasePlanDao;
	}

	public synchronized TransExtAttrDao getTransExtAttrDao()
	{
		if (null == transExtAttrDao)
		{
			transExtAttrDao = new TransExtAttrDaoImpl();
		}
		return transExtAttrDao;
	}

	public synchronized OperateRecordDao getOperateRecordDao()
	{
		if (null == operateRecordDao)
		{
			operateRecordDao = new OperateRecordDaoImpl();
		}
		return operateRecordDao;
	}
	public synchronized ReceivePlanDao getReceivePlanDao()
	{
		if (null == receivePlanDao)
		{
			receivePlanDao = new ReceivePlanDaoImpl();
		}
		return receivePlanDao;
	}
	
	public synchronized TransOperRecordDao getTransOperRecordDao()
	{
		if (null == transOperRecordDao)
		{
			transOperRecordDao = new TransOperRecordDaoImpl();
		}
		return transOperRecordDao;
	}

}
