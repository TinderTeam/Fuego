package cn.tinder.fuego.webservice.struts.bo.discard;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class AssetsDiscardInfoBo {
	
	private String index;
	AssetsBo assetsBo;
	private String note;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public AssetsBo getAssetsBo() {
		return assetsBo;
	}
	public void setAssetsBo(AssetsBo assetsBo) {
		this.assetsBo = assetsBo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "AssetsDiscardInfoBo [index=" + index + ", assetsBo=" + assetsBo
				+ ", note=" + note + "]";
	}
	

}
