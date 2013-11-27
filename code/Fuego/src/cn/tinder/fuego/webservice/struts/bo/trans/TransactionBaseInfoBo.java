/**   
 * @Title: TransactionBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.trans 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午12:58:47 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.trans;

import java.util.Date;

import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

/**
 * @ClassName: TransactionBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午12:58:47
 * 
 */

public class TransactionBaseInfoBo
{
	private String transID;
	private String transName;
	private Date createTime;
	private String createUser;
	private Date endTime;
	private String handleUser;
	private String state;
	private String url;	   
 
 
	
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

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getCreateUser()
	{
		return createUser;
	}

	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public String getHandleUser()
	{
		return handleUser;
	}

	public void setHandleUser(String handleUser)
	{
		this.handleUser = handleUser;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "TransactionBo [transID=" + transID + ", transName=" + transName + ", createTime=" + createTime + ", createUser=" + createUser + ", endTime=" + endTime + ", handleUser=" + handleUser
				+ ", state=" + state + "]";
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	public boolean canOperate(SystemUserBo user)
	{
		boolean canOperate = false;
		if(user.getUserID().equals(this.handleUser))
		{
			canOperate = true;
		}
		return canOperate;
	}

}
