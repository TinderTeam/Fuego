package cn.tinder.fuego.domain.po;

public class AssetsQuota
{
	private String assetsName;
	private String deptID;
	private String spec;
	private int quantity;
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private String expectYear;
	/**
	 * @return the assetsName
	 */
	public String getAssetsName() {
		return assetsName;
	}
	/**
	 * @param assetsName the assetsName to set
	 */
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	/**
	 * @return the deptID
	 */
	public String getDeptID() {
		return deptID;
	}
	/**
	 * @param deptID the deptID to set
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	/**
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * @return the expectYear
	 */
	public String getExpectYear() {
		return expectYear;
	}
	/**
	 * @param expectYear the expectYear to set
	 */
	public void setExpectYear(String expectYear) {
		this.expectYear = expectYear;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssetsQuota [assetsName=" + assetsName + ", deptID=" + deptID
				+ ", spec=" + spec + ", quantity=" + quantity + ", expectYear="
				+ expectYear + "]";
	}


}
