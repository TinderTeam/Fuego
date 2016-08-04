/**   
 * @Title: AssignPlanBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午12:57:09 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assign;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;

/**
 * @ClassName: AssignPlanBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午12:57:09
 * 
 */

public class AssignPlanBo
{
	private AssignTransBo transInfo = new AssignTransBo();
	private AssetsPageBo assetsPage = new AssetsPageBo();

	public AssignTransBo getTransInfo()
	{
		return transInfo;
	}

	public void setTransInfo(AssignTransBo transInfo)
	{
		this.transInfo = transInfo;
	}

	public AssetsPageBo getAssetsPage()
	{
		return assetsPage;
	}

	public void setAssetsPage(AssetsPageBo assetsPage)
	{
		this.assetsPage = assetsPage;
	}

}
