package test.util;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;

public class ActionTest  extends MockStrutsTestCase{
	String requestPathInfo;
	protected void setUp() throws Exception {     
		  super.setUp();  
		  setContextDirectory(new File("WebRoot"));  
		  this.setConfigFile("/WEB-INF/struts-config.xml");  
		  setRequestPathInfo(requestPathInfo);
		 }  
	
	public void tearDown() throws Exception{
	      super.tearDown();
	  }
	  public ActionTest(String testName,String requestPath){
	      super(testName);
	      requestPathInfo=requestPath;
	  }
}
