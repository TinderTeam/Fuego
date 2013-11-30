package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class AssetsQuota implements Serializable
{
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
 
	
	
}
