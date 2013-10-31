package cn.tinder.fuego.webservice.struts.bo.base;

/**
 * 
 * @ClassName: PurchasePlanRefListBo
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-28 上午01:44:30
 * 
 */
public class PurchasePlanRefBo
{
	private String index;
	private String assetsID;
	private String assetsName;
	private String price;
	private String unit;
	private String quantity;
	private String money;
	private String useDepartment;
	private String note;
	private String manufacture;

	/**
	 * @return the index
	 */
	public String getIndex()
	{
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index)
	{
		this.index = index;
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
	 * @return the price
	 */
	public String getPrice()
	{
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price)
	{
		this.price = price;
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
	public String getQuantity()
	{
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(String quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * @return the money
	 */
	public String getMoney()
	{
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(String money)
	{
		this.money = money;
	}

	/**
	 * @return the useDepartment
	 */
	public String getUseDepartment()
	{
		return useDepartment;
	}

	/**
	 * @param useDepartment
	 *            the useDepartment to set
	 */
	public void setUseDepartment(String useDepartment)
	{
		this.useDepartment = useDepartment;
	}

	/**
	 * @return the note
	 */
	public String getNote()
	{
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note)
	{
		this.note = note;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PurchasePlanRefListBo [index=" + index + ", assetsID=" + assetsID + ", assetsName=" + assetsName + ", price=" + price + ", unit=" + unit + ", quantity=" + quantity + ", money="
				+ money + ", useDepartment=" + useDepartment + ", note=" + note + ", manufacture=" + manufacture + "]";
	}
}
