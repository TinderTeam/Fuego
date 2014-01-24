package test.cn.tinder.fuego.webservice.struts.action.purchaseplan.purchaseplanaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.ActionTest;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

public class PurchasePlanActionBasicTest extends ActionTest{
	
	ApplicationContext testctx = new ClassPathXmlApplicationContext(
			"/test/resources/cn/tinder/fuego/webservice/struts/action/purchaseTestBeans.xml");
	
	public PurchasePlanActionBasicTest(String testName) {
		super(testName, "/PurchasePlan.do");		
	}

	
	@Test
	/**
	 * @author Bowen Nan 
	 * 测试分支#1：User is null
	 */
    public void testUserIsNull(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, null);   
        actionPerform();      
        verifyForward(PageNameConst.LOGIN_PAGE);
    }

	

	/**
	 * @author Bowen Nan 
	 * 测试分支#2：Form is null
	 */
	@Test
    public void testFormIsNull(){
		
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,testctx.getBean("EMPTY_USER"));
       	PurchasePlanForm form=null;       	
       	setActionForm(form);
       	actionPerform();      
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	/**
	 * @author Bowen Nan 
	 * 测试分支#3：Para is null
	 */
	@Test
    public void testParaIsNull(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,testctx.getBean("GAS_USER"));
       	setActionForm((PurchasePlanForm)testctx.getBean("EMPTY_FORM"));
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	/**
	 * @author Nan Bowen
	 * 测试参数为空
	 */
	@Test
    public void testParaIsEmpty(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,testctx.getBean("GAS_USER"));       	
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"");
    	setActionForm((PurchasePlanForm)testctx.getBean("EMPTY_FORM"));
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	/**
	 * @author Nan Bowen
	 * 测试参数未定义
	 */
	@Test
    public void testParaIsUnDefine(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, testctx.getBean("GAS_USER"));   
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"UnDefine");
    	setActionForm((PurchasePlanForm)testctx.getBean("EMPTY_FORM"));
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	

}

