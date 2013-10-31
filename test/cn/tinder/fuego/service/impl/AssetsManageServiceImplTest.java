package cn.tinder.fuego.service.impl;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.text.DateFormat;
import java.util.List;



import org.junit.Test;

import cn.tinder.fuego.service.AssetsManageService;
import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class AssetsManageServiceImplTest
{

	AssetsManageService service = ServiceContext.getInstance().getAssetsManageService();

	@Test
	public void testGetAssetsByDept()
	{
 
	}

	@Test
	public void testGetAssetsByFilter()
	{
		 
	 
		fail("Not yet implemented");
	}

}
