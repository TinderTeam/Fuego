package cn.tinder.fuego.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.service.GasAssetsApplyService;
import cn.tinder.fuego.webservice.struts.bo.apply.GasAssetsApplyInitPageBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

public class GasAssetsApplyServiceImpl implements GasAssetsApplyService{

	@Override
	public GasAssetsApplyInitPageBo getPageBoByUser(SystemUserBo user) {
		// TODO Auto-generated method stub
		GasAssetsApplyInitPageBo bo = new GasAssetsApplyInitPageBo();
		AssetsBo a = new AssetsBo();
		a.setAssetsID("12324");
		a.setAssetsName("Name ");
		a.setAssetsSRC("sadfsa");
		
		List<AssetsBo> list =new ArrayList<AssetsBo>();
		list.add(a);
	
		List<String>  list2 = new ArrayList<String>();
		list2.add("list2_1");
		list2.add("list2_2");
		
		List<String>  list4 = new ArrayList<String>();
		list4.add("list4");
		list4.add("45");
		
		bo.setAssetBoList(list);
		bo.setTypeList(list4);
		bo.setAssetsList(list2);
		return bo;
	}

}
