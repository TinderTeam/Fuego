/**   
* @Title: GasStationCheckStatusBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.check 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-4 上午09:38:43 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.check;

/** 
 * @ClassName: GasStationCheckStatusBo 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-4 上午09:38:43 
 *  
 */
public class GasStationCheckStatusBo
{
	private String dept;
	private String gasName;
	private String statusUrl;
	private String status;
	/**
	 * @return the dept
	 */
	public String getDept()
	{
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept)
	{
		this.dept = dept;
	}
	/**
	 * @return the gasName
	 */
	public String getGasName()
	{
		return gasName;
	}
	/**
	 * @param gasName the gasName to set
	 */
	public void setGasName(String gasName)
	{
		this.gasName = gasName;
	}
	/**
	 * @return the statusUrl
	 */
	public String getStatusUrl()
	{
		return statusUrl;
	}
	/**
	 * @param statusUrl the statusUrl to set
	 */
	public void setStatusUrl(String statusUrl)
	{
		this.statusUrl = statusUrl;
	}
	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "GasStationCheckStatusBo [dept=" + dept + ", gasName=" + gasName
				+ ", status=" + status + ", statusUrl=" + statusUrl + "]";
	}
	

}
