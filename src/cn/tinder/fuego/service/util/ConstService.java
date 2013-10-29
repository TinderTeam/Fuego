package cn.tinder.fuego.service.util;

import java.util.List;

import cn.tinder.fuego.domain.po.SystemUser;

public interface ConstService  {
	/**
	 * Get all UserList
	 * @return
	 */
	public List<String> getAllUserList();

	public List<String> getAllGasNameList();
	
	public List<String> getAssetsTypeList();
	

}
