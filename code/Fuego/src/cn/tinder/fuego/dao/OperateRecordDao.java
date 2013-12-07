package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.OperateRecord;

public interface OperateRecordDao
{
	public void create(OperateRecord record);
	
	public void create(List<OperateRecord> recordList);


	public void saveOrUpdate(OperateRecord record);

	public void delete(OperateRecord record);

	// public OperateRecord find(String assetsid);
	public OperateRecord getByAssetsID(String assetsid);
}
