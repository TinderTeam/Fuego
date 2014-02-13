package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import org.apache.struts.action.ActionForm;
import org.junit.Test;

import cn.tinder.fuego.stub.strust.bo.base.SystemUserBoStub;
import cn.tinder.fuego.stub.strust.bo.purchase.PurchasePlanSessionBoStub;
import cn.tinder.fuego.stub.strust.form.PurchasePlanCreateFormStub;
import cn.tinder.fuego.stub.strust.form.PurchasePlanFormStub;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;
import cn.tinder.fuego.webservice.struts.form.purchase.PurchasePlanForm;
import test.util.ActionTest;


public class PurchasePlanCreateActionTest extends ActionTest{

	public PurchasePlanCreateActionTest(String testName) {
		super(testName, "/PurchasePlanCreate.do");		
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
       	setActionForm(PurchasePlanCreateFormStub.getNullFrom());
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	/**
	 * @author Nan Bowen
	 * 测试参数为空
	 */
	@Test
    public void testParaIsEmpty(){
		
		//更改其中form来源
		ActionForm form = PurchasePlanCreateFormStub.getNullFrom();
		//		
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getGasstationSystemUser());       	
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"");
       	setActionForm(form);
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);       
    }
	
	/**
	 * @author Nan Bowen
	 * 测试参数未定义
	 */
	@Test
    public void testParaIsUnDefine(){
		//更改其中form来源
		ActionForm form = PurchasePlanCreateFormStub.getNullFrom();
		//	
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getGasstationSystemUser());   
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"UnDefine");
       	setActionForm(form);
       	actionPerform();        
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	
	/**
	 * @author Nan Bowen
	 * 测试参数ADD_NEW_PARA_NAME
	 * 
	 */
	@Test
    public void testParaSubmit1(){
		//更改其中form来源
		ActionForm form = PurchasePlanCreateFormStub.getFullFrom();
		PurchasePlanSessionBo purchasePlanSessionBo = PurchasePlanSessionBoStub.getFullBo();
		//	
		
		request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getGasstationSystemUser());  
		request.getSession().setAttribute(RspBoNameConst.PURCHASE_PLAN_DATA,  purchasePlanSessionBo);  
		
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,ParameterConst.ADD_NEW_PARA_NAME);
       	
    	setActionForm(form);
       	actionPerform();        
        verifyForward(PageNameConst.PURCHASE_PLAN_CREATE_ACTION);
        /**
       	 * request 交互内容断言
       	 */
        assertNotNull( request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA)); 
        assertEquals(
        		PurchasePlanSessionBo.class,
        		request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA).getClass()
        		);
	
	}
}
