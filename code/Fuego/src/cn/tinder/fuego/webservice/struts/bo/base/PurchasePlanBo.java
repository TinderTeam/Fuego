package cn.tinder.fuego.webservice.struts.bo.base;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.service.model.PurchaseSumModel;

public class PurchasePlanBo extends ActionForm {
	private int index;
	AssetsBo assetsBo = new AssetsBo();

	private String money;
	private String price;
	private String depreciation;
	private String value;

	/**
	 * TASK #7 #80 资产采购增加配置数量描述信息 2014-1-1 重构 By Bowen Nan
	 * 
	 */

	private int currentQuantity = 0; // 现有数量(含不可用状态)
	private int disableQuantity = 0; // 不可用数量(待报废、损坏)
	private int quotaQuantity = 0; // 配置数量
	private int purchaseQuantity = 0; // 需采购数量

	/**
	 * @return the currentQuantity
	 */
	public int getCurrentQuantity() {
		return currentQuantity;
	}

	/**
	 * @param currentQuantity
	 *            the currentQuantity to set
	 */
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	/**
	 * @return the disableQuantity
	 */
	public int getDisableQuantity() {
		return disableQuantity;
	}

	/**
	 * @param disableQuantity
	 *            the disableQuantity to set
	 */
	public void setDisableQuantity(int disableQuantity) {
		this.disableQuantity = disableQuantity;
	}

	/**
	 * @return the quotaQuantity
	 */
	public int getQuotaQuantity() {
		return quotaQuantity;
	}

	/**
	 * @param quotaQuantity
	 *            the quotaQuantity to set
	 */
	public void setQuotaQuantity(int quotaQuantity) {
		this.quotaQuantity = quotaQuantity;
	}

	/**
	 * @return the purchaseQuantity
	 */
	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	/**
	 * @param purchaseQuantity
	 *            the purchaseQuantity to set
	 */
	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public void countMoney() {

		this.money = String.valueOf((float) this.assetsBo.getQuantity()
				* Float.valueOf(price));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assetsBo == null) ? 0 : assetsBo.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchasePlanBo other = (PurchasePlanBo) obj;
		if (assetsBo == null) {
			if (other.assetsBo != null)
				return false;
		} else if (!assetsBo.equals(other.assetsBo))
			return false;
		return true;
	}

	public AssetsBo getAssetsBo() {
		return assetsBo;
	}

	public void setAssetsBo(AssetsBo assetsBo) {
		this.assetsBo = assetsBo;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {

		this.money = money;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "PurchasePlanBo [index=" + index + ", assetsBo=" + assetsBo
				+ ", money=" + money + ", price=" + price + "]";
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public PurchaseSumModel getPurchaseSumModel() {
		PurchaseSumModel sumModel = new PurchaseSumModel();
		sumModel.setAssetsName(this.getAssetsBo().getAssetsID());
		sumModel.setManufacture(this.getAssetsBo().getManufacture());
		sumModel.setSpec(this.getAssetsBo().getSpec());
		sumModel.setGasName(this.getAssetsBo().getDuty());
		return sumModel;
	}

}
