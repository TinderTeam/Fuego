package test.cn.tinder.fuego.webservice.struts.action.purchaseplan;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import servletunit.struts.MockStrutsTestCase;

import cn.tinder.fuego.domain.po.SystemUser;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;

public class PurchasePlanActionTest extends MockStrutsTestCase{
	
	protected void setUp() throws Exception {     
		  super.setUp();  
		  setContextDirectory(new File("WebRoot"));  
		  this.setConfigFile("/WEB-INF/struts-config.xml");  
		 }  
    public void tearDown() throws Exception{
        super.tearDown();
    }
    public PurchasePlanActionTest(String testName){
        super(testName);
    }
    
    
	@Test
    public void testSuccessfulLogin(){
        setRequestPathInfo("/PurchasePlan.do");
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, null);   
        actionPerform();
        verifyForward(PageNameConst.LOGIN_PAGE);
    }

}
