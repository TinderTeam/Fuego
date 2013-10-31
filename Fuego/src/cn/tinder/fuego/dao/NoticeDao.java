package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.Notice;

public interface NoticeDao
{
	/**
	 * Create SystemUser
	 * 
	 * @param SystemUser
	 */
	public void create(Notice notice);

	/**
	 * saveOrUpdate SystemUser
	 * 
	 * @param SystemUser
	 */
	public void saveOrUpdate(Notice notice);

	/**
	 * delete SystemUser
	 * 
	 * @param SystemUser
	 */
	public void delete(Notice notice);

	/**
	 * update SystemUser
	 * 
	 * @param SystemUser
	 */

	public Notice find(String transid);

	public List<Notice> findByUser(String username);

}
