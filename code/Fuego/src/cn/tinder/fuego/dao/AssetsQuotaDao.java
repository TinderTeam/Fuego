package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.AssetsQuota;

public interface AssetsQuotaDao
{

	public void create(List<AssetsQuota> quotaList);
	
	public void create(AssetsQuota quotaList);

	public void saveOrUpdate(AssetsQuota quota);

	public void delete(AssetsQuota quota);

	// public AssetsQuota find(String assetsname);
	public List<AssetsQuota> getByFilter(AssetsQuota filter);
	
	public List<AssetsQuota> getByDuty(String duty);

	public List<AssetsQuota> getAllAssetsQuota();
}
