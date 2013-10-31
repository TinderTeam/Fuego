package cn.tinder.fuego.dao.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.MenuTreeDao;
import cn.tinder.fuego.domain.po.MenuTree;

public class MenuTreeDaoImplTest
{
	private MenuTreeDao menuTreeDao = DaoContext.getInstance().getMenuTreeDao();

	@Test
	public void testGetAllMenuTree()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMenuByParentID()
	{
		List<MenuTree> menuTreeList = menuTreeDao.getMenuByParentID(0);
		System.out.println(menuTreeList);
		assert (true);
	}

}
