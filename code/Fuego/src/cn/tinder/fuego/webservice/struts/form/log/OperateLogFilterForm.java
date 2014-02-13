package cn.tinder.fuego.webservice.struts.form.log;

import org.apache.struts.action.ActionForm;

public class OperateLogFilterForm extends ActionForm
{
	private String userName ;
	private String startOperTime ;
	private String endOperTime;
	private String operName ;
	private String transID;
 	private String assetsID;         //exact match
	private String assetsType;       //exact match
	private String techState;        //exact match
	
	private String assetsName;       //approximately match
	private String duty;             //approximately match
	
	private String manageName;       //approximately match
	private String location;         //approximately match
	
	private String startPurchaseDate;  // Start time of purchase date
	private String startDueDate;       // Start time of due date
	
	private String endPurchaseDate;    // End time of purchase date

	private String endDueDate;         // End time of due date
	
 
	private int pageNum = 1; //default page 


	public String getUserName()
	{
		return userName;
	}


	public void setUserName(String userName)
	{
		this.userName = userName;
	}


 
 

	public String getStartOperTime()
	{
		return startOperTime;
	}


	public void setStartOperTime(String startOperTime)
	{
		this.startOperTime = startOperTime;
	}


	public String getEndOperTime()
	{
		return endOperTime;
	}


	public void setEndOperTime(String endOperTime)
	{
		this.endOperTime = endOperTime;
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


	public String getAssetsID()
	{
		return assetsID;
	}


	public void setAssetsID(String assetsID)
	{
		this.assetsID = assetsID;
	}


	public String getAssetsType()
	{
		return assetsType;
	}


	public void setAssetsType(String assetsType)
	{
		this.assetsType = assetsType;
	}


	public String getTechState()
	{
		return techState;
	}


	public void setTechState(String techState)
	{
		this.techState = techState;
	}


	public String getAssetsName()
	{
		return assetsName;
	}


	public void setAssetsName(String assetsName)
	{
		this.assetsName = assetsName;
	}


	public String getDuty()
	{
		return duty;
	}


	public void setDuty(String duty)
	{
		this.duty = duty;
	}


	public String getManageName()
	{
		return manageName;
	}


	public void setManageName(String manageName)
	{
		this.manageName = manageName;
	}


	public String getLocation()
	{
		return location;
	}


	public void setLocation(String location)
	{
		this.location = location;
	}


	public String getStartPurchaseDate()
	{
		return startPurchaseDate;
	}


	public void setStartPurchaseDate(String startPurchaseDate)
	{
		this.startPurchaseDate = startPurchaseDate;
	}


	public String getStartDueDate()
	{
		return startDueDate;
	}


	public void setStartDueDate(String startDueDate)
	{
		this.startDueDate = startDueDate;
	}


	public String getEndPurchaseDate()
	{
		return endPurchaseDate;
	}


	public void setEndPurchaseDate(String endPurchaseDate)
	{
		this.endPurchaseDate = endPurchaseDate;
	}


	public String getEndDueDate()
	{
		return endDueDate;
	}


	public void setEndDueDate(String endDueDate)
	{
		this.endDueDate = endDueDate;
	}


	public int getPageNum()
	{
		return pageNum;
	}


	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	
	 
	

	
	

}
