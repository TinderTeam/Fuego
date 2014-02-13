package cn.tinder.fuego.dao.impl;

import junit.framework.TestCase;

import org.junit.Test;

import cn.tinder.fuego.domain.po.SystemUser;

public class SystemUserDaoImplTest extends TestCase
{

	SystemUser su = new SystemUser();
	SystemUserDaoImpl daoimpl = new SystemUserDaoImpl();

	// @Test
	public void testCreate()
	{
		su.setUserName("TestSystemUser2");
		su.setPassword("123456");
		su.setDepartment("TestDepartment");
		su.setDepartmentID("123456");
		su.setRole("Admin");

		daoimpl.create(su);

	}

	// @Test
	public void testDelete()
	{
		su.setUserName("TestSystemUser2");
		su.setPassword("123456");
		su.setDepartment("TestDepartment");
		su.setDepartmentID("123456");
		su.setRole("Admin");
		daoimpl.delete(su);
	}

	@Test
	public void testFindString()
	{
		su.setUserName("test");

		System.out.println(daoimpl.find("test"));

	}

	// @Test
	public void testFindStringObject()
	{
		su.setUserName("TestSystemUser");
		daoimpl.find(su.getUserName());

	}

	// @Test
	public void testSaveOrUpdate()
	{
		su.setUserName("SaveOrUpdateSystemUser");
		daoimpl.saveOrUpdate(su);
	}

}
