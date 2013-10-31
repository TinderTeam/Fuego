package cn.tinder.fuego.webservice.struts.bo.discard;
/**
 * 
* @ClassName: DiscardPlanBo 
* @Description: TODO
* @author Li yong lei
* @date 2013-10-4 上午12:15:32 
*
 */

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;

/**
 * 
* @ClassName: DiscardBo 
* @Description: TODO
* @author Li yong lei
* @date 2013-10-4 上午12:02:49 
*
 */

public class DiscardPlanBo
{
	private DiscardTransBo transInfo = new DiscardTransBo();
	private AssetsPageBo assetsPage = new AssetsPageBo();
	public DiscardTransBo getTransInfo()
	{
		return transInfo;
	}
	public void setTransInfo(DiscardTransBo transInfo)
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

