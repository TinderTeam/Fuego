package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class PurchasePlan implements Serializable
{
	private String transID;
 	private String assetsName;
 	private String manufacture;
	private String spec;
	private String duty;
	private String unit;
	private int quantity;
	private float price;
	private float sum;
	private String note;
	public String getTransID()
	{
		return transID;
	}
	public void setTransID(String transID)
	{
		this.transID = transID;
	}
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
	public String getSpec()
	{
		return spec;
	}
	public void setSpec(String spec)
	{
		this.spec = spec;
	}
 
	public String getDuty()
	{
		return duty;
	}
	public void setDuty(String duty)
	{
		this.duty = duty;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public float getPrice()
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	}
	public float getSum()
	{
		return sum;
	}
	public void setSum(float sum)
	{
		this.sum = sum;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
 
	
	 
}
