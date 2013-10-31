/**
 * 
 */
package cn.tinder.fuego.dao;

import cn.tinder.fuego.domain.po.AssetsType;

/**
 * @author zhuliucao
 * 
 */
public interface AssetsTypeDao
{
	public void create(AssetsType type);

	public void saveOrUpdate(AssetsType type);

	public void delete(AssetsType type);

	// public AssetsType find(String typeid);
	public AssetsType getByTypeName(String typeName);
}
