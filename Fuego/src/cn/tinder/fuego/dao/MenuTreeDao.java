package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.MenuTree;

public interface MenuTreeDao
{
	public List<MenuTree> getAllMenuTree();

	public List<MenuTree> getMenuByParentID(int parentID);

}
