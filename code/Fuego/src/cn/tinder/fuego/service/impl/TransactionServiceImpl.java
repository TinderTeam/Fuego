/**   
 * @Title: TransactionServiceImpl.java 
 * @Package cn.tinder.fuego.service.impl 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午01:53:18 
 * @version V1.0   
 */
package cn.tinder.fuego.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.TransEventTypeDao;
import cn.tinder.fuego.dao.TransOperRecordDao;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.domain.po.TransEventType;
import cn.tinder.fuego.domain.po.TransOperRecord;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransPlanService;
import cn.tinder.fuego.service.TransactionService;
import cn.tinder.fuego.service.cache.CacheContext;
import cn.tinder.fuego.service.cache.UserCache;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.model.convert.ConvertTransactionModel;
import cn.tinder.fuego.util.ValidatorUtil;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.trans.TransOperInfoBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;
import cn.tinder.fuego.webservice.struts.form.TransFilterForm;

/**
 * @ClassName: TransactionServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午01:53:18
 * 
 */

public class TransactionServiceImpl implements TransactionService
{
	private static final Log log = LogFactory.getLog(TransactionServiceImpl.class);

	private TransEventDao transEventDao = DaoContext.getInstance().getTransEventDao();
	private TransOperRecordDao transOperRecordDao = DaoContext.getInstance().getTransOperRecordDao();

	private TransEventTypeDao transEventTypeDao = DaoContext.getInstance().getTransEventTypeDao();
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransactionService#createTransByUserAndType(java .lang.String, java.lang.String)
	 */
	@Override
	public TransactionBaseInfoBo createTransByUserAndType(String createUser, String handleUser,String transType, String parentTransID)
	{
		int currentID;
		int currentStep;
		String transactionID;
		Date creatTime;

		TransEvent transEvent = new TransEvent();
		TransEventType transEventType  = transEventTypeDao.getByType(transType); // get
																	// transEventType
		currentID = transEventType.getCurrentID() + 1; // get currentID
		transEventType.setCurrentID(currentID); // set currentID to
												// transEventType
		transactionID = getTransIDbyTypeAndCurID(transType,currentID); // get transactionID
		transEvent.setTransID(transactionID); // set transactionID to transEvent

		transEvent.setTransName(ConvertTransactionModel.getTransNameByTransType(transType)); // get
																		// TransName
																		// and
																		// set
																		// to
																		// transEvent

		creatTime = new Date(System.currentTimeMillis());
		transEvent.setCreateTime(creatTime); // get CreateTime and set to
												// transEvent

		transEvent.setCreateUser(createUser); // get CreateUser and set to
											// transEvent

		transEvent.setEndTime(null); // get EndTime and set to transEvent

		if(ValidatorUtil.isEmpty(handleUser))
		{
			transEvent.setHandleUser(createUser); // get HandleUser and set to
		}
		else
		{
			transEvent.setHandleUser(handleUser); // get HandleUser and set to
		}
											// transEvent
 
		transEvent.setCurrentStep(transEventType.getStep()); // get CurrentStep and set to
												// transEvent
		transEvent.setStatus(TransactionConst.TRANS_STATUS_TODO);

		transEvent.setType(transType); // get type and set to transEvent

		transEvent.setParentTransID(parentTransID);  // get ParentTransID
		transEventDao.create(transEvent); // push new transEvent to DB
		transEventTypeDao.saveOrUpdate(transEventType);// push new
														// transEventType to DB

		return ConvertTransactionModel.covertTransBase(transEvent);
		// Convert new transEvent to TransactionBaseInfoBo
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransactionService#getTransByID(java.lang.String)
	 */
	@Override
	public TransactionBaseInfoBo getTransByID(String transID)
	{
		TransEvent transEvent = new TransEvent();
		transEvent = transEventDao.getByTransID(transID);
		if (null == transEvent)
		{
			log.warn("can not get transaction by id. id is " + transID);
			return null;
		}

		return ConvertTransactionModel.covertTransBase(transEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransactionService#deleteTransByID(java.lang. String)
	 */
	@Override
	public void deleteTransByID(String transID)
	{
		TransEvent transEvent;
		transEvent = transEventDao.getByTransID(transID);
		transEventDao.delete(transEvent);

	}
	
	public void deletePlanByTransID(String user,String transID)
	{
		TransEvent transEvent;
		transEvent = transEventDao.getByTransID(transID);
		if(!transEvent.getCreateUser().equals(user))
		{
			log.warn("the transaction is created by user,can not be deleted" + user);
			throw new ServiceException(ExceptionMsg.TRANSACTION_NOT_CREATE_BY_USER);
		}
		TransPlanService planService = ServiceContext.getInstance().getPlanServiceByType(transEvent.getType());
		planService.deletePlan(transID);
		
		List<TransEvent> childList = transEventDao.getTransByParentID(transID);
		if(null != childList && !childList.isEmpty())
		{
			//delele all the child plan
			for(TransEvent event : childList)
			{
				deletePlanByTransID(event.getCreateUser(),event.getTransID());
			}
		}


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransactionService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID, String handleUser,String transInfo)
	{

		TransEvent transEvent =transEventDao.getByTransID(transID);
		if (null == transEvent)
		{
			throw new ServiceException(ExceptionMsg.TRANS_ID_NOEXIST);
		}
		int curStep = transEvent.getCurrentStep();
		if (TransactionConst.END_STEP_FLAG == curStep)
		{
			throw new ServiceException(ExceptionMsg.TRANS_HAS_OVER);
		}
		else
		{
			curStep--;
			
		}
		if(null !=  transInfo)
		{
			addTransOperInfo(transEvent,transInfo,true);
		}
		//get the status by current step 
		if(TransactionConst.END_STEP_FLAG == curStep)
		{
			transEvent.setEndTime(DateService.getCurrentDate());
			transEvent.setStatus(TransactionConst.TRANS_STATUS_DONE);

			log.info("the transaction is finished." + transEvent.toString());
		}
		else
		{	
			transEvent.setStatus(TransactionConst.TRANS_STATUS_DOING);

		}
		
		transEvent.setCurrentStep(curStep);
		transEvent.setHandleUser(handleUser);
		transEvent.setHandleTime(DateService.getCurrentDate());
		transEventDao.saveOrUpdate(transEvent);
		

		
	}

	private void addTransOperInfo(TransEvent transEvent,String transInfo,boolean isPass)
	{
		TransOperRecord transOperRecord = new TransOperRecord();
		transOperRecord.setTransID(transEvent.getTransID());
		transOperRecord.setOperTime(DateService.getCurrentDateTimeStr());
		
		if(ValidatorUtil.isEmpty(transEvent.getHandleUser()))
		{
			transOperRecord.setUserName(transEvent.getCreateUser());

		}
		else
		{
			transOperRecord.setUserName(transEvent.getHandleUser());
		}
		transOperRecord.setStep(transEvent.getCurrentStep());
		transOperRecord.setTransInfo(transInfo);
		
		TransPlanService service = ServiceContext.getInstance().getPlanServiceByType(transEvent.getType());
		
		String result = "";
		if(isPass)
		{
			if(transEvent.getCurrentStep() == service.getMaxStep(transEvent.getTransID()))
			{
				result = TransactionConst.TRANS_RESULT_SUCCESS;
			}
			else if(service.isApporalStep(transEvent.getCurrentStep()))
			{
				result = TransactionConst.TRANS_RESULT_AGREE;
			}
			else
			{
				result = TransactionConst.TRANS_RESULT_SUCCESS;
			}
		}
		else
		{
			if(transEvent.getCurrentStep() == service.getMaxStep(transEvent.getTransID()))
			{
				result = TransactionConst.TRANS_RESULT_SUCCESS;
			}
			else if(service.isApporalStep(transEvent.getCurrentStep()))
			{
				result = TransactionConst.TRANS_RESULT_REFUSE;
			}
			else
			{
				result = TransactionConst.TRANS_RESULT_FAILED;
			}
		}
		transOperRecord.setResult(result);

		transOperRecordDao.create(transOperRecord);
	}

	 
	public void updateTrans(String transID, String handleUser,String executeName)
	{
 		TransEvent transEvent = new TransEvent();
		transEvent = transEventDao.getByTransID(transID);
		transEvent.setHandleUser(handleUser);
		transEvent.setExecuteName(executeName);
 
		transEventDao.saveOrUpdate(transEvent);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransactionService#updateTrans(cn.tinder.fuego .webservice.struts.bo.trans.TransactionBaseInfoBo)
	 */
	@Override
	public void updateTrans(String transID, String handleUser)
	{
 		TransEvent transEvent = new TransEvent();
		transEvent = transEventDao.getByTransID(transID);
		transEvent.setHandleUser(handleUser);
 
 
		transEventDao.saveOrUpdate(transEvent);
	}
	
	public void updateTrans(TransactionBaseInfoBo trans)
	{
 		TransEvent transEvent = new TransEvent();
 		transEvent.setTransID(trans.getTransID());
 		transEvent.setTransName(trans.getTransName());
 		transEvent.setCreateTime(trans.getCreateTime());
 		transEvent.setCreateUser(trans.getCreateUser());
 		//transEvent.setCurrentStep(tran);
 		transEvent.setEndTime(trans.getEndTime());
 		
 		transEvent.setHandleUser(trans.getHandleUser());
 		//transEvent.setParentTransID(trans);
 		transEvent.setStatus(trans.getState());
 		//transEvent.setType(trans.get);
  
		transEventDao.saveOrUpdate(transEvent);
	}



	// Convert currentID to TransID
	private String getTransIDbyTypeAndCurID(String transType,int currentID)
	{
		String curID = "";
		int m = 0;
		curID = String.valueOf(currentID);
		m = TransactionConst.ID_LENGTH - curID.length();
		curID = transType + getZeroStr(m) + curID;

		return curID;

	}
	// Convert UserName by Department 
	public String getStaff (String dept)
	{
		List<SystemUser> userList =systemUserDao.getUserByDept(dept);
		String staff = null;
		
		if(null != userList && !userList.isEmpty())
		{
			staff = userList.get(0).getUserName();
		}
		for(SystemUser user : userList)
		{
			if( UserRoleConst.DEPT.equals(user.getRole()))
			{
				staff = user.getUserName();
			}
			else if(UserRoleConst.SUPER_DEPT.equals(user.getRole()))
			{
				staff = user.getUserName();
			}
			else
			{
				log.warn("can not find the staff by department =" + dept );
			}
		}

		return staff;

	}
	
	
	public String getLeader (String userName)
	{
		SystemUser systemUser = systemUserDao.find(userName);
		List<SystemUser> userList = systemUserDao.getUserByDept(systemUser.getDepartment());
		String staff = null;
		
		if(null != userList && !userList.isEmpty())
		{
			staff = userList.get(0).getUserName();
		}
		for(SystemUser user : userList)
		{
			if( UserRoleConst.DEPT_LEADER.equals(user.getRole()))
			{
				staff = user.getUserName();
			}
			else if(UserRoleConst.SUPER_LEADER.equals(user.getRole()))
			{
				staff = user.getUserName();
			}
			else
			{
				log.warn("can not find the staff by department =" + user);
			}
		}


		return staff;

	}
	
	
	// get some "0" and add to transID
	private String getZeroStr(int n)
	{
		String str = "";
		for (int i = 0; i < n; i++)
		{
			str = str + "0";
		}

		return str;

	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransactionService#getTransListByUser()
	 */
	@Override
	public List<TransactionBaseInfoBo> getDisTransByUser(String userID)
	{
		SystemUser nowUser = CacheContext.getInstance().getUserCache().getUserByName(userID);
		
		List<String> userList = new ArrayList<String>();  

		if(nowUser.getRole().equals(UserRoleConst.SUPER_DEPT))
		{
			for(SystemUser user : CacheContext.getInstance().getUserCache().getAllUser())
			{
				userList.add(user.getUserName());
			}
		}
		else if (nowUser.getRole().equals(UserRoleConst.DEPT))
		{
			for(SystemUser user : CacheContext.getInstance().getUserCache().getUserListByRole(UserRoleConst.GASSTATION))
			{
				userList.add(user.getUserName());
			}
		}
 
		userList.add(userID);
		
		List<TransEvent> eventList = this.transEventDao.getTransByUser(userList,userID);

		return ConvertTransactionModel.covertTransBaseList(eventList);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransactionService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID,String transInfo)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);
		
		this.addTransOperInfo(transEvent, transInfo,false);

		TransEventType type = transEventTypeDao.getByType(transEvent.getType());
		transEvent.setCurrentStep(type.getStep());
		transEvent.setHandleUser(transEvent.getCreateUser());
		transEvent.setHandleTime(DateService.getCurrentDate());
		transEvent.setStatus(TransactionConst.TRANS_RESULT_REFUSE);
		transEventDao.saveOrUpdate(transEvent);
	}

	public boolean hasChildTrans(String transID)
	{
		List<TransEvent> eventList = transEventDao.getTransByParentID(transID);
		if(null == eventList || eventList.isEmpty())
		{
			return false;
		}
		return true;
	}
	public boolean isParentTransFinish(String transID)
	{
        List<TransEvent>  childList = transEventDao.getTransByParentID(transID);
		
		boolean finished = true;

		if(null != childList && !childList.isEmpty())
		{
			for(TransEvent child : childList)
			{	
				if(TransactionConst.END_STEP_FLAG != child.getCurrentStep())
				{
					finished = false;
				}
			}
		}
		return finished;
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransactionService#getTransListByUser(java.lang.String, cn.tinder.fuego.webservice.struts.form.TransFilterForm)
	 */
	@Override
	public List<TransactionBaseInfoBo> getTransListByUser(String userID, TransFilterForm filter)
	{
		TransEvent filter1 = new TransEvent();
		TransEvent filter2 = new TransEvent();
		filter1.setCreateUser(userID);
		if(null  != filter)
		{
			filter1.setTransName(filter.getTransName());
			filter1.setEndTime(DateService.stringToDate(filter.getFirstEndTime()));
			filter2.setEndTime(DateService.stringToDate(filter.getLastEndTime()));
		}
		 
		 
		List<TransEvent> eventList = this.transEventDao.getTransByFilter(filter1, filter2);
		
		return ConvertTransactionModel.covertTransBaseList(eventList);
 	}
	
	
	// get transName by transType
	public  String getTransTypeByTransName(String name)
	{
		String type = "";
		if (TransactionConst.ASSIGN_PLAN_NAME.equals(name))
		{
			type = TransactionConst.ASSIGN_PLAN_TYPE;
		}
		else if(TransactionConst.CHECK_PLAN_NAME.equals(name))
		{
			type = TransactionConst.CHECK_PLAN_TYPE;
		}
		else if(TransactionConst.DISCARD_PLAN_NAME.equals(name))
		{
			type = TransactionConst.DISCARD_PLAN_TYPE;
		}
		else if(TransactionConst.PURCHASE_PLAN_NAME.equals(name))
		{
			type = TransactionConst.PURCHASE_PLAN_TYPE;
		}
		else if(TransactionConst.RECAPTURE_PLAN_NAME.equals(name))
		{
			type = TransactionConst.RECAPTURE_PLAN_TYPE;
		}
		else if(TransactionConst.RECEIVE_PLAN_NAME.equals(name))
		{
			type = TransactionConst.RECEIVE_PLAN_TYPE;
		}
		
		
		return type;
	}
	
	public List<String> getChildTransIDByIDList(List<String> transIDList)
	{
		List<String> allTransIDList = new ArrayList<String>();
		for(String transID : transIDList)
		{
			List<TransEvent> transEventList = transEventDao.getTransByParentID(transID);
			
			//if the transaction is parent, we should get the plan by his child transaction
			if(null == transEventList || transEventList.isEmpty())
			{
				allTransIDList.add(transID);
			}
			else
			{	
				for(TransEvent transEvent : transEventList)
				{
					allTransIDList.add(transEvent.getTransID());
				}
			}
		}
		return allTransIDList;
	}
	
	public int getAssetsCount(List<PhysicalAssetsStatus> assetsList)
	{
		
		int cnt = 0;
		for(PhysicalAssetsStatus assets : assetsList)
		{
			cnt +=   assets.getQuantity();
		}
		
		return cnt;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransPlanService#getPlanAssetsSumValue(java.util.List)
	 */
 
	public float getAssetsSumValue(List<PhysicalAssetsStatus> assetsList)
	{
		float sumValue = 0;
 
		for(PhysicalAssetsStatus assets : assetsList)
		{
			sumValue += assets.getOriginalValue()* assets.getQuantity();
		}
		return sumValue;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransactionService#getTransOperInfoByTransID(java.lang.String)
	 */
	@Override
	public List<TransOperInfoBo> getTransOperInfoByTransID(String transID)
	{
	    List<TransOperRecord> recordList = this.transOperRecordDao.getByTransID(transID);
	    TransEvent transEvent = this.transEventDao.getByTransID(transID);

	    List<TransOperInfoBo> transOperInfoList = new ArrayList<TransOperInfoBo>();
	    for(TransOperRecord record : recordList)
	    {
	    	TransOperInfoBo transOperInfo = new TransOperInfoBo();
	    	transOperInfo.setTransID(record.getTransID());
	    	transOperInfo.setTransName(transEvent.getTransName());
	    	transOperInfo.setUserName(record.getUserName());
	    	transOperInfo.setOperTime(record.getOperTime());
	    	transOperInfo.setNickName(UserCache.getInstance().getUserByName(record.getUserName()).getNickName());
	    	transOperInfo.setStep(record.getStep());
	    	transOperInfo.setStepName(record.getStepName());
	    	transOperInfo.setResult(record.getResult());
	    	transOperInfo.setTransInfo(record.getTransInfo());
	    	transOperInfoList.add(transOperInfo);

	    }
		return transOperInfoList;
	}

 
}
