/**   
* @Title: IDCreateService.java 
* @Package cn.tinder.fuego.service 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-12 上午12:06:40 
* @version V1.0   
*/ 
package cn.tinder.fuego.service;

import java.util.List;

/** 
 * @ClassName: IDCreateService 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-12 上午12:06:40 
 *  
 */

public interface IDCreateService
{
	public List<String> createIDList(String type,int idCount);
 
}
