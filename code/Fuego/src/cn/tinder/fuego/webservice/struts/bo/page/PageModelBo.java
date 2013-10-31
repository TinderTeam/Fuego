/**   
* @Title: PageModel.java 
* @Package cn.tinder.fuego.dao.util 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-20 下午10:29:28 
* @version V1.0   
*/ 
package cn.tinder.fuego.webservice.struts.bo.page;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: PageModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-20 下午10:29:28 
 *  
 */

public class PageModelBo
{
	private List<Integer> pages =new ArrayList<Integer>();
	private int pageSize =100;  //defualt page size
	private int currentPage = 1;
	private int count = 0;
	
	public int getStartNum()
	{
		return (currentPage-1)*pageSize;  
	}
	
	public int getEndNum()
	{
		return  getStartNum()+pageSize;
	}
	public List<Integer> getPages()
	{
		pages.clear();
		int i = 1;
		for(;i<=count/pageSize;i++)
		{
			pages.add(i);
		}
		if(count%pageSize!=0)  
		{
			pages.add(i);
		}
		return pages;
	}
	public void setPages(List<Integer> pages)
	{
		this.pages = pages;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public int getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	
	

}
