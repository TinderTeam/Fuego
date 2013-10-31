/**   
 * @Title: AssetsInfoBo.java 
 * @Package cn.tinder.fuego.webservice.struts.bo.assets 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-10-2 下午07:07:26 
 * @version V1.0   
 */
package cn.tinder.fuego.webservice.struts.bo.assets;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.service.exception.ServiceException;
import cn.tinder.fuego.service.exception.msg.ExceptionMsg;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/**
 * @ClassName: AssetsInfoBo
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-2 下午07:07:26
 * 
 */

public class AssetsInfoBo extends ActionForm
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

	public void inportTest(){
		if(	emptyTest(assets.getAssetsName())){
			//Assets id is null
			throw new ServiceException(ExceptionMsg.ASSETS_NAME_EMPTY);
		}else if(emptyTest(assets.getUnit())){
			//Unit id is null
			throw new ServiceException(ExceptionMsg.UNIT_EMPTY);
		}else if(emptyTest(assets.getPurchaseDate())){
			//Date is null
			throw new ServiceException(ExceptionMsg.PURCHASEDATE_ERR);
		}
	}
	
	private boolean emptyTest(String str){
		if(str==null||str.equals("")){
			return true;
		}
		return false;
	}
}
