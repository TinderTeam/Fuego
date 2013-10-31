package cn.tinder.fuego.webservice.struts.bo.recapture;

import java.util.List;

public class RecaptureSearchBo {

	private List<String> gasNameList;
	private List<String> assetsTypeList;
	public List<String> getGasNameList() {
		return gasNameList;
	}
	public void setGasNameList(List<String> gasNameList) {
		this.gasNameList = gasNameList;
	}
	public List<String> getAssetsTypeList() {
		return assetsTypeList;
	}
	public void setAssetsTypeList(List<String> assetsTypeList) {
		this.assetsTypeList = assetsTypeList;
	}
	@Override
	public String toString() {
		return "AssetsRecaptureSearchBo []";
	}
	
	
}
