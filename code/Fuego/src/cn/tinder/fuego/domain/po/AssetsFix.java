package cn.tinder.fuego.domain.po;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component ("assetsFix")
//利用自动Spring自动加载功能
public class AssetsFix implements Serializable{
	private String indexNumber;
	private String context;
	private String gasStation;
	private String dept;
	private String handleUser;
	private String budget;
	private String sendTime;
	private String startTime;
	private String finishTime;
	private String payMoney;
	private String payTime;
	private String diff;
	private String alreadyPay;
	private String unPay;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssetsFix [indexNumber=" + indexNumber + ", context=" + context
				+ ", gasStation=" + gasStation + ", dept=" + dept
				+ ", handleUser=" + handleUser + ", budget=" + budget
				+ ", sendTime=" + sendTime + ", startTime=" + startTime
				+ ", finishTime=" + finishTime + ", payMoney=" + payMoney
				+ ", payTime=" + payTime + ", diff=" + diff + ", alreadyPay="
				+ alreadyPay + ", unPay=" + unPay + ", note=" + note + "]";
	}
	private String note;
	

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * @return the gasStation
	 */
	public String getGasStation() {
		return gasStation;
	}
	/**
	 * @param gasStation the gasStation to set
	 */
	public void setGasStation(String gasStation) {
		this.gasStation = gasStation;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return the handleUser
	 */
	public String getHandleUser() {
		return handleUser;
	}
	/**
	 * @param handleUser the handleUser to set
	 */
	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}
	/**
	 * @return the budget
	 */
	public String getBudget() {
		return budget;
	}
	/**
	 * @param budget the budget to set
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}
	/**
	 * @return the sendTime
	 */
	public String getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the finishTime
	 */
	public String getFinishTime() {
		return finishTime;
	}
	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	/**
	 * @return the payMoney
	 */
	public String getPayMoney() {
		return payMoney;
	}
	/**
	 * @param payMoney the payMoney to set
	 */
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	/**
	 * @return the payTime
	 */
	public String getPayTime() {
		return payTime;
	}
	/**
	 * @param payTime the payTime to set
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	/**
	 * @return the diff
	 */
	public String getDiff() {
		return diff;
	}
	/**
	 * @param diff the diff to set
	 */
	public void setDiff(String diff) {
		this.diff = diff;
	}
	/**
	 * @return the alreadyPay
	 */
	public String getAlreadyPay() {
		return alreadyPay;
	}
	/**
	 * @param alreadyPay the alreadyPay to set
	 */
	public void setAlreadyPay(String alreadyPay) {
		this.alreadyPay = alreadyPay;
	}
	/**
	 * @return the unPay
	 */
	public String getUnPay() {
		return unPay;
	}
	/**
	 * @param unPay the unPay to set
	 */
	public void setUnPay(String unPay) {
		this.unPay = unPay;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public String getIndexNumber() {
		return indexNumber;
	}
}
