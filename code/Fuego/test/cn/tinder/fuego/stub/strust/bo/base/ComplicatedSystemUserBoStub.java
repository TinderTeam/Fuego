package cn.tinder.fuego.stub.strust.bo.base;

import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

public class ComplicatedSystemUserBoStub {
	private SystemUserBo user;
	private String id;
	
	/**
	 * @return the user
	 */
	public SystemUserBo getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(SystemUserBo user) {
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public static ComplicatedSystemUserBoStub getSuperDeptSystemUser(){
		SystemUserBo user = new SystemUserBo();
		user.setDeptID("33251087");
		user.setDeptName("无部门");
		user.setManageName("财务部负责人");
		user.setPassword("1234");
		user.setRole("SUPER_DEPT");
		user.setUserID("实物资产管理组");
		ComplicatedSystemUserBoStub bo = new ComplicatedSystemUserBoStub();
		bo.user=user;
		bo.id="testid";
		return bo;
		
	}
}
