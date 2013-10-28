package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

public class AssetsSelectForm extends ActionForm {
	
	private String gasName;
	private String assetsType;
	public String getGasName() {
		return gasName;
	}
	public void setGasName(String gasName) {
		this.gasName = gasName;
	}
	public String getAssetsType() {
		return assetsType;
	}
	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}
	@Override
	public String toString() {
		return "AssetsSelectForm [gasName=" + gasName + ", assetsType="
				+ assetsType + "]";
	}
	
}
