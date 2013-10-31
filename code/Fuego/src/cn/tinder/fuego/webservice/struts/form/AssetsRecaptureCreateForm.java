package cn.tinder.fuego.webservice.struts.form;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/**
 * 
* @ClassName: AssetsRecoverCreateForm 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-3 上午12:50:08 
*
 */
public class AssetsRecaptureCreateForm extends ActionForm {
	
	private AssetsBo assets = new AssetsBo();
	String [] assetsIDList ;
	public AssetsBo getAssets() {
		return assets;
	}
	public void setAssets(AssetsBo assets) {
		this.assets = assets;
	}
	public String[] getAssetsIDList() {
		return assetsIDList;
	}
	public void setAssetsIDList(String[] assetsIDList) {
		this.assetsIDList = assetsIDList;
	}
	@Override
	public String toString() {
		return "AssetsRecaptureCreateForm [assets=" + assets
				+ ", assetsIDList=" + Arrays.toString(assetsIDList) + "]";
	}
	

}
