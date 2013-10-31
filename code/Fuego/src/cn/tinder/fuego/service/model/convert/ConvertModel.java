package cn.tinder.fuego.service.model.convert;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.model.MenuTreeModel;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

/**
 * 
 * @ClassName: ConvertModel
 * @Description: TODO
 * @author Li yong lei
 * @date 2013-10-1 ����08:58:26
 * 
 */
public class ConvertModel
{
	public static List<MenuTreeBo> convertMenuTreeModelList(List<MenuTreeModel> menuTreeList)
	{
		List<MenuTreeBo> menuItemTreeList = new ArrayList<MenuTreeBo>();
		for (MenuTreeModel menuModel : menuTreeList)
		{
			MenuTreeBo menuTreeBo = convertMenuTreeModel(menuModel);
			if (null != menuModel.getChildMenuList())
			{
				List<MenuTreeBo> childMenuList = convertMenuTreeModelList(menuModel.getChildMenuList());
				menuTreeBo.setChildItemList(childMenuList);
			}
			menuItemTreeList.add(menuTreeBo);
		}
		return menuItemTreeList;
	}

	public static MenuTreeBo convertMenuTreeModel(MenuTreeModel menuModel)
	{
		MenuTreeBo menuBo = new MenuTreeBo();

		menuBo.setName(menuModel.getMenu().getName());
		menuBo.setValue(menuModel.getMenu().getValue());
		menuBo.setMenuCss(menuModel.getMenu().getMenuCss());
		menuBo.setIconCss(menuModel.getMenu().getIconCss());
		menuBo.setUrl(menuModel.getMenu().getUrl());

		return menuBo;
	}

	public static SystemUserBo convertSystemUser(SystemUser systemUser)
	{
		SystemUserBo systemUserBo = new SystemUserBo();
		systemUserBo.setUserID(systemUser.getUserName());
		systemUserBo.setPassword(systemUser.getPassword());
		systemUserBo.setDeptID(systemUser.getDepartmentID());
		systemUserBo.setDeptName(systemUser.getDepartment());
		systemUserBo.setRole(systemUser.getRole());

		return systemUserBo;
	}
}
