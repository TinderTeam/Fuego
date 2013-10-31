/**   
 * @Title: SystemUserBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-22 下午10:39:53 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.base;

/**
 * @ClassName: SystemUserBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-22 下午10:39:53
 * 
 */

public class SystemUserBo
{
	private String userID;
	private String password;
	private String deptID;
	private String deptName;
	private String role;
	private String manageName;

	public SystemUserBo()
	{
		
	}
	
	/**
	 * @param userID
	 * @param password
	 * @param deptID
	 * @param deptName
	 * @param role
	 * @param manageName
	 */
	public SystemUserBo(SystemUserBo user)
	{
		super();
		this.userID = user.userID;
		this.password = user.password;
		this.deptID = user.deptID;
		this.deptName = user.deptName;
		this.role = user.role;
		this.manageName = user.manageName;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDeptID()
	{
		return deptID;
	}

	public void setDeptID(String deptID)
	{
		this.deptID = deptID;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	/**
	 * @return the manageName
	 */
	public String getManageName()
	{
		return manageName;
	}

	/**
	 * @param manageName the manageName to set
	 */
	public void setManageName(String manageName)
	{
		this.manageName = manageName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemUserBo [deptID=" + deptID + ", deptName=" + deptName
				+ ", manageName=" + manageName + ", password=" + password
				+ ", role=" + role + ", userID=" + userID + "]";
	}

	
	

}
