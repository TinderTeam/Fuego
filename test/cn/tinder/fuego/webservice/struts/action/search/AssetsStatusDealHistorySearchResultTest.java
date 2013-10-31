package cn.tinder.fuego.webservice.struts.action.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.search.AssetsStatusDealHistorySearchResultBo;

public class AssetsStatusDealHistorySearchResultTest {

	public static List<AssetsStatusDealHistorySearchResultBo> assetsStatusDealHistorySearchResult() {
		// TODO Auto-generated method stub
		List<AssetsStatusDealHistorySearchResultBo> l= new ArrayList<AssetsStatusDealHistorySearchResultBo>();
		AssetsStatusDealHistorySearchResultBo b = new AssetsStatusDealHistorySearchResultBo();
		
		AssetsBo a = new AssetsBo();
		
		a.setAssetsID("479110");
		a.setAssetsName("car");
		a.setAssetsSRC("xiafa");
		a.setManufacture("Benz");
		a.setSpec("C100");
		a.setUnit("ge");
		a.setQuantity(2);
		a.setPurchaseDate(DateService.DateToString(new Date(System.currentTimeMillis())));
		a.setOriginalValue(100);
		a.setExpectYear(10);
		a.setDueDate(DateService.DateToString(new Date(System.currentTimeMillis())));
		a.setDept("kara");
		a.setDuty("Zhang");
		a.setAssetsType("dizhiyihao");
		a.setLocation("Dongguan");
		a.setTechState("Good");
		
		b.setUserName("Nam");
		b.setOperationDate("2013/9/10");
		b.setOperationType("Add");
		b.setBo(a);
		
		l.add(b);
		
		
		
		return l;
	}

}
