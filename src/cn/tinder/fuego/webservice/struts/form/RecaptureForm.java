package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ClassName: AssetsRecoverForm
 * @Description: TODO
 * @author Kong Jingliang
 * @date 2013-10-2 下午09:10:26
 * 
 */
public class RecaptureForm extends ActionForm {

	private String gasName ;
	private String assetsType ;
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
		return "AssetsRecoverForm [gasName=" + gasName + ", assetsType="
				+ assetsType + "]";
	}
	
}
