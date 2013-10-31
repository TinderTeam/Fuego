package cn.tinder.fuego.webservice.struts.bo.search;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class AssetsStatusDealHistorySearchResultBo {
	
	AssetsBo bo;
	private String userName;
	private String operationDate;
	private String operationType;
	public AssetsBo getBo() {
		return bo;
	}
	public void setBo(AssetsBo bo) {
		this.bo = bo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	@Override
	public String toString() {
		return "AssetsStatusDealHistorySearchResultBo [bo=" + bo
				+ ", userName=" + userName + ", operationDate=" + operationDate
				+ ", operationType=" + operationType + "]";
	}
	


}
