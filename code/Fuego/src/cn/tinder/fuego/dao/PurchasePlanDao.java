package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.PurchasePlan;

public interface PurchasePlanDao
{

	public void create(PurchasePlan plan);

	public void saveOrUpdate(PurchasePlan plan);

	public void delete(PurchasePlan plan);

	// public PurchasePlan find(String transid);
	public List<PurchasePlan> getByTransID(String transid);
	
	public List<PurchasePlan> getByTransID(List<String> transIDList);

	
	public void deleteByTransID(String transID);
}
