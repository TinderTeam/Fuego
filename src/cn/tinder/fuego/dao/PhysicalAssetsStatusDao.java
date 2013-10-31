package cn.tinder.fuego.dao;

import java.util.Date;
import java.util.List;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;

/**
 * 
 * @param dueDate
 * @param assetsTypeList
 * @param dutyList
 * @return
 */
public interface PhysicalAssetsStatusDao
{
	public void create(PhysicalAssetsStatus status);
	
	public void create(List<PhysicalAssetsStatus> assetsList);

	public void saveOrUpdate(PhysicalAssetsStatus status);

	public void delete(PhysicalAssetsStatus status);

	public PhysicalAssetsStatus getByAssetsID(String assetsid);
 
	public List<PhysicalAssetsStatus> getAssetsByDept(String dept);
	
	public List<PhysicalAssetsStatus> getAssetsByDuty(String dutyDept);
	
	public int getAssetsListByFilterCount(PhysicalAssetsStatus filter,PhysicalAssetsStatus filterDate);

 	public List<PhysicalAssetsStatus> getAssetsListByFilter(PhysicalAssetsStatus filter,PhysicalAssetsStatus filterDate,int startNum,int endNum);
 	
	public List<PhysicalAssetsStatus> getAssetsListByAssetsIDList(List<String> assetsIDList);

 	public List<PhysicalAssetsStatus> getAssetsListByFilter(Date dueDate, List<String> assetsTypeList,List<String> dutyList,List<String> statusList);
 	
 	public List<PhysicalAssetsStatus> getAssetsListByDateOrStatuListAndTypeList(Date dueDate,List<String> techStatusList,List<String> assetsTypeList);

	public void deleteAssetListsByAssetsIDList(List<String> assetsIDList);
}
