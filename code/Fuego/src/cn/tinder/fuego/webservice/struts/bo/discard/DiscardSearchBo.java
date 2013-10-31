package cn.tinder.fuego.webservice.struts.bo.discard;

import java.util.List;

public class DiscardSearchBo {
	
	private String date;
	private List<String> techStatusList;
	private List<String> assetsTypeList;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getTechStatusList() {
		return techStatusList;
	}
	public void setTechStatusList(List<String> techStatusList) {
		this.techStatusList = techStatusList;
	}
	public List<String> getAssetsTypeList() {
		return assetsTypeList;
	}
	public void setAssetsTypeList(List<String> assetsTypeList) {
		this.assetsTypeList = assetsTypeList;
	}
	@Override
	public String toString() {
		return "DiscardSearchBo [date=" + date + ", techStatusList="
				+ techStatusList + ", assetsTypeList=" + assetsTypeList + "]";
	}


	
	

}
