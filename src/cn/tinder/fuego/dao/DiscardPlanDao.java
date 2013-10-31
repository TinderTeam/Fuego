package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.DiscardPlan;

public interface DiscardPlanDao
{
	public void create(DiscardPlan plan);

	public void saveOrUpdate(DiscardPlan plan);

	public void delete(DiscardPlan plan);
	
	public void deleteByTransID(String transID);

	// public DiscardPlan find(String transid);
	public List<DiscardPlan> getByTransID(String transid);
}
