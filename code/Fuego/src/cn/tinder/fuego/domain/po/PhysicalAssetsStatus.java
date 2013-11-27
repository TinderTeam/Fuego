package cn.tinder.fuego.domain.po;

import java.util.Date;


public class PhysicalAssetsStatus
{
	private String assetsName;
	private String assetsSRC;
	private String manufacture;
	private String spec;
	private String unit;
	private int quantity;
	private Date purchaseDate;
	private float originalValue;
	private int expectYear;
	private Date dueDate;
	private String dept;
	private String duty;
	private String assetsType;
	private String manageName;
	private String location;
	private String techState;
	private Date checkDate;
	private String assetsID;
	private String note;
	private String attrType;


	public String getAssetsName()
	{
		return assetsName;
	}

	public void setAssetsName(String assetsName)
	{
		this.assetsName = assetsName;
	}

	public String getAssetsSRC()
	{
		return assetsSRC;
	}

	public void setAssetsSRC(String assetsSRC)
	{
		this.assetsSRC = assetsSRC;
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

	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	public float getOriginalValue()
	{
		return originalValue;
	}

	public void setOriginalValue(float originalValue)
	{
		this.originalValue = originalValue;
	}

	public int getExpectYear()
	{
		return expectYear;
	}

	public void setExpectYear(int expectYear)
	{
		this.expectYear = expectYear;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	public String getDept()
	{
		return dept;
	}

	public void setDept(String dept)
	{
		this.dept = dept;
	}

	public String getDuty()
	{
		return duty;
	}

	public void setDuty(String duty)
	{
		this.duty = duty;
	}

	public String getAssetsType()
	{
		return assetsType;
	}

	public void setAssetsType(String assetsType)
	{
		this.assetsType = assetsType;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getTechState()
	{
		return techState;
	}

	public void setTechState(String techState)
	{
		this.techState = techState;
	}

	public Date getCheckDate()
	{
		return checkDate;
	}

	public void setCheckDate(Date checkDate)
	{
		this.checkDate = checkDate;
	}

	public String getAssetsID()
	{
		return assetsID;
	}

	public void setAssetsID(String assetsID)
	{
		this.assetsID = assetsID;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}
	
	

	public String getManageName()
	{
		return manageName;
	}

	public void setManageName(String manageName)
	{
		this.manageName = manageName;
	}

	@Override
	public String toString()
	{
		return "PhysicalAssetsStatus [assetsName=" + assetsName + ", assetsSRC=" + assetsSRC + ", manufacture=" + manufacture + ", spec=" + spec + ", unit=" + unit + ", quantity=" + quantity
				+ ", purchaseDate=" + purchaseDate + ", originalValue=" + originalValue + ", expectYear=" + expectYear + ", dueDate=" + dueDate + ", dept=" + dept + ", duty=" + duty + ", assetsType="
				+ assetsType + ", location=" + location + ", techState=" + techState + ", checkDate=" + checkDate + ", assetsID=" + assetsID + ", note=" + note + "]";
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttrType() {
		return attrType;
	}

}
