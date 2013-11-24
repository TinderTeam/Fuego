package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.RecapturePlan;

public interface RecapturePlanDao
{
	public void create(RecapturePlan plan);

	public void saveOrUpdate(RecapturePlan plan);

	public void delete(RecapturePlan plan);
	
	public void deleteByTransID(String transID);

	// public DiscardPlan find(String transID);
	public List<RecapturePlan> getByTransID(String transID);
	public List<RecapturePlan> getByTransID(List<String> transID);


}
