package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.RoleMapMenu;

public interface RoleMapMenuDao
{
	public List<Integer> getMenuIDByRole(String role);

	public void create(RoleMapMenu roleMenu);

}
