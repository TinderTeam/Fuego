package cn.fuego.activity.domain.po;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: BasicActivityRecord 
* @Description: 基本活动记录是存储在数据库中的事件记录
* @author Nan Bowen
* @date 2013-12-14 下午05:05:19 
*
 */
@Component ("BasicActivityRecord")
public class BasicActivityRecord {
	private String	actvtID;	
	private String	actvtCreator;
	private Date	actvtCreatorTime;	
	private String	actvtHandleUser;
	
	private Date	actvtLastHandelTime;
	private int		currentStep;
	private int		lastStep;
	private String	actvtType;
	private String	prarentActvtID;
	/**
	 * @return the actvtID
	 */
	public String getActvtID() {
		return actvtID;
	}
	/**
	 * @param actvtID the actvtID to set
	 */
	public void setActvtID(String actvtID) {
		this.actvtID = actvtID;
	}
	/**
	 * @return the actvtCreator
	 */
	public String getActvtCreator() {
		return actvtCreator;
	}
	/**
	 * @param actvtCreator the actvtCreator to set
	 */
	public void setActvtCreator(String actvtCreator) {
		this.actvtCreator = actvtCreator;
	}
	/**
	 * @return the actvtCreatorTime
	 */
	public Date getActvtCreatorTime() {
		return actvtCreatorTime;
	}
	/**
	 * @param actvtCreatorTime the actvtCreatorTime to set
	 */
	public void setActvtCreatorTime(Date actvtCreatorTime) {
		this.actvtCreatorTime = actvtCreatorTime;
	}
	/**
	 * @return the actvtHandleUser
	 */
	public String getActvtHandleUser() {
		return actvtHandleUser;
	}
	/**
	 * @param actvtHandleUser the actvtHandleUser to set
	 */
	public void setActvtHandleUser(String actvtHandleUser) {
		this.actvtHandleUser = actvtHandleUser;
	}
	/**
	 * @return the actvtLastHandelTime
	 */
	public Date getActvtLastHandelTime() {
		return actvtLastHandelTime;
	}
	/**
	 * @param actvtLastHandelTime the actvtLastHandelTime to set
	 */
	public void setActvtLastHandelTime(Date actvtLastHandelTime) {
		this.actvtLastHandelTime = actvtLastHandelTime;
	}
	/**
	 * @return the currentStep
	 */
	public int getCurrentStep() {
		return currentStep;
	}
	/**
	 * @param currentStep the currentStep to set
	 */
	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}
	/**
	 * @return the actvtType
	 */
	public String getActvtType() {
		return actvtType;
	}
	/**
	 * @param actvtType the actvtType to set
	 */
	public void setActvtType(String actvtType) {
		this.actvtType = actvtType;
	}
	/**
	 * @return the prarentActvtID
	 */
	public String getPrarentActvtID() {
		return prarentActvtID;
	}
	/**
	 * @param prarentActvtID the prarentActvtID to set
	 */
	public void setPrarentActvtID(String prarentActvtID) {
		this.prarentActvtID = prarentActvtID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicActivityRecord [actvtID=" + actvtID + ", actvtCreator="
				+ actvtCreator + ", actvtCreatorTime=" + actvtCreatorTime
				+ ", actvtHandleUser=" + actvtHandleUser
				+ ", actvtLastHandelTime=" + actvtLastHandelTime
				+ ", currentStep=" + currentStep + ", actvtType=" + actvtType
				+ ", prarentActvtID=" + prarentActvtID + "]";
	}
	public void setLastStep(int lastStep) {
		this.lastStep = lastStep;
	}
	public int getLastStep() {
		return lastStep;
	}
	
	
	

	
}
