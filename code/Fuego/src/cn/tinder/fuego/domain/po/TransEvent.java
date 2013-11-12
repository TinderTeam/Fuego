package cn.tinder.fuego.domain.po;

import java.sql.Date;

public class TransEvent
{
	private String transID;
	private String transName;
	private Date createTime;
	private String createUser;
	private Date endTime;
	private String handleUser;
	private int currentStep;
	private String status;
	private String type;
	private String parentTransID;

	/**
	 * @return the transID
	 */
	public String getTransID()
	{
		return transID;
	}

	/**
	 * @param transID
	 *            the transID to set
	 */
	public void setTransID(String transID)
	{
		this.transID = transID;
	}

	/**
	 * @return the transName
	 */
	public String getTransName()
	{
		return transName;
	}

	/**
	 * @param transName
	 *            the transName to set
	 */
	public void setTransName(String transName)
	{
		this.transName = transName;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime()
	{
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser()
	{
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * @return the handleUser
	 */
	public String getHandleUser()
	{
		return handleUser;
	}

	/**
	 * @param handleUser
	 *            the handleUser to set
	 */
	public void setHandleUser(String handleUser)
	{
		this.handleUser = handleUser;
	}

	/**
	 * @return the state
	 */

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	/**
	 * @param currentStep
	 *            the currentStep to set
	 */
	public void setCurrentStep(int currentStep)
	{
		this.currentStep = currentStep;
	}

	/**
	 * @return the currentStep
	 */
	public int getCurrentStep()
	{
		return currentStep;
	}

	public String getParentTransID()
	{
		return parentTransID;
	}

	public void setParentTransID(String parentTransID)
	{
		this.parentTransID = parentTransID;
	}

	
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		return "TransEvent [transID=" + transID + ", transName=" + transName + ", createTime=" + createTime + ", createUser=" + createUser + ", endTime=" + endTime + ", handleUser=" + handleUser
				+ ", currentStep=" + currentStep + ", type=" + type + ", parentTransID=" + parentTransID + "]";
	}

}
