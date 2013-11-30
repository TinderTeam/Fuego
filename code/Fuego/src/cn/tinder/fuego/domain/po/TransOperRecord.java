/**   
* @Title: TransOperRecord.java 
* @Package cn.tinder.fuego.domain.po 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-12-26 上午12:30:37 
* @version V1.0   
*/ 
package cn.tinder.fuego.domain.po;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName: TransOperRecord 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-12-26 上午12:30:37 
 *  
 */
public class TransOperRecord implements Serializable
{
	private String transID;
	private String userName;
	private String operTime;  //can not use timestamp, use the string temp
	private int step;
	private String stepName;
	private String result;
	private String transInfo;
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
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
 
 
	public String getOperTime()
	{
		return operTime;
	}
	public void setOperTime(String operTime)
	{
		this.operTime = operTime;
	}
	/**
	 * @return the step
	 */
	public int getStep()
	{
		return step;
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(int step)
	{
		this.step = step;
	}
	/**
	 * @return the stepName
	 */
	public String getStepName()
	{
		return stepName;
	}
	/**
	 * @param stepName the stepName to set
	 */
	public void setStepName(String stepName)
	{
		this.stepName = stepName;
	}
	/**
	 * @return the result
	 */
	public String getResult()
	{
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result)
	{
		this.result = result;
	}
	/**
	 * @return the transInfo
	 */
	public String getTransInfo()
	{
		return transInfo;
	}
	/**
	 * @param transInfo the transInfo to set
	 */
	public void setTransInfo(String transInfo)
	{
		this.transInfo = transInfo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransOperRecord [result=" + result + ", step=" + step
				+ ", stepName=" + stepName + ", time=" + operTime + ", transID="
				+ transID + ", transInfo=" + transInfo + ", userName="
				+ userName + "]";
	}
	

}
