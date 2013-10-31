package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.ArrayList;
import java.util.List;

public class GasStationCheckInitBo {

	private List<String> monthList ;
	
	private String transID ;
	
	private List<AssetsCheckBo> checkList = new ArrayList<AssetsCheckBo>() ;

	public List<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public List<AssetsCheckBo> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<AssetsCheckBo> checkList) {
		this.checkList = checkList;
	}

	@Override
	public String toString() {
		return "GasStationCheckInitBo [monthList=" + monthList + ", transID="
				+ transID + ", checkList=" + checkList + "]";
	}
	
	
	
}
