package cn.tinder.fuego.dao.hibernate;

import org.junit.Test;

import cn.tinder.fuego.dao.impl.SystemUserDaoImpl;
import cn.tinder.fuego.domain.po.SystemUser;

import junit.framework.TestCase;

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
