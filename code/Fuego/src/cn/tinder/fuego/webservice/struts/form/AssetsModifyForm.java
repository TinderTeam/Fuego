package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;

public class AssetsModifyForm extends ActionForm
{
	AssetsInfoBo assetsInfo = new AssetsInfoBo();

	public AssetsInfoBo getAssetsInfo()
	{
		return assetsInfo;
	}

	public void setAssetsInfo(AssetsInfoBo assetsInfo)
	{
		this.assetsInfo = assetsInfo;
	}
	

}
