package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.CheckPlan;

public interface CheckPlanDao
{
	public void create(CheckPlan plan);
	public void create(List<CheckPlan> planlist);
	public void saveOrUpdate(CheckPlan plan);

	public void delete(CheckPlan plan);
	public void deleteByTransID(String transID);
	// public CheckPlan find(String transid);
	// public CheckPlan getByTransID(String transid);
	public List<CheckPlan> getByTransID(String transid);
	
	public List<CheckPlan> getByTransIDList(List<String> transID);
}
