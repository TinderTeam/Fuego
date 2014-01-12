package cn.tinder.fuego.service.model.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.tinder.fuego.dao.AssetsFixDao;
import cn.tinder.fuego.domain.po.AssetsFix;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.model.MenuTreeModel;
import cn.tinder.fuego.util.ConfigInformation;
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
	
	public static AssetsFix convertyToAssetsFix(List<String> list) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext( ConfigInformation.getResourcePath()+"/resource/assetsFixBean.xml");
		AssetsFix fix =  (AssetsFix) ctx.getBean("assetsFix");
		fix.setIndexNumber(list.get(0));
		fix.setContext(list.get(1));
		fix.setGasStation(list.get(2));
		fix.setDept(list.get(3));
		fix.setHandleUser(list.get(4));
		fix.setBudget(list.get(5));
		fix.setSendTime(list.get(6));
		fix.setStartTime(list.get(7));
		fix.setFinishTime(list.get(8));
		fix.setPayMoney(list.get(9));			
		fix.setPayTime(list.get(10));		
		fix.setDiff(list.get(11));
		fix.setAlreadyPay(list.get(12));
		fix.setUnPay(list.get(13));
		fix.setNote(list.get(14));
		return fix;
	}

	public static AssetsFix convertyToAssetsFixDelete(List<String> item) {
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext( ConfigInformation.getResourcePath()+"/resource/assetsFixBean.xml");		
		
			AssetsFix fix =  (AssetsFix) ctx.getBean("assetsFix");
			fix.setIndexNumber(item.get(0));
	
		return fix;
	}
}
