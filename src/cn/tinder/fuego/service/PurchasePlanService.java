package cn.tinder.fuego.service;

import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanEnsureBo;
import cn.tinder.fuego.webservice.struts.bo.base.PurchasePlanRefBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanCreatePageBo;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanEnsurePageBo;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanCreateForm;
import cn.tinder.fuego.webservice.struts.form.purchase.RefPlanCreateForm;

/**
 * 
 * @ClassName: PurchasePlanService
 * @Description: 其中更新了多态实现getPurchasePlanByForm
 * @author Nan Bowen
 * @date 2013-9-30 1:39
 * 
 */
public interface PurchasePlanService
{
	/**
	 * Search Assets By Type and compare the data with the limit time, then output the list of assets for next page.
	 * 
	 * @param form
	 *            consist of the condition for Selecting the Assets Type.
	 */
	

	public PurchasePlanCreatePageBo getEmptyPlan();
	

	

	/**
	 * 
	 * @param refPlanCreateForm
	 * @param refBo
	 * @return
	 */
	public PurchasePlanCreatePageBo getPurchasePlanByForm(RefPlanCreateForm refPlanCreateForm, List<PurchasePlanRefBo> refBo);

	/**
	 * 
	 * @param purchasePlanCreateForm
	 * @return
	 */
	public PurchasePlanCreatePageBo getPurchasePlanByForm(PurchasePlanCreateForm purchasePlanCreateForm);

	public PurchasePlanEnsurePageBo getEnsureBo(PurchasePlanCreatePageBo pagebo);

	public PurchasePlanCreatePageBo getPurchasePlanCreatePageBoByList(List<PurchasePlanEnsureBo> assetsList);

}
