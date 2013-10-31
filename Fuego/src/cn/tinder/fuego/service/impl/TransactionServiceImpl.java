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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.TransEventTypeDao;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.domain.po.TransEventType;
import cn.tinder.fuego.service.TransactionService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.model.convert.ConvertTransactionModel;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.TransEventBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

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
		TransEventType transEventType = new TransEventType();

		transEventType = transEventTypeDao.getByType(transType); // get
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

		transEvent.setHandleUser(handleUser); // get HandleUser and set to
											// transEvent

		currentStep = transEventType.getStep();
		transEvent.setCurrentStep(currentStep); // get CurrentStep and set to
												// transEvent

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
		TransEvent transEvent = new TransEvent();
		transEvent = transEventDao.getByTransID(transID);
		transEventDao.delete(transEvent);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.TransactionService#forwardNext(java.lang.String)
	 */
	@Override
	public void forwardNext(String transID, String handleUser)
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
		
		transEvent.setCurrentStep(curStep);
		transEvent.setHandleUser(handleUser);
		updateTrans(transID,handleUser);
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
		Date endTime;
		TransEvent transEvent = new TransEvent();
		transEvent = transEventDao.getByTransID(transID);
		transEvent.setHandleUser(handleUser);
		if (TransactionConst.END_STEP_FLAG == transEvent.getCurrentStep())
		{
			endTime = new Date(System.currentTimeMillis());
			transEvent.setEndTime(endTime);
			log.info("the transaction is finished." + transEvent.toString());
		}
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
	public List<TransactionBaseInfoBo> getTransListByUser(String userID)
	{
		List<TransEvent> eventList = this.transEventDao.getTodoTransByHandlerUser(userID);
		
		return ConvertTransactionModel.covertTransBaseList(eventList);
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.TransactionService#backward(java.lang.String)
	 */
	@Override
	public void backward(String transID)
	{
		TransEvent transEvent =transEventDao.getByTransID(transID);
		TransEventType type = transEventTypeDao.getByType(transEvent.getType());
		transEvent.setCurrentStep(type.getStep());
		transEvent.setHandleUser(transEvent.getCreateUser());
		transEventDao.saveOrUpdate(transEvent);
	}
	 

}
