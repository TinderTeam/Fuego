package cn.tinder.fuego.service.impl;

import org.junit.Test;

import cn.tinder.fuego.service.LoadService;
import cn.tinder.fuego.service.ServiceContext;

public class LoadServiceImplTest
{
	LoadService service = ServiceContext.getInstance().getLoadService();
	@Test
	public void testGetAssignListByUser()
	{
		String userName = "nbw";
		System.out.println(service.getAssignListByUser(userName));
	}

}
