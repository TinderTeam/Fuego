package cn.tinder.fuego.domain.po;

import java.sql.Date;

public class OperateRecord
{
	private String userName;
	private Date time;
	private String operate;
	private String assetsID;
	private String assetsName;
	private String assetsSRC;
	private String manufacture;
	private String spec;
	private String unit;
	private Integer quantity;
	private Date purchaseDate;
	private Float originalValue;
	private Integer expectYear;
	private Date dueDate;
	private String dept;
	private String duty;
	private String assetsType;
	private String location;
	private String techState;

	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @return the time
	 */
	public Date getTime()
	{
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time)
	{
		this.time = time;
	}

	/**
	 * @return the operate
	 */
	public String getOperate()
	{
		return operate;
	}

	/**
	 * @param operate
	 *            the operate to set
	 */
	public void setOperate(String operate)
	{
		this.operate = operate;
	}

	/**
	 * @return the assetsID
	 */
	public String getAssetsID()
	{
		return assetsID;
	}

	/**
	 * @param assetsID
	 *            the assetsID to set
	 */
	public void setAssetsID(String assetsID)
	{
		this.assetsID = assetsID;
	}

	/**
	 * @return the assetsName
	 */
	public String getAssetsName()
	{
		return assetsName;
	}

	/**
	 * @param assetsName
	 *            the assetsName to set
	 */
	public void setAssetsName(String assetsName)
	{
		this.assetsName = assetsName;
	}

	/**
	 * @return the assetsSRC
	 */
	public String getAssetsSRC()
	{
		return assetsSRC;
	}

	/**
	 * @param assetsSRC
	 *            the assetsSRC to set
	 */
	public void setAssetsSRC(String assetsSRC)
	{
		this.assetsSRC = assetsSRC;
	}

	/**
	 * @return the manufacture
	 */
	public String getManufacture()
	{
		return manufacture;
	}

	/**
	 * @param manufacture
	 *            the manufacture to set
	 */
	public void setManufacture(String manufacture)
	{
		this.manufacture = manufacture;
	}

	/**
	 * @return the spec
	 */
	public String getSpec()
	{
		return spec;
	}

	/**
	 * @param spec
	 *            the spec to set
	 */
	public void setSpec(String spec)
	{
		this.spec = spec;
	}

	/**
	 * @return the unit
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity()
	{
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	/**
	 * @param purchaseDate
	 *            the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	/**
	 * @return the originalValue
	 */
	public Float getOriginalValue()
	{
		return originalValue;
	}

	/**
	 * @param originalValue
	 *            the originalValue to set
	 */
	public void setOriginalValue(Float originalValue)
	{
		this.originalValue = originalValue;
	}

	/**
	 * @return the expectYear
	 */
	public Integer getExpectYear()
	{
		return expectYear;
	}

	/**
	 * @param expectYear
	 *            the expectYear to set
	 */
	public void setExpectYear(Integer expectYear)
	{
		this.expectYear = expectYear;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate()
	{
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	/**
	 * @return the dept
	 */
	public String getDept()
	{
		return dept;
	}

	/**
	 * @param dept
	 *            the dept to set
	 */
	public void setDept(String dept)
	{
		this.dept = dept;
	}

	/**
	 * @return the duty
	 */
	public String getDuty()
	{
		return duty;
	}

	/**
	 * @param duty
	 *            the duty to set
	 */
	public void setDuty(String duty)
	{
		this.duty = duty;
	}

	/**
	 * @return the assetsType
	 */
	public String getAssetsType()
	{
		return assetsType;
	}

	/**
	 * @param assetsType
	 *            the assetsType to set
	 */
	public void setAssetsType(String assetsType)
	{
		this.assetsType = assetsType;
	}

	/**
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}

	/**
	 * @return the techState
	 */
	public String getTechState()
	{
		return techState;
	}

	/**
	 * @param techState
	 *            the techState to set
	 */
	public void setTechState(String techState)
	{
		this.techState = techState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OperateRecord [assetsID=" + assetsID + ", assetsName=" + assetsName + ", assetsSRC=" + assetsSRC + ", assetsType=" + assetsType + ", dept=" + dept + ", dueDate=" + dueDate + ", duty="
				+ duty + ", expectYear=" + expectYear + ", location=" + location + ", manufacture=" + manufacture + ", operate=" + operate + ", originalValue=" + originalValue + ", purchaseDate="
				+ purchaseDate + ", quantity=" + quantity + ", spec=" + spec + ", techState=" + techState + ", time=" + time + ", unit=" + unit + ", userName=" + userName + "]";
	}

}
