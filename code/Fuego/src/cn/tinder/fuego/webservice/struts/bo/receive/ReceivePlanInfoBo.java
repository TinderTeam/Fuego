/**   
* @Title: ReceivePlanInfoBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.receive 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-22 上午12:14:19 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.receive;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;

/** 
 * @ClassName: ReceivePlanInfoBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-22 上午12:14:19 
 *  
 */

public class ReceivePlanInfoBo
{
	private AssetsPageBo assetsPage = new AssetsPageBo();

	public AssetsPageBo getAssetsPage()
	{
		return assetsPage;
	}

	public void setAssetsPage(AssetsPageBo assetsPage)
	{
		this.assetsPage = assetsPage;
	}
	
	
}
