package cn.tinder.fuego.webservice.struts.bo.excelimport;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


public class ImportAssetsExcelFileTest {
	private static final Log log = LogFactory.getLog(ImportAssetsExcelFileTest.class);
	ImportAssetsExcelFile importFile;
	@Test
	public void test(){
		Long startTime = System.currentTimeMillis();
		
		log.info("StartTime is:"+startTime);	
		importFile.load(new File("C:\\test.xls"));
		Long endTime = System.currentTimeMillis();
		log.info("EndTime is:"+endTime);
		log.info("Runtime:" +(endTime-startTime) +"ms");
		
	}
}
