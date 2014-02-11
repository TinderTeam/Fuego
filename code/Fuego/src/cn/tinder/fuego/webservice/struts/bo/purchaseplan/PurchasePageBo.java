package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanBo;
import cn.tinder.fuego.webservice.struts.bo.page.PageModelBo;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanCreateForm;

public class PurchasePageBo
{
	Log log = LogFactory.getLog(PurchasePageBo.class);
	private List<PurchasePlanBo> assetsList = new ArrayList<PurchasePlanBo>();

	private PageModelBo<PurchasePlanBo> page = new PageModelBo<PurchasePlanBo>();

	/*
	 * 
	 * 通过选择的序号筛选采购表条目
	 */
	public void addData(List<PurchasePlanBo> planList)
	{
		List<PurchasePlanBo> dataList = page.getAllPageData();
		
		for(PurchasePlanBo plan : planList)
		{
			if(null == getByIndexFromData(dataList,plan.getIndex()))
			{
				dataList.add(plan);
			}
		}
		page.setAllPageData(dataList);
		assetsList = page.getCurrentPageData();
	}
 
	public void setDataSource(List<PurchasePlanBo> planList)
	{
		sort(planList);
		page.setAllPageData(planList);
	}
	public void selectItemsByStringArray(String[] indexAry)
	{

		List<PurchasePlanBo> newList = new ArrayList<PurchasePlanBo>();

		if (null == indexAry)
		{
			return;
		}
		int i = 1;
		for (String index : indexAry)
		{
			int num = Integer.valueOf(index);
			for (PurchasePlanBo ob : page.getCurrentPageData())
			{
				if (ob.getIndex() == num)
				{
					ob.setIndex(i);
					newList.add(ob);
					i++;
				}
			}
		}
		assetsList.addAll(newList);
	}

	public PurchasePlanBo getByIndexFromData(List<PurchasePlanBo> planList,int index)
	{
		for (PurchasePlanBo b : planList)
		{
			if(b.getIndex() == index)
			{
				return b;
			}
		}
		return null;
	}
	public PurchasePlanBo find(PurchasePlanBo bo)
	{

		for (PurchasePlanBo b : assetsList)
		{
			if (b.equals(bo))
			{
				return b;
			} else
			{
			}
		}
		return null;

	}

	/*
	 * 重构list 的index 序号
	 */
	private void sort()
	{
		sort(assetsList);
 
	}
	private void sort(List<PurchasePlanBo> planList)
	{
		if (planList != null)
		{
			for (int i = 0; i < planList.size(); i++)
			{
				planList.get(i).setIndex(i + 1);
			}
		}
	}
	public List<PurchasePlanBo> getAssetsList()
	{
		sort();
		return assetsList;
	}

	public void setAssetsList(List<PurchasePlanBo> assetsList)
	{
		this.assetsList = assetsList;
	}

	public PageModelBo<PurchasePlanBo> getPage()
	{
		return page;
	}

	public void setPage(PageModelBo<PurchasePlanBo> page)
	{
		this.page = page;
	}

	@Override
	public String toString()
	{
		return "PurchasePageBo [assetsList=" + assetsList + "]";
	}

	/**
	 * 根据Form的结果更新列表
	 * 
	 * @param purchasePlanCreateForm
	 */
	public void updateByForm(PurchasePlanCreateForm purchasePlanCreateForm)
	{

		log.info("==FORM size" + purchasePlanCreateForm.getAssetsCreateBo().size());
		log.info("==assetsList" + assetsList.size());

		if (assetsList.size() == purchasePlanCreateForm.getAssetsCreateBo().size())
		{
			for (int i = 0; i < assetsList.size(); i++)
			{
				PurchasePlanBo changeBo = (PurchasePlanBo) purchasePlanCreateForm.getAssetsCreateBo().get(i);
				log.info("更新人工调整信息 : " + assetsList.get(i).getAssetsBo().getAssetsName());
				log.info("Manufacture:" + assetsList.get(i).getAssetsBo().getManufacture() + "->" + changeBo.getAssetsBo().getManufacture());

				assetsList.get(i).getAssetsBo().setManufacture(changeBo.getAssetsBo().getManufacture());
				assetsList.get(i).getAssetsBo().setSpec(changeBo.getAssetsBo().getSpec());
				assetsList.get(i).getAssetsBo().setUnit(changeBo.getAssetsBo().getUnit());
				assetsList.get(i).getAssetsBo().setQuantity(changeBo.getAssetsBo().getQuantity());
				assetsList.get(i).setPrice(changeBo.getPrice());
				assetsList.get(i).setMoney(String.valueOf(Float.valueOf(changeBo.getPrice()) * (changeBo.getAssetsBo().getQuantity())));
				assetsList.get(i).getAssetsBo().setNote(changeBo.getAssetsBo().getNote());

			}

			log.info("==FORM" + purchasePlanCreateForm.getAssetsCreateBo());
			log.info("==assetsList" + assetsList);

		}
	}
}
