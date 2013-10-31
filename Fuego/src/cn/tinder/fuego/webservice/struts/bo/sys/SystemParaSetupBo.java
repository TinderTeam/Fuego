/**   
* @Title: SystemParaSetupBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.sys 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-5 上午02:42:32 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.sys;

import java.util.List;

/** 
 * @ClassName: SystemParaSetupBo 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-5 上午02:42:32 
 *  
 */
public class SystemParaSetupBo
{
	private List<String> deptList1;
	private List<String> gasList;
	private List<String> deptList2;
	private String orignDept=null;
	private String currentGas=null;
	/**
	 * @return the deptList1
	 */
	public List<String> getDeptList1()
	{
		return deptList1;
	}
	/**
	 * @param deptList1 the deptList1 to set
	 */
	public void setDeptList1(List<String> deptList1)
	{
		this.deptList1 = deptList1;
	}
	/**
	 * @return the gasList
	 */
	public List<String> getGasList()
	{
		return gasList;
	}
	/**
	 * @param gasList the gasList to set
	 */
	public void setGasList(List<String> gasList)
	{
		this.gasList = gasList;
	}
	/**
	 * @return the deptList2
	 */
	public List<String> getDeptList2()
	{
		return deptList2;
	}
	/**
	 * @param deptList2 the deptList2 to set
	 */
	public void setDeptList2(List<String> deptList2)
	{
		this.deptList2 = deptList2;
	}
	/**
	 * @param orginDept the orginDept to set
	 */
	public void setOrignDept(String orignDept)
	{
		this.orignDept = orignDept;
	}
	/**
	 * @return the orginDept
	 */
	public String getOrignDept()
	{
		return orignDept;
	}
	/**
	 * @param currentGas the currentGas to set
	 */
	public void setCurrentGas(String currentGas)
	{
		this.currentGas = currentGas;
	}
	/**
	 * @return the currentGas
	 */
	public String getCurrentGas()
	{
		return currentGas;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemParaSetupBo [currentGas=" + currentGas + ", deptList1="
				+ deptList1 + ", deptList2=" + deptList2 + ", gasList="
				+ gasList + ", orignDept=" + orignDept + "]";
	}



	

}
