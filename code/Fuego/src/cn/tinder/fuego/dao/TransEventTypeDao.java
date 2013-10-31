package cn.tinder.fuego.dao;

import cn.tinder.fuego.domain.po.TransEventType;

public interface TransEventTypeDao
{
	public void create(TransEventType transtype);

	public void saveOrUpdate(TransEventType transtype);

	public void delete(TransEventType transtype);

	public TransEventType getByType(String type);
}
