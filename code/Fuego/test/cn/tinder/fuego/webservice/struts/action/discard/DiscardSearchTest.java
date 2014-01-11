package cn.tinder.fuego.webservice.struts.action.discard;

import java.util.List;

import cn.tinder.fuego.service.ConstServiceTest;
import cn.tinder.fuego.webservice.struts.bo.discard.DiscardSearchBo;

public class DiscardSearchTest {

	public static DiscardSearchBo discardSearch() {
		// TODO Auto-generated method stub
		DiscardSearchBo list = new DiscardSearchBo();
		//DiscardSearchBo a = new DiscardSearchBo();
		
		List<String> techStatusList = null;
		List<String> assetsTypeList = null;
		
		
		techStatusList = ConstServiceTest.getTechStateList();
		assetsTypeList = ConstServiceTest.getAssetsTypeList();
		
		list.setDate("2013-7-4");
		list.setAssetsTypeList(assetsTypeList);
		list.setTechStatusList(techStatusList);

	
		
		return list;
	}

}
