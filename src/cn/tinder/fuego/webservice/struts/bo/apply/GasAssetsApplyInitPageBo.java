package cn.tinder.fuego.webservice.struts.bo.apply;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class GasAssetsApplyInitPageBo {
	
	List<AssetsBo> 	assetBoList;
	List<String>	typeList;
	List<String>	AssetsList;
	List<String>	techList;
	
	
	
	public List<String> getTechList() {
		return techList;
	}
	public void setTechList(List<String> techList) {
		this.techList = techList;
	}
	
	@Override
	public String toString() {
		return "GasAssetsApplyInitPageBo [assetBoList=" + assetBoList
				+ ", typeList=" + typeList + ", AssetsList=" + AssetsList
				+ ", techList=" + techList + "]";
	}
	public List<AssetsBo> getAssetBoList() {
		return assetBoList;
	}
	public void setAssetBoList(List<AssetsBo> assetBoList) {
		this.assetBoList = assetBoList;
	}
	public List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public List<String> getAssetsList() {
		return AssetsList;
	}
	public void setAssetsList(List<String> assetsList) {
		AssetsList = assetsList;
	}
	
	
	
	
}
