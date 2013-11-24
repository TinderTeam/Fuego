package cn.tinder.fuego.webservice.struts.bo.recapture;

import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/**
 * 
* @ClassName: RecaptureTransBo 
* @Description: TODO
* @author Li yong lei
* @date 2013-10-5 下午10:47:54 
*
 */
public class RecaptureTransBo
{
	private TransactionBaseInfoBo transInfo;
	private String location;


	public TransactionBaseInfoBo getTransInfo()
	{
		return transInfo;
	}

	public void setTransInfo(TransactionBaseInfoBo transInfo)
	{
		this.transInfo = transInfo;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
	

}
