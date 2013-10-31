/**   
* @Title: PurchasePlanServiceImpl.java 
* @Package cn.tinder.fuego.service.impl.plan 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 上午01:51:57 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl.plan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.PurchasePlanDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.TransExtAttrDao;
import cn.tinder.fuego.domain.po.CheckPlan;
import cn.tinder.fuego.domain.po.PurchasePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanInfoBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckTransBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/** 
 * @ClassName: PurchasePlanServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午01:51:57 
 *  
 */

public class PurchasePlanServiceImpl<E> extends TransactionServiceImpl implements TransPlanService<E>
{
	private PurchasePlanDao purchasePlanDao = DaoContext.getInstance().getPurchasePlanDao();
 	private TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();

  	
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user)
	{
		PurchasePlanSessionBo plan = new PurchasePlanSessionBo();
		TransactionBaseInfoBo baseTrans = super.createTransByUserAndType(user,user, TransactionConst.PURCHASE_PLAN_TYPE , null);
 
		plan.getPurchaseTransBo().setTransInfo(baseTrans);
 
		return (E)plan;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#deletePlan(java.lang.String)
	 */
	@Override
	public void deletePlan(String transID)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#updatePlan(java.lang.Object)
	 */
	@Override
	public void updatePlan(E plan)
	{
		//step1: force transform 
		PurchasePlanSessionBo planInfo = new PurchasePlanSessionBo();
		planInfo = (PurchasePlanSessionBo) plan;
		
		
		//step2: save basic transaction information
		String transID = planInfo.getPurchaseTransBo().getTransInfo().getTransID();
		String handleUser = planInfo.getPurchaseTransBo().getTransInfo().getHandleUser();
		super.updateTrans(transID,handleUser);

 
		//step4: save assign plan list
		purchasePlanDao.deleteByTransID(transID);
		List<PurchasePlanBo> assignAssetsList = planInfo.getPurchasePageBo().getAssetsList();
		for (PurchasePlanBo assets: assignAssetsList)
		{
			PurchasePlan purchasePlan = new PurchasePlan();
			purchasePlan.setTransID(transID);
			purchasePlan.setAssetsName(assets.getAssetsBo().getAssetsName());
			purchasePlan.setManufacture(assets.getAssetsBo().getManufacture());
			purchasePlan.setSpec(assets.getAssetsBo().getSpec());
			purchasePlan.setQuantity(assets.getAssetsBo().getQuantity());
			purchasePlan.setPrice(Float.valueOf(assets.getPrice()));
			purchasePlan.setNote(assets.getAssetsBo().getNote());
			purchasePlan.setUnit(assets.getAssetsBo().getUnit());
			purchasePlan.setSum(Float.valueOf(assets.getMoney()));
			purchasePlanDao.saveOrUpdate(purchasePlan);
		}
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);
  
		String handleUser;
		switch(transEvent.getCurrentStep())
		{
 
		case 3 :
			handleUser = super.getLeader(transEvent.getCreateUser());
			break;	
		case 2 :
		    handleUser = transEvent.getCreateUser();
 		    break;
		case 1 :
		    handleUser = transEvent.getCreateUser();
 		    break;    
		default :
			handleUser = transEvent.getCreateUser();
		}
		

		super.forwardNext(transID,handleUser);
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanByTransID(java.lang.String)
	 */
	@Override
	public E getPlanByTransID(String transID)
	{
		TransactionBaseInfoBo baseTrans = super.getTransByID(transID);
		if(null == baseTrans)
		{
			return null;
		}
		
		PurchasePlanSessionBo plan = new PurchasePlanSessionBo();

 		plan.getPurchaseTransBo().setTransInfo(baseTrans);
		
 		 

		List<PurchasePlanBo> planBoList = new ArrayList<PurchasePlanBo>();
		List<PurchasePlan> purchasePlanList = purchasePlanDao.getByTransID(transID);
		for(PurchasePlan purchasePlan : purchasePlanList)
		{
			PurchasePlanBo planInfoBo = new PurchasePlanBo();
			planInfoBo.getAssetsBo().setAssetsName(purchasePlan.getAssetsName());
			planInfoBo.getAssetsBo().setManufacture(purchasePlan.getManufacture());
			planInfoBo.getAssetsBo().setSpec(purchasePlan.getSpec());
			planInfoBo.getAssetsBo().setNote(purchasePlan.getNote());
			planInfoBo.getAssetsBo().setQuantity(purchasePlan.getQuantity());
			planInfoBo.setMoney(String.valueOf(purchasePlan.getSum()));
			planInfoBo.setPrice(String.valueOf(purchasePlan.getPrice()));
			planInfoBo.getAssetsBo().setUnit(purchasePlan.getUnit());
			planBoList.add(planInfoBo);
		}
		plan.getPurchasePageBo().setAssetsList(planBoList);
 
		return (E) plan;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getExportFile(java.lang.Object)
	 */
	@Override
	public File getExportFile(E plan)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID)
	{
		super.backward(transID);
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#validate(java.lang.Object)
	 */
	
	@Override
	public void validate(E plan)
	{
		PurchasePlanSessionBo planInfo = (PurchasePlanSessionBo)plan;
 		
 		List<PurchasePlanBo> assetsList = planInfo.getPurchasePageBo().getAssetsList();
 		if(null == assetsList || assetsList.isEmpty())
 		{
 			throw new ServiceException(ExceptionMsg.ASSETS_LIST_EMPTY);
 		}
 
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String, java.util.List)
	 */
	@Override
	public E createPlan(String user, List<String> childUserList)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
