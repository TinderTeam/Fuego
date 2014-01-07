package cn.tinder.fuego.webservice.struts.form;

import java.util.Arrays;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.util.date.DateService;

public class TransFilterForm extends ActionForm
{
	private String transID;
	private String transName;
	private String firstCreateTime = DateService.getCurMonthFirstDate();
	private String lastCreateTime = DateService.getCurMonthLastDate();
	private String createUser;
	private String firstEndTime = DateService.getCurMonthFirstDate();
	private String lastEndTime = DateService.getCurMonthLastDate();
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
	public String getFirstCreateTime()
	{
		return firstCreateTime;
	}
	public void setFirstCreateTime(String firstCreateTime)
	{
		this.firstCreateTime = firstCreateTime;
	}
	public String getLastCreateTime()
	{
		return lastCreateTime;
	}
	public void setLastCreateTime(String lastCreateTime)
	{
		this.lastCreateTime = lastCreateTime;
	}
	public String getCreateUser()
	{
		return createUser;
	}
	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}
	public String getFirstEndTime()
	{
		return firstEndTime;
	}
	public void setFirstEndTime(String firstEndTime)
	{
		this.firstEndTime = firstEndTime;
	}
	public String getLastEndTime()
	{
		return lastEndTime;
	}
	public void setLastEndTime(String lastEndTime)
	{
		this.lastEndTime = lastEndTime;
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
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	@Override
	public String toString()
	{
		return "TransFilterForm [transID=" + transID + ", transName=" + transName + ", firstCreateTime=" + firstCreateTime + ", lastCreateTime=" + lastCreateTime + ", createUser=" + createUser
				+ ", firstEndTime=" + firstEndTime + ", lastEndTime=" + lastEndTime + ", handleUser=" + handleUser + ", state=" + state + ", url=" + url + "]";
	}

	

}
