/**   
 * @Title: Assets.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.base 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-25 上午01:17:57 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.base;

import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * @ClassName: Assets
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-25 上午01:17:57
 * 
 */

public class AssetsBo extends ActionForm
{
	private String assetsID;
	private String assetsName;
	private String assetsSRC;
	private String manufacture;
	private String spec;
	private String unit;
	private int quantity;
	private String purchaseDate;
	private float originalValue;
	private int expectYear;
	private String dueDate;
	private String dept;
	private String duty;
	private String manageName;
	private String assetsType;
	private String location;
	private String techState;
	private String checkDate;
	private String note;

	private String attrType;

	
	public AssetsBo(){
		quantity= 1;
		originalValue=(float)0;
		expectYear=0;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assetsName == null) ? 0 : assetsName.hashCode());
		result = prime * result
				+ ((manufacture == null) ? 0 : manufacture.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetsBo other = (AssetsBo) obj;
		if (assetsName == null) {
			if (other.assetsName != null)
				return false;
		} else if (!assetsName.equals(other.assetsName))
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

	public String getAssetsID()
	{
		return assetsID;
	}

	public void setAssetsID(String assetsID)
	{
		this.assetsID = assetsID;
	}

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

	public String getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate)
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

	public String getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(String dueDate)
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

	public String getCheckDate()
	{
		return checkDate;
	}

	public void setCheckDate(String checkDate)
	{
		this.checkDate = checkDate;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getAttrType()
	{
		return attrType;
	}

	public void setAttrType(String attrType)
	{
		this.attrType = attrType;
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
		return "AssetsBo [assetsID=" + assetsID + ", assetsName=" + assetsName + ", assetsSRC=" + assetsSRC + ", manufacture=" + manufacture + ", spec=" + spec + ", unit=" + unit + ", quantity="
				+ quantity + ", purchaseDate=" + purchaseDate + ", originalValue=" + originalValue + ", expectYear=" + expectYear + ", dueDate=" + dueDate + ", dept=" + dept + ", duty=" + duty
				+ ", assetsType=" + assetsType + ", location=" + location + ", techState=" + techState + ", checkDate=" + checkDate + ", note=" + note + ", attrType=" + attrType + "]";
	}

 


}
