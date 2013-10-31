package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


/**
 * 
* @ClassName: PurchaseWarehousingForm 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-2 下午09:17:28 
*
 */
public class ImportAssetsForm extends ActionForm
{
	private FormFile assetsFile;

	public FormFile getAssetsFile()
	{
		return assetsFile;
	}

	public void setAssetsFile(FormFile assetsFile)
	{
		this.assetsFile = assetsFile;
	}

 
	
}
