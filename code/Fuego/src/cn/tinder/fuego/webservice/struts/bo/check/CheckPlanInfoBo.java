/**   
* @Title: CheckPlanInfoBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.check 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-5 下午02:29:57 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.check;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/** 
 * @ClassName: CheckPlanInfoBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-5 下午02:29:57 
 *  
 */

public class CheckPlanInfoBo
{
 
	private AssetsBo assets = new AssetsBo();

 
	private int checkCnt;
	private String checkState;
	public AssetsBo getAssets()
	{
		return assets;
	}
	public void setAssets(AssetsBo assets)
	{
		this.assets = assets;
	}
 
	public int getCheckCnt()
	{
		return checkCnt;
	}
	public void setCheckCnt(int checkCnt)
	{
		this.checkCnt = checkCnt;
	}
	public String getCheckState()
	{
		return checkState;
	}
	public void setCheckState(String checkState)
	{
		this.checkState = checkState;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assets == null) ? 0 : assets.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckPlanInfoBo other = (CheckPlanInfoBo) obj;
		if (assets == null)
		{
			if (other.assets != null)
				return false;
		} else if (!assets.equals(other.assets))
			return false;
		return true;
	}
	
	

}
