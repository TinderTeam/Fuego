package cn.tinder.fuego.dao;

import java.util.List;

import cn.tinder.fuego.domain.po.AssignPlan;

public interface AssignPlanDao
{
	public void create(AssignPlan plan);

	public void saveOrUpdate(AssignPlan plan);

	public void delete(AssignPlan plan);
	
	public void deleteByTransID(String transID);

	public AssignPlan getByPriKey(String transID, String assetsID);

	public List<AssignPlan> getByTransID(String transID);
	
	public List<AssignPlan> getByTransID(List<String> transIDList);

}
