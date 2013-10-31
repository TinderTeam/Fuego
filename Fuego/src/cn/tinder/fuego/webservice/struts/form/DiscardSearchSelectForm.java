package cn.tinder.fuego.webservice.struts.form;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

public class DiscardSearchSelectForm extends ActionForm {
	

	private AssetsBo assets = new AssetsBo();
	private String [] assetsIDList ;
	public AssetsBo getAssets()
	{
		return assets;
	}
	@Override
	public String toString() {
		return "SelectAssetsForm [assets=" + assets + ", assetsIDList="
				+ Arrays.toString(assetsIDList) + "]";
	}
	public void setAssets(AssetsBo assets)
	{
		this.assets = assets;
	}
	public String[] getAssetsIDList()
	{

		return assetsIDList;
	}
	public void setAssetsIDList(String[] assetsIDList)
	{
		this.assetsIDList = assetsIDList;
	}


}
