/**   
 * @Title: AssignPlanServiceImpl.java 
 * @Package cn.tinder.fuego.service.impl 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午02:31:14 
 * @version V1.0   
 */
package cn.tinder.fuego.service.impl.plan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.AssignPlanDao;
import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.TransExtAttrDao;
import cn.tinder.fuego.domain.po.AssignPlan;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.RecapturePlan;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.domain.po.TransExtAttr;
import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.TransactionExtAttrConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.impl.TransactionServiceImpl;
import cn.tinder.fuego.service.model.convert.ConvertAssetsModel;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignTransBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.download.AssignFile;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/**
 * @ClassName: AssignPlanServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午02:31:14
 * 
 */

public class AssignPlanServiceImpl<E> extends TransactionServiceImpl implements TransPlanService<E>
{
	private static final Log log = LogFactory.getLog(CheckPlanServiceImpl.class);

	AssignPlanDao assignPlanDao = DaoContext.getInstance().getAssignPlanDao();
	TransExtAttrDao transExtAtrrDao = DaoContext.getInstance().getTransExtAttrDao();
	TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();
	PhysicalAssetsStatusDao physicalAssetsStatusDao = DaoContext.getInstance().getPhysicalAssetsStatusDao();
	AssetsManageService assetsManageService = ServiceContext.getInstance().getAssetsManageService();
	/*
	 * (non-Javadoc)
	 * S
	 * @see cn.tinder.fuego.service.TransPlanService#createPlan(java.lang.String)
	 */
	@Override
	public E createPlan(String user)
	{
		AssignPlanBo plan = new AssignPlanBo();
		TransactionBaseInfoBo baseTrans = super.createTransByUserAndType(user,user, TransactionConst.ASSIGN_PLAN_TYPE , null);
		AssignTransBo assignPlan = new AssignTransBo();
		assignPlan.setTransInfo(baseTrans);
		plan.setTransInfo(assignPlan);
		return (E) plan;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#updatePlan(cn.tinder.fuego. webservice.struts.bo.trans.TransactionBo, java.lang.Object)
	 */
	@Override
	public void updatePlan(E plan)
	{
		if(null == plan)
		{
			log.warn("the plan is null");
			return;
		}
		//step1: force transform 
 		AssignPlanBo planInfo = new AssignPlanBo();
		planInfo = (AssignPlanBo) plan;
		
		
		//step2: save basic transaction information
		String transID = planInfo.getTransInfo().getTransInfo().getTransID();
		String handleUser = planInfo.getTransInfo().getTransInfo().getHandleUser();
		super.updateTrans(transID,handleUser);

		//step3: save extend transaction information
		TransExtAttr ext = new TransExtAttr();
		ext.setTransID(transID);
		//step3.1 save input department to transaction extend attribute
		ext.setAttrName(TransactionExtAttrConst.ASSIGN_IN_DEPT);
		ext.setAttrValue(planInfo.getTransInfo().getInDept());
		transExtAtrrDao.saveOrUpdate(ext);
		//step3.1 save output department to transaction extend attribute
		ext.setAttrName(TransactionExtAttrConst.ASSIGN_OUT_DEPT);
		ext.setAttrValue(planInfo.getTransInfo().getOutDept());
		transExtAtrrDao.saveOrUpdate(ext);
		
		//step4: save assign plan list
		assignPlanDao.deleteByTransID(transID);
		List<AssetsInfoBo> assignAssetsList = planInfo.getAssetsPage().getAssetsList();
		for (AssetsInfoBo assets: assignAssetsList)
		{
			AssignPlan assignPlan = new AssignPlan();
			assignPlan.setTransID(transID);
			assignPlan.setAssetsID(assets.getAssets().getAssetsID());
			assignPlan.setNote(assets.getExtAttr().getNote());
			assignPlanDao.saveOrUpdate(assignPlan);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);

		List<TransExtAttr> extAtrrList = transExtAtrrDao.getByTransID(transID);
		
		String inDept ="";
		String outDept ="";
		for(TransExtAttr extAttr : extAtrrList )
		{
			if(TransactionExtAttrConst.ASSIGN_IN_DEPT.equals(extAttr.getAttrName()))
			{
				inDept = extAttr.getAttrValue();
			}
			if(TransactionExtAttrConst.ASSIGN_OUT_DEPT.equals(extAttr.getAttrName()))
			{
				outDept = extAttr.getAttrValue();
			}
		}
		
		String handleUser;
		switch(transEvent.getCurrentStep())
		{
		case 5 :
			handleUser = super.getLeader(transEvent.getCreateUser());
			break;
		case 4 :
			handleUser = super.getStaff(outDept);
			break;
		case 3 :
			handleUser = super.getStaff(inDept);
			break;	
		case 2 :
		    handleUser = transEvent.getCreateUser();
		case 1 :
		    handleUser = transEvent.getCreateUser();
		    updateAssetsDuty(transID, inDept);
		    break;
		default :
			handleUser = transEvent.getCreateUser();
		}

		super.forwardNext(transID,handleUser);

	}

	private void updateAssetsDuty(String transID, String dutyDept)
	{
		List<AssignPlan> assignPlanList =  assignPlanDao.getByTransID(transID);
		List<String> assetsIDList = new ArrayList<String>();
		for(AssignPlan assignPlan : assignPlanList)
		{
			String assetsID = assignPlan.getAssetsID();
		    assetsIDList.add(assetsID);
		    
		}
		List<PhysicalAssetsStatus> physicalAssetsList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(assetsIDList);
		for(PhysicalAssetsStatus assets : physicalAssetsList)
		{
			assets.setDuty(dutyDept);
			physicalAssetsStatusDao.saveOrUpdate(assets);

		}	
 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanByTransID(java.lang.String )
	 */
	@Override
	public E getPlanByTransID(String transID)
	{
		TransactionBaseInfoBo baseTrans = super.getTransByID(transID);
		if(null == baseTrans)
		{
			return null;
		}
		//new a AssignPlan
		AssignPlanBo assignPlan = new AssignPlanBo();
		// get assetsStatusList by TransID
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(getAssetsIDListByTransID(transID));
		// get assetsListBo
		List<AssetsInfoBo> assetsListBo = null;
		if(null != assetsStatusList)
		{
			assetsListBo = ConvertAssetsModel.convertAssetsList(assetsStatusList);
		}
		
		 
 
		assignPlan.getAssetsPage().setAssetsList(assetsListBo);
		//get discardTransBo by discardPlan 
 
		List<TransExtAttr> extAtrrList = transExtAtrrDao.getByTransID(transID);
		
		String inDept ="";
		String outDept ="";
		for(TransExtAttr extAttr : extAtrrList )
		{
			if(TransactionExtAttrConst.ASSIGN_IN_DEPT.equals(extAttr.getAttrName()))
			{
				inDept = extAttr.getAttrValue();
			}
			if(TransactionExtAttrConst.ASSIGN_OUT_DEPT.equals(extAttr.getAttrName()))
			{
				outDept = extAttr.getAttrValue();
			}
		}
 
		assignPlan.getTransInfo().setInDept(inDept);
		assignPlan.getTransInfo().setOutDept(outDept);

		assignPlan.getTransInfo().setTransInfo(baseTrans);
		
		 //init the all page data
		 assignPlan.getAssetsPage().getPage().setAllPageData(assignPlan.getAssetsPage().getAssetsList());

		return (E) assignPlan;
	}
	private List<String> getAssetsIDListByTransID(String transID)
	{
		List<AssignPlan> assignPlanList = assignPlanDao.getByTransID(transID);
		List<String> assetsIDList = new ArrayList<String>();
        String assetsID = null;
 		for(AssignPlan assignAssetsPlan : assignPlanList)
		{
			 assetsID = assignAssetsPlan.getAssetsID();
			 assetsIDList.add(assetsID);
		}
		return assetsIDList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransPlanService#deletePlan(java.lang.String)
	 */
	@Override
	public void deletePlan(String transID)
	{
		super.deleteTransByID(transID);
		assignPlanDao.deleteByTransID(transID);

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getExportFile(java.lang.Object)
	 */
	@Override
	public File getExportFile(E plan)
	{
		if(null == plan)
		{
			log.warn("the plan is null");
			return null;
		}
		//step1: force transform 
 		AssignPlanBo planInfo = new AssignPlanBo();
		planInfo = (AssignPlanBo) plan;
		
		AssignFile assignFile=new AssignFile(planInfo);
		
		return assignFile.getFile();
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
 		AssignPlanBo planInfo = (AssignPlanBo)plan;
 		
 		List<AssetsInfoBo> assetsList = planInfo.getAssetsPage().getAssetsList();
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

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanCount(java.util.List)
	 */
	@Override
	public int getPlanCount(List<String> transIDList)
	{
		int cnt = super.getAssetsCount(getAssestByTransIDList(transIDList));
	 
		
		return cnt;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanAssetsSumValue(java.util.List)
	 */
	@Override
	public float getPlanAssetsSumValue(List<String> transIDList)
	{
		float sumValue = super.getAssetsSumValue(getAssestByTransIDList(transIDList));
		return sumValue;
	}
	
	private List<PhysicalAssetsStatus> getAssestByTransIDList(List<String> transIDList)
	{
		List<AssignPlan> planList = assignPlanDao.getByTransID(transIDList);
		List<String> assetsIDList = new ArrayList<String>();
 		for(AssignPlan plan : planList)
		{
			 assetsIDList.add(plan.getAssetsID());
		}
		List<PhysicalAssetsStatus> assetsStatusList = physicalAssetsStatusDao.getAssetsListByAssetsIDList(assetsIDList);
		return assetsStatusList;
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
		// TODO Auto-generated method stub
		return 0;
	}
}
