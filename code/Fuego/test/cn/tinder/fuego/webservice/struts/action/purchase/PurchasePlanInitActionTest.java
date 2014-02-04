package cn.tinder.fuego.webservice.struts.action.purchase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.ActionTest;
import cn.tinder.fuego.stub.strust.bo.base.SystemUserBoStub;
import cn.tinder.fuego.stub.strust.form.PurchasePlanFormStub;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;

public class PurchasePlanInitActionTest extends ActionTest{
	
	
	public PurchasePlanInitActionTest(String testName) {
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
		
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getSuperDeptSystemUser());
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
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getSuperDeptSystemUser());
       	setActionForm(PurchasePlanFormStub.getFullFrom());
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	/**
	 * @author Nan Bowen
	 * 测试参数为空
	 */
	@Test
    public void testParaIsEmpty(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getSuperDeptSystemUser());       	
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"");
    	setActionForm(PurchasePlanFormStub.getFullFrom());
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	/**
	 * @author Nan Bowen
	 * 测试参数未定义
	 */
	@Test
    public void testParaIsUnDefine(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getSuperDeptSystemUser());   
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"UnDefine");
    	setActionForm(PurchasePlanFormStub.getFullFrom());
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	/**
	 * @author Nan Bowen
	 * 测试参数Submit1
	 * 
	 */
	@Test
    public void testParaSubmit1(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getSuperDeptSystemUser());   
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,ParameterConst.SUBMIT_1);
    	setActionForm(PurchasePlanFormStub.getFullFrom());
       	actionPerform();        
        verifyForward(PageNameConst.PURCHASE_REF_PLAN_CREATE_ACTION);
    }
	
	/**
	 * @author Nan Bowen
	 * 测试参数Submit1
	 * 
	 */
	@Test
    public void testParaSubmit2(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getSuperDeptSystemUser());   
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,ParameterConst.SUBMIT_2);
    	setActionForm(PurchasePlanFormStub.getFullFrom());
       	actionPerform();        
        verifyForward(PageNameConst.PURCHASE_PLAN_CREATE_ACTION);
    }

}

