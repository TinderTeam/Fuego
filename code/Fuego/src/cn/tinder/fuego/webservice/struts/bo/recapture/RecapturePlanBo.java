package cn.tinder.fuego.webservice.struts.bo.recapture;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;

/**
 * 
* @ClassName: RecapturePlanBo 
* @Description: TODO
* @author Li yong lei
* @date 2013-10-5 下午10:40:32 
*
 */
public class RecapturePlanBo
{
	private RecaptureTransBo transInfo = new RecaptureTransBo();
	private AssetsPageBo assetsPage = new AssetsPageBo();
		public RecaptureTransBo getTransInfo()
		{
			return transInfo;
		}
		public void setTransInfo(RecaptureTransBo transInfo)
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
