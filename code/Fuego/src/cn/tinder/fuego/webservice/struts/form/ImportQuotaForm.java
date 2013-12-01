package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


/**
 * 
* @ClassName: ImportPriceForm 
* @Description: TODO
* @author Nan Bowen
* @date 2013-11-22 上午12:55:03 
*
 */

public class ImportQuotaForm extends ActionForm
{
	private FormFile priceFile;

	public void setPriceFile(FormFile priceFile) {
		this.priceFile = priceFile;
	}

	public FormFile getPriceFile() {
		return priceFile;
	}

	
 
	
}
