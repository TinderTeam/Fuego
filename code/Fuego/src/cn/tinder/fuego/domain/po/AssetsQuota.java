package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class AssetsQuota implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assetsName;
	private String manufacture;
	private String duty;
	private String spec;
	private String expectYear;
	private int quantity;
	public String getAssetsName()
	{
		return assetsName;
	}
	public void setAssetsName(String assetsName)
	{
		this.assetsName = assetsName;
	}
	public String getManufacture()
	{
		return manufacture;
	}
	public void setManufacture(String manufacture)
	{
		this.manufacture = manufacture;
	}
	public String getDuty()
	{
		return duty;
	}
	public void setDuty(String duty)
	{
		this.duty = duty;
	}
	public String getSpec()
	{
		return spec;
	}
	public void setSpec(String spec)
	{
		this.spec = spec;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public String getExpectYear()
	{
		return expectYear;
	}
	public void setExpectYear(String expectYear)
	{
		this.expectYear = expectYear;
	}
	@Override
	public String toString()
	{
		return "AssetsQuota [assetsName=" + assetsName + ", manufacture=" + manufacture + ", duty=" + duty + ", spec=" + spec + ", expectYear=" + expectYear + ", quantity=" + quantity + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assetsName == null) ? 0 : assetsName.hashCode());
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
		result = prime * result
				+ ((manufacture == null) ? 0 : manufacture.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
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
		AssetsQuota other = (AssetsQuota) obj;
		if (assetsName == null) {
			if (other.assetsName != null)
				return false;
		} else if (!assetsName.equals(other.assetsName))
			return false;
		if (duty == null) {
			if (other.duty != null)
				return false;
		} else if (!duty.equals(other.duty))
			return false;
		if (manufacture == null) {
			if (other.manufacture != null)
				return false;
		} else if (!manufacture.equals(other.manufacture))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		return true;
	}
 
	
	
}
