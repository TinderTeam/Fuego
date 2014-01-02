package cn.tinder.fuego.util.engine.jxl;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ExcelReaderTest {
	
	
	@Test
	public void testExcelReaderFile() {
		
		ExcelReader t = new ExcelReader("C:\\Users\\asus\\Desktop\\PurchasePlanModel (2).xls", 2);
		
		
			List a =t.getItem(0);
			System.out.println(a);


	

	}

	@Test
	public void testExcelReaderFileInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcelReaderStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcelReaderString() {
		fail("Not yet implemented");
	}

}
