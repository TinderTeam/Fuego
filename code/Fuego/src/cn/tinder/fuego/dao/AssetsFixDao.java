package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.AssetsFix;

public interface AssetsFixDao {
	public void create(AssetsFix fix);

	public void saveOrUpdate(AssetsFix fix);

	public void delete(AssetsFix fix);

	public List<AssetsFix> getAllAssetsFix();
}
