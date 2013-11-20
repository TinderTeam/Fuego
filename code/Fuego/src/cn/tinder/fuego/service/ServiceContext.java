package cn.tinder.fuego.service;

import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.impl.AssetsManageServiceImpl;
import cn.tinder.fuego.service.impl.GasAssetsApplyServiceImpl;
import cn.tinder.fuego.service.impl.GasStationCheckServiceImpl;
import cn.tinder.fuego.service.impl.LoadServiceImpl;
import cn.tinder.fuego.service.impl.LoginServiceImpl;
import cn.tinder.fuego.service.impl.SystemMaintanceServiceImpl;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.service.impl.id.AssetsIDCreateServiceImpl;
import cn.tinder.fuego.service.impl.plan.AssignPlanServiceImpl;
import cn.tinder.fuego.service.impl.plan.CheckPlanServiceImpl;
import cn.tinder.fuego.service.impl.plan.DiscardPlanServiceImpl;
import cn.tinder.fuego.service.impl.plan.PurchasePlanServiceImpl;
import cn.tinder.fuego.service.impl.plan.RecapturePlanServiceImpl;
import cn.tinder.fuego.service.impl.plan.ReceivePlanServiceImpl;
import cn.tinder.fuego.service.impl.sys.FileLoadServiceImpl;
import cn.tinder.fuego.service.impl.sys.SystemParaServiceImpl;
import cn.tinder.fuego.service.impl.util.ConstServiceImpl;
import cn.tinder.fuego.service.sys.FileLoadService;
import cn.tinder.fuego.service.sys.SystemParaService;
import cn.tinder.fuego.service.util.ConstService;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardPlanBo;
import cn.tinder.fuego.webservice.struts.bo.receive.ReceivePlanBo;
public class ServiceContext
{
	private static ServiceContext instance;

	private LoadService loadService;

	private LoginService loginService;
	private GasAssetsApplyService  gasAssetsApplyService ;

	private AssetsManageService assetsManageService;

	private SystemParaService systemParaService;
	

	private TransactionService transService;

	
	private ConstService constService;
	
	private GasStationCheckService gasStationCheckService;
	
 	private FileLoadService fileLoadService;

	private TransPlanService assignPlanService;
	private TransPlanService discardService;
	private TransPlanService checkPlanService;
	private TransPlanService recapturePlanService;
	private TransPlanService purchasePlanService;
	private TransPlanService receivePlanService;


	private SystemMaintanceService systemMaintanceService;
	private SystemMaintanceServiceImpl systemMaintanceServiceImpl;
	
	private IDCreateService assetsIDCreateService ;


	public synchronized FileLoadService getFileLoadService()
	{
		if (null == fileLoadService)
		{
			fileLoadService = new FileLoadServiceImpl();
		}
		return fileLoadService;
	}
	
	public synchronized SystemParaService getSystemParaService()
	{
		if (null == systemParaService)
		{
			systemParaService = new SystemParaServiceImpl();
		}
		return systemParaService;
	}
	
	public synchronized ConstService getConstService()
	{
		if (null == constService)
		{
			constService = new ConstServiceImpl();
		}
		return constService;
	}

	public static synchronized ServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new ServiceContext();
		}
		return instance;
	}



	public synchronized LoadService getLoadService()
	{
		if (null == loadService)
		{
			loadService = new LoadServiceImpl();
		}
		return loadService;
	}
	
	public synchronized GasAssetsApplyService getGasAssetsApplyService()
	{
		if(null == gasAssetsApplyService)
		{
			gasAssetsApplyService = new GasAssetsApplyServiceImpl();
		}
		return gasAssetsApplyService;
	}
	public synchronized LoginService getLoginService()
	{
		if (null == loginService)
		{
			loginService = new LoginServiceImpl();
		}
		return loginService;
	}

	public synchronized AssetsManageService getAssetsManageService()
	{
		if (null == assetsManageService)
		{
			assetsManageService = new AssetsManageServiceImpl();
		}
		return assetsManageService;
	}

	public synchronized SystemMaintanceService getSystemMaintanceService()
	{
		if (null == systemMaintanceService)
		{
			systemMaintanceService = new SystemMaintanceServiceImpl();
		}
		return systemMaintanceService;
	}
	public synchronized SystemMaintanceServiceImpl getSystemMaintanceServiceImpl()
	{
		if (null == systemMaintanceServiceImpl)
		{
			systemMaintanceServiceImpl = new SystemMaintanceServiceImpl();
		}
		return systemMaintanceServiceImpl;
	}
	
	public synchronized TransactionService getTransactionService()
	{
		if (null == transService)
		{
			transService = new TransactionServiceImpl();
		}
		return transService;
	}

	public synchronized TransPlanService getAssignPlanService()
	{
		if(null == assignPlanService)
		{
			assignPlanService = new AssignPlanServiceImpl<AssignPlanBo>();
		}
		return assignPlanService;
  
	}

	public synchronized TransPlanService getDiscardPlanService()
	{
		if(null == discardService)
		{
			discardService = new DiscardPlanServiceImpl<DiscardPlanBo>();
		}
		return discardService;
	}
	
	public synchronized TransPlanService getPurchasePlanService()
	{
		if(null == purchasePlanService)
		{
			purchasePlanService = new PurchasePlanServiceImpl<AssignPlanBo>();
		}
		return purchasePlanService;
	}
	
	public synchronized TransPlanService getCheckPlanService()
	{
		if(null == checkPlanService)
		{
			checkPlanService = new CheckPlanServiceImpl<CheckPlanBo>();
		}
		return checkPlanService;
	}
 


	public TransPlanService getRecapturePlanService()
	{
		if(null == recapturePlanService)
		{
			recapturePlanService = new RecapturePlanServiceImpl<AssignPlanBo>();
		}
		return recapturePlanService;
	}
	
	public TransPlanService getReceivePlanService()
	{
		if(null == receivePlanService)
		{
			receivePlanService = new ReceivePlanServiceImpl<ReceivePlanBo>();
		}
		return receivePlanService;
	}
	
	public TransPlanService getPlanServiceByType(String tranType)
	{
		TransPlanService planService = null;
		
		if(TransactionConst.ASSIGN_PLAN_TYPE.equals(tranType))
		{
			planService = this.getAssignPlanService();
		}
		else if(TransactionConst.CHECK_PLAN_TYPE.equals(tranType))
		{
			planService = this.getCheckPlanService();
		}
		else if(TransactionConst.DISCARD_PLAN_TYPE.equals(tranType))
		{
			planService = this.getDiscardPlanService();
		}
		else if(TransactionConst.PURCHASE_PLAN_TYPE.equals(tranType))
		{
			planService = this.getPurchasePlanService();
		}
		else if(TransactionConst.RECAPTURE_PLAN_TYPE.equals(tranType))
		{
			planService = this.getReceivePlanService();
		}
		else if(TransactionConst.RECEIVE_PLAN_TYPE.equals(tranType))
		{
			planService = this.getReceivePlanService();
		}
		
		return planService;
	}




	
	public synchronized GasStationCheckService getGasStationCheckService()
	{
		if(null == gasStationCheckService)
		{
			gasStationCheckService = new GasStationCheckServiceImpl();
		}
		return gasStationCheckService;
	}

 
	public synchronized IDCreateService getAssetsIDCreateService()
	{
		
		if(null == assetsIDCreateService)
		{
			assetsIDCreateService = new AssetsIDCreateServiceImpl();
		}
		return assetsIDCreateService;
	}



	

}
