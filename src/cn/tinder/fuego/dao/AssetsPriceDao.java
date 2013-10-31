package cn.tinder.fuego.dao;

import cn.tinder.fuego.domain.po.AssetsPrice;

public interface AssetsPriceDao
{

	public void create(AssetsPrice price);

	public void saveOrUpdate(AssetsPrice price);

	public void delete(AssetsPrice price);

	/**
	 * @param spec
	 * @return
	 */
	public AssetsPrice getBySpec(String spec);

}
