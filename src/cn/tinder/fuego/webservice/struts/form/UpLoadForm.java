package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


/**
 * 
* @ClassName: UpLoadForm 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-6 上午01:37:27 
*
 */
public class UpLoadForm extends ActionForm {

	private FormFile myFile;

	public FormFile getMyFile() {
		return myFile;
	}

	public void setMyFile(FormFile myFile) {
		this.myFile = myFile;
	}

	@Override
	public String toString() {
		return "UpLoadForm [myFile=" + myFile + "]";
	}    
	
	
}
