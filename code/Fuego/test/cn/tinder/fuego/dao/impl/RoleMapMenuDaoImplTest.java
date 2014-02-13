package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.RoleMapMenuDao;
import cn.tinder.fuego.domain.po.RoleMapMenu;

public class RoleMapMenuDaoImplTest
{

	private RoleMapMenuDao roleMapMenuDao = DaoContext.getInstance().getRoleMapMenuDao();

	@Test
	public void testGetMenuIDByRole()
	{
		RoleMapMenu roleMenu = new RoleMapMenu();
		roleMenu.setRole("aaa");
		roleMenu.setMenuID(1);
		roleMapMenuDao.create(roleMenu);
		String role = "ADMIN";
		List<Integer> menuIDList = roleMapMenuDao.getMenuIDByRole(role);
		System.out.println(menuIDList);
		assert (true);
	}

}
