package cn.tinder.fuego.stub.strust.bo.base;

import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

public class SystemUserBoStub {
	public static SystemUserBo getSuperDeptSystemUser(){
		SystemUserBo user = new SystemUserBo();
		user.setDeptID("33251087");
		user.setDeptName("无部门");
		user.setManageName("财务部负责人");
		user.setPassword("1234");
		user.setRole("SUPER_DEPT");
		user.setUserID("实物资产管理组");
		return user;
		
	}
}
