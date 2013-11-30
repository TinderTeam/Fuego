/**   
* @Title: TransOperInfoBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.trans 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-26 上午01:28:07 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.trans;

import java.util.Date;

/** 
 * @ClassName: TransOperInfoBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-26 上午01:28:07 
 *  
 */

public class TransOperInfoBo
{
	private String transID;
	private String transName;
	private String userName;
	private String nickName;
	private String operTime;
	private int step;
	private String stepName;
	private String result;
	private String transInfo;
	public String getTransID()
	{
		return transID;
	}
	public void setTransID(String transID)
	{
		this.transID = transID;
	}
	public String getTransName()
	{
		return transName;
	}
	public void setTransName(String transName)
	{
		this.transName = transName;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getNickName()
	{
		return nickName;
	}
	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}
 
	public String getOperTime()
	{
		return operTime;
	}
	public void setOperTime(String operTime)
	{
		this.operTime = operTime;
	}
	public int getStep()
	{
		return step;
	}
	public void setStep(int step)
	{
		this.step = step;
	}
	public String getStepName()
	{
		return stepName;
	}
	public void setStepName(String stepName)
	{
		this.stepName = stepName;
	}
	public String getResult()
	{
		return result;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	public String getTransInfo()
	{
		return transInfo;
	}
	public void setTransInfo(String transInfo)
	{
		this.transInfo = transInfo;
	}
	 
	
}
