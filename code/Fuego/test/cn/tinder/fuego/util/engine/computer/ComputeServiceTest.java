package cn.tinder.fuego.util.engine.computer;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import cn.tinder.fuego.util.date.DateService;

public class ComputeServiceTest {

	
	/**
	 * 2014/2/10
	 * By Nan Bowen
	 * 利用年份-1进行测试
	 */
	@Test
	public void testCptUsedYears() {
		Date today = new Date();
		String strToday=DateService.DateToString(today);
		String strThisYear = strToday.substring(0,4);
		String strLastYear = String.valueOf((Integer.valueOf(strThisYear)+1));
		String strLastDay = strToday.replace(strThisYear, strLastYear);
		System.out.println("输入的日期为："+strLastDay);
		String result=String.valueOf(ComputeService.cptUsedYears(strLastDay));
		System.out.println("结果为："+result);
		assertEquals("1.0",result);
	}

	@Ignore
	public void testCptValue() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testCptShowValue() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testCptValueAsList() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testCptShowValueAsList() {
		fail("Not yet implemented");
	}

	/**
	 * 2014/2/10
	 * By Nan Bowen
	 */
	@Test
	public void testCptValueMoney() {
		float f1 = ComputeService.cptValueMoney(500, 2, 3); //未超期
		float f2 = ComputeService.cptValueMoney(500, 4,3);	//超期
		
		DecimalFormat df = new DecimalFormat("0.0");  
		
		System.out.println(f2);
		assertEquals("-83.3",df.format(f1));
		assertEquals("33.3",df.format(f2));
	}

}
