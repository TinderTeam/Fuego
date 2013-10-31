package cn.tinder.fuego.webservice.struts.bo.base;

public class WareHousingEnsureBo {
	AssetsBo bo;
	String index;
	
	public AssetsBo getBo() {
		return bo;
	}
	public void setBo(AssetsBo bo) {
		this.bo = bo;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return "WareHousingEnsureBo [bo=" + bo + ", index=" + index + "]";
	}
	

}
