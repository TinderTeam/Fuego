package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class RecapturePlan implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transID;
	private String assetsID;
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

}
