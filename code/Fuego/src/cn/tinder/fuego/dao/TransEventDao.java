package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.TransEvent;

public interface TransEventDao
{
	public void create(TransEvent trans);
	
	public void create(List<TransEvent> transList);

	public void saveOrUpdate(TransEvent trans);

	public void delete(TransEvent trans);

	// public Transaction find(String transid);
	public TransEvent getByTransID(String transid);
	
	public List<TransEvent> getTodoTransByHandlerUser(String userID);
	
	public List<TransEvent> getTransByParentID(String parentTransID);

}
