package cn.tinder.fuego.service;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;
import cn.tinder.fuego.webservice.struts.bo.base.MenuTreeBo;

/**
 * 
 * @ClassName: LoadService
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-24 上午12:34:37
 * 
 */
public interface LoadService
{
	/**
	 * 
	 * @param userID
	 * @return
	 */
	public List<MenuTreeBo> loadMenuTreeByUserID(String userID);

	public List<String> loadAllDeptInfo();
	
	public List<DeptInfoBo> getAssignListByUser(String userName);
	
	public List<String> loadAssetsTypeList();
	
	public List<String> loadAssetsTechList();
	
	public List<String> loadAssetsSrcList();
	
	public List<String> loadManageDeptList();
	
	public List<String> loadGasNameList();
	
	public List<String> loadOperateType();

}
