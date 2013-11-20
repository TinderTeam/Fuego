/**   
* @Title: TransSumInfoBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.trans 
* @Description: TODO
* @author Tang Jun   
* @date 2013-11-21 上午12:22:56 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.trans;

/** 
 * @ClassName: TransSumInfoBo 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-11-21 上午12:22:56 
 *  
 */

public class TransSumInfoBo
{
	private int transNum;
	private int assetsNum;
	private float assetsValue;
	public int getTransNum()
	{
		return transNum;
	}
	public void setTransNum(int transNum)
	{
		this.transNum = transNum;
	}
	public int getAssetsNum()
	{
		return assetsNum;
	}
	public void setAssetsNum(int assetsNum)
	{
		this.assetsNum = assetsNum;
	}
	public float getAssetsValue()
	{
		return assetsValue;
	}
	public void setAssetsValue(float assetsValue)
	{
		this.assetsValue = assetsValue;
	}
	
	

}
