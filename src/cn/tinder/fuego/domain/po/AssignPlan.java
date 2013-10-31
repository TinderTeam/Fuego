package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class AssignPlan implements Serializable
{
	private String transID;
	private String assetsID;
	private String inDeptID;
	private String note;

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
	 * @return the inDeptID
	 */
	public String getInDeptID()
	{
		return inDeptID;
	}

	/**
	 * @param inDeptID
	 *            the inDeptID to set
	 */
	public void setInDeptID(String inDeptID)
	{
		this.inDeptID = inDeptID;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AssignPlan [assetsID=" + assetsID + ", inDeptID=" + inDeptID + ", note=" + note + ", transID=" + transID + "]";
	}

}
