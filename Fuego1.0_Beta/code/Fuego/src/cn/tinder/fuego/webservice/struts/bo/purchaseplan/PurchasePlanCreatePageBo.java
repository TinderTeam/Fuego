package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;

/**
 * 
 * @ClassName: PurchasePlanCreateBo
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-25 上午12:49:13
 * 
 */
public class PurchasePlanCreatePageBo
{

	private List<PurchasePlanBo> assetsList;

	@Override
	public String toString()
	{
		return "PurchasePlanCreatePageBo [assetsList=" + assetsList + "]";
	}

	/**
	 * @param assetsList
	 *            the assetsList to set
	 */
	public void setAssetsList(List<PurchasePlanBo> assetsList)
	{
		this.assetsList = assetsList;
	}

	/**
	 * @return the assetsList
	 */
	public List<PurchasePlanBo> getAssetsList()
	{
		return assetsList;
	}

}
