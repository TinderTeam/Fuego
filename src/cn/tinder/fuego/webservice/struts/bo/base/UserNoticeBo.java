package cn.tinder.fuego.webservice.struts.bo.base;

public class UserNoticeBo
{
	/**
	 * @author Nan Bowen UserNotice Class is created as a page-show table data. Each UserNotice is a Notice message for show.
	 * 
	 */

	private int num;
	private String transNameAndID;
	private String createTime;
	private String createUser;
	private String type;
	private String handleUser;
	private String noticeType;
	private String url;
	private String disabled;

	/**
	 * @return the transNameAndID
	 */
	public String getTransNameAndID()
	{
		return transNameAndID;
	}

	/**
	 * @param transNameAndID
	 *            the transNameAndID to set
	 */
	public void setTransNameAndID(String transNameAndID)
	{
		this.transNameAndID = transNameAndID;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime()
	{
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser()
	{
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the handleUser
	 */
	public String getHandleUser()
	{
		return handleUser;
	}

	/**
	 * @param handleUser
	 *            the handleUser to set
	 */
	public void setHandleUser(String handleUser)
	{
		this.handleUser = handleUser;
	}

	/**
	 * @return the noticeType
	 */
	public String getNoticeType()
	{
		return noticeType;
	}

	/**
	 * @param noticeType
	 *            the noticeType to set
	 */
	public void setNoticeType(String noticeType)
	{
		this.noticeType = noticeType;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(int num)
	{
		this.num = num;
	}

	/**
	 * @return the num
	 */
	public int getNum()
	{
		return num;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UserNotice [num=" + num + ", transNameAndID=" + transNameAndID + ", createTime=" + createTime + ", createUser=" + createUser + ", type=" + type + ", handleUser=" + handleUser
				+ ", noticeType=" + noticeType + ", url=" + url + ", disabled=" + disabled + "]";
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
	}

	/**
	 * @return the disabled
	 */
	public String getDisabled()
	{
		return disabled;
	}

}
