package cn.tinder.fuego.service.model;

import java.util.List;

import cn.tinder.fuego.domain.po.MenuTree;

public class MenuTreeModel
{
	private MenuTree menu;
	private List<MenuTreeModel> childMenuList;

	public MenuTree getMenu()
	{
		return menu;
	}

	public void setMenu(MenuTree menu)
	{
		this.menu = menu;
	}

	public List<MenuTreeModel> getChildMenuList()
	{
		return childMenuList;
	}

	public void setChildMenuList(List<MenuTreeModel> childMenuList)
	{
		this.childMenuList = childMenuList;
	}

	@Override
	public String toString()
	{
		return "MenuTreeModel [menu=" + menu + ", childMenuList=" + childMenuList + "]";
	}

}
