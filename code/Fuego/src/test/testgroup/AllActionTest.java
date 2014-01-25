package test.testgroup;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.cn.tinder.fuego.webservice.struts.action.purchaseplan.purchaseplanaction.PurchasePlanActionTest;

@RunWith(Suite.class)  
@SuiteClasses({PurchasePlanActionTest.class}) 
public class AllActionTest {

}
