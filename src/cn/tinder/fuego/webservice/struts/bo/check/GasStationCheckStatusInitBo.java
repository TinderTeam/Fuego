/**   
* @Title: GasStationCheckInitBo.java 
* @Package cn.tinder.fuego.webservice.struts.bo.check 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-10-4 上午09:37:57 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: GasStationCheckInitBo 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-4 上午09:37:57 
 *  
 */
public class GasStationCheckStatusInitBo
{
	private String title;
	private List<GasStationCheckStatusBo> checklist=new ArrayList<GasStationCheckStatusBo>();
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	/**
	 * @return the checklist
	 */
	public List<GasStationCheckStatusBo> getChecklist()
	{
		return checklist;
	}
	/**
	 * @param checklist the checklist to set
	 */
	public void setChecklist(List<GasStationCheckStatusBo> checklist)
	{
		this.checklist = checklist;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "GasStationCheckStatusInitBo [checklist=" + checklist + ", title="
				+ title + "]";
	}
	

}
