package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.AssetsQuota;

public interface AssetsQuotaDao
{

	public void create(AssetsQuota quota);

	public void saveOrUpdate(AssetsQuota quota);

	public void delete(AssetsQuota quota);

	// public AssetsQuota find(String assetsname);
	public AssetsQuota getByAssetsName(String assetsname);
	
	public List<AssetsQuota> getByDuty(String duty);

	public List<AssetsQuota> getAllAssetsQuota();
}
