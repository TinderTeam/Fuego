/**   
* @Title: ReceivePlan.java 
* @Package cn.tinder.fuego.domain.po 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-22 上午12:18:28 
* @version V1.0   
*/ 
package cn.tinder.fuego.domain.po;

import java.io.Serializable;

/** 
 * @ClassName: ReceivePlan 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-22 上午12:18:28 
 *  
 */
public class ReceivePlan implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transID;
	private String assetsID;
	private String receiveState;
	private String note;
	/**
	 * @return the transID
	 */
	public String getTransID()
	{
		return transID;
	}
	/**
	 * @param transID the transID to set
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
	 * @param assetsID the assetsID to set
	 */
	public void setAssetsID(String assetsID)
	{
		this.assetsID = assetsID;
	}
	/**
	 * @return the receiveState
	 */
	public String getReceiveState()
	{
		return receiveState;
	}
	/**
	 * @param receiveState the receiveState to set
	 */
	public void setReceiveState(String receiveState)
	{
		this.receiveState = receiveState;
	}
	/**
	 * @return the note
	 */
	public String getNote()
	{
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note)
	{
		this.note = note;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ReceivePlan [assetsID=" + assetsID + ", note=" + note
				+ ", receiveState=" + receiveState + ", transID=" + transID
				+ "]";
	}
	

}
