package cn.tinder.fuego.webservice.struts.bo.purchaseplan;

import java.util.List;

/**
 * 
 * @ClassName: PurchasePlanRefBo
 * @Description: Ref. PurchasePlan.jsp(.html)
 * @author Nan Bowen
 * @date 2013-9-24 下午07:37:45
 * 
 */
public class PurchasePlanPageBo
{

	private String departmentName;

	private List<String> assetsTpyeList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PurchasePlanRefBo [departmentName=" + departmentName + ", assetsTpyeList=" + assetsTpyeList + "]";
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * @return the assetsTpyeList
	 */
	public List<String> getAssetsTpyeList()
	{
		return assetsTpyeList;
	}

	/**
	 * @param assetsTpyeList
	 *            the assetsTpyeList to set
	 */
	public void setAssetsTpyeList(List<String> assetsTpyeList)
	{
		this.assetsTpyeList = assetsTpyeList;
	}

}
