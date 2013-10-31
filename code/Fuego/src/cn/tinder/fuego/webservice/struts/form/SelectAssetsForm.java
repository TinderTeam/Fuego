/**   
 * @Title: SelectAssetsForm.java 
 * @Package cn.tinder.fuego.webservice.struts.form 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-26 下午11:35:10 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.form;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/**
 * @ClassName: SelectAssetsForm
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-26 下午11:35:10
 * 
 */

public class SelectAssetsForm extends ActionForm
{
	private AssetsBo assets = new AssetsBo();
	String [] assetsIDList ;
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
