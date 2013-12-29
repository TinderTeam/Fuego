package cn.tinder.fuego.service.model.convert;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.domain.po.TransEvent;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

public class ConvertTransactionModel
{
	
	public static TransactionBaseInfoBo covertTransBase(TransEvent transEvent)
	{
 		TransactionBaseInfoBo transactionBaseInfoBo = new TransactionBaseInfoBo();
		transactionBaseInfoBo.setTransID(transEvent.getTransID());
		transactionBaseInfoBo.setTransName(transEvent.getTransName());
		transactionBaseInfoBo.setCreateTime(transEvent.getCreateTime());
		transactionBaseInfoBo.setCreateUser(transEvent.getCreateUser());
		transactionBaseInfoBo.setEndTime(transEvent.getEndTime());
		transactionBaseInfoBo.setHandleUser(transEvent.getHandleUser());
		transactionBaseInfoBo.setExpendTime(DateService.countDayNum(DateService.getCurrentDate(), transEvent.getHandleTime()));
		transactionBaseInfoBo.setState(transEvent.getStatus());
		 
		transactionBaseInfoBo.setUrl(getUrlByTransType(transEvent.getType(),transEvent.getTransID(),transEvent.getCurrentStep()));
		

		return transactionBaseInfoBo;
	}
	
	public static List<TransactionBaseInfoBo> covertTransBaseList(List<TransEvent> transEventList)
	{
		List<TransactionBaseInfoBo> baseInfoList = new ArrayList<TransactionBaseInfoBo>();
		for(TransEvent transEvent : transEventList )
		{	
			TransactionBaseInfoBo baseInfo = new TransactionBaseInfoBo();

			baseInfo = covertTransBase(transEvent);
		 
			baseInfoList.add(baseInfo);
		}


		return baseInfoList;
	}
	
	// get transName by transType
	public static String getTransNameByTransType(String transType)
	{
		String transName = "";
		if (TransactionConst.ASSIGN_PLAN_TYPE.equals(transType))
		{
			transName = TransactionConst.ASSIGN_PLAN_NAME;
		}
		else if(TransactionConst.CHECK_PLAN_TYPE.equals(transType))
		{
			transName = TransactionConst.CHECK_PLAN_NAME;
		}
		else if(TransactionConst.DISCARD_PLAN_TYPE.equals(transType))
		{
			transName = TransactionConst.DISCARD_PLAN_NAME;
		}
		else if(TransactionConst.PURCHASE_PLAN_TYPE.equals(transType))
		{
			transName = TransactionConst.PURCHASE_PLAN_NAME;
		}
		else if(TransactionConst.RECAPTURE_PLAN_TYPE.equals(transType))
		{
			transName = TransactionConst.RECAPTURE_PLAN_NAME;
		}
		else if(TransactionConst.RECEIVE_PLAN_TYPE.equals(transType))
		{
			transName = TransactionConst.RECEIVE_PLAN_NAME;
		}
		
		
		return transName;
	}
	
	
	
	public static  String getUrlByTransType(String transType,String transID,int step)
	{
		String url = "";
		if (TransactionConst.ASSIGN_PLAN_TYPE.equals(transType))
		{
			url = TransactionConst.ASSIGN_PLAN_URL;
		}
		else if(TransactionConst.CHECK_PLAN_TYPE.equals(transType))
		{
			url = TransactionConst.CHECK_PLAN_URL;
		}
		else if(TransactionConst.DISCARD_PLAN_TYPE.equals(transType))
		{
			url = TransactionConst.DISCARD_PLAN_URL;
		}
		else if(TransactionConst.PURCHASE_PLAN_TYPE.equals(transType))
		{
			url = TransactionConst.PURCHASE_PLAN_URL;
		}
		else if(TransactionConst.RECAPTURE_PLAN_TYPE.equals(transType))
		{
			url = TransactionConst.RECAPTURE_PLAN_URL;
		}
		else if(TransactionConst.RECEIVE_PLAN_TYPE.equals(transType))
		{
			url = TransactionConst.RECEIVE_PLAN_URL;

		}
		url += "?transID="+transID+"&step="+step;
		return url;
	}

}
