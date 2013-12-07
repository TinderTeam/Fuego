/**   
* @Title: OperateLogModel.java 
* @Package cn.tinder.fuego.service.model 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-8 上午12:30:22 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.model;

/** 
 * @ClassName: OperateLogModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-8 上午12:30:22 
 *  
 */

public class OperateLogModel<E>
{
	private String userName ;
	private String operTime ;
	private String operName ;
	private String transID;
	private E operObj;
 
	
	public String getUserName()
	{
		return userName;
	}
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
	public String getOperName()
	{
		return operName;
	}
	public void setOperName(String operName)
	{
		this.operName = operName;
	}
	public String getTransID()
	{
		return transID;
	}
	public void setTransID(String transID)
	{
		this.transID = transID;
	}
	public E getOperObj()
	{
		return operObj;
	}
	public void setOperObj(E operObj)
	{
		this.operObj = operObj;
	}
	
	
}
