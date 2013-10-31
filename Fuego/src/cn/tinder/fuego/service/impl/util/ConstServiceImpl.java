package cn.tinder.fuego.service.impl.util;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.dao.DaoContext;
import cn.tinder.fuego.dao.SystemUserDao;
import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.service.util.ConstService;

public class ConstServiceImpl implements ConstService{
	private SystemUserDao systemUserDao = DaoContext.getInstance().getSystemUserDao();
	@Override
	public List<String> getAllUserList() {

		List<String> accountList =new ArrayList<String>();
		List<SystemUser> userList =new ArrayList<SystemUser>();
		userList=systemUserDao.getAllSystemUser();
		for(SystemUser user:userList)
		{
			accountList.add(user.getUserName());
		}
		return accountList;
	}

	@Override
	public List<String> getAllGasNameList() {
		List<String> l =new ArrayList<String>();
		l.add("Test");
		l.add("test2");
		
		return l;
	}

	@Override
	public List<String> getAssetsTypeList() {
		List<String> l =new ArrayList<String>();
		l.add("Test");
		l.add("test2");
		
		return l;
	}

	
	
}
