package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;

/**
 * 
 * @ClassName: PurchasePlanRefBo
 * @Description: Ref. PurchasePlan.jsp(.html)
 * @author Nan Bowen
 * @date 2013-9-24 下午07:37:45
 * 
 */
public class PurchasePlanSessionBo
{

	private static final Log log = LogFactory.getLog(PurchasePlanSessionBo.class);

	private PurchasePageBo purchasePageBo = new PurchasePageBo();
	private PurchasePageBo selectPageBo = new PurchasePageBo();
	private PurchasePageBo purchaseAddPageBo = new PurchasePageBo();
	private PurchaseTransBo purchaseTransBo = new PurchaseTransBo();

	/*
	 * 将增选条目，加入到已选条目表中
	 */
	public void updateAdd()
	{

		for (PurchasePlanBo add : purchaseAddPageBo.getAssetsList())
		{
			PurchasePlanBo nowData = purchasePageBo.getPage().getFromAllData(add);

			if (null != nowData)
			{
				nowData.getAssetsBo().setQuantity(nowData.getAssetsBo().getQuantity() + 1);
			} else
			{
				add.setIndex(purchasePageBo.getPage().getAllPageData().size() + 1);
				purchasePageBo.getAssetsList().add(add);

			}

		}

		log.info("ADD:" + purchaseAddPageBo);

		log.info("EXIST:" + purchasePageBo);

	}

	/*
	 * 自动生成方法
	 */

	public PurchasePageBo getSelectPageBo()
	{
		return selectPageBo;
	}

	public void setSelectPageBo(PurchasePageBo selectPageBo)
	{
		this.selectPageBo = selectPageBo;
	}

	@Override
	public String toString()
	{
		return "PurchasePlanSessionBo [purchasePageBo=" + purchasePageBo + ", purchaseAddPageBo=" + purchaseAddPageBo + ", purchaseTransBo=" + purchaseTransBo + "]";
	}

	public void setPurchaseTransBo(PurchaseTransBo purchaseTransBo)
	{
		this.purchaseTransBo = purchaseTransBo;
	}

	public PurchaseTransBo getPurchaseTransBo()
	{
		return purchaseTransBo;
	}

	public PurchasePageBo getPurchasePageBo()
	{
		return purchasePageBo;
	}

	public void setPurchasePageBo(PurchasePageBo purchasePageBo)
	{
		this.purchasePageBo = purchasePageBo;
	}

	public void setPurchaseAddPageBo(PurchasePageBo purchaseAddPageBo)
	{
		this.purchaseAddPageBo = purchaseAddPageBo;
	}

	public PurchasePageBo getPurchaseAddPageBo()
	{
		return purchaseAddPageBo;
	}

}
