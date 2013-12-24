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
	
	private FormFile initAssetsFile;
	private FormFile addAssetsFile;
	private FormFile deleteAssetsFile;
	private FormFile updateAssetsFile;
	
	
	/**
	 * @return the initAssetsFile
	 */
	public FormFile getInitAssetsFile() {
		return initAssetsFile;
	}
	/**
	 * @param initAssetsFile the initAssetsFile to set
	 */
	public void setInitAssetsFile(FormFile initAssetsFile) {
		this.initAssetsFile = initAssetsFile;
	}
	/**
	 * @return the addAssetsFile
	 */
	public FormFile getAddAssetsFile() {
		return addAssetsFile;
	}
	/**
	 * @param addAssetsFile the addAssetsFile to set
	 */
	public void setAddAssetsFile(FormFile addAssetsFile) {
		this.addAssetsFile = addAssetsFile;
	}
	/**
	 * @return the deleteAssetsFile
	 */
	public FormFile getDeleteAssetsFile() {
		return deleteAssetsFile;
	}
	/**
	 * @param deleteAssetsFile the deleteAssetsFile to set
	 */
	public void setDeleteAssetsFile(FormFile deleteAssetsFile) {
		this.deleteAssetsFile = deleteAssetsFile;
	}
	/**
	 * @return the updateAssetsFile
	 */
	public FormFile getUpdateAssetsFile() {
		return updateAssetsFile;
	}
	/**
	 * @param updateAssetsFile the updateAssetsFile to set
	 */
	public void setUpdateAssetsFile(FormFile updateAssetsFile) {
		this.updateAssetsFile = updateAssetsFile;
	}
	
}
