package cn.tinder.fuego.webservice.struts.action.purchaseplan;

import org.apache.struts.action.ActionForm;
import org.junit.Test;

import test.util.ActionTest;
import cn.tinder.fuego.stub.strust.bo.base.SystemUserBoStub;
import cn.tinder.fuego.stub.strust.form.PurchasePlanFormStub;
import cn.tinder.fuego.webservice.struts.bo.purchaseplan.PurchasePlanSessionBo;
import cn.tinder.fuego.webservice.struts.constant.PageNameConst;
import cn.tinder.fuego.webservice.struts.constant.ParameterConst;
import cn.tinder.fuego.webservice.struts.constant.RspBoNameConst;


/**
 * 
* @ClassName: PurchasePlanActionTest 
* @Description: 测试内容
* 1.用户空
* 2.Form 空
* 3.Para = null
* 4.Para = ""
* 5.submit1
* 6.submit2
* @author Nan Bowen
* @date 2014-2-12 上午12:44:12 
*
 */
public class PurchasePlanActionTest extends ActionTest{

	public PurchasePlanActionTest(String testName) {
		super(testName, "/PurchasePlan.do");		
	}

	
	@Test
	/**
	 * @author Bowen Nan 
	 * Action测试分支：User is null
	 */
    public void testUserIsNull(){
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, null);   
        actionPerform();      
        verifyForward(PageNameConst.LOGIN_PAGE);
    }

	

	/**
	 * @author Bowen Nan 
	 * Action测试分支：Form is null
	 */
	@Test
    public void testFormIsNull(){
		
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getGasstationSystemUser());           	
       	setActionForm(null);
       	actionPerform();      
        verifyForward(PageNameConst.SYSTEM_ERROR_PAGE);
    }
	
	/**
	 * @author Bowen Nan 
	 * Action测试分支：Para is null
	 */
	@Test
    public void testParaIsNull(){
		//更改其中form来源
		ActionForm form = PurchasePlanFormStub.getFullFrom();
		//
		
		
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER,SystemUserBoStub.getGasstationSystemUser());
       	setActionForm(form);
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
		ActionForm form = PurchasePlanFormStub.getFullFrom();
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
		ActionForm form = PurchasePlanFormStub.getFullFrom();
		//	
       	request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getGasstationSystemUser());   
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,"UnDefine");
       	setActionForm(form);
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
		//更改其中form来源
		ActionForm form = PurchasePlanFormStub.getFullFrom();
		//	
		
		request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getGasstationSystemUser());  
		
       	addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,ParameterConst.SUBMIT_1);
    	setActionForm(form);
       	actionPerform();        
        verifyForward(PageNameConst.PURCHASE_REF_PLAN_CREATE_ACTION);
        /**
       	 * request 交互内容断言
       	 */
        assertNotNull( request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA)); 
        assertEquals(
        		PurchasePlanSessionBo.class,
        		request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA).getClass()
        		);
	
	}
	
	/**
	 * @author Nan Bowen
	 * 测试参数Submit1
	 * 
	 */
	@Test
    public void testParaSubmit2(){
		//更改其中form来源
		ActionForm form = PurchasePlanFormStub.getFullFrom();
		//
		
		request.getSession().setAttribute(RspBoNameConst.SYSTEM_USER, SystemUserBoStub.getGasstationSystemUser());  
		addRequestParameter(ParameterConst.SUBMIT_PARA_NAME,ParameterConst.SUBMIT_2);
		setActionForm(form);
       	actionPerform();        
       	/**
       	 * 跳转目标断言
       	 */
        verifyForward(PageNameConst.PURCHASE_PLAN_CREATE_ACTION);
       	/**
       	 * request 交互内容断言
       	 */
        //非空
        assertNotNull( request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA)); 
        //类型正确
        assertEquals(
        		PurchasePlanSessionBo.class,
        		request.getSession().getAttribute(RspBoNameConst.PURCHASE_PLAN_DATA).getClass()
        		);
	}

}

