package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanEnsureBo;

/**
 * 
 * @ClassName: PurchasePlanCreateBo
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-25 上午12:49:13
 * 
 */
public class PurchasePlanEnsurePageBo
{

	private List<PurchasePlanEnsureBo> assetsList;

	@Override
	public String toString()
	{
		return "PurchasePlanEnsurePageBo [assetsList=" + assetsList + "]";
	}

	public List<PurchasePlanEnsureBo> getAssetsList()
	{

		return assetsList;
	}

	public void setAssetsList(List<PurchasePlanEnsureBo> assetsList)
	{
		this.assetsList = assetsList;
	}

}
