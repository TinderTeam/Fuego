/**   
 * @Title: TransExtAttrDao.java 
 * @Package cn.tinder.fuego.dao 
 * @Description: TODO
 * @author Zhu Liucao   
 * @date 2013-10-2 上午02:01:53 
 * @version V1.0   
 */
package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.TransExtAttr;

/**
 * @ClassName: TransExtAttrDao
 * @Description: TODO
 * @author Zhu Liucao
 * @date 2013-10-2 上午02:01:53
 * 
 */
public interface TransExtAttrDao
{
	public void create(TransExtAttr transattr);

	public void saveOrUpdate(TransExtAttr transattr);

	public void delete(TransExtAttr transattr);

	// public TransactionType find(String type);
	public List<TransExtAttr> getByTransID(String transid);

}
