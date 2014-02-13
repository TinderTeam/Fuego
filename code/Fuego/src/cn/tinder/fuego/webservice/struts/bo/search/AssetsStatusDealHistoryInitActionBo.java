package cn.tinder.fuego.webservice.struts.bo.search;

public class AssetsStatusDealHistoryInitActionBo {
	
	private String assetsID;
	private String assetsName;
	private String purchaseDate;
	private String location;
	public String getAssetsID() {
		return assetsID;
	}
	public void setAssetsID(String assetsID) {
		this.assetsID = assetsID;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override			
	public String toString() {
		return "AssetsStatusDealHistoryInitActionBo [assetsID=" + assetsID
				+ ", assetsName=" + assetsName + ", purchaseDate="
				+ purchaseDate + ", location=" + location + "]";
	}
	

	
}
