package cn.tinder.fuego.webservice.struts.action.search;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusDealHistoryInitActionBo;

public class AssetsStatusDealHistoryTest {

	public static List<AssetsStatusDealHistoryInitActionBo> assetsStatusDealHistory() {
		// TODO Auto-generated method stub
		List<AssetsStatusDealHistoryInitActionBo> list = new ArrayList<AssetsStatusDealHistoryInitActionBo>();
		AssetsStatusDealHistoryInitActionBo a = new AssetsStatusDealHistoryInitActionBo();
		

		a.setAssetsID("7806391");
		a.setAssetsName("car");
		a.setPurchaseDate("2013/9/10");
		a.setLocation("Shanghai");

		
		list.add(a);

		return list;
	}

	
}
