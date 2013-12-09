package cn.tinder.fuego.dao;

import java.util.Date;
import java.util.List;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.model.DomainFilterModel;

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
	
	public void deleteAssetListsByAssetsIDList(List<String> assetsIDList);

	
	public PhysicalAssetsStatus getByAssetsID(String assetsid);
 
 
	
	public List<PhysicalAssetsStatus> getAssetsByDuty(String dutyDept);
	
	public int getAssetsListByFilterCount(PhysicalAssetsStatus filter, PhysicalAssetsStatus filterDate,DomainFilterModel domainFilter);

 	public List<PhysicalAssetsStatus> getAssetsListByFilter(PhysicalAssetsStatus filter,PhysicalAssetsStatus filterDate,DomainFilterModel domainFilter ,int startNum,int pageSize);
 	
	public List<PhysicalAssetsStatus> getAssetsListByAssetsIDList(List<String> assetsIDList);

  	
 	public List<PhysicalAssetsStatus> getAssetsListByDateOrStatuListAndTypeList(Date dueDate,List<String> techStatusList,List<String> assetsTypeList,String duty,String manageName,DomainFilterModel domainFilter);

}
