package cn.tinder.fuego.util.engine;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.tinder.fuego.util.ConfigInformation;
import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.util.engine.computer.ComputeService;

public class ComputeServiceTestTest {
	  private static final Log log = LogFactory.getLog(ComputeServiceTestTest.class);
	

	@Test
	public void testTestCptValuePositive() {
		long oneYear =DateService.stringToDate("2013-01-01").getTime()-DateService.stringToDate("2012-01-01").getTime();		
		Date date = new Date(System.currentTimeMillis()-oneYear);
		log.info(DateService.DateToString(date));
		int v= (int) ComputeService.cptValue(date, 2, 1000);			
		assertEquals(500,v);
	}
	
	@Test
	public void testTestCptValueNegtive() {
		long oneYear =DateService.stringToDate("2013-01-01").getTime()-DateService.stringToDate("2012-01-01").getTime();		
		Date date = new Date(System.currentTimeMillis()-2*oneYear);
		log.info(DateService.DateToString(date));
		int v= (int) ComputeService.cptValue(date, 1, 1000);			
		assertEquals(500,v);
	}

	@Test
	public void testTestCptShowValue() {
		long oneYear =DateService.stringToDate("2013-01-01").getTime()-DateService.stringToDate("2012-01-01").getTime();		
		Date date = new Date(System.currentTimeMillis()-oneYear);
		log.info(DateService.DateToString(date));
		int v= (int) ComputeService.cptShowValue(date, 2, 1000);			
		assertEquals(500,v);
	}

	@Test
	public void testTestCptValueAsList() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestCptShowValueAsList() {
		fail("Not yet implemented");
	}

}
