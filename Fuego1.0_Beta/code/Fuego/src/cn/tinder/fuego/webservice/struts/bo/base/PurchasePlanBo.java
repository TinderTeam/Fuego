package cn.tinder.fuego.webservice.struts.bo.base;

import org.apache.struts.action.ActionForm;

public class PurchasePlanBo extends ActionForm
{
	private int index;
	AssetsBo assetsBo =new AssetsBo();
	
	private String money;
	private String price;
	private String badCnt;
	private String discardCnt;
	private String dueCnt;
	private String depreciation;
	private String value;

	public void countMoney() {

			this.money = String.valueOf((float)this.assetsBo.getQuantity()*Float.valueOf(price));
		
	
 	}
	
	
	
	





 





	/* (non-Javadoc)
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















	/* (non-Javadoc)
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















	public String getBadCnt()
	{
		return badCnt;
	}


	public void setBadCnt(String badCnt)
	{
		this.badCnt = badCnt;
	}


	public String getDiscardCnt()
	{
		return discardCnt;
	}


	public void setDiscardCnt(String discardCnt)
	{
		this.discardCnt = discardCnt;
	}


	public String getDueCnt()
	{
		return dueCnt;
	}


	public void setDueCnt(String dueCnt)
	{
		this.dueCnt = dueCnt;
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

}
