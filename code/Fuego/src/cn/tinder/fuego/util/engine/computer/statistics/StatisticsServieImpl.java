package cn.tinder.fuego.util.engine.computer.statistics;

import java.util.Date;
import java.util.List;

import cn.tinder.fuego.dao.DiscardPlanDao;
import cn.tinder.fuego.dao.PhysicalAssetsStatusDao;
import cn.tinder.fuego.dao.ReceivePlanDao;
import cn.tinder.fuego.dao.TransEventDao;
import cn.tinder.fuego.dao.impl.DiscardPlanDaoImpl;
import cn.tinder.fuego.dao.impl.PhysicalAssetsStatusDaoImpl;
import cn.tinder.fuego.dao.impl.ReceivePlanDaoImpl;
import cn.tinder.fuego.dao.impl.TransEventDaoImpl;
import cn.tinder.fuego.domain.po.DiscardPlan;
import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.domain.po.ReceivePlan;
import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.constant.TransactionConst;

public class StatisticsServieImpl implements StatisticsServie {
 
	TransEventDao transEventDao = new TransEventDaoImpl();
	PhysicalAssetsStatusDao assetsDao = new PhysicalAssetsStatusDaoImpl();
	
	@Override
	public String receiveAssetsStatistics(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		ReceivePlanDao receivePlanDao= new ReceivePlanDaoImpl();
		List<TransEvent> transEventList = getTransIDByType(startDate, endDate,TransactionConst.RECEIVE_PLAN_TYPE);
		for (TransEvent event:transEventList){
			List<ReceivePlan> receivePlanList = receivePlanDao.getByTransID(event.getTransID());
			for (ReceivePlan plan:receivePlanList){
				PhysicalAssetsStatus assets = assetsDao.getByAssetsID(plan.getAssetsID());
			}
		}
		return null;
	}

	@Override
	public String discardAssetsStatistics(Date startDate, Date endDate) {

		int assetsNum=0;
		int exceedNum=0;
		int unexceedNum=0;
		
		int discardIncome=0;
		
		
		DiscardPlanDao discardPlanDao= new DiscardPlanDaoImpl();
		List<TransEvent> transEventList = getTransIDByType(startDate, endDate,TransactionConst.DISCARD_PLAN_TYPE);
		
		/*
		 * 迭代处置事件的计划
		 */
		for (TransEvent event:transEventList){
	
		}
		return null;
	}



	@Override
	public String assignAssetsStatistics(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获取TransID方法
	 * 
	 * 通过事务类型和截止时间范围寻找，统计目标事务
	 * @param startDate
	 * @param endDate
	 * @param PlanType
	 * @return
	 */
	private List<TransEvent> getTransIDByType(Date startDate, Date endDate, String PlanType) {
		TransEvent  filter1 = new TransEvent();
		TransEvent  filter2 = new TransEvent();
		//
		filter1.setEndTime(startDate);
		filter1.setType(PlanType);
		filter1.setStatus(TransactionConst.TRANS_STATUS_DONE);
		//
		filter2.setEndTime(endDate);
		List<TransEvent>  transEventList= transEventDao.getTransByFilter(filter1, filter2);
		return transEventList;
	}
}
