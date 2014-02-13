package cn.tinder.fuego.webservice.struts.bo.base;




public class AssetsPuchaseDeployBo {

	private String index;
	private String purchasePlanName;
	private String createTime;
	private String createUser;
	private String handleUser;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getPurchasePlanName() {
		return purchasePlanName;
	}
	public void setPurchasePlanName(String purchasePlanName) {
		this.purchasePlanName = purchasePlanName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getHandleUser() {
		return handleUser;
	}
	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}

	@Override
	public String toString() {
		return "AssetsPuchaseDeployBo [index=" + index + ", purchasePlanName="
				+ purchasePlanName + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", handleUser=" + handleUser
				+ "]";
	}
	

}
