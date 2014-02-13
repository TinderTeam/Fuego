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
 
	public static SystemUserBo getGasstationSystemUser(){
		SystemUserBo user = new SystemUserBo();
		user.setDeptID("33251087");
		user.setDeptName("惠城经管部");
		user.setManageName("惠城经管部");
		user.setPassword("1234");
		user.setRole("GASSTATION");
		user.setUserID("三家村加油站");
		return user;	
	}
	
	public static String getAdminUserName(){
		return "ADMIN";
	}
	
	public static String getTestUserName(){
		return "测试加油站";
	}
	
	public static String getGasstationUserName(){
		return "三家村加油站";
	}
	
	public static String getSuperDeptUserName(){
		return "实物资产管理组";
	}
	
	public static String getDeptUserName(){
		return "财务资产部";
	}
}
