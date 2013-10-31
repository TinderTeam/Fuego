/**   
* @Title: CheckPlanServiceImpl.java 
* @Package cn.tinder.fuego.service.impl.plan 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 上午03:23:11 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.impl.plan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.CheckPlanDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.domain.po.CheckPlan;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckPlanInfoBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckTransBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/** 
 * @ClassName: CheckPlanServiceImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 上午03:23:11 
 *  
 */

public class CheckPlanServiceImpl<E> extends TransactionServiceImpl implements TransPlanService<E>
{
	private static final Log log = LogFactory.getLog(CheckPlanServiceImpl.class);

	private TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();

	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	private CheckPlanDao checkPlanDao = DaoContext.getInstance().getCheckPlanDao();
	private AssetsManageService assetsManageService = ServiceContext.getInstance().getAssetsManageService();

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user)
	{
		CheckPlanBo plan = new CheckPlanBo();

		TransactionBaseInfoBo parentTrans = super.createTransByUserAndType(user,user, TransactionConst.CHECK_PLAN_TYPE,null);
		
		
		List<SystemUser> gasUserList = systemUserDao.getUserByRole(UserRoleConst.GASSTATION);
 		
		//create a transaction for every gas station
		for(SystemUser gasUser : gasUserList)
		{
 			TransactionBaseInfoBo trans = super.createTransByUserAndType(user,gasUser.getUserName(), TransactionConst.CHECK_PLAN_TYPE,parentTrans.getTransID());
 			 
 			 List<CheckPlanInfoBo> planList = assetsManageService.getCheckSumAssetsList(gasUser.getDepartment());
 			 for(CheckPlanInfoBo checkPlanBo : planList)
 			 {
 				CheckPlan checkPlan = convertCheckPlanByBo(trans, checkPlanBo);
 	  			checkPlanDao.create(checkPlan);
  			 }
 			plan.getPlanInfo().addAll(planList);
		}
  		plan.getTransInfo().setTransInfo(parentTrans);
		return (E)plan;
	}

	private CheckPlan convertCheckPlanByBo(TransactionBaseInfoBo trans, CheckPlanInfoBo checkPlanBo)
	{
		CheckPlan checkPlan = new CheckPlan();
		checkPlan.setTransID(trans.getTransID());
		checkPlan.setDeptID(checkPlanBo.getAssets().getDuty());
		checkPlan.setAssetsName(checkPlanBo.getAssets().getAssetsName());
		checkPlan.setManufacture(checkPlanBo.getAssets().getManufacture());
		checkPlan.setSpec(checkPlanBo.getAssets().getSpec());
		checkPlan.setQuantity(checkPlanBo.getAssets().getQuantity());
		checkPlan.setCheckCnt(checkPlanBo.getCheckCnt());
		checkPlan.setCheckState(checkPlanBo.getCheckState());
		return checkPlan;
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
		// TODO Auto-generated method stub
		
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
		case 1 :
		    handleUser = transEvent.getCreateUser();
		    break;
		default :
			handleUser = transEvent.getCreateUser();
			log.warn("the step i unexpected. step is" + transEvent.getCurrentStep());
			
		}
		super.forwardNext(transID, handleUser);
		
		
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
		CheckPlanBo plan = new CheckPlanBo();
		CheckTransBo checkTrans = new CheckTransBo();
		checkTrans.setTransInfo(baseTrans);
		plan.setTransInfo(checkTrans);

		List<TransEvent> childEventList = transEventDao.getTransByParentID(transID);

		 for(TransEvent event : childEventList)
		 {
			 if(null == checkTrans.getChildTransList())
			 {
				 checkTrans.setChildTransList(new ArrayList<CheckTransBo>());
			 }
			CheckTransBo child = new CheckTransBo();
			child.setTransInfo(super.getTransByID(event.getTransID()));
			checkTrans.getChildTransList().add(child);
		 } 
		

		List<CheckPlanInfoBo> planInfoList = new ArrayList<CheckPlanInfoBo>();
		List<CheckPlan> checkPlanList = getCheckPlanListByTranID(transID);
		for(CheckPlan checkPlan : checkPlanList)
		{
			CheckPlanInfoBo planInfoBo = new CheckPlanInfoBo();
			planInfoBo.getAssets().setAssetsName(checkPlan.getAssetsName());
			planInfoBo.getAssets().setManufacture(checkPlan.getManufacture());
			planInfoBo.getAssets().setSpec(checkPlan.getSpec());
			planInfoBo.getAssets().setDuty(checkPlan.getDeptID());
			planInfoBo.setCheckState(checkPlan.getCheckState());
			planInfoBo.setCheckCnt(checkPlan.getCheckCnt());
			planInfoList.add(planInfoBo);
		}
		plan.setPlanInfo(planInfoList);

		return (E) plan;
	}

	private List<CheckPlan>  getCheckPlanListByTranID(String transID)
	{
		List<TransEvent> childEventList = transEventDao.getTransByParentID(transID);
		 
		 List<String> childEventIDList = new ArrayList<String>();
		 for(TransEvent event : childEventList)
		 {
			 childEventIDList.add(event.getTransID());
		 }
		 
		 List<CheckPlan> checkPlanList;
		 // the check transaction is child 
		 if(null == childEventList || childEventList.isEmpty())
		 {
			 checkPlanList = checkPlanDao.getByTransID(transID);
		 }
		 else
		 {
			 checkPlanList = checkPlanDao.getByTransIDList(childEventIDList);
		 }
		 return checkPlanList;
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#validate(java.lang.Object)
	 */
	@Override
	public void validate(E plan)
	{
		// TODO Auto-generated method stub
		
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
