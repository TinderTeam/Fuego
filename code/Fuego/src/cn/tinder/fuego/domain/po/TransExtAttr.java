/**   
 * @Title: TransExtAttr.java 
 * @Package cn.tinder.fuego.domain.po 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 上午01:35:19 
 * @version V1.0   
 */
package cn.tinder.fuego.domain.po;

import java.io.Serializable;

/**
 * @ClassName: TransExtAttr
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 上午01:35:19
 * 
 */
public class TransExtAttr implements Serializable
{
	private String transID;
	private String attrName;
	private String attrValue;

	/**
	 * @param transID
	 *            the transID to set
	 */
	public void setTransID(String transID)
	{
		this.transID = transID;
	}

	/**
	 * @return the transID
	 */
	public String getTransID()
	{
		return transID;
	}

	/**
	 * @param attrName
	 *            the attrName to set
	 */
	public void setAttrName(String attrName)
	{
		this.attrName = attrName;
	}

	/**
	 * @return the attrName
	 */
	public String getAttrName()
	{
		return attrName;
	}

	/**
	 * @param attrValue
	 *            the attrValue to set
	 */
	public void setAttrValue(String attrValue)
	{
		this.attrValue = attrValue;
	}

	/**
	 * @return the attrValue
	 */
	public String getAttrValue()
	{
		return attrValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransExtAttr [attrName=" + attrName + ", attrValue=" + attrValue + ", transID=" + transID + "]";
	}

}
