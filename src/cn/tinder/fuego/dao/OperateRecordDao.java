package cn.tinder.fuego.dao;

import cn.tinder.fuego.domain.po.OperateRecord;

public interface OperateRecordDao
{
	public void create(OperateRecord record);

	public void saveOrUpdate(OperateRecord record);

	public void delete(OperateRecord record);

	// public OperateRecord find(String assetsid);
	public OperateRecord getByAssetsID(String assetsid);
}
