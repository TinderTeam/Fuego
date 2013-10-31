package cn.tinder.fuego.webservice.struts.form.purchase;

import java.util.Arrays;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.util.MyArrayList;

/**
 * 
 * @ClassName: PurchasePlanForm
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-24 下午08:33:43
 * 
 */
public class PurchasePlanCreateForm extends ActionForm
{

	private MyArrayList assetsCreateBo = new MyArrayList(PurchasePlanBo.class);
	private String newAssetsName;
	private FormFile myFile;

	/**
	 * @return the assetsCreateBo
	 */
	public MyArrayList getAssetsCreateBo() {
		return assetsCreateBo;
	}

	/**
	 * @return the myFile
	 */
	public FormFile getMyFile() {
		return myFile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchasePlanCreateForm [assetsCreateBo=" + assetsCreateBo
				+ ", myFile=" + myFile + "]";
	}

	/**
	 * @param myFile the myFile to set
	 */
	public void setMyFile(FormFile myFile) {
		this.myFile = myFile;
	}

	/**
	 * @param assetsCreateBo the assetsCreateBo to set
	 */
	public void setAssetsCreateBo(MyArrayList assetsCreateBo) {
		this.assetsCreateBo = assetsCreateBo;
	}

	public void setNewAssetsName(String newAssetsName) {
		this.newAssetsName = newAssetsName;
	}

	public String getNewAssetsName() {
		return newAssetsName;
	}


}
