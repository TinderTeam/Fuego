package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class OperateRecord  implements Serializable
{
	private String id;

	private String userName;
	private String time;
	private String operate;
	private String transID;
	private PhysicalAssetsStatus assets;
	
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
 
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getOperate()
	{
		return operate;
	}
	public void setOperate(String operate)
	{
		this.operate = operate;
	}
	public String getTransID()
	{
		return transID;
	}
	public void setTransID(String transID)
	{
		this.transID = transID;
	}
	public PhysicalAssetsStatus getAssets()
	{
		return assets;
	}
	public void setAssets(PhysicalAssetsStatus assets)
	{
		this.assets = assets;
	}
	
	
}
