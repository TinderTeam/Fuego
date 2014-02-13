/**   
* @Title: ReceivePlanDao.java 
* @Package cn.tinder.fuego.dao 
* @Description: 回收事务Dao接口
* @author Zhu Liucao   
* @date 2013-10-22 上午12:42:30 
* @version V1.0   
*/ 
package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.ReceivePlan;

public interface ReceivePlanDao
{
	public void create(ReceivePlan plan);
	
	public void create(List<ReceivePlan> planlist);

	public void saveOrUpdate(ReceivePlan plan);

	public void delete(ReceivePlan plan);
	
	public void deleteByTransID(String transID);

	public List<ReceivePlan> getByTransID(String transid);
	public List<ReceivePlan> getByTransID(List<String> transIDList);

	
}
