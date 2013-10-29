/**   
 * @Title: TransactionService.java 
 * @Package cn.tinder.fuego.service 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-28 上午01:42:54 
 * @version V1.0   
 */
package cn.tinder.fuego.service;

import java.util.Date;
import java.util.List;

import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/**
 * 
 * @ClassName: TransactionService
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-28 上午01:44:25
 * 
 */

public interface TransactionService
{
	public TransactionBaseInfoBo createTransByUserAndType(String createUser, String handleUser,String transType, String parentTransID);

	public TransactionBaseInfoBo getTransByID(String transID);

	public void deleteTransByID(String transID);

	public void updateTrans(String transID, String handleUser);

	public void forwardNext(String transID, String userID);
	
	public void backward(String transID);
	
	public List<TransactionBaseInfoBo> getTransListByUser(String userID);
	
	public void deletePlanByTransID(String user,String transID);

}
