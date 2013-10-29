/**
 * 
 * @version       0.0.1.130916_Base
 */
package cn.tinder.fuego.domain.po;

/**
 * <h3>CopyRright (c) 2008-Tinder</h3> <h3>Class Infornation</h3>
 * <p>
 * Project: Feugo <br>
 * Module ID: <br>
 * Comments: System user roles persistent object <br>
 * JDK version used: JDK1.6 <br>
 * Namespace: cn.tinder.feugo.domain.po <br>
 * Create Date: 2013-9-16
 * 
 * <h3>Modified Information</h3>
 * <br>
 * Modified By: Nan Bowen <br>
 * Modified Date: 2013-9-16 <br>
 * Why & What is modified:
 * 
 * 
 * @author Nan Bowen
 * @version 0.0.1.130916_Base
 */
public class SystemUser
{

	private String userName;
	private String password;
	private String department;
	private String departmentID;
	private String manageName;
	private String role;

	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the department
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * @return the departmentID
	 */
	public String getDepartmentID()
	{
		return departmentID;
	}

	/**
	 * @param departmentID
	 *            the departmentID to set
	 */
	public void setDepartmentID(String departmentID)
	{
		this.departmentID = departmentID;
	}

	public void setManageName(String manageName)
	{
		this.manageName = manageName;
	}

	/**
	 * @return the manageName
	 */
	public String getManageName()
	{
		return manageName;
	}

	/**
	 * @return the role
	 */
	public String getRole()
	{
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role)
	{
		this.role = role;
	}
	/**
	 * @param manageName
	 *            the manageName to set
	 */

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemUser [department=" + department + ", departmentID="
				+ departmentID + ", manageName=" + manageName + ", password="
				+ password + ", role=" + role + ", userName=" + userName + "]";
	}

}
