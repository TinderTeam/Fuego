package cn.tinder.fuego.dao;

import cn.tinder.fuego.domain.po.AssetsQuota;

public interface AssetsQuotaDao
{

	public void create(AssetsQuota quota);

	public void saveOrUpdate(AssetsQuota quota);

	public void delete(AssetsQuota quota);

	// public AssetsQuota find(String assetsname);
	public AssetsQuota getByAssetsName(String assetsname);
}
