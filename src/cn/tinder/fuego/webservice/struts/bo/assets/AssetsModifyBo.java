/**   
 * @Title: AssetsInfoBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.assets 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-2 下午07:07:26 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assets;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/**
 * @ClassName: AssetsInfoBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-2 下午07:07:26
 * 
 */

public class AssetsModifyBo
{
	AssetsBo assets = new AssetsBo();
	AssetsExtAttrBo extAttr = new AssetsExtAttrBo();

	public AssetsBo getAssets()
	{
		return assets;
	}

	public void setAssets(AssetsBo assets)
	{
		this.assets = assets;
	}

	public AssetsExtAttrBo getExtAttr()
	{
		return extAttr;
	}

	public void setExtAttr(AssetsExtAttrBo extAttr)
	{
		this.extAttr = extAttr;
	}

	@Override
	public String toString()
	{
		return "AssetsInfoBo [assets=" + assets + ", extAttr=" + extAttr + "]";
	}

}
