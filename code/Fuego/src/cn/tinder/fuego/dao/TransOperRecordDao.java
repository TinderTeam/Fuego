/**   
* @Title: TransOperRecordDao.java 
* @Package cn.tinder.fuego.dao 
* @Description: TODO
* @author Zhu Liucao   
* @date 2013-12-26 上午12:56:08 
* @version V1.0   
*/ 
package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.TransOperRecord;

/** 
 * @ClassName: TransOperRecordDao 
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-12-26 上午12:56:08 
 *  
 */
public interface TransOperRecordDao
{
	public void create(TransOperRecord record);

	public void saveOrUpdate(TransOperRecord record);

	public void delete(TransOperRecord record);

	// public OperateRecord find(String assetsid);
	public List<TransOperRecord> getByTransID(String transID);

}
