package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class CheckPlan implements Serializable
{
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private String transID;
	private String assetsID;
	private String deptID;
	private String assetsName;
	private String manufacture;
	private String spec;
	private int quantity;
	private int checkCnt;
	private String checkState;
	private String note;

	
	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getAssetsID()
	{
		return assetsID;
	}

	public void setAssetsID(String assetsID)
	{
		this.assetsID = assetsID;
	}

	/**
	 * @return the transID
	 */
	public String getTransID()
	{
		return transID;
	}

	/**
	 * @param transID
	 *            the transID to set
	 */
	public void setTransID(String transID)
	{
		this.transID = transID;
	}

	/**
	 * @return the deptID
	 */
	public String getDeptID()
	{
		return deptID;
	}

	/**
	 * @param deptID
	 *            the deptID to set
	 */
	public void setDeptID(String deptID)
	{
		this.deptID = deptID;
	}

	/**
	 * @return the checkState
	 */
	public String getCheckState()
	{
		return checkState;
	}

	/**
	 * @param checkState
	 *            the checkState to set
	 */
	public void setCheckState(String checkState)
	{
		this.checkState = checkState;
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

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getCheckCnt()
	{
		return checkCnt;
	}

	public void setCheckCnt(int checkCnt)
	{
		this.checkCnt = checkCnt;
	}

	@Override
	public String toString()
	{
		return "CheckPlan [transID=" + transID + ", deptID=" + deptID + ", assetsName=" + assetsName + ", manufacture=" + manufacture + ", spec=" + spec + ", quantity=" + quantity + ", checkCnt="
				+ checkCnt + ", checkState=" + checkState + "]";
	}


}
