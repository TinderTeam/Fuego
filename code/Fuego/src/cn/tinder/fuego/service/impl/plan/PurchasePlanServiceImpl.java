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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.IOP.ServiceContext;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PurchasePlanDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.domain.po.PurchasePlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.TransPlanService;

import cn.tinder.fuego.service.cache.AssetsTypeParaCache;
import cn.tinder.fuego.service.cache.UserCache;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserNameConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
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
	private static final Log log = LogFactory.getLog(CheckPlanServiceImpl.class);

  	
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
		super.deleteTransByID(transID);
		purchasePlanDao.deleteByTransID(transID);
		
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
			purchasePlan.setDuty(assets.getAssetsBo().getDuty());
			purchasePlan.setSpec(assets.getAssetsBo().getSpec());
			purchasePlan.setQuantity(assets.getAssetsBo().getQuantity());
			purchasePlan.setPrice(Float.valueOf(assets.getPrice()));
			purchasePlan.setNote(assets.getAssetsBo().getNote());
			purchasePlan.setAssetsType(assets.getAssetsBo().getAssetsType());
			purchasePlan.setUnit(assets.getAssetsBo().getUnit());
			purchasePlan.setSum(Float.valueOf(assets.getMoney()));
			purchasePlanDao.saveOrUpdate(purchasePlan);
		}
	}

	@Override
	public void forwardNext(String transID)
	{
		forwardNext(transID,null);
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID,String transInfo)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);
		
		List<PurchasePlan> planList = purchasePlanDao.getByTransID(transID);
  
		String type="";
		if(null != planList && !planList.isEmpty())
		{	

			type=planList.get(0).getAssetsType();
		}
		String handleUser;
		switch(transEvent.getCurrentStep())
		{
 
        case 5 :

        	handleUser = AssetsTypeParaCache.getInstance().getDeptByType(type);

        	if(null == UserCache.getInstance().getUserByName(handleUser))
        	{
        		 log.warn("can not get the user by name." + handleUser);
        		 throw new ServiceException(ExceptionMsg.ASSETS_TYPE_WRONG);
        	}
        	if(UserNameConst.CWZCB.equals(handleUser))
        	{
        		super.forwardNext(transID,handleUser,TransactionConst.TRANS_OPERATE_SUBMIT);
        	}
        	transInfo = null;

        	break;
        case 4 :
        	handleUser = UserNameConst.CWZCB;
        	break;
		case 3 :
			 handleUser = super.getLeader(UserNameConst.CWZCB);
			break;	
		case 2 :
		    handleUser = transEvent.getCreateUser();
 		    break;
		case 1 :
		    handleUser = transEvent.getCreateUser();
        	transInfo = TransactionConst.TRANS_OPERATE_FINISH;

 		    break;    
		default :
			handleUser = transEvent.getCreateUser();
		}
		

		super.forwardNext(transID,handleUser,transInfo);
		
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
			planInfoBo.getAssetsBo().setDuty(purchasePlan.getDuty());
			planInfoBo.getAssetsBo().setNote(purchasePlan.getNote());
			planInfoBo.getAssetsBo().setQuantity(purchasePlan.getQuantity());
			planInfoBo.setMoney(String.valueOf(purchasePlan.getSum()));
			planInfoBo.setPrice(String.valueOf(purchasePlan.getPrice()));
			planInfoBo.getAssetsBo().setUnit(purchasePlan.getUnit());
			planInfoBo.getAssetsBo().setAssetsType(purchasePlan.getAssetsType());
			planBoList.add(planInfoBo);
		}
		plan.getPurchasePageBo().setAssetsList(planBoList);
 
		 //init the all page data
 
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
	public void backward(String transID,String transInfo)
	{
		super.backward(transID,transInfo);
		
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

		return null;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanCount(java.util.List)
	 */
	@Override
	public int getPlanCount(List<String> transIDList)
	{
		int cnt = 0;

		List<PurchasePlan> purchasePlanList = purchasePlanDao.getByTransID(transIDList);
		for(PurchasePlan plan : purchasePlanList)
		{
			cnt +=  plan.getQuantity();
		}
		return cnt;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanAssetsSumValue(java.util.List)
	 */
	@Override
	public float getPlanAssetsSumValue(List<String> transIDList)
	{
		float sumValue = 0;
		List<PurchasePlan> purchasePlanList = purchasePlanDao.getByTransID(transIDList);
		for(PurchasePlan plan : purchasePlanList)
		{
			sumValue += plan.getPrice()*plan.getQuantity();
		}
 
		return sumValue;
	}

	@Override
	public List<AssetsInfoBo> importByFile(File file) {
		return null;
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#isMaxStep(int)
	 */
	@Override
	public int getMaxStep(String transID)
	{
		TransactionBaseInfoBo baseTrans = super.getTransByID(transID);

		SystemUser user = UserCache.getInstance().getUserByName(baseTrans.getCreateUser());
		if(null == user)
		{
			log.error("can not find the user by user name " + baseTrans.getCreateUser());
		}
		else
		{	
			if(user.getRole().equals(UserRoleConst.GASSTATION))
			{
				return Integer.valueOf(TransactionConst.DISCARD_GAS_MAX_STEP);
			}
			else if(user.getRole().equals(UserRoleConst.DEPT))
			{
				return Integer.valueOf(TransactionConst.DISCARD_MAX_STEP);

			}
			else
			{
				return Integer.valueOf(TransactionConst.PURCHASE_MAX_STEP);

			}
				
			
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getAprovalStep(java.lang.String)
	 */
	@Override
	public boolean isApporalStep(int step)
	{
		if(4 == step || 3 == step || 2 == step)
		{
			return true;
		}
		return false;
	}
}