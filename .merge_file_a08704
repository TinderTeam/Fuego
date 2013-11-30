package cn.tinder.fuego.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.MenuTreeDao;
import cn.tinder.fuego.dao.RoleMapMenuDao;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.MenuTree;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.constant.AssetsConst;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.constant.UserRoleConst;
import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.service.model.MenuTreeModel;
import cn.tinder.fuego.service.model.convert.ConvertModel;
import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;

/**
 * 
 * @ClassName: LoadServiceImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-22 下午10:15:24
 * 
 */
public class LoadServiceImpl implements LoadService
{

	private static final Log log = LogFactory.getLog(LoadServiceImpl.class);

	private MenuTreeDao menuTreeDao = DaoContext.getInstance().getMenuTreeDao();
	private RoleMapMenuDao roleMapMenuDao = DaoContext.getInstance().getRoleMapMenuDao();
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();

	// for data cache
	private List<MenuTreeModel> allMenuList = null;
	private Map<String, List<Integer>> roleMapMenuIDList = new HashMap<String, List<Integer>>();

	@Override
	public List<MenuTreeBo> loadMenuTreeByUserID(String userID)
	{
		// load all menu tree
		List<MenuTreeModel> menuTreeList = loadAllMenu();

		// get role by user id
		SystemUser user = systemUserDao.find(userID);

		if (null == user)
		{
			throw new ServiceException(ExceptionMsg.LOGIN_NO_USER);
		}
		// get menu list by role
		List<Integer> menuIDList = getMenuIDListByRole(user.getRole());

		
 
		return ConvertModel.convertMenuTreeModelList(getMenuTreeByMenuIDList(menuTreeList, menuIDList));
	}

	private List<MenuTreeModel> getMenuTreeByMenuIDList(List<MenuTreeModel> menuTreeList, List<Integer> menuIDList)
	{
		List<MenuTreeModel> nowMenuTreeList = new ArrayList<MenuTreeModel>();
		for(MenuTreeModel menuTree : menuTreeList)
		{
			if(null != menuIDList && menuIDList.contains(menuTree.getMenu().getMenuID()))
			{	
 			 
				MenuTreeModel nowMenuTree = convertMenuTreeModel(menuTree);
	 			if (null != menuTree.getChildMenuList())
				{
					List<MenuTreeModel> childMenuList = getMenuTreeByMenuIDList(menuTree.getChildMenuList(),menuIDList);
					nowMenuTree.setChildMenuList(childMenuList);
				}
	 			nowMenuTreeList.add(nowMenuTree);
			}
		}
		return nowMenuTreeList;
	}
	private static MenuTreeModel convertMenuTreeModel(MenuTreeModel menuModel)
	{
		MenuTreeModel menu = new MenuTreeModel();
		MenuTree menuTree = new MenuTree();
		menuTree.setName(menuModel.getMenu().getName());
		menuTree.setValue(menuModel.getMenu().getValue());
		menuTree.setMenuID(menuModel.getMenu().getMenuID());
		menuTree.setMenuCss(menuModel.getMenu().getMenuCss());
		menuTree.setIconCss(menuModel.getMenu().getIconCss());
		menuTree.setUrl(menuModel.getMenu().getUrl());
		menuTree.setParentID(menuModel.getMenu().getParentID());
 
		menu.setMenu(menuTree);

		return menu;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.LoadService#loadAllDeptInfo()
	 */
	@Override
	public List<String> loadAllDeptInfo()
	{
		Set<String> deptList = new HashSet<String>();
		List<SystemUser> allUserList = systemUserDao.getAllSystemUser();
		for (SystemUser user : allUserList)
		{
 			deptList.add(user.getDepartment());
		}

		return new ArrayList<String>(deptList);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.tinder.fuego.service.LoadService#loadAllDeptInfo()
	 */
	@Override
	public List<DeptInfoBo> getAssignListByUser(String userName)
	{
		
		Set<DeptInfoBo> deptList = new HashSet<DeptInfoBo>();
		List<SystemUser> assignDeptList =systemUserDao.getUserByRole(UserRoleConst.GASSTATION);
		SystemUser systemUser =null;
		systemUser=systemUserDao.find(userName);
	
	    DeptInfoBo dept = null;
		for (SystemUser user : assignDeptList)
		{
			dept = new DeptInfoBo();
			dept.setName(user.getDepartment());
			
		}
		dept = new DeptInfoBo();
		dept.setName(systemUser.getDepartment());

		deptList.add(dept);
		return  new ArrayList<DeptInfoBo>(deptList);
	}
    
	
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.LoadService#loadAssetsTypeList()
	 */
	@Override
	public List<String> loadAssetsTypeList()
	{
		List<String> assetsTypeList = new ArrayList<String>();
		assetsTypeList.add(AssetsConst.ASSETS_GDZC_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_DZYH_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_XFQC_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_YLSS_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_FWJZ_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_XXSB_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_LBYP_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_JQSB_TYPE);
		assetsTypeList.add(AssetsConst.ASSETS_LSZC_TYPE);
		
		return assetsTypeList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.LoadService#loadAssetsSrcList()
	 */
	@Override
	public List<String> loadAssetsSrcList()
	{
		List<String> assetsSrcList = new ArrayList<String>();
		assetsSrcList.add(AssetsConst.ASSETS_SRC_PURCHASE);
		assetsSrcList.add(AssetsConst.ASSETS_SRC_ASSIGN);
 
		return assetsSrcList;
	}
	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.LoadService#loadAssetsTechList()
	 */
	@Override
	public List<String> loadAssetsTechList()
	{
		List<String> techList = new ArrayList<String>();
		techList.add(AssetsConst.ASSETS_STATUS_IDLE);
		techList.add(AssetsConst.ASSETS_STATUS_DISCARD);
		techList.add(AssetsConst.ASSETS_STATUS_NORMAL);
		techList.add(AssetsConst.ASSETS_STATUS_BAD);
	
		
		return techList;
	}
	
	@Override
	public List<String> loadTransNameList()
	{
		List<String> transNameList = new ArrayList<String>();
		
		transNameList.add(TransactionConst.PURCHASE_PLAN_NAME);
		transNameList.add(TransactionConst.DISCARD_PLAN_NAME);

 		transNameList.add(TransactionConst.ASSIGN_PLAN_NAME);
//
//		transNameList.add(TransactionConst.CHECK_PLAN_NAME);
 		transNameList.add(TransactionConst.RECAPTURE_PLAN_NAME);
 		transNameList.add(TransactionConst.RECEIVE_PLAN_NAME);

	
		
		return transNameList;
	}

	
	/**
	 * filter menu tree by menu id list
	 * 
	 * @param menuTreeList
	 * @param menuIDList
	 */
	private void filterMenuByMenuIDList(List<MenuTreeModel> menuTreeList, List<Integer> menuIDList)
	{
		log.info(menuTreeList);
		if (null == menuTreeList)
		{
			log.warn("the menu tree list is null");
			return;
		}
		Iterator<MenuTreeModel> iterator = menuTreeList.iterator();

		while (iterator.hasNext())
		{
			MenuTreeModel menuTree = iterator.next();
			if (null == menuIDList || !menuIDList.contains(menuTree.getMenu().getMenuID()))
			{
				iterator.remove();
			} else
			{
				filterMenuByMenuIDList(menuTree.getChildMenuList(), menuIDList);
			}
		}

	}

	/**
	 * get menu id list by role
	 * 
	 * @param role
	 * @return
	 */
	private List<Integer> getMenuIDListByRole(String role)
	{
		List<Integer> menuIDList = roleMapMenuIDList.get(role);
		if (null == menuIDList)
		{
			menuIDList = this.roleMapMenuDao.getMenuIDByRole(role);
			this.roleMapMenuIDList.put(role, menuIDList);
			log.info("load menu id list by role." + role + menuIDList);
		}
		return menuIDList;
	}

	/**
	 * load all menu
	 * 
	 * @return
	 */
	private List<MenuTreeModel> loadAllMenu()
	{
		if (null == allMenuList)
		{
			// there is no parent menu when parent id is 0
			allMenuList = loadMenuTreeByParentID(0);
			log.info("loaded all menu list" + allMenuList);
		}
		return allMenuList;
	}

	/**
	 * load menu tree by parent id
	 * 
	 * @param parentID
	 * @return
	 */
	private List<MenuTreeModel> loadMenuTreeByParentID(int parentID)
	{
		List<MenuTreeModel> menuTreeList = new ArrayList<MenuTreeModel>();

		for (MenuTree menu : menuTreeDao.getMenuByParentID(parentID))
		{
			MenuTreeModel menuTreeModel = new MenuTreeModel();
			menuTreeModel.setMenu(menu);
			if (null != menuTreeDao.getMenuByParentID(menu.getMenuID()) && !menuTreeDao.getMenuByParentID(menu.getMenuID()).isEmpty())
			{
				menuTreeModel.setChildMenuList(loadMenuTreeByParentID(menu.getMenuID()));
			}
			menuTreeList.add(menuTreeModel);
		}
		return menuTreeList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.LoadService#loadManageDeptList()
	 */
	@Override
	public List<String> loadManageDeptList()
	{
		// TODO Auto-generated method stub
		Set<String> manageDeptSet =new HashSet<String>();
 		
		List<SystemUser> allUserList = systemUserDao.getAllSystemUser();
			for (SystemUser user : allUserList)
			{
				if((null != user.getManageName())&& (!user.getManageName().isEmpty()) )
				{
					manageDeptSet.add(user.getManageName());
				}
			}
 
		return new ArrayList<String>(manageDeptSet);
	}
	
	public List<String> loadGasNameList()
	{
		// TODO Auto-generated method stub
		List<String> gasNameList =new ArrayList<String>();
 		
		//List<SystemUser> allUserList = systemUserDao.getAllSystemUser();
		
		List<SystemUser> allGasList =systemUserDao.getUserByRole(UserRoleConst.GASSTATION);
			for (SystemUser user : allGasList)
			{
				 gasNameList.add(user.getUserName());
			}
 				
		return gasNameList;
	}

	/* (non-Javadoc)
	 * @see cn.tinder.fuego.service.LoadService#loadOperateType()
	 */
	@Override
	public List<String> loadOperateType()
	{
		List<String> operateTypeList = new ArrayList<String>();
		
		operateTypeList.add(AssetsConst.ASSETS_OPERATE_TYPE_CREATE);
		operateTypeList.add(AssetsConst.ASSETS_OPERATE_TYPE_ASSIGN);
		operateTypeList.add(AssetsConst.ASSETS_OPERATE_TYPE_DISCARD);
		operateTypeList.add(AssetsConst.ASSETS_OPERATE_TYPE_RECOVER);
		
		return operateTypeList;
	}
 
}
