/**   
* @Title: AssetsReceiveForm.java 
* @Package cn.tinder.fuego.webservice.struts.form.purchase 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-26 下午11:02:50 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.util.MyArrayList;

/** 
 * @ClassName: AssetsReceiveForm 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-26 下午11:02:50 
 *  
 */

public class AssetsListForm extends ActionForm
{
	private MyArrayList assetsList = new MyArrayList(AssetsInfoBo.class);

	public MyArrayList getAssetsList()
	{
		return assetsList;
	}

	public void setAssetsList(MyArrayList assetsList)
	{
		this.assetsList = assetsList;
	}
 

}
