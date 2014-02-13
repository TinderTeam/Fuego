package cn.tinder.fuego.webservice.struts.bo.sys;

import java.util.List;

public class NickNameSetupPageBo {
	private List<String> userList;
    private String nickName;
    private String userName;
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public List<String> getUserList() {
		return userList;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NickNameSetupPageBo [userList=" + userList + ", nickName="
				+ nickName + ", userName=" + userName + "]";
	}


	
	
}
