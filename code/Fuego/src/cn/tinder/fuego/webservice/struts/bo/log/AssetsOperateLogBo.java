/**   
* @Title: AssetsOperateLogBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.log 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-10 上午01:51:53 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.log;

import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;

/** 
 * @ClassName: AssetsOperateLogBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-10 上午01:51:53 
 *  
 */

public class AssetsOperateLogBo
{
	private String userName ;
	private String operTime ;
	private String operName ;
	private String transID;
	private AssetsBo assets = new AssetsBo();
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getOperTime()
	{
		return operTime;
	}
	public void setOperTime(String operTime)
	{
		this.operTime = operTime;
	}
	public String getOperName()
	{
		return operName;
	}
	public void setOperName(String operName)
	{
		this.operName = operName;
	}
	public String getTransID()
	{
		return transID;
	}
	public void setTransID(String transID)
	{
		this.transID = transID;
	}
	public AssetsBo getAssets()
	{
		return assets;
	}
	public void setAssets(AssetsBo assets)
	{
		this.assets = assets;
	}
	
	

}
