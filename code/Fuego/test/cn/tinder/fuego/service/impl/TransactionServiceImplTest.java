package cn.tinder.fuego.service.impl;

import org.junit.Test;

import cn.tinder.fuego.service.ServiceContext;
import cn.tinder.fuego.service.TransactionService;
import cn.tinder.fuego.service.constant.TransactionConst;
import cn.tinder.fuego.service.model.convert.ConvertTransactionModel;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

public class TransactionServiceImplTest
{
	TransactionService service = ServiceContext.getInstance().getTransactionService();

	@Test
	public void testCreateTransByUserAndType()
	{
		String userName = "My_name";
		String type = TransactionConst.ASSIGN_PLAN_TYPE;
		System.out.println(service.createTransByUserAndType(userName,userName, type , null));
	}

	@Test
	public void testUpdateTrans()
	{
		TransactionBaseInfoBo transEvent = new TransactionBaseInfoBo();
		String transID = "A000000003";
		String handleUser = "you name";
		transEvent = service.getTransByID("A000000003");
		transEvent.setHandleUser("you_name");

		service.updateTrans(transID,handleUser);

	}

	@Test
	public void testGetTransByID()
	{
		System.out.println(service.getTransByID("A000000003"));

	}

	@Test
	public void testDeleteTransByID()
	{
		service.deleteTransByID("A000000004");
	}

}
