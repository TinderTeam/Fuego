/**   
* @Title: DomainFilterModel.java 
* @Package cn.tinder.fuego.service.model 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-4 下午05:48:18 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.model;

import java.util.List;

/** 
 * @ClassName: DomainFilterModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-4 下午05:48:18 
 *  
 */

public class DomainFilterModel
{
	private List<String> dutyList;
	private List<String> manageList;
	private List<String> assetsTypeList;
	public List<String> getDutyList()
	{
		return dutyList;
	}
	public void setDutyList(List<String> dutyList)
	{
		this.dutyList = dutyList;
	}
	public List<String> getManageList()
	{
		return manageList;
	}
	public void setManageList(List<String> manageList)
	{
		this.manageList = manageList;
	}
	public List<String> getAssetsTypeList()
	{
		return assetsTypeList;
	}
	public void setAssetsTypeList(List<String> assetsTypeList)
	{
		this.assetsTypeList = assetsTypeList;
	}
	
	

}
