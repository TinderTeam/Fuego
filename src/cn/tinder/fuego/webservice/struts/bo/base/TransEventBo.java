package cn.tinder.fuego.webservice.struts.bo.base;

import java.sql.Date;

public class TransEventBo {
	
	private String transID;
	private String transName;
	private Date createTime;
	private String createUser;
	private Date endTime;
	private String handleUser;
	private int currentStep;
	private String type;
	
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getHandleUser() {
		return handleUser;
	}
	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}
	public int getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "TransEventBo [transID=" + transID + ", transName=" + transName
				+ ", createTime=" + createTime + ", createUser=" + createUser
				+ ", endTime=" + endTime + ", handleUser=" + handleUser
				+ ", currentStep=" + currentStep + ", type=" + type + "]";
	}
	
}
