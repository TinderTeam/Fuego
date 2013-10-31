package cn.tinder.fuego.domain.po;

import java.sql.Date;

import org.junit.Test;

public class TransactionTest
{

	@SuppressWarnings("deprecation")
	@Test
	public void toStringTest()
	{

		TransEvent A = new TransEvent();
		A.setTransID("ff");
		A.setTransName("nan");
		Date nowDate = new Date(System.currentTimeMillis());
		A.setCreateTime(nowDate);
		A.setCreateUser("joe");
		Date laterDate = new Date(1);
		laterDate.setDate(30);
		System.out.println(laterDate);

		A.setEndTime(laterDate);
		A.setHandleUser("yhu");
		A.setType("报废");
		A.setType("B类");
		System.out.println(A.toString());

	}

}
