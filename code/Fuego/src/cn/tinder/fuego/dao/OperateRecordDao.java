package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.OperateRecord;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.model.DomainFilterModel;

public interface OperateRecordDao
{
	public void create(OperateRecord record);
	
	public void create(List<OperateRecord> recordList);


	public void saveOrUpdate(OperateRecord record);

	public void delete(OperateRecord record);

	// public OperateRecord find(String assetsid);
	public OperateRecord getByAssetsID(String assetsid);
	
	public List<OperateRecord>  getByTransID(String transID);
	public List<OperateRecord>  getByTransID(List<String> transIDList);


	
	public int getAssetsOperateLogByFilterCount(OperateRecord filter, OperateRecord filterDate);

 	public List<OperateRecord> getAssetsOperateLogByFilter(OperateRecord filter, OperateRecord filterDate,int startNum,int pageSize);
 	
}
