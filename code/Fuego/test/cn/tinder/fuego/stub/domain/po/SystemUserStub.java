package cn.tinder.fuego.stub.domain.po;

import cn.tinder.fuego.domain.po.SystemUser;

public class SystemUserStub {
	public static SystemUser getGasstationUser() {
		SystemUser user = new SystemUser();
		user.setUserName("测试加油站");
		user.setRole("GASSTATION");
		user.setPassword("1234");
		user.setManageName("测试经管部");
		user.setDepartment("测试加油站");
		user.setDepartmentID("1234");
		user.setNickName("测试昵称");
		
		return user;
	}
	
}
