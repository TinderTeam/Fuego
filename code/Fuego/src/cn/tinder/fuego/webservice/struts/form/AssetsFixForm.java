package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;


/**
 * 
* @ClassName: AssetsFixForm 
* @Description: TODO
* @author Nan Bowen
* @date 2014-1-12 下午10:18:21 
*
 */
public class AssetsFixForm extends ActionForm
{
	

	private FormFile addAssetsFixFile;
	private FormFile deleteAssetsFixFile;
	private FormFile updateAssetsFixFile;
	/**
	 * @return the addAssetsFixFile
	 */
	public FormFile getAddAssetsFixFile() {
		return addAssetsFixFile;
	}
	/**
	 * @param addAssetsFixFile the addAssetsFixFile to set
	 */
	public void setAddAssetsFixFile(FormFile addAssetsFixFile) {
		this.addAssetsFixFile = addAssetsFixFile;
	}
	/**
	 * @return the deleteAssetsFixFile
	 */
	public FormFile getDeleteAssetsFixFile() {
		return deleteAssetsFixFile;
	}
	/**
	 * @param deleteAssetsFixFile the deleteAssetsFixFile to set
	 */
	public void setDeleteAssetsFixFile(FormFile deleteAssetsFixFile) {
		this.deleteAssetsFixFile = deleteAssetsFixFile;
	}
	/**
	 * @return the updateAssetsFixFile
	 */
	public FormFile getUpdateAssetsFixFile() {
		return updateAssetsFixFile;
	}
	/**
	 * @param updateAssetsFixFile the updateAssetsFixFile to set
	 */
	public void setUpdateAssetsFixFile(FormFile updateAssetsFixFile) {
		this.updateAssetsFixFile = updateAssetsFixFile;
	}


	
}
